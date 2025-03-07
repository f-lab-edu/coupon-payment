package com.couponPayment.entity.mapper;

import com.couponPayment.entity.MyWalletInfoTb;
import com.couponPayment.entity.dto.MyWalletInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MyWalletInfoMapper{

    @Mapping(source="userInfoId", target = "userInfoTb.id")
    MyWalletInfoTb toEntity(MyWalletInfoDto myWalletInfoDto);

    @Mapping(source = "userInfoTb.id", target = "userInfoId")
    MyWalletInfoDto toDto(MyWalletInfoTb myWalletInfoTb);



}
