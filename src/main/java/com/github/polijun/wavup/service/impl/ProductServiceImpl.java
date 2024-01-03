package com.github.polijun.wavup.service.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import com.github.polijun.wavup.exception.ResourceNotFound;
import com.github.polijun.wavup.model.Product;
import com.github.polijun.wavup.repository.ProductRepository;
import com.github.polijun.wavup.service.ProductService;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Product"));

    }

    @Override
    public List<Product> findProductByName(String name) {

        List<Product> foundProducts = productRepository.findByName(name);
        if (foundProducts.isEmpty())
            throw new ResourceNotFound("Product");
        return foundProducts;
    }

    @Override
    public List<Product> findProductByCategory(String category) {

        List<Product> foundProducts = productRepository.findByCategory(category);
        if (foundProducts.isEmpty())
            throw new ResourceNotFound("Product");
        return foundProducts;
    }

    @Override
    public List<Product> findProductByBrand(String brand) {

        List<Product> foundProducts = productRepository.findByBrand(brand);
        if (foundProducts.isEmpty())
            throw new ResourceNotFound("Product");
        return foundProducts;
    }

    @Override
    public List<Product> findProductBySize(String size) {

        List<Product> foundProducts = productRepository.findBySize(size);
        if (foundProducts.isEmpty())
            throw new ResourceNotFound("Product");
        return foundProducts;
    }

    @Override
    public List<Product> findProductByPriceBetween(BigDecimal start, BigDecimal end) {

        List<Product> foundProducts = productRepository.findByPriceBetween(start, end);
        if (foundProducts.isEmpty())
            throw new ResourceNotFound("Product");
        return foundProducts;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product updatingProduct =
                productRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Product"));
        updatingProduct.setBrand(product.getBrand());
        updatingProduct.setCategory(product.getCategory());
        updatingProduct.setDescription(product.getDescription());
        updatingProduct.setName(product.getName());
        updatingProduct.setOutfits(product.getOutfits());
        updatingProduct.setPrice(product.getPrice());
        updatingProduct.setQuantity(product.getQuantity());
        updatingProduct.setSize(product.getSize());
        updatingProduct.setStyles(product.getStyles());
        return productRepository.save(updatingProduct);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

}
