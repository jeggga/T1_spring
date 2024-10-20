package org.example.service;

import org.example.model.Product;
import org.example.repository.ProductDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAllProducts(Long userId) {
        try {
            return productDao.getAllProducts(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Product getProductById(Long productId) {
        try {
            return productDao.getProductById(productId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
