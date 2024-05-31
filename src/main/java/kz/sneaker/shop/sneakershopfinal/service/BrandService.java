package kz.sneaker.shop.sneakershopfinal.service;

import java.util.List;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Brand;

public interface BrandService {
  Brand saveBrand(Brand brand);
  Brand updateBrand(Brand brand);
  void deleteBrand(Long id);
  Brand getBrandById(Long id);
  List<Brand> getAllBrand();
}
