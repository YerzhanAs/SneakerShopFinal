package kz.sneaker.shop.sneakershopfinal.service;

import java.util.List;
import kz.sneaker.shop.sneakershopfinal.domian.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends UserDetailsService {

  Boolean signUp(String email, String password, String repeatPassword, String fullName);
  Boolean updatePassword(String oldPassword, String newPassword, String repeatNewPassword);

  User getCurrentUser();

  User saveUser(User user);

  void updateUser(User updatedUser);

  void deleteUser(Long id);

  User getUserById(Long id);


  List<User> searchUsers(String email, String fullName);


  void updateUserAvatar(MultipartFile file) throws Exception;

}
