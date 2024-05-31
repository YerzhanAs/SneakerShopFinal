package kz.sneaker.shop.sneakershopfinal.domian.repositories;

import kz.sneaker.shop.sneakershopfinal.domian.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
