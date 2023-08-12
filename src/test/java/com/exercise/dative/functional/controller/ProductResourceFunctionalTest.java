package com.exercise.dative.functional.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.exercise.dative.application.dto.request.ProductCreationDto;
import com.exercise.dative.application.dto.response.ApiErrorDto;
import com.exercise.dative.application.dto.response.ProductDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class ProductResourceFunctionalTest {

  @Autowired
  private TestRestTemplate restTemplate;

  private String productControllerUri;

  @BeforeEach
  void setUp() {
    productControllerUri = restTemplate.getRootUri() + "/products";
  }

  @Test
  void testProductIsCreated() {
    // Given
    ProductCreationDto productCreationDto = new ProductCreationDto(
        "product", "brand", LocalDateTime.now().plusDays(10), BigDecimal.valueOf(100));

    // When
    ResponseEntity<ProductDto> response = restTemplate.postForEntity(
        productControllerUri, productCreationDto, ProductDto.class);

    // Then
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    ProductDto product = response.getBody();
    assertNotNull(product);
    assertEquals(productCreationDto.name(), product.name());
    assertEquals(productCreationDto.brand(), product.brand());
    assertEquals(productCreationDto.expirationDate(), product.expirationDate());
    assertEquals(productCreationDto.price(), product.price());
  }

  @Test
  void testProductCreationFailsValidation() {
    // Given
    ProductCreationDto productCreationDto = new ProductCreationDto(
        null, "brand", LocalDateTime.now().plusDays(10), BigDecimal.valueOf(100));

    // When
    ResponseEntity<ApiErrorDto> response = restTemplate.postForEntity(
        productControllerUri, productCreationDto, ApiErrorDto.class);

    // Then
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    ApiErrorDto error = response.getBody();
    assertNotNull(error);
    assertEquals("Product name is required", error.message());
    assertEquals("bad.request", error.code());
  }
}
