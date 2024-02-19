package com.github.polijun.wavup.service;

import com.github.polijun.wavup.model.Product;
import com.github.polijun.wavup.model.Style;
import com.github.polijun.wavup.model.constant.Category;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.lang.NonNull;

public interface ProductService {

        List<Product> getAllProducts();

        Product getProductById(@NonNull Long productId);

        Product getProductsByName(String productName);

        List<Product> getProductsByBrand(String brand);

        List<Product> getProductsByStyles(@NonNull List<Style> styles);

        List<Product> getProductsByCategory(Category category);

        List<Product> getProductsByPriceBetween(@NonNull BigDecimal minPrice,
                        @NonNull BigDecimal maxPrice);

        List<Product> getProductsByMultiConditions(String brand, @NonNull List<Style> styles,
                        @NonNull BigDecimal minPrice, @NonNull BigDecimal maxPrice);

        Product createProduct(@NonNull Product product);

        void updateProduct(@NonNull Long productId, @NonNull Product updatedProduct);

        void deleteProduct(@NonNull Long productId);
}
