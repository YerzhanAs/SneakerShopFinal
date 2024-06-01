package kz.sneaker.shop.sneakershopfinal.service.impl;

import java.time.LocalDateTime;
import java.util.Set;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Sneaker;
import kz.sneaker.shop.sneakershopfinal.domian.entities.User;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Wishlist;
import kz.sneaker.shop.sneakershopfinal.domian.repositories.SneakerRepository;
import kz.sneaker.shop.sneakershopfinal.domian.repositories.WishlistRepository;
import kz.sneaker.shop.sneakershopfinal.service.UserService;
import kz.sneaker.shop.sneakershopfinal.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WishlistServiceImpl implements WishlistService {

  private final WishlistRepository wishlistRepository;

  private final UserService userService;

  private final SneakerRepository sneakerRepository;

  @Override
  @Transactional
  public Wishlist addToWishlist(Long sneakerId) {
    User user = userService.getCurrentUser();
    Sneaker sneaker = sneakerRepository.findById(sneakerId)
        .orElseThrow(() -> new RuntimeException("Sneaker not found"));

    Wishlist wishlist = new Wishlist();
    wishlist.setUser(user);
    wishlist.setSneaker(sneaker);
    wishlist.setAddedAt(LocalDateTime.now());

    return wishlistRepository.saveAndFlush(wishlist);
  }

  public Set<Wishlist> myWishlists() {
    return wishlistRepository.findAllByUser(userService.getCurrentUser());
  }
}
