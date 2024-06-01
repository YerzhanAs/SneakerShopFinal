package kz.sneaker.shop.sneakershopfinal.domian.repositories;

import java.util.List;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

  List<Review> findAllById(Long sneakerId);
}
