package kz.sneaker.shop.sneakershopfinal.domian.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sneakers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sneaker extends BaseEntity {

  @Column(nullable = false, length = 50)
  private String model;

  @Column(nullable = false)
  private Double price;

  @Column(nullable = false)
  private Integer stock;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(length = 255)
  private String imageUrl;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Column()
  private LocalDateTime updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "brand_id")
  private Brand brand;


  @OneToMany(mappedBy = "sneaker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Review> reviews;

  @OneToMany(mappedBy = "sneaker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Wishlist> wishlists;

}
