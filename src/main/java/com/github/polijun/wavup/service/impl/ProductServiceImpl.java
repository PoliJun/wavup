package com.github.polijun.wavup.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.polijun.wavup.exception.AlreadyExistsException;
import com.github.polijun.wavup.exception.NonExistsException;
import com.github.polijun.wavup.model.Product;
import com.github.polijun.wavup.model.Style;
import com.github.polijun.wavup.repository.ProductRepository;
import com.github.polijun.wavup.service.ProductService;
import lombok.RequiredArgsConstructor;

/**
 * ProductServiceImpl
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private static final String PRODUCT = "Product";

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(@NonNull Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NonExistsException(PRODUCT));
    }

    @Override
    public Product getProductsByName(String productName) {
        return productRepository.findByName(productName)
                .orElseThrow(() -> new NonExistsException(PRODUCT));
    }


    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);

    }

    @Override
    public List<Product> getProductsByStyles(@NonNull List<Style> styles) {
        return productRepository.findByStylesIn(styles);

    }

    @Override
    public List<Product> getProductsByPriceBetween(@NonNull BigDecimal minPrice,
            @NonNull BigDecimal maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);

    }

    @Override
    public Product createProduct(@NonNull Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void updateProduct(@NonNull Long productId, @NonNull Product updatedProduct) {
        Product productExists = productRepository.findById(productId)
                .orElseThrow(() -> new NonExistsException(PRODUCT));
        productExists.setBrand(updatedProduct.getBrand());
        productExists.setDescription(updatedProduct.getDescription());
        productExists.setInventory(updatedProduct.getInventory());
        productExists.setName(updatedProduct.getName());
        productExists.setOutfits(updatedProduct.getOutfits());
        productExists.setPrice(updatedProduct.getPrice());
        productExists.setStyles(updatedProduct.getStyles());
    }

    @Override
    public void deleteProduct(@NonNull Long productId) {
        Optional<Product> productExists = productRepository.findById(productId);
        if (productExists.isPresent()) {
            productRepository.deleteById(productId);
        } else {
            throw new NonExistsException(PRODUCT);
        }
    }

    @Override
    public List<Product> getProductsByMultiConditions(String brand, @NonNull List<Style> styles,
            @NonNull BigDecimal minPrice, @NonNull BigDecimal maxPrice) {
        List<Product> productsByBrand = getProductsByBrand(brand);
        List<Product> productsByStyle = getProductsByStyles(styles);
        List<Product> productsByPrice = getProductsByPriceBetween(minPrice, maxPrice);

        List<Product> intersection = new ArrayList<>(productsByBrand);
        intersection.retainAll(productsByStyle);
        intersection.retainAll(productsByPrice);

        return intersection;
    }



}
