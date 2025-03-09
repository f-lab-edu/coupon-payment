package com.couponPayment.entity.mapper;

import com.couponPayment.entity.MyWalletInfoTb;
import com.couponPayment.entity.dto.MyWalletInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserInfoMapperHelper.class})
public interface MyWalletInfoMapper{
    @Mapping(source="userInfoId", target = "userInfoTb", qualifiedByName = "mapUserInfo")
    MyWalletInfoTb toEntity(MyWalletInfoDto myWalletInfoDto);

    @Mapping(source = "userInfoTb.id", target = "userInfoId")
    MyWalletInfoDto toDto(MyWalletInfoTb myWalletInfoTb);

}
