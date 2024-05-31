package kz.sneaker.shop.sneakershopfinal.utils.exceptions;

import java.nio.file.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AccessDeniedException.class)
  public String handleAccessDeniedException(){
    return "error/access-denied";
  }
}
