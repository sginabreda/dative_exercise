package com.exercise.dative.unit.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.exercise.dative.domain.exception.ProductNotFoundException;
import com.exercise.dative.domain.usecase.DeleteProductUseCase;
import com.exercise.dative.infrastructure.db.ProductRepository;
import com.exercise.dative.infrastructure.db.model.ProductModel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class DeleteProductUseCaseTest {

  private final ProductRepository repository = Mockito.mock();
  private final DeleteProductUseCase useCase = new DeleteProductUseCase(repository);

  @Test
  void testProductIsDeleted() {
    // Given
    Long productId = 1L;
    ProductModel storedProduct = new ProductModel(productId, "productName", "brand", LocalDateTime.now(), BigDecimal.valueOf(100.00));

    // When
    when(repository.findById(any())).thenReturn(Optional.of(storedProduct));
    doNothing().when(repository).deleteById(any());

    // Then
    useCase.execute(productId);
  }

  @Test
  void testNonexistentProductIdThrowsException() {
    // Given
    Long productId = 355L;

    // When
    when(repository.findById(any())).thenReturn(Optional.empty());

    // Then
    Assertions.assertThrows(ProductNotFoundException.class, () -> useCase.execute(productId));
  }
}
