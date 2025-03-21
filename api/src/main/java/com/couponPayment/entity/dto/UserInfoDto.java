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
    private String name;
    private String phone;
    private String mail;
    private Integer useFlag;
    private Long storeInfoId;
}
