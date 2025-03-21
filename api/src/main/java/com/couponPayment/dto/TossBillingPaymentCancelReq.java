package com.couponPayment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor(force = true) //force = true를 사용하여 final 필드에 null 값 허용
@Getter
public class TossBillingPaymentCancelReq {
    private final String paymentKey;
    private final String cancelReason;
    private final String secretKey;
}
