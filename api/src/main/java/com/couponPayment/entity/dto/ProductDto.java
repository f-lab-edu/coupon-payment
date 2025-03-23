package com.couponPayment.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class ProductDto {
    private final String name;
    private final int price;
    private final int quantity;
}
