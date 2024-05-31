package kz.sneaker.shop.sneakershopfinal.service;

import java.util.List;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Permission;

public interface PermissionService {

  Permission findById(Long id);

  List<Permission> findAll();

}
