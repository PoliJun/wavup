package com.github.polijun.wavup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.polijun.wavup.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}