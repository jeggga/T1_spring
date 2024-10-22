package org.example.controller;

import org.example.model.dto.PaymentDto;
import org.example.model.dto.ProductDto;
import org.example.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("payments")
public class PaymentController {
    private final ProductService service;

    public PaymentController(ProductService service) {
        this.service = service;
    }

    @GetMapping("{userId}/products")
    public ResponseEntity<ProductDto[]> getProductsByUserId(@PathVariable("userId") Long userId) {
        return service.getProductsByUserId(userId);
    }

    @PostMapping("pay")
    public void paymentByProduct(@RequestParam ("userId") Long userId, @RequestBody PaymentDto paymentDto) {
        service.paymentByProduct(userId, paymentDto);
    }
}
