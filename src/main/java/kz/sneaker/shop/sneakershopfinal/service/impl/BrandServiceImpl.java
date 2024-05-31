package kz.sneaker.shop.sneakershopfinal.service.impl;

import java.util.List;
import kz.sneaker.shop.sneakershopfinal.domian.entities.Brand;
import kz.sneaker.shop.sneakershopfinal.service.BrandService;
import kz.sneaker.shop.sneakershopfinal.service.SneakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

  private BrandService brandService;

  @Override
  public Brand saveBrand(Brand brand) {
    return null;
  }

  @Override
  public Brand updateBrand(Brand brand) {
    return null;
  }

  @Override
  public void deleteBrand(Long id) {

  }

  @Override
  public Brand getBrandById(Long id) {
    return null;
  }

  @Override
  public List<Brand> getAllBrand() {
    return null;
  }
}
