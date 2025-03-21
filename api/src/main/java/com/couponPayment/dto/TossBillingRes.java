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
public class TossBillingRes {
    private final String mId;
    private final String customerKey;
    private final String authenticatedAt;
    private final String method;
    private final String billingKey;
    private final CardInfo card;
    private final String cardCompany;
    private final String cardNumber;
    private final String code;
    private final String message;

    @AllArgsConstructor
    @NoArgsConstructor(force = true) //force = true를 사용하여 final 필드에 null 값 허용
    @Getter
    public static class CardInfo {
        private final String issuerCode;
        private final String acquirerCode;
        private final String number;
        private final String cardType;
        private final String ownerType;
    }
}
