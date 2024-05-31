package kz.sneaker.shop.sneakershopfinal.configuration;

import kz.sneaker.shop.sneakershopfinal.domian.repositories.UserRepository;
import kz.sneaker.shop.sneakershopfinal.service.UserService;
import kz.sneaker.shop.sneakershopfinal.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {


  private final PasswordEncoder passwordEncoder;

  private final UserService userService;



  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder);

    http.exceptionHandling(
        exception -> exception.accessDeniedPage("/forbidden")
    );

    http.authorizeRequests(
        authorize -> authorize
            .requestMatchers("/sign-in", "/entering", "/sign-up", "/registration").anonymous()
            .requestMatchers("/sign-out", "/change-password", "/save-password").authenticated()
            .requestMatchers("/profile").authenticated()
            .requestMatchers("/css/**", "/js/**").permitAll()
            .requestMatchers("/users").hasAuthority("ROLE_ADMIN")
            .anyRequest().permitAll()
    ).formLogin(
        login -> login
            .loginProcessingUrl("/entering")
            .defaultSuccessUrl("/profile")
            .loginPage("/sign-in")
            .failureUrl("/sign-in?error")
            .usernameParameter("user_email")
            .passwordParameter("user_password")
    ).logout(
        logout -> logout
            .logoutSuccessUrl("/sign-in?logout")
            .logoutUrl("/logout")
            .permitAll()
    ).csrf(AbstractHttpConfigurer::disable);

    return http.build();
  }
}
