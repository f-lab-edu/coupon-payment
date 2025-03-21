package com.couponPayment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(force = true) //force = true를 사용하여 final 필드에 null 값 허용
@Getter
@JsonIgnoreProperties(ignoreUnknown = true) // 알 수 없는 필드는 무시
public class TossBillingPaymentRes {
    //Jackson Lombok의 Getter 네이밍 컨벤션에 의하여 붙여야함 ex) getMId()의 경우 첫2글자가 대문자라 다 소문자로 치환 됨
    @JsonProperty("mId")
    private final String mId;
    private final String lastTransactionKey;
    private final String paymentKey;
    private final String orderId;
    private final String orderName;
    private final int taxExemptionAmount;
    private final String status;
    private final String requestedAt;
    private final String approvedAt;
    private final boolean useEscrow;
    private final boolean cultureExpense;
    private final String type;
    private final String country;
    private final boolean isPartialCancelable;
    private final String transactionKey;
    private final String currency;
    private final int totalAmount;
    private final int balanceAmount;
    private final int suppliedAmount;
    private final int vat;
    private final int taxFreeAmount;
    private final String method;
    private final String version;
    private final Receipt receipt;
    private final Checkout checkout;
    private final Card card;
    private final Failure failure;
    private final String code;
    private final String message;

    @AllArgsConstructor
    @NoArgsConstructor(force = true) //force = true를 사용하여 final 필드에 null 값 허용
    @Getter
    public static class Failure {
        private final String code;
        private final String message;
    }

    @AllArgsConstructor
    @NoArgsConstructor(force = true) //force = true를 사용하여 final 필드에 null 값 허용
    @Getter
    public static class Receipt {
        private final String url;
    }

    @AllArgsConstructor
    @NoArgsConstructor(force = true) //force = true를 사용하여 final 필드에 null 값 허용
    @Getter
    public static class Checkout {
        private final String url;
    }

    @AllArgsConstructor
    @NoArgsConstructor(force = true) //force = true를 사용하여 final 필드에 null 값 허용
    @Getter
    public static class Card {
        private final String company;
        private final String issuerCode;
        private final String acquirerCode;
        private final String number;
        private final int installmentPlanMonths;
        private final boolean isInterestFree;
        private final String interestPayer;
        private final String approveNo;
        private final boolean useCardPoint;
        private final String cardType;
        private final String ownerType;
        private final String acquireStatus;
        private final String receiptUrl;
        private final String provider;
        private final int amount;
    }
}
