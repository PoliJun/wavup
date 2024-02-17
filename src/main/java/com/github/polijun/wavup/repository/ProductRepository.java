package com.github.polijun.wavup.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.polijun.wavup.model.Product;

/**
 * ProductRepository
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String productName);

    List<Product> findByBrand(String brand);

    List<Product> findByStyle(Long styleId);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);


}
