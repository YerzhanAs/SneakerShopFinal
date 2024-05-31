package kz.sneaker.shop.sneakershopfinal.domian.repositories;

import java.util.List;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Sneaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SneakerRepository extends JpaRepository<Sneaker, Long> {

  List<Sneaker> findByCategoryId(Long categoryId);

  List<Sneaker> findByCategoryName(String categoryName);

  List<Sneaker> findByModel(String model);

  List<Sneaker> findByCategoryIgnoreCaseNameOrModelIgnoreCase(String category, String model);
}
