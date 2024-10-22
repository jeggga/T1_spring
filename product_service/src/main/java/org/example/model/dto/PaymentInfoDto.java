package org.example.model.dto;

import java.math.BigDecimal;

public record PaymentInfoDto(String productType, BigDecimal paymentAmount) {}
