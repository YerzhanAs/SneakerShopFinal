package kz.sneaker.shop.sneakershopfinal.configuration;

import kz.sneaker.shop.sneakershopfinal.domian.repositories.PermissionRepository;
import kz.sneaker.shop.sneakershopfinal.domian.repositories.UserRepository;
import kz.sneaker.shop.sneakershopfinal.service.UserService;
import kz.sneaker.shop.sneakershopfinal.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
public class AppConfig {

  private final UserRepository userRepository;

  private final PermissionRepository permissionRepository;

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserService userService(){
    return new UserServiceImpl(userRepository, permissionRepository, passwordEncoder());
  }
}
