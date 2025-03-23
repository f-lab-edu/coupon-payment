package com.couponPayment.entity.dto;

import com.couponPayment.consts.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class OrderDto {
    private final BigDecimal totalAmount; //주문 총 금액

    private final OrderStatus orderStatus; //주문 상태

    private final String orderNum; //주문 번호

    private final Long userInfoId;

}
