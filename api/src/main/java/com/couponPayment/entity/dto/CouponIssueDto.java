package com.couponPayment.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class CouponIssueDto {
    private final Boolean used;
    private final String usedDt;
    private final Long couponId;
    private final Long orderItemId;
    private final Long userInfoId;
}
