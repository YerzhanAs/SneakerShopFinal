package kz.sneaker.shop.sneakershopfinal.service;

import java.util.List;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Review;

public interface ReviewService {

  Review addReview(Long sneakerId, String comment, Integer rating);

  List<Review> getReviewsBySneakerId(Long sneakerId);

}
