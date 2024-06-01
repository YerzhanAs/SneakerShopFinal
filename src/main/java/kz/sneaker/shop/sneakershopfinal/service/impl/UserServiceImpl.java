package kz.sneaker.shop.sneakershopfinal.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Order;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Permission;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Sneaker;
import kz.sneaker.shop.sneakershopfinal.domian.entities.User;
import kz.sneaker.shop.sneakershopfinal.domian.repositories.PermissionRepository;
import kz.sneaker.shop.sneakershopfinal.domian.repositories.SneakerRepository;
import kz.sneaker.shop.sneakershopfinal.domian.repositories.UserRepository;
import kz.sneaker.shop.sneakershopfinal.domian.repositories.WishlistRepository;
import kz.sneaker.shop.sneakershopfinal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PermissionRepository permissionRepository;
  private final PasswordEncoder passwordEncoder;
  private Path rootLocation;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username);
    if (user != null) {
      return user;
    }
    throw new UsernameNotFoundException("User Not Found");
  }

  @Override
  public User getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return userRepository.findByEmail(authentication.getName());
  }

  @Override
  @Transactional
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  @Transactional
  public void updateUser(User updatedUser) {
    Optional<User> existingUserOptional = userRepository.findById(updatedUser.getId());
    if (existingUserOptional.isPresent()) {
      User existingUser = existingUserOptional.get();

      existingUser.setFullName(updatedUser.getFullName());
      existingUser.setEmail(updatedUser.getEmail());
      existingUser.setBalance(updatedUser.getBalance());

      if (updatedUser.getRoles() != null) {
        existingUser.getRoles().clear();
        existingUser.getRoles().addAll(updatedUser.getRoles());
      }

      if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
        existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
      }

      userRepository.save(existingUser);
    } else {
      throw new IllegalArgumentException("User with ID " + updatedUser.getId() + " not found.");
    }
  }

  @Override
  @Transactional
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  @Override
  public User getUserById(Long id) {
    return userRepository.findById(id).orElse(null);
  }


  @Override
  @Transactional
  public Boolean signUp(String email, String password, String repeatPassword, String fullName) {
    User existingUser = userRepository.findByEmail(email);
    if (existingUser == null) {
      if (password.equals(repeatPassword)) {
        List<Permission> permissions = new ArrayList<>();
        Permission simplePermission = permissionRepository.findByPermission("ROLE_USER");
        permissions.add(simplePermission);

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setFullName(fullName);
        newUser.setRoles(permissions);

        userRepository.save(newUser);
        return true;
      }
      return false;
    }
    return null;
  }

  @Override
  @Transactional
  public Boolean updatePassword(String oldPassword, String newPassword, String repeatNewPassword) {
    User currentUser = getCurrentUser();
    if (currentUser != null) {
      if (passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
        if (newPassword.equals(repeatNewPassword)) {
          currentUser.setPassword(passwordEncoder.encode(newPassword));
          userRepository.save(currentUser);
          return true;
        }
        return false;
      }
      return false;
    }
    return false;
  }

  @Override
  public List<User> searchUsers(String email, String fullName) {
    if ((email != null && !email.isEmpty()) || (fullName != null && !fullName.isEmpty())) {
      return userRepository.findByEmailAndFullNameIgnoreCase(email, fullName);
    } else {
      return userRepository.findAll();
    }
  }

  @Override
  @Transactional
  public void updateUserAvatar(MultipartFile file) throws Exception {
    User currentUser = getCurrentUser();
    String picName = DigestUtils.sha1Hex("avatar_" + currentUser.getId() + "_!Picture") + ".jpg";

    Path path = this.rootLocation.resolve(picName);
    byte[] bytes = file.getBytes();
    Files.write(path, bytes);

    currentUser.setUserImage(picName);
    userRepository.save(currentUser);
  }
}
