package com.exercise.dative.infrastructure.db.mapper;

import com.exercise.dative.domain.entity.Product;
import com.exercise.dative.infrastructure.db.model.ProductModel;

public class ProductModelMapper {

  public static ProductModel toModel(Product product) {
    return new ProductModel(
        product.id(),
        product.name(),
        product.brand(),
        product.expirationDate(),
        product.price()
    );
  }
}
