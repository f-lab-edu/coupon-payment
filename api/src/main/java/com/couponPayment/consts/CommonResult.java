package com.couponPayment.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonResult {
    E0000("0000", "성공"),
    E9000("9000", "해당 가맹점이 존재하지 않습니다.");

    private String code;
    private String message;
}
