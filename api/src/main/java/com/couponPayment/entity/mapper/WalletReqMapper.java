package com.couponPayment.entity.mapper;

import com.couponPayment.entity.WalletReq;
import com.couponPayment.entity.dto.WalletReqDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletReqMapper {
    WalletReq toEntity(WalletReqDto walletReqDto);
    WalletReqDto toDto(WalletReq walletReq);
}
