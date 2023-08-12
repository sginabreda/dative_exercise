package com.exercise.dative.unit.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.exercise.dative.domain.entity.Product;
import com.exercise.dative.domain.exception.ProductNotFoundException;
import com.exercise.dative.domain.usecase.GetProductUseCase;
import com.exercise.dative.infrastructure.db.ProductRepository;
import com.exercise.dative.infrastructure.db.model.ProductModel;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class GetProductUseCaseTest {

  private final ProductRepository repository = Mockito.mock();
  private final GetProductUseCase useCase = new GetProductUseCase(repository);

  @Test
  void testExistingProductIsReturned() {
    // Given
    Long productId = 1L;
    ProductModel storedProduct = new ProductModel(productId, "productName", "brand", LocalDateTime.now(), BigDecimal.valueOf(100.00));

    // When
    when(repository.findById(any())).thenReturn(Optional.of(storedProduct));

    Product product = useCase.execute(productId);

    // Then
    assertEquals(storedProduct.getId(), product.id());
    assertEquals(storedProduct.getName(), product.name());
    assertEquals(storedProduct.getBrand(), product.brand());
    assertEquals(storedProduct.getExpirationDate(), product.expirationDate());
    assertEquals(storedProduct.getPrice(), product.price());
  }

  @Test
  void testNonexistentProductIdThrowsException() {
    // Given
    Long productId = 355L;

    // When
    when(repository.findById(any())).thenReturn(Optional.empty());

    // Then
    assertThrows(ProductNotFoundException.class, () -> useCase.execute(productId));
  }
}
