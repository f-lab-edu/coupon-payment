package com.couponPayment.entity.mapper;

import com.couponPayment.entity.MyWalletInfo;
import com.couponPayment.entity.dto.MyWalletInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MyWalletInfoMapper{
    @Mapping(source="userInfoId", target = "userInfo.id")
    MyWalletInfo toEntity(MyWalletInfoDto myWalletInfoDto);

    @Mapping(source = "userInfo.id", target = "userInfoId")
    MyWalletInfoDto toDto(MyWalletInfo myWalletInfo);

}
