package com.couponPayment.entity.mapper;

import com.couponPayment.entity.Coupon;
import com.couponPayment.entity.dto.CouponDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CouponMapper {
    Coupon toEntity(CouponDto couponDto);

    CouponDto toDto(Coupon coupon);
}
