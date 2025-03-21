package com.couponPayment.entity.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class UserInfoDto {
    private final String name;
    private final String phone;
    private final String mail;
    private final Integer useFlag;
    private final Long storeInfoId;
}
