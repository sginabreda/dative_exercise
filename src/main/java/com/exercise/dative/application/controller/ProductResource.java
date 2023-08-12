package com.exercise.dative.application.controller;

import com.exercise.dative.application.dto.request.ProductCreationDto;
import com.exercise.dative.application.dto.response.ProductDto;
import com.exercise.dative.application.dto.mapper.ProductDtoMapper;
import com.exercise.dative.domain.entity.Product;
import com.exercise.dative.domain.usecase.CreateProductUseCase;
import com.exercise.dative.domain.usecase.DeleteProductUseCase;
import com.exercise.dative.domain.usecase.GetProductUseCase;
import com.exercise.dative.domain.usecase.ListProductsUseCase;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/products", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductResource {

  private final CreateProductUseCase create;
  private final GetProductUseCase get;
  private final ListProductsUseCase list;
  private final DeleteProductUseCase delete;

  public ProductResource(
      CreateProductUseCase create, GetProductUseCase get, ListProductsUseCase list,
      DeleteProductUseCase delete) {
    this.create = create;
    this.get = get;
    this.list = list;
    this.delete = delete;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProductDto create(@RequestBody @Valid ProductCreationDto product) {
    Product newProduct = ProductDtoMapper.toDomain(product);
    return ProductDtoMapper.toDto(this.create.execute(newProduct));
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ProductDto getProduct(@PathVariable Long id) {
    return ProductDtoMapper.toDto(this.get.execute(id));
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProductDto> listProduct() {
    return this.list.execute()
        .stream()
        .map(ProductDtoMapper::toDto)
        .collect(Collectors.toList());
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteProduct(@PathVariable Long id) {
    this.delete.execute(id);
  }
}
