package kz.sneaker.shop.sneakershopfinal.controller;

import java.util.Arrays;
import java.util.List;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Permission;
import kz.sneaker.shop.sneakershopfinal.domian.entities.User;
import kz.sneaker.shop.sneakershopfinal.service.PermissionService;
import kz.sneaker.shop.sneakershopfinal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  private final PermissionService permissionService;

  @GetMapping("/profile")
  @PreAuthorize("isAuthenticated()")
  public String profile(Model model){
    User user = userService.getCurrentUser();
    model.addAttribute("person", user);
    return "profile";
  }

  @GetMapping("/change-password")
  @PreAuthorize("isAuthenticated()")
  public String changePasswordPage(Model model){
    return "change-password";
  }

  @PostMapping("/save-password")
  @PreAuthorize("isAuthenticated()")
  public String savePassword(@RequestParam(name = "user_old_password") String oldPassword,
      @RequestParam(name = "user_new_password") String newPassword,
      @RequestParam(name = "user_repeat_new_password") String repeatNewPassword){

    Boolean result = userService.updatePassword(oldPassword, newPassword, repeatNewPassword);

    if(result != null){
      if(result){
        return "redirect:/change-password?success";
      }

      return "redirect:/change-password?newPasswordError";
    }

    return "redirect:/change-password?oldPasswordError";
  }

  @GetMapping("/admin/all")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String allUsers(Model model,
      @RequestParam(required = false) String email,
      @RequestParam(required = false) String fullName) {
    List<User> users = userService.searchUsers(email, fullName);
    model.addAttribute("users", users);
    model.addAttribute("permissions", permissionService.findAll());
    return "admin-user";
  }

  @GetMapping("/admin/user/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String userDetails(@PathVariable Long id, Model model) {
    User user = userService.getUserById(id);
    model.addAttribute("permissions", permissionService.findAll());
    model.addAttribute("user", user);
    return "user-details";
  }

  @PostMapping("/admin/add-user")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String addUser(@RequestParam String email, @RequestParam String fullName, @RequestParam String[] roles, RedirectAttributes redirectAttributes) {
    User newUser = new User();
    newUser.setEmail(email);
    newUser.setFullName(fullName);
    List<Permission> roleObjects = Arrays.stream(roles)
        .map(roleId -> permissionService.findById(Long.parseLong(roleId)))
        .toList();
    newUser.setRoles(roleObjects);
    userService.saveUser(newUser);
    redirectAttributes.addFlashAttribute("message", "User added successfully!");
    return "redirect:/admin/all";
  }

  @PostMapping("/admin/update-user")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String updateUser(@ModelAttribute User user, BindingResult result, RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      return "redirect:/admin/user/" + user.getId()+"?error";
    }
    userService.updateUser(user); // Assuming this method handles saving the updated user
    redirectAttributes.addFlashAttribute("message", "User updated successfully!");
    return "redirect:/admin/user/" + user.getId();
  }

  @PostMapping("/admin/delete-user/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    try {
      userService.deleteUser(id);
      redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully!");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage", "Error deleting user.");
    }
    return "redirect:/admin/all";
  }


  @PostMapping(value="/uploadavatar")
  @PreAuthorize("isAuthenticated()")
  public String uploadAvatar(@RequestParam(name="user_ava") MultipartFile file, RedirectAttributes redirectAttributes) {
    if (!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/png")) {
      redirectAttributes.addFlashAttribute("error", "Invalid file type.");
      return "redirect:/";
    }

    try {
      userService.updateUserAvatar(file);
      redirectAttributes.addFlashAttribute("success", "Avatar updated successfully!");
      return "redirect:/profile?success";
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("error", "Failed to upload avatar.");
      return "redirect:/profile?error";
    }
  }

}
