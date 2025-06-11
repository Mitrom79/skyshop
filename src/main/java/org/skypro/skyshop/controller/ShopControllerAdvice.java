package org.skypro.skyshop.controller;

import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.exception.ShopError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(new ShopError("400", "Продукт не найден"));
    }
}