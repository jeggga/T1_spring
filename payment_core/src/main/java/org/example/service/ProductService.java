package org.example.service;

import org.example.model.dto.PaymentDto;
import org.example.model.dto.ProductDto;
import org.example.rest.ProductRestClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRestClient restClient;

    public ProductService(ProductRestClient restClient) {
        this.restClient = restClient;
    }

    public ResponseEntity<ProductDto[]> getProductsByUserId(Long userId) {
        return restClient.getProductsByUserId(userId);
    }

    public void paymentByProduct(Long userId, PaymentDto paymentDto) {
        restClient.makePayment(userId, paymentDto);
    }
}
