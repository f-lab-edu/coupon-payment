package com.couponPayment.entity.mapper;

import com.couponPayment.entity.StoreInfoTb;
import com.couponPayment.entity.dto.StoreInfoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreInfoMapper {
    StoreInfoTb toEntity(StoreInfoDto storeInfoDto);

    StoreInfoDto toDto(StoreInfoTb storeInfoTb);
}
