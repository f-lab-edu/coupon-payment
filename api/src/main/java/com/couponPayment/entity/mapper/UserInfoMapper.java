package com.couponPayment.entity.mapper;

import com.couponPayment.entity.StoreInfoTb;
import com.couponPayment.entity.UserInfoTb;
import com.couponPayment.entity.WalletReqTb;
import com.couponPayment.entity.dto.UserInfoDto;
import com.couponPayment.entity.dto.WalletReqDto;
import com.couponPayment.repository.StoreInfoRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

@Mapper(componentModel = "spring", uses = { StoreInfoMapperHelper.class })
public interface UserInfoMapper {

    @Mapping(source="storeInfoId", target = "storeInfoTb", qualifiedByName = "mapStoreInfo")
    UserInfoTb toEntity(UserInfoDto userInfoDto);
    @Mapping(source="storeInfoTb.id", target = "storeInfoId")
    UserInfoDto toDto(UserInfoTb userInfoTb);

}
