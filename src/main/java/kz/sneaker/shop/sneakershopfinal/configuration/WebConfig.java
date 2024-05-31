package kz.sneaker.shop.sneakershopfinal.configuration;

import kz.sneaker.shop.sneakershopfinal.utils.exceptions.StringToPermissionConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  private final StringToPermissionConverter stringToPermissionConverter;

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(stringToPermissionConverter);
  }
}
