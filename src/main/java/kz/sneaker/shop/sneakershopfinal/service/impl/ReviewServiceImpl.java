package kz.sneaker.shop.sneakershopfinal.service.impl;

import java.util.List;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Review;
import kz.sneaker.shop.sneakershopfinal.domian.repositories.ReviewRepository;
import kz.sneaker.shop.sneakershopfinal.service.ReviewService;
import kz.sneaker.shop.sneakershopfinal.service.SneakerService;
import kz.sneaker.shop.sneakershopfinal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;

  private final UserService userService;

  private final SneakerService sneakerService;

  @Transactional
  public Review addReview(Long sneakerId, String comment, Integer rating) {

    Review review = new Review();
    review.setSneaker(sneakerService.getSneakerById(sneakerId));
    review.setUser(userService.getCurrentUser());
    review.setComment(comment);
    review.setRating(rating);
    return reviewRepository.save(review);
  }

  public List<Review> getReviewsBySneakerId(Long sneakerId) {
    return reviewRepository.findAllById(sneakerId);
  }

}
