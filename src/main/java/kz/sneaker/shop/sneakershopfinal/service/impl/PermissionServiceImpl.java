package kz.sneaker.shop.sneakershopfinal.service.impl;

import java.util.List;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Permission;
import kz.sneaker.shop.sneakershopfinal.domian.repositories.PermissionRepository;
import kz.sneaker.shop.sneakershopfinal.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PermissionServiceImpl implements PermissionService {

  private final PermissionRepository permissionRepository;


  @Override
  public Permission findById(Long id) {
    return  permissionRepository.findById(id).orElse(null);
  }

  @Override
  public List<Permission> findAll() {
    return permissionRepository.findAll();
  }
}
