package kz.sneaker.shop.sneakershopfinal.domian.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "wishlist")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wishlist extends BaseEntity {

  @ManyToOne()
  @JoinColumn(name = "user_id")
  @Cascade(CascadeType.ALL)
  private User user;

  @ManyToOne()
  @JoinColumn(name = "sneaker_id")
  @Cascade(CascadeType.ALL)
  private Sneaker sneaker;

  @Column(nullable = false)
  private LocalDateTime addedAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Wishlist wishlist = (Wishlist) o;
    return Objects.equals(user, wishlist.user) && Objects.equals(sneaker,
        wishlist.sneaker);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, sneaker);
  }
}
