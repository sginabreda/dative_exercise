package com.exercise.dative.domain.usecase;

import com.exercise.dative.domain.entity.Product;
import com.exercise.dative.domain.exception.ProductNotFoundException;
import com.exercise.dative.domain.mapper.ProductMapper;
import com.exercise.dative.infrastructure.db.ProductRepository;
import com.exercise.dative.infrastructure.db.model.ProductModel;

import java.util.Optional;

public class GetProductUseCase {

  private final ProductRepository repository;

  public GetProductUseCase(ProductRepository repository) {
    this.repository = repository;
  }

  public Product execute(Long id) {
    Optional<ProductModel> productModel = repository.findById(id);
    if (productModel.isEmpty()) {
      throw new ProductNotFoundException();
    }

    return ProductMapper.toProduct(productModel.get());
  }
}
