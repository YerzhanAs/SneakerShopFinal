package kz.sneaker.shop.sneakershopfinal.domian.repositories;

import kz.sneaker.shop.sneakershopfinal.domian.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
