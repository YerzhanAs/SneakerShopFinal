package kz.sneaker.shop.sneakershopfinal.service.impl;

import java.util.List;
import java.util.Optional;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Category;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Sneaker;
import kz.sneaker.shop.sneakershopfinal.domian.repositories.CategoryRepository;
import kz.sneaker.shop.sneakershopfinal.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Override
  public Category saveCategory(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  public Category updateCategory(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  public void deleteCategory(Long id) {
    categoryRepository.deleteById(id);
  }

  @Override
  public Category getCategoryById(Long id) {
    Optional<Category> category = categoryRepository.findById(id);
    return category.orElse(null);
  }

  @Override
  public List<Category> getAllCategory() {
    return categoryRepository.findAll();
  }
}
