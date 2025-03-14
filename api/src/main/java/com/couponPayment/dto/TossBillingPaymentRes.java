package com.couponPayment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true) // 알 수 없는 필드는 무시
public class TossBillingPaymentRes {
    //Jackson Lombok의 Getter 네이밍 컨벤션에 의하여 붙여야함 ex) getMId()의 경우 첫2글자가 대문자라 다 소문자로 치환 됨
    @JsonProperty("mId")
    private String mId;
    private String lastTransactionKey;
    private String paymentKey;
    private String orderId;
    private String orderName;
    private int taxExemptionAmount;
    private String status;
    private String requestedAt;
    private String approvedAt;
    private boolean useEscrow;
    private boolean cultureExpense;
    private String type;
    private String country;
    private boolean isPartialCancelable;
    private String transactionKey;
    private String currency;
    private int totalAmount;
    private int balanceAmount;
    private int suppliedAmount;
    private int vat;
    private int taxFreeAmount;
    private String method;
    private String version;
    private Receipt receipt;
    private Checkout checkout;
    private Card card;
    private Failure failure;
    private String code;
    private String message;
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Setter
    @Getter
    public static class Failure {
        private String code;
        private String message;
    }
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Setter
    @Getter
    public static class Receipt {
        private String url;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Setter
    @Getter
    public static class Checkout {
        private String url;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Setter
    @Getter
    public static class Card {
        private String company;
        private String issuerCode;
        private String acquirerCode;
        private String number;
        private int installmentPlanMonths;
        private boolean isInterestFree;
        private String interestPayer;
        private String approveNo;
        private boolean useCardPoint;
        private String cardType;
        private String ownerType;
        private String acquireStatus;
        private String receiptUrl;
        private String provider;
        private int amount;
    }
}
