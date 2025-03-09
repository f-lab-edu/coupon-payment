package com.couponPayment.entity.mapper;

import com.couponPayment.entity.TransactionInfoTb;
import com.couponPayment.entity.dto.TransactionInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserInfoMapperHelper.class, StoreInfoMapperHelper.class,
        MyWalletInfoMapperHelper.class, WalletReqMapperHelper.class})
public interface TransactionMapper {
    @Mapping(source="userInfoId", target = "userInfoTb", qualifiedByName = "mapUserInfo")
    @Mapping(source="storeInfoId", target = "storeInfoTb", qualifiedByName = "mapStoreInfo")
    @Mapping(source="myWalletInfoId", target = "myWalletInfoTb", qualifiedByName = "mapMyWalletInfo")
    @Mapping(source="walletReqId", target = "walletReqTb", qualifiedByName = "mapWalletReq")
    TransactionInfoTb toEntity(TransactionInfoDto transactionInfoDto);

    @Mapping(source="myWalletInfoTb.id", target = "myWalletInfoId")
    @Mapping(source="walletReqTb.id", target = "walletReqId")
    @Mapping(source="storeInfoTb.id", target = "storeInfoId")
    @Mapping(source="userInfoTb.id", target = "userInfoId")
    TransactionInfoDto toDto(TransactionInfoTb transactionInfoTb);
}
