package com.couponPayment.entity.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class WalletReqDto {
    private String merchantId;
    private String merchantMemberId;
    private String orderId;
    private String orderNum;
    private int amount;
}
