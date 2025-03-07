package com.couponPayment.entity.mapper;

import com.couponPayment.entity.TransactionInfoTb;
import com.couponPayment.entity.dto.TransactionInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(source="myWalletInfoId", target = "myWalletInfoTb.id")
    @Mapping(source="walletReqId", target = "walletReqTb.id")
    TransactionInfoTb toEntity(TransactionInfoDto transactionInfoDto);

    @Mapping(source="myWalletInfoTb.id", target = "myWalletInfoId")
    @Mapping(source="walletReqTb.id", target = "walletReqId")
    TransactionInfoDto toDto(TransactionInfoTb transactionInfoTb);
}
