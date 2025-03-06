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
    private int amount;
    private String orderId;
    private String orderName;
    private String customerEmail;
    private String customerName;
    private String taxFreeAmount;
    private String taxExemptionAmount;
}
