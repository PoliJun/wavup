package com.github.polijun.wavup.service;

import java.math.BigDecimal;
import java.util.List;
import com.github.polijun.wavup.model.Product;

public interface ProductService {
    List<Product> findAllProducts();

    Product findProductById(Long id);

    List<Product> findProductByName(String name);

    List<Product> findProductByCategory(String category);

    List<Product> findProductByBrand(String brand);

    List<Product> findProductBySize(String size);

    List<Product> findProductByPriceBetween(BigDecimal start, BigDecimal end);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    void deleteProductById(Long id);
}
