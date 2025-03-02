package com.couponPayment.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PaymentHistoryReq {
    private String merchantId;
    private String merchantMemberId;
}
