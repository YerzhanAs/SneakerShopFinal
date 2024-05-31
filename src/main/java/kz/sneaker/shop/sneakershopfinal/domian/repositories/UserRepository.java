package kz.sneaker.shop.sneakershopfinal.domian.repositories;

import java.util.List;
import kz.sneaker.shop.sneakershopfinal.domian.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);

  @Query("SELECT u FROM User u WHERE " +
      "(:email IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
      "(:fullName IS NULL OR LOWER(u.fullName) LIKE LOWER(CONCAT('%', :fullName, '%')))")
  List<User> findByEmailAndFullNameIgnoreCase(
      @Param("email") String email,
      @Param("fullName") String fullName);
}
