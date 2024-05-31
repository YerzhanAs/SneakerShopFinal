package kz.sneaker.shop.sneakershopfinal.controller;

import kz.sneaker.shop.sneakershopfinal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;

  @GetMapping("/sign-in")
  @PreAuthorize("isAnonymous()")
  public String loginPage(Model model){
    return "sign-in";
  }


  @GetMapping("/sign-up")
  @PreAuthorize("isAnonymous()")
  public String signUpPage(Model model){
    return "sign-up";
  }

  @PostMapping("/registration")
  @PreAuthorize("isAnonymous()")
  public String registration(@RequestParam(name = "user_email") String email,
      @RequestParam(name = "user_password") String password,
      @RequestParam(name = "user_repeat_password") String repeatPassword,
      @RequestParam(name = "user_full_name") String fullName){

    Boolean result = userService.signUp(email, password, repeatPassword, fullName);

    if(result != null){
      if(result){
        return "redirect:/sign-up?success";
      }

      return "redirect:/sign-up?passwordError";
    }

    return "redirect:/sign-up?emailError";
  }

}
