package com.exercise.dative.application.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ProductDto(Long id, String name, String brand, LocalDateTime expirationDate,
                        BigDecimal price) {}
