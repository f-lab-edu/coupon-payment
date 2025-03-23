package com.couponPayment.entity.mapper;

import com.couponPayment.entity.CouponIssue;
import com.couponPayment.entity.dto.CouponIssueDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CouponIssueMapper {
    @Mapping(source = "couponId", target = "coupon.id")
    @Mapping(source = "orderItemId", target = "orderItem.id")
    @Mapping(source = "userInfoId", target = "userInfo.id")
    CouponIssue toEntity(CouponIssueDto couponIssueDto);

    @Mapping(source = "coupon.id", target = "couponId")
    @Mapping(source = "orderItem.id", target = "orderItemId")
    @Mapping(source = "userInfo.id", target = "userInfoId")
    CouponIssueDto toDto(CouponIssue couponIssue);
}
