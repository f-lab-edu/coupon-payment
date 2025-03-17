package com.couponPayment.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Builder
public class TossBillingPaymentReq {
    private String customerKey;
    private String secretKey;
    private int amount;
    private int cardInstallmentPlan;
    private String cardId;
    private String orderId;
    private String orderName;
    private String customerEmail;
    private String customerName;
    private String taxFreeAmount;
    private String taxExemptionAmount;
}
