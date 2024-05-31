package kz.sneaker.shop.sneakershopfinal.service.impl;

import java.util.List;
import java.util.Optional;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Sneaker;
import kz.sneaker.shop.sneakershopfinal.domian.repositories.SneakerRepository;
import kz.sneaker.shop.sneakershopfinal.service.SneakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SneakerServiceImpl implements SneakerService {

  private final SneakerRepository sneakerRepository;

  @Override
  public Sneaker saveSneaker(Sneaker sneaker) {
    return sneakerRepository.save(sneaker);
  }

  @Override
  public Sneaker updateSneaker(Sneaker sneaker) {
    return sneakerRepository.save(sneaker);
  }

  @Override
  public void deleteSneaker(Long id) {
    sneakerRepository.deleteById(id);
  }

  @Override
  public Sneaker getSneakerById(Long id) {
    Optional<Sneaker> sneaker = sneakerRepository.findById(id);
    return sneaker.orElse(null);
  }

  @Override
  public List<Sneaker> getAllSneakers() {
    return sneakerRepository.findAll();
  }

  @Override
  public List<Sneaker> findSneakersByCategory(String categoryName) {
    return sneakerRepository.findByCategoryName(categoryName);
  }

  @Override
  public List<Sneaker> searchSneakers(String model, String category) {
    if ((model == null || model.isEmpty()) && (category == null || category.isEmpty())) {
      return sneakerRepository.findAll(); // Returns all sneakers if both fields are empty or null
    } else {
      return sneakerRepository.findByCategoryIgnoreCaseNameOrModelIgnoreCase(category, model); // Search by category or model
    }
  }

}
