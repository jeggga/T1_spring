package org.example.model.dto;

import java.math.BigDecimal;

public record ProductDto(long id, String accountNumber, BigDecimal balance, String typeOfProduct, long userId){}
