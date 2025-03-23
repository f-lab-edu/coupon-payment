package com.couponPayment.entity.mapper;

import com.couponPayment.entity.Order;
import com.couponPayment.entity.dto.OrderDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderMapper {
    @Mapping(source = "userInfoId", target = "userInfo.id")
    Order toEntity(OrderDto orderDto);

    @Mapping(source = "userInfo.id", target = "userInfoId")
    OrderDto toDto(Order order);
}
