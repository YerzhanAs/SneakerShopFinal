package kz.sneaker.shop.sneakershopfinal.domian.repositories;

import kz.sneaker.shop.sneakershopfinal.domian.entities.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

}
