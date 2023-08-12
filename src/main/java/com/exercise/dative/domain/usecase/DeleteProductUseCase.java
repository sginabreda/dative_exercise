package com.exercise.dative.domain.usecase;

import com.exercise.dative.domain.exception.ProductNotFoundException;
import com.exercise.dative.infrastructure.db.ProductRepository;
import com.exercise.dative.infrastructure.db.model.ProductModel;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class DeleteProductUseCase {

  private final ProductRepository repository;

  public DeleteProductUseCase(ProductRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public void execute(Long id) {
    Optional<ProductModel> productModel = repository.findById(id);
    if (productModel.isEmpty()) {
      throw new ProductNotFoundException();
    }

    repository.deleteById(productModel.get().getId());
  }
}
