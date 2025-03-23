package com.couponPayment.entity.mapper;

import com.couponPayment.entity.OrderItem;
import com.couponPayment.entity.dto.OrderItemDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderItemMapper {

    @Mapping(source="productId", target = "productTb.id")
    @Mapping(source="couponIssueId", target = "couponIssue.id")
    @Mapping(source="orderId", target = "orderTb.id")
    OrderItem toEntity(OrderItemDto orderItemDto);

    @Mapping(source= "productTb.id", target = "productId")
    @Mapping(source= "couponIssue.id", target = "couponIssueId")
    @Mapping(source= "orderTb.id", target = "orderId")
    OrderItemDto toDto(OrderItem orderItem);

}
