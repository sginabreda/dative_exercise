package com.exercise.dative.domain.mapper;

import com.exercise.dative.domain.entity.Product;
import com.exercise.dative.infrastructure.db.model.ProductModel;

public class ProductMapper {

  public static Product toProduct(ProductModel productModel) {
    return new Product(
        productModel.getId(),
        productModel.getName(),
        productModel.getBrand(),
        productModel.getExpirationDate(),
        productModel.getPrice()
    );
  }
}
