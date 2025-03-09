package com.couponPayment.entity.mapper;

import com.couponPayment.entity.WalletReqTb;
import com.couponPayment.entity.dto.WalletReqDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletReqMapper {
    WalletReqTb toEntity(WalletReqDto walletReqDto);
    WalletReqDto toDto(WalletReqTb walletReqTb);
}
