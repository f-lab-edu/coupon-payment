package com.couponPayment.entity.mapper;

import com.couponPayment.entity.UserInfo;
import com.couponPayment.entity.dto.UserInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserInfoMapper {

    @Mapping(source="storeInfoId", target = "storeInfo.id")
    UserInfo toEntity(UserInfoDto userInfoDto);
    @Mapping(source= "storeInfo.id", target = "storeInfoId")
    UserInfoDto toDto(UserInfo userInfo);

}
