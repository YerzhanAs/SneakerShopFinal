package kz.sneaker.shop.sneakershopfinal.controller;

import kz.sneaker.shop.sneakershopfinal.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;

  @PostMapping("/reviews/add/{sneakerId}")
  public String addReview(@PathVariable Long sneakerId,
      @RequestParam String comment,
      @RequestParam Integer rating,
      Model model) {
    reviewService.addReview(sneakerId, comment, rating);
    return "redirect:/sneakers/details/" + sneakerId;
  }

}
