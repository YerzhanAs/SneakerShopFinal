package kz.sneaker.shop.sneakershopfinal.domian.repositories;

import kz.sneaker.shop.sneakershopfinal.domian.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
  Permission findByPermission(String permission);
}
