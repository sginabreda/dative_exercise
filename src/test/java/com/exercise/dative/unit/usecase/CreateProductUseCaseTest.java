package com.exercise.dative.unit.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.exercise.dative.domain.entity.Product;
import com.exercise.dative.domain.usecase.CreateProductUseCase;
import com.exercise.dative.infrastructure.db.model.ProductModel;
import com.exercise.dative.infrastructure.db.ProductRepository;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateProductUseCaseTest {

  private final ProductRepository repository = Mockito.mock();
  private final CreateProductUseCase useCase = new CreateProductUseCase(repository);

  @Test
  void testProductIsCreated() {
    // Given
    Product product = new Product(
        null, "product_name", "product_brand", LocalDateTime.now(), BigDecimal.valueOf(100.00));
    ProductModel storedProduct = new ProductModel(1L, "product_name", "product_brand", LocalDateTime.now(), BigDecimal.valueOf(100));

    // When
    Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(storedProduct);

    Product newProduct = useCase.execute(product);

    // Then
    assertEquals(storedProduct.getBrand(), newProduct.brand());
    assertEquals(storedProduct.getName(), newProduct.name());
    assertEquals(storedProduct.getExpirationDate(), newProduct.expirationDate());
    assertEquals(storedProduct.getPrice(), newProduct.price());
    Mockito.verify(repository, Mockito.times(1)).save(ArgumentMatchers.any());
  }
}
