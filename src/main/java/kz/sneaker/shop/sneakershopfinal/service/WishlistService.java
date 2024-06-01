package kz.sneaker.shop.sneakershopfinal.service;

import java.util.Set;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Wishlist;


public interface WishlistService {

  Wishlist addToWishlist(Long sneakerId);

  Set<Wishlist> myWishlists();
}
