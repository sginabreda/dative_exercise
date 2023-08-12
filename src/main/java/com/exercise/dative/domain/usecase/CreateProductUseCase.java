package com.exercise.dative.domain.usecase;

import com.exercise.dative.domain.entity.Product;
import com.exercise.dative.domain.mapper.ProductMapper;
import com.exercise.dative.infrastructure.db.ProductRepository;
import com.exercise.dative.infrastructure.db.mapper.ProductModelMapper;
import com.exercise.dative.infrastructure.db.model.ProductModel;

import org.springframework.transaction.annotation.Transactional;

public class CreateProductUseCase {

  private final ProductRepository repository;

  public CreateProductUseCase(ProductRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public Product execute(Product product) {
    ProductModel storedModel = repository.save(ProductModelMapper.toModel(product));
    return ProductMapper.toProduct(storedModel);
  }
}
