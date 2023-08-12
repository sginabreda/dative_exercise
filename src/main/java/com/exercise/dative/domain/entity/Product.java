package com.exercise.dative.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Product(Long id, String name, String brand, LocalDateTime expirationDate,
                     BigDecimal price) { }
