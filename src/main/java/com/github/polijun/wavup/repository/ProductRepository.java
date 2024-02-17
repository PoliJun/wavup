package com.github.polijun.wavup.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.polijun.wavup.model.Product;
import com.github.polijun.wavup.model.Style;

/**
 * ProductRepository
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String productName);

    List<Product> findByBrand(String brand);


    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Product> findByStylesIn(List<Style> styles);

}
