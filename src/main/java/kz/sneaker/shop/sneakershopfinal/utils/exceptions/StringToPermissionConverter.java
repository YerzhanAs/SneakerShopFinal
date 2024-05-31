package kz.sneaker.shop.sneakershopfinal.utils.exceptions;

import kz.sneaker.shop.sneakershopfinal.domian.entities.Permission;
import kz.sneaker.shop.sneakershopfinal.domian.repositories.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StringToPermissionConverter implements Converter<String, Permission> {


  private final PermissionRepository permissionRepository;

  @Override
  public Permission convert(String source) {
    return permissionRepository.findById(Long.parseLong(source)).orElse(null);
  }
}

