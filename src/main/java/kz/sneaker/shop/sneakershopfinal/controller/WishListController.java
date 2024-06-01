package kz.sneaker.shop.sneakershopfinal.controller;

import java.util.Set;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Wishlist;
import kz.sneaker.shop.sneakershopfinal.service.OrderService;
import kz.sneaker.shop.sneakershopfinal.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishListController {

  private final WishlistService wishlistService;
  private final OrderService orderService;


  @PostMapping("/add")
  public String addSneakerToWishlist(@RequestParam("sneakerId") Long sneakerId, Model model) {
    wishlistService.addToWishlist(sneakerId);
    return "redirect:/sneakers/search";
  }

  @GetMapping("/list")
  public String myWishlists(Model model) {
    Set<Wishlist> wishlists = wishlistService.myWishlists();
    model.addAttribute("wishlists", wishlists);
    return "wishlist";
  }


  @PostMapping("/purchase/{sneakerId}")
  public String purchaseSneakerFromWishlist(@PathVariable Long sneakerId, RedirectAttributes redirectAttributes) {
    try {
      orderService.purchaseSneaker(sneakerId);
      redirectAttributes.addFlashAttribute("successMessage", "Purchase successful!");
      return "redirect:/wishlist/list";
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage", "Purchase failed: " + e.getMessage());
      return "redirect:/wishlist/list";
    }
  }
}
