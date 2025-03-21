package com.couponPayment.entity.mapper;

import com.couponPayment.dto.TossBillingPaymentCancelRes;
import com.couponPayment.dto.TossBillingPaymentRes;
import com.couponPayment.entity.TransactionInfo;
import com.couponPayment.entity.dto.TransactionInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(source="userInfoId", target = "userInfo.id")
    @Mapping(source="storeInfoId", target = "storeInfo.id")
    @Mapping(source="myWalletInfoId", target = "myWalletInfo.id")
    @Mapping(source="walletReqId", target = "walletReq.id")
    TransactionInfo toEntity(TransactionInfoDto transactionInfoDto);

    @Mapping(source= "myWalletInfo.id", target = "myWalletInfoId")
    @Mapping(source= "walletReq.id", target = "walletReqId")
    @Mapping(source= "storeInfo.id", target = "storeInfoId")
    @Mapping(source= "userInfo.id", target = "userInfoId")
    TransactionInfoDto toDto(TransactionInfo transactionInfo);

    @Mapping(source = "paymentKey", target = "tranNum")
    @Mapping(source = "totalAmount", target = "approvalAmount")
    @Mapping(source = "approvedAt", target = "approvalDt")
    @Mapping(source = "card.approveNo", target = "approvalNum")
    @Mapping(source = "card.installmentPlanMonths", target = "installment")
    @Mapping(source = "status", target = "status")
    TransactionInfo toTransactionInfoApproval(TossBillingPaymentRes res, @MappingTarget TransactionInfo transactionInfo);

    @Mapping(source = "cancelAmount", target = "cancelAmount")
    @Mapping(source = "canceledAt", target = "cancelDt")
    @Mapping(source = "cancelStatus", target = "status")
    TransactionInfo toTransactionInfoCancel(TossBillingPaymentCancelRes.Cancels res, @MappingTarget TransactionInfo transactionInfo);

}
