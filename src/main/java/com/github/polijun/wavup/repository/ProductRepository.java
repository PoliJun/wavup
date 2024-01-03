package com.github.polijun.wavup.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.polijun.wavup.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name);

    List<Product> findByCategory(String category);

    List<Product> findByBrand(String brand);

    List<Product> findBySize(String size);

    List<Product> findByPriceBetween(BigDecimal start, BigDecimal end);

}
