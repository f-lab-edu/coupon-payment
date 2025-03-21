package com.couponPayment.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class MyWalletInfoDto {
    private final String cardId;
    private final String cardCompany;
    private final String cardNumber;
    private final String issuerCode;
    private final String acquirerCode;
    private final String cardType;
    private final String ownerType;

    private final Long userInfoId;
}
