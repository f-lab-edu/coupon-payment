package com.couponPayment.entity.mapper;

import com.couponPayment.entity.StoreInfo;
import com.couponPayment.entity.dto.StoreInfoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreInfoMapper {
    StoreInfo toEntity(StoreInfoDto storeInfoDto);

    StoreInfoDto toDto(StoreInfo storeInfo);

}
