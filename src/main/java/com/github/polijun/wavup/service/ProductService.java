package com.github.polijun.wavup.service;

import com.github.polijun.wavup.model.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long productId);

    List<Product> getProductsByName(String productName);

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByStyle(Long styleId);

    List<Product> getProductsByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    Product createProduct(Product product);

    Product updateProduct(Long productId, Product updatedProduct);

    void deleteProduct(Long productId);
}
