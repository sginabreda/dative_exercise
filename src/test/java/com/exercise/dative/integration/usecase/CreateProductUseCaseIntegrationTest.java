package com.exercise.dative.integration.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.exercise.dative.domain.entity.Product;
import com.exercise.dative.domain.usecase.CreateProductUseCase;

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
public class CreateProductUseCaseIntegrationTest {

  @Autowired
  private CreateProductUseCase useCase;

  @Test
  void testCreateProduct_shouldReturnProduct() {
    // Given
    Product product = new Product(null, "productName", "brand", LocalDateTime.now(), BigDecimal.valueOf(100.00));

    // When
    Product newProduct = useCase.execute(product);

    // Then
    assertNotNull(newProduct.id());
    assertEquals(product.name(), newProduct.name());
    assertEquals(product.brand(), newProduct.brand());
    assertEquals(product.expirationDate(), newProduct.expirationDate());
    assertEquals(product.price(), newProduct.price());
  }
}
