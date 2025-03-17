package com.couponPayment.service;

import com.couponPayment.consts.PaymentStatus;
import com.couponPayment.dto.*;
import com.couponPayment.entity.MyWalletInfo;
import com.couponPayment.entity.StoreInfo;
import com.couponPayment.entity.TransactionInfo;
import com.couponPayment.entity.UserInfo;
import com.couponPayment.entity.dto.TransactionInfoDto;
import com.couponPayment.entity.mapper.TransactionMapper;
import com.couponPayment.repository.MyWalletInfoRepository;
import com.couponPayment.repository.StoreInfoRepository;
import com.couponPayment.repository.TransactionInfoRepository;
import com.couponPayment.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private MyWalletInfoRepository myWalletInfoRepository;

    @Mock
    private TransactionInfoRepository transactionInfoRepository;

    @Mock
    private StoreInfoRepository storeInfoRepository;

    @Mock
    private TossBillingService tossBillingService;
    private TransactionMapper transactionMapper = Mappers.getMapper(TransactionMapper.class);

    @Test
    public void payment() {
        PaymentReq paymentReq = PaymentReq
                .builder()
                .merchantId("bbq")
                .merchantMemberId("young")
                .orderNum("orderNum")
                .orderId("orderId1")
                .installment(0)
                .cardId("BillingKey")
                .amount(2000)
                .build();

        StoreInfo storeInfo = new StoreInfo(
                1L, "bbq", "toss", null, null);
        UserInfo userInfo = new UserInfo(1L, storeInfo, "young", "010", "naver.com", 0, "young",
                "1234", null, null);

        /*MyWalletInfo myWalletInfo = myWalletInfoRepository.findById(1L).get();
        myWalletInfo.getUserInfo().getStoreInfo().getTossPaymentId();*/
        when(myWalletInfoRepository.findByCardIdAndUseFlag("cardId", 0))
                .thenReturn(Optional.of(new MyWalletInfo(2L, userInfo, "cardId", "NH", "123", "1", "5", "2", "3", "4", 0, null)));

        /**요청 온 카드정보 찾기 및 검증
         * ex) 체크카드인데 신용
         */

        MyWalletInfo myWalletInfo = myWalletInfoRepository.findByCardIdAndUseFlag("cardId", 0).get();

        String tossPaymentId = myWalletInfo.getUserInfo().getStoreInfo().getTossPaymentId();
        String customerKey = myWalletInfo.getUserInfo().getCustomerKey();

        //결제 전 Transaction 정보
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        TransactionInfoDto transactionInfoDto = TransactionInfoDto
                .builder()
                .requestDt(formattedDate)
                .amount(1000)
                .myWalletInfoId(myWalletInfo.getId())
                .userInfoId(userInfo.getId())
                .storeInfoId(storeInfo.getId())
                .build();

        when(transactionInfoRepository.save(Mockito.any(TransactionInfo.class)))
                .thenReturn(transactionMapper.toEntity(transactionInfoDto));

        TransactionInfo transactionInfo = transactionInfoRepository.save(transactionMapper.toEntity(transactionInfoDto));

        TossBillingPaymentReq tossBillingPaymentReq = TossBillingPaymentReq
                .builder()
                .amount(1000)
                .cardId(paymentReq.getCardId())
                .customerKey(customerKey)
                .cardInstallmentPlan(paymentReq.getInstallment())
                .secretKey(tossPaymentId)
                .orderId(paymentReq.getOrderId())
                .orderName(paymentReq.getOrderNum())
                .build();

        //모킹으로 서비스 로직
        TossBillingPaymentRes.Card card = TossBillingPaymentRes.Card.builder()
                .installmentPlanMonths(0)
                .approveNo("00000000")
                .build();

        when(tossBillingService.billingPayment(tossBillingPaymentReq)).thenReturn(
                TossBillingPaymentRes
                        .builder()
                        .paymentKey("결제 키 값")
                        .requestedAt("요청 시간")
                        .totalAmount(1000)
                        .status("DONE")
                        .approvedAt("승인 시간")
                        .card(card)
                        .build()
        );

        //결제 성공 로직
        TossBillingPaymentRes tossBillingPaymentRes =
                tossBillingService.billingPayment(tossBillingPaymentReq);

        //결제 성공
        if (tossBillingPaymentRes.getStatus().equals(PaymentStatus.DONE.name())) {
            transactionInfo.approvalPayment(tossBillingPaymentRes);
        }

    }

    @Test
    public void paymentCancel() {
        PaymentCancelReq paymentCancelReq = PaymentCancelReq
                .builder()
                .merchantId("bbq")
                .merchantMemberId("young")
                .tranNum("tranNum")
                .build();

        StoreInfo storeInfo = new StoreInfo(
                1L, "bbq", "toss", null, null);
        UserInfo userInfo = new UserInfo(1L, storeInfo, "young", "010", "naver.com", 0, "young",
                "1234", null, null);
        MyWalletInfo myWalletInfo = new MyWalletInfo(2L, userInfo, "cardId", "NH", "123", "1", "5", "2", "3", "4", 0, null);

        TransactionInfo transactionInfo = new TransactionInfo(
                null,                      // id (초기 저장 시 null)
                myWalletInfo,              // myWalletInfo
                null,                 // walletReq
                storeInfo,                 // storeInfo
                userInfo,                  // userInfo
                "결제 키 값",              // tranNum
                "2025-03-18 00:31:40",     // requestDt
                1000,                      // amount
                1000,                      // approvalAmount
                "승인 시간",               // approvalDt
                "00000000",                // approvalNum
                null,                      //cancelAmount
                null,                      // cancelDt (초기에는 null)
                0,                         // installment
                null,                      // callbackUrl
                "DONE"                    // status
        );

        //결제 상태가 존재하는 Mock
        when(transactionInfoRepository.findByTranNumAndStatus("tranNum","DONE"))
                .thenReturn(Optional.of(transactionInfo));

        transactionInfo = transactionInfoRepository.findByTranNumAndStatus("tranNum","DONE").get();

        /*when(storeInfoRepository.findByMerchantId("toss"))
                .thenReturn(Optional.of(new StoreInfo(
                        null,"bbq","toss",null,null)));

        StoreInfo storeInfo = storeInfoRepository.findByMerchantId("toss").get();*/

        //결제 전 Transaction 정보
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        TransactionInfoDto transactionInfoDto = TransactionInfoDto
                .builder()
                .requestDt(formattedDate)
                .amount(1000)
                .tranNum(transactionInfo.getTranNum())
                .myWalletInfoId(transactionInfo.getMyWalletInfo().getId())
                .userInfoId(transactionInfo.getUserInfo().getId())
                .storeInfoId(transactionInfo.getStoreInfo().getId())
                .build();

        when(transactionInfoRepository.save(Mockito.any(TransactionInfo.class)))
                .thenReturn(transactionMapper.toEntity(transactionInfoDto));

        TransactionInfo cancelTransactionInfo = transactionInfoRepository.save(transactionMapper.toEntity(transactionInfoDto));


        TossBillingPaymentCancelReq tossBillingPaymentCancelReq = TossBillingPaymentCancelReq
                .builder()
                .paymentKey(transactionInfo.getTranNum())
                .secretKey(storeInfo.getTossPaymentId())
                .cancelReason("취소 이유")
                .build();

        List<TossBillingPaymentCancelRes.Cancels> cancels = new ArrayList<>();
        TossBillingPaymentCancelRes.Cancels cancel = TossBillingPaymentCancelRes.Cancels
                .builder()
                .canceledAt("2024-02-13T12:20:23+09:00")
                .cancelAmount(1000)
                .cancelStatus("DONE")
                .build();
        cancels.add(cancel);

        when(tossBillingService.billingPaymentCancel(tossBillingPaymentCancelReq))
                .thenReturn(
                        TossBillingPaymentCancelRes
                                .builder()
                                .cancels(cancels)
                                .build()
                );

        //결제 취소 성공 로직
        TossBillingPaymentCancelRes tossBillingPaymentCancelRes =
                tossBillingService.billingPaymentCancel(tossBillingPaymentCancelReq);

        //분할 취소를 위해 list로 있지만 내 서비스에 분할 취소는 없다
        if(tossBillingPaymentCancelRes.getCancels().get(0).getCancelStatus().equals(PaymentStatus.DONE.name())){
            cancelTransactionInfo.cancelPayment(tossBillingPaymentCancelRes);
        }
    }
}
