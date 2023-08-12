package com.exercise.dative.application.dto.mapper;

import com.exercise.dative.application.dto.request.ProductCreationDto;
import com.exercise.dative.application.dto.response.ProductDto;
import com.exercise.dative.domain.entity.Product;

public class ProductDtoMapper {

  public static Product toDomain(ProductCreationDto product) {
    return new Product(
        null,
        product.name(),
        product.brand(),
        product.expirationDate(),
        product.price()
    );
  }

  public static ProductDto toDto(Product product) {
    return new ProductDto(
        product.id(),
        product.name(),
        product.brand(),
        product.expirationDate(),
        product.price()
    );
  }
}
