package kz.sneaker.shop.sneakershopfinal.controller;

import java.util.List;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Sneaker;
import kz.sneaker.shop.sneakershopfinal.service.CategoryService;
import kz.sneaker.shop.sneakershopfinal.service.SneakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sneakers")
@RequiredArgsConstructor
public class SneakerController {

  private final SneakerService sneakerService;

  private final CategoryService categoryService;

  @GetMapping("/all")
  public String showSneakers(Model model) {
    model.addAttribute("sneakers", sneakerService.getAllSneakers());
    model.addAttribute("categories", categoryService.getAllCategory());
    return "sneakers";
  }

  @GetMapping("/search-by-category")
  public String searchSneakersByCategory(@RequestParam(value = "category", required = false) String category, Model model) {
    List<Sneaker> sneakers;
    if (category == null || category.isEmpty()) {
      sneakers = sneakerService.getAllSneakers();
    } else {
      sneakers = sneakerService.findSneakersByCategory(category);
    }
    model.addAttribute("sneakers", sneakers);
    model.addAttribute("categories", categoryService.getAllCategory());
    return "sneakers";
  }

  @GetMapping("/search")
  public String searchSneakers(@RequestParam(required = false, name = "model") String models,
      @RequestParam(required = false) String category, Model model) {
    List<Sneaker> sneakers = sneakerService.searchSneakers(models, category);
    model.addAttribute("sneakers", sneakers);
    model.addAttribute("categories", categoryService.getAllCategory());
    return "sneakers";
  }

}
