package kz.sneaker.shop.sneakershopfinal.service;

import java.util.List;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Sneaker;

public interface SneakerService {
  Sneaker saveSneaker(Sneaker sneaker);
  Sneaker updateSneaker(Sneaker sneaker);
  void deleteSneaker(Long id);
  Sneaker getSneakerById(Long id);
  List<Sneaker> getAllSneakers();
  List<Sneaker> findSneakersByCategory(String categoryName);

  List<Sneaker> searchSneakers(String model, String category);

}
