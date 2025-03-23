package com.couponPayment.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class OrderItemDto {
    private final Integer quantity;
    private final Long orderId;
    private final Long productId;
    private final Long couponIssueId;
}
