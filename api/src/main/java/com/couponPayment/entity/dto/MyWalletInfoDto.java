package com.couponPayment.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MyWalletInfoDto {
    private String cardId;
    private String cardCompany;
    private String cardNumber;
    private String issuerCode;
    private String acquirerCode;
    private String number;
    private String cardType;
    private String ownerType;

    private Long userInfoId;
}
