package kz.sneaker.shop.sneakershopfinal.service;

import java.util.List;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Category;

public interface CategoryService {

  Category saveCategory(Category category);
  Category updateCategory(Category category);
  void deleteCategory(Long id);
  Category getCategoryById(Long id);
  List<Category> getAllCategory();
}
