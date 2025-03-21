package com.couponPayment.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(force = true) //force = true를 사용하여 final 필드에 null 값 허용
@Getter
public class PaymentHistoryReq {
    private final String merchantId;
    private final String merchantMemberId;
}
