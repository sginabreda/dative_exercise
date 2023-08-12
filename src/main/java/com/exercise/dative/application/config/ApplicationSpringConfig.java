package com.exercise.dative.application.config;

import com.exercise.dative.domain.usecase.CreateProductUseCase;
import com.exercise.dative.domain.usecase.DeleteProductUseCase;
import com.exercise.dative.domain.usecase.GetProductUseCase;
import com.exercise.dative.domain.usecase.ListProductsUseCase;
import com.exercise.dative.infrastructure.db.ProductRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationSpringConfig {

  @Bean
  public CreateProductUseCase create(ProductRepository repository) {
    return new CreateProductUseCase(repository);
  }

  @Bean
  public GetProductUseCase get(ProductRepository repository) {
    return new GetProductUseCase(repository);
  }

  @Bean
  public ListProductsUseCase list(ProductRepository repository) {
    return new ListProductsUseCase(repository);
  }

  @Bean
  public DeleteProductUseCase delete(ProductRepository repository) {
    return new DeleteProductUseCase(repository);
  }
}
