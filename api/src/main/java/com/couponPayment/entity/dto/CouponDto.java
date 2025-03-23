package com.couponPayment.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class CouponDto {
    private final String code; // ex. SPRING5000
    private final int discountAmount;// 정액 할인
    private final double discountRate; // 정률 할인도 가능 (ex. 10%)

    private final Integer quantity; //발급 가능한 수
    private final String issuedDt; //생성 날짜
    private final String expiresDt; //만료 기한
}
