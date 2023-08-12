package com.exercise.dative.domain.usecase;

import com.exercise.dative.domain.entity.Product;
import com.exercise.dative.domain.mapper.ProductMapper;
import com.exercise.dative.infrastructure.db.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ListProductsUseCase {

  private final ProductRepository repository;

  public ListProductsUseCase(ProductRepository repository) {
    this.repository = repository;
  }

  public List<Product> execute() {
    return repository.findAll()
        .stream()
        .map(ProductMapper::toProduct)
        .collect(Collectors.toList());
  }
}
