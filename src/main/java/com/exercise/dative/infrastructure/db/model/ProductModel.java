package com.exercise.dative.infrastructure.db.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "PRODUCTS")
@Table(name = "PRODUCTS", schema = "public")
public class ProductModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "product_name")
  private String name;
  private String brand;
  @Column(name = "expiration_date")
  private LocalDateTime expirationDate;
  private BigDecimal price;

  public ProductModel(
      Long id, String name, String brand, LocalDateTime expirationDate, BigDecimal price) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.expirationDate = expirationDate;
    this.price = price;
  }

  public ProductModel() {
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getBrand() {
    return brand;
  }

  public LocalDateTime getExpirationDate() {
    return expirationDate;
  }

  public BigDecimal getPrice() {
    return price;
  }
}
