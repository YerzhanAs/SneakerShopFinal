package kz.sneaker.shop.sneakershopfinal.domian.repositories;

import kz.sneaker.shop.sneakershopfinal.domian.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
