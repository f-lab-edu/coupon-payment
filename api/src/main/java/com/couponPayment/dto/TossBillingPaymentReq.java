package com.couponPayment.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(force = true) //force = true를 사용하여 final 필드에 null 값 허용
@Getter
@Builder
@Setter
/**
 * null값 허용되는 데이터들이 있어 필요한 값만 채울 목적으로 builder패턴 사용
* */
public class TossBillingPaymentReq {
    private final String customerKey;
    private final String secretKey;
    private final int amount;
    private final int cardInstallmentPlan;
    private final String cardId;
    private final String orderId;
    private final String orderName;
    private final String customerEmail;
    private final String customerName;
    private final String taxFreeAmount;
    private final String taxExemptionAmount;
}
