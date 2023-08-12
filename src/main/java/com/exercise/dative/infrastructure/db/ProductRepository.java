package com.exercise.dative.infrastructure.db;

import com.exercise.dative.infrastructure.db.model.ProductModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
}
