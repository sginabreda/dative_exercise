package com.exercise.dative.integration.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.exercise.dative.domain.entity.Product;
import com.exercise.dative.domain.exception.ProductNotFoundException;
import com.exercise.dative.domain.usecase.GetProductUseCase;
import com.exercise.dative.infrastructure.db.ProductRepository;
import com.exercise.dative.infrastructure.db.model.ProductModel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class GetProductUseCaseIntegrationTest {

  @Autowired
  private GetProductUseCase useCase;
  @Autowired
  private ProductRepository repository;

  @Test
  void testGetProduct_shouldReturnProductStored() {
    // Given
    ProductModel product = new ProductModel(null, "productName", "brand", LocalDateTime.now(), BigDecimal.valueOf(100, 2));
    Long productId = repository.save(product).getId();

    // When
    Product storedProduct = useCase.execute(productId);

    // Then
    assertEquals(productId, storedProduct.id());
    assertEquals(product.getName(), storedProduct.name());
    assertEquals(product.getBrand(), storedProduct.brand());
    assertEquals(product.getExpirationDate(), storedProduct.expirationDate());
    assertEquals(product.getPrice(), storedProduct.price());
  }

  @Test
  void testGetProduct_shouldThrowExceptionWithNonexistentProduct() {
    // When / Then
    assertThrows(ProductNotFoundException.class, () -> useCase.execute(1390L));
  }
}
