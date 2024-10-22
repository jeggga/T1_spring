package org.example.service;

import org.example.exception.CustomNotFoundException;
import org.example.exception.NotEnoughMoneyException;
import org.example.model.Product;
import org.example.model.constant.TypeOfProduct;
import org.example.model.dto.PaymentInfoDto;
import org.example.repository.ProductDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void makePayment(Long userId, PaymentInfoDto paymentInfoDto) {
        List<Product> products = getAllProducts(userId);

        if (products.isEmpty()) {
            throw new CustomNotFoundException(String.format("Products for client - %s does not exist",
                    userId));
        }

        Optional<Product> oProduct = products.stream()
                .filter(productStream -> productStream.getTypeOfProduct() == TypeOfProduct. fromString(paymentInfoDto.productType()))
                .findFirst();

        if (oProduct.isEmpty()) {
            throw new CustomNotFoundException(String.format("Product type - %s for client - %s does not exist", paymentInfoDto.productType(),
                    userId));
        }

        Product product = oProduct.get();

        if (paymentInfoDto.paymentAmount().doubleValue() > product.getBalance().doubleValue()) {
            throw new NotEnoughMoneyException(String.format("Product type - %s for client - %s does not have enough money", paymentInfoDto.productType(),
                    userId));
        }

        product.setBalance(product.getBalance().subtract(paymentInfoDto.paymentAmount()));

        productDao.updateProduct(product);
    }
}
