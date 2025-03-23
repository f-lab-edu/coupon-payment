package com.couponPayment.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    WAIT, RECEIPT, COMPLETE, CANCEL;
}
