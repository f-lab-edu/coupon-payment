package com.couponPayment.service;

import com.couponPayment.consts.CommonResult;
import com.couponPayment.consts.PaymentStatus;
import com.couponPayment.dto.ApiResponse;
import com.couponPayment.dto.PaymentCancelReq;
import com.couponPayment.dto.PaymentCancelRes;
import com.couponPayment.dto.PaymentReq;
import com.couponPayment.dto.PaymentRes;
import com.couponPayment.dto.TossBillingPaymentCancelReq;
import com.couponPayment.dto.TossBillingPaymentCancelRes;
import com.couponPayment.dto.TossBillingPaymentReq;
import com.couponPayment.dto.TossBillingPaymentRes;
import com.couponPayment.entity.MyWalletInfo;
import com.couponPayment.entity.StoreInfo;
import com.couponPayment.entity.TransactionInfo;
import com.couponPayment.entity.UserInfo;
import com.couponPayment.entity.dto.TransactionInfoDto;
import com.couponPayment.entity.mapper.TransactionMapper;
import com.couponPayment.repository.MyWalletInfoRepository;
import com.couponPayment.repository.StoreInfoRepository;
import com.couponPayment.repository.TransactionInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
        /*PaymentReq paymentReq = PaymentReq
                .builder()
                .merchantId("bbq")
                .merchantMemberId("young")
                .orderNum("orderNum")
                .orderId("orderId1")
                .installment(0)
                .cardId("BillingKey")
                .amount(2000)
                .build();*/
        PaymentReq paymentReq = new PaymentReq("bbq", "young", "orderNum", "orderId1", "BillingKey", 0, 2000);
        StoreInfo storeInfo = new StoreInfo(
                1L, "bbq", "toss", null, null);
        UserInfo userInfo = new UserInfo(1L, storeInfo, "young", "010", "naver.com", 0, "young",
                "1234", null, null);

        /*MyWalletInfo myWalletInfo = myWalletInfoRepository.findById(1L).get();
        myWalletInfo.getUserInfo().getStoreInfo().getTossPaymentId();*/
        when(myWalletInfoRepository.findByCardIdAndUseFlag("cardId", 0))
                .thenReturn(Optional.of(new MyWalletInfo(2L, userInfo, "cardId", "NH", "123", "1", "5", "3", "4", 0, null)));

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
        /*TossBillingPaymentRes.Card card = TossBillingPaymentRes.Card.builder()
                .installmentPlanMonths(0)
                .number("1234")
                .approveNo("00000000")
                .build();
*/
        TossBillingPaymentRes.Card card = new TossBillingPaymentRes.Card("company", "issueCd", "acCd", "number", 0, false, "payer", "apNo", false, "cdType"
                , "ownerType", "acStatus", "reUrl", "provider", 1000);

        when(tossBillingService.billingPayment(tossBillingPaymentReq)).thenReturn(
                new TossBillingPaymentRes(
                        "MID123456",
                        "LTK123456",
                        "PK123456",
                        "ORDER123456",
                        "Test Order",
                        0,
                        "DONE",
                        "2025-03-20T12:00:00",
                        "2025-03-20T12:05:00",
                        false,
                        false,
                        "CARD",
                        "KR",
                        true,
                        "TK123456",
                        "KRW",
                        100000,
                        50000,
                        90000,
                        10000,
                        0,
                        "CreditCard",
                        "1.0",
                        new TossBillingPaymentRes.Receipt("https://test-receipt.com"),
                        new TossBillingPaymentRes.Checkout("https://test-checkout.com"),
                        card,
                        new TossBillingPaymentRes.Failure("ERROR123", "Transaction Failed"),
                        "200",
                        "Success"
                )
        );

        //결제 성공 로직
        TossBillingPaymentRes tossBillingPaymentRes =
                tossBillingService.billingPayment(tossBillingPaymentReq);

        //결제 성공
        if (tossBillingPaymentRes.getStatus().equals(PaymentStatus.DONE.name())) {
            transactionMapper.toTransactionInfoApproval(tossBillingPaymentRes, transactionInfo);
        }

        /*PaymentRes paymentRes = PaymentRes
                .builder()
                .merchantId(paymentReq.getMerchantId())
                .merchantMemberId(paymentReq.getMerchantMemberId())
                .orderNum(paymentReq.getOrderNum())
                .cardCompany(myWalletInfo.getCardCompany())
                .cardNum(tossBillingPaymentRes.getCard().getNumber())
                .approvalAmount(tossBillingPaymentRes.getCard().getAmount())
                .approvalNum(tossBillingPaymentRes.getCard().getApproveNo())
                .approvalDate(tossBillingPaymentRes.getApprovedAt())
                .tranNum(tossBillingPaymentRes.getPaymentKey())
                .resultCode(CommonResult.E0000.getCode())
                .resultMessage(CommonResult.E0000.getMessage())
                .build();*/

        PaymentRes paymentRes = new PaymentRes(
                paymentReq.getMerchantId(),
                paymentReq.getMerchantMemberId(),
                paymentReq.getOrderNum(),
                myWalletInfo.getCardCompany(),
                tossBillingPaymentRes.getCard().getNumber(),
                tossBillingPaymentRes.getCard().getAmount(),
                tossBillingPaymentRes.getCard().getApproveNo(),
                tossBillingPaymentRes.getApprovedAt(),
                tossBillingPaymentRes.getPaymentKey()
                );
        ApiResponse.of(CommonResult.E0000, paymentRes);

        assertThat(tossBillingPaymentRes.getPaymentKey()).isEqualTo(transactionInfo.getTranNum());
        assertThat(tossBillingPaymentRes.getTotalAmount()).isEqualTo(transactionInfo.getApprovalAmount());
        assertThat(tossBillingPaymentRes.getApprovedAt()).isEqualTo(transactionInfo.getApprovalDt());
        assertThat(tossBillingPaymentRes.getCard().getApproveNo()).isEqualTo(transactionInfo.getApprovalNum());
        assertThat(tossBillingPaymentRes.getCard().getInstallmentPlanMonths()).isEqualTo(transactionInfo.getInstallment());
        assertThat(tossBillingPaymentRes.getStatus()).isEqualTo(transactionInfo.getStatus());
        System.out.println(ApiResponse.of(CommonResult.E0000, paymentRes));
    }

    @Test
    public void paymentCancel() {
        PaymentCancelReq paymentCancelReq = new PaymentCancelReq("bbq", "young", "tranNum");

        StoreInfo storeInfo = new StoreInfo(
                1L, "bbq", "toss", null, null);
        UserInfo userInfo = new UserInfo(1L, storeInfo, "young", "010", "naver.com", 0, "young",
                "1234", null, null);
        MyWalletInfo myWalletInfo = new MyWalletInfo(2L, userInfo, "cardId", "NH", "123", "1", "5", "3", "4", 0, null);

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
        when(transactionInfoRepository.findByTranNumAndStatus(paymentCancelReq.getTranNum(), "DONE"))
                .thenReturn(Optional.of(transactionInfo));

        transactionInfo = transactionInfoRepository.findByTranNumAndStatus("tranNum", "DONE").get();

        if (!transactionInfo.getTranNum().equals(paymentCancelReq.getTranNum())) {

            return;
        }
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

        /*TossBillingPaymentCancelReq tossBillingPaymentCancelReq = TossBillingPaymentCancelReq
                .builder()
                .paymentKey(transactionInfo.getTranNum())
                .secretKey(storeInfo.getTossPaymentId())
                .cancelReason("취소 이유")
                .build();*/
        TossBillingPaymentCancelReq tossBillingPaymentCancelReq = new TossBillingPaymentCancelReq(transactionInfo.getTranNum(),"취소 이유",storeInfo.getTossPaymentId());

        List<TossBillingPaymentCancelRes.Cancels> cancels = new ArrayList<>();
        TossBillingPaymentCancelRes.Cancels cancel = new TossBillingPaymentCancelRes.Cancels(
                "TK123456",
                "Customer Request",
                0,
                "2024-02-13T12:20:23+09:00",
                500,
                200,
                "RECEIPT123",
                "DONE",
                "REQ123456",
                1000,
                0,
                800
        );
        cancels.add(cancel);

        when(tossBillingService.billingPaymentCancel(tossBillingPaymentCancelReq))
                .thenReturn(
                        new TossBillingPaymentCancelRes(cancels)
                );

        //결제 취소 성공 로직
        TossBillingPaymentCancelRes tossBillingPaymentCancelRes =
                tossBillingService.billingPaymentCancel(tossBillingPaymentCancelReq);

        //분할 취소를 위해 list로 있지만 내 서비스에 분할 취소는 없다
        if (tossBillingPaymentCancelRes.getCancels().get(0).getCancelStatus().equals(PaymentStatus.DONE.name())) {
            transactionMapper.toTransactionInfoCancel(tossBillingPaymentCancelRes.getCancels().get(0), cancelTransactionInfo);
        }

        assertThat(cancel.getCanceledAt()).isEqualTo(cancelTransactionInfo.getCancelDt());
        assertThat(cancel.getCancelAmount()).isEqualTo(cancelTransactionInfo.getCancelAmount());
        assertThat(cancel.getCancelStatus()).isEqualTo(cancelTransactionInfo.getStatus());

        /*PaymentCancelRes paymentCancelRes = PaymentCancelRes
                .builder()
                .merchantId(storeInfo.getMerchantId())
                .merchantMemberId(userInfo.getMerchantMemberId())
                .tranNum(tossBillingPaymentCancelRes.getPaymentKey())
                .orderNum(tossBillingPaymentCancelRes.getOrderName())
                .cancelAmount(tossBillingPaymentCancelRes.getCancels().get(0).getCancelAmount())
                .cancelDate(tossBillingPaymentCancelRes.getCancels().get(0).getCanceledAt())
                .resultCode(CommonResult.E0000.getCode())
                .resultMessage(CommonResult.E0000.getMessage())
                .build();*/
        PaymentCancelRes paymentCancelRes = new PaymentCancelRes(
                storeInfo.getMerchantId(),
                userInfo.getMerchantMemberId(),
                tossBillingPaymentCancelRes.getPaymentKey(),
                tossBillingPaymentCancelRes.getOrderName(),
                tossBillingPaymentCancelRes.getCancels().get(0).getCancelAmount(),
                tossBillingPaymentCancelRes.getCancels().get(0).getCanceledAt()
        );

        ApiResponse.of(CommonResult.E0000, paymentCancelRes);
        System.out.println(ApiResponse.of(CommonResult.E0000, paymentCancelRes));
    }
}
