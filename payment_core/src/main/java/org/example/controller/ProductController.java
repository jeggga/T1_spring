package org.example.controller;

import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("product")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{userId}/all")
    public ResponseEntity<List<Product>> getAllProductsByUserId(@PathVariable("userId") Long userId) {

        try {
            List<Product> products = service.getAllProducts(userId);
            if (products.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(products);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId) {
        try {
            Product product = service.getProductById(productId);
            if (Objects.isNull(product)) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
