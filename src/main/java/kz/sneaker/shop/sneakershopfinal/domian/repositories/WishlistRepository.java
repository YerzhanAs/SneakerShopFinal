package kz.sneaker.shop.sneakershopfinal.domian.repositories;

import java.util.Set;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Sneaker;
import kz.sneaker.shop.sneakershopfinal.domian.entities.User;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

  Set<Wishlist> findAllByUser(User user);

  void deleteByUserAndSneaker(User user, Sneaker sneaker);

}
