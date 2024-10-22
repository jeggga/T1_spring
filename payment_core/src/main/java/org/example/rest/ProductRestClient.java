package org.example.rest;

import org.example.exception.CustomRestException;
import org.example.exception.dto.ErrorResponse;
import org.example.model.dto.PaymentDto;
import org.example.model.dto.ProductDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductRestClient {
    private final RestTemplate restTemplate;

    public ProductRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<ProductDto[]> getProductsByUserId(Long userId) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId.toString());
        try {
            return restTemplate.getForEntity("/{userId}/all", ProductDto[].class, map);
        } catch (Exception exception) {
            throw new CustomRestException(new ErrorResponse(exception.getMessage(), "product_service"));
        }
    }

    public void makePayment(Long userId, PaymentDto paymentDto) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId.toString());

        HttpEntity<PaymentDto> request = new HttpEntity<>(paymentDto);
        UriComponents uri = UriComponentsBuilder.fromUriString("/pay").queryParam("userId", userId).build();

        ResponseEntity<String> response = restTemplate.postForEntity(uri.toUriString(), request, String.class, map);
    }
}
