package kz.sneaker.shop.sneakershopfinal.service.impl;

import java.time.LocalDateTime;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Order;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Sneaker;
import kz.sneaker.shop.sneakershopfinal.domian.entities.User;
import kz.sneaker.shop.sneakershopfinal.domian.repositories.OrderRepository;
import kz.sneaker.shop.sneakershopfinal.domian.repositories.SneakerRepository;
import kz.sneaker.shop.sneakershopfinal.domian.repositories.WishlistRepository;
import kz.sneaker.shop.sneakershopfinal.service.OrderService;
import kz.sneaker.shop.sneakershopfinal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

  private final UserService userService;
  private final SneakerRepository sneakerRepository;
  private final WishlistRepository wishlistRepository;
  private final OrderRepository orderRepository;

  @Transactional
  public void purchaseSneaker(Long sneakerId) {
    User user =  userService.getCurrentUser();
    Sneaker sneaker = sneakerRepository.findById(sneakerId).orElseThrow(() -> new RuntimeException("Sneaker not found"));
    if (user.getBalance() >= sneaker.getPrice()) {
      user.setBalance(user.getBalance() - sneaker.getPrice());
      wishlistRepository.deleteByUserAndSneaker(user, sneaker);
      Order newOrder = new Order();
      newOrder.setUser(user);
      newOrder.setSneaker(sneaker);
      newOrder.setPrice(sneaker.getPrice());
      newOrder.setOrderDate(LocalDateTime.now());
      orderRepository.save(newOrder);
    } else {
      throw new RuntimeException("Insufficient funds");
    }
  }

}
