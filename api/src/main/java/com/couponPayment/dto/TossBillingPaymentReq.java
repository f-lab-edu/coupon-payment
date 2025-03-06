package com.couponPayment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
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
