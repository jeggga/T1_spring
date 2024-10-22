package org.example.model.dto;

import java.math.BigDecimal;

public record PaymentDto(String productType, BigDecimal paymentAmount) {}
