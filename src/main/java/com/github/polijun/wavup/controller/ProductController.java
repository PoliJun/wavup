package com.github.polijun.wavup.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.polijun.wavup.model.Product;
import com.github.polijun.wavup.model.constant.Category;
import com.github.polijun.wavup.service.ProductService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



/**
 * ProductController
 */
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            return ResponseEntity.ok().body(productService.getAllProducts());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // get product by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable @NonNull Long id) {
        try {
            return ResponseEntity.ok().body(productService.getProductById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // get product by name
    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductsByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok().body(productService.getProductsByName(name));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // get product by brand
    @GetMapping("/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        try {
            return ResponseEntity.ok().body(productService.getProductsByBrand(brand));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // get product by category
    @GetMapping("/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Category category) {
        try {
            return ResponseEntity.ok().body(productService.getProductsByCategory(category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // create product
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody @NonNull Product product) {

        return ResponseEntity.ok().body(productService.createProduct(product));

    }

    // update product
    @PutMapping("path/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable @NonNull Long id,
            @RequestBody @NonNull Product product) {
        try {
            productService.updateProduct(id, product);
            return ResponseEntity.ok().body(productService.getProductById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable @NonNull Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
