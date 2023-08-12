package com.exercise.dative.unit.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.exercise.dative.domain.entity.Product;
import com.exercise.dative.domain.usecase.ListProductsUseCase;
import com.exercise.dative.infrastructure.db.ProductRepository;
import com.exercise.dative.infrastructure.db.model.ProductModel;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class ListProductsUseCaseTest {

  private final ProductRepository repository = Mockito.mock();
  private final ListProductsUseCase useCase = new ListProductsUseCase(repository);

  @Test
  void testListOfProductsIsReturned() {
    // Given
    ProductModel product = new ProductModel(1L, "productName", "brand", LocalDateTime.now(), BigDecimal.valueOf(100.00));

    // When
    when(repository.findAll()).thenReturn(List.of(product));

    List<Product> products = useCase.execute();

    // Then
    assertEquals(1, products.size());
    Product result = products.get(0);
    assertEquals(product.getId(), result.id());
    assertEquals(product.getName(), result.name());
    assertEquals(product.getBrand(), result.brand());
    assertEquals(product.getExpirationDate(), result.expirationDate());
    assertEquals(product.getPrice(), result.price());
  }

  @Test
  void testNoProductsStored_emptyListIsReturned() {
    // When
    when(repository.findAll()).thenReturn(List.of());

    List<Product> products = useCase.execute();

    // Then
    assertEquals(Collections.emptyList(), products);
  }
}
