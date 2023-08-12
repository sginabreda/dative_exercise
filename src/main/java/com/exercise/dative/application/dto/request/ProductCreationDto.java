package com.exercise.dative.application.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ProductCreationDto(
    @NotNull(message = "Product name is required")
    @NotBlank(message = "Product name can't be blank")
    String name,
    @NotNull(message = "Brand is required")
    @NotBlank(message = "Brand can't be blank")
    String brand,
    @NotNull(message = "Expiration date is required")
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    LocalDateTime expirationDate,
    @NotNull(message = "Product price is required")
    BigDecimal price
) { }
