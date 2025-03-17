package com.couponPayment.service;

import com.couponPayment.consts.PaymentStatus;
import com.couponPayment.dto.PaymentReq;
import com.couponPayment.dto.TossBillingPaymentReq;
import com.couponPayment.dto.TossBillingPaymentRes;
import com.couponPayment.entity.MyWalletInfo;
import com.couponPayment.entity.StoreInfo;
import com.couponPayment.entity.TransactionInfo;
import com.couponPayment.entity.UserInfo;
import com.couponPayment.entity.dto.TransactionInfoDto;
import com.couponPayment.entity.mapper.TransactionMapper;
import com.couponPayment.repository.MyWalletInfoRepository;
import com.couponPayment.repository.TransactionInfoRepository;
import com.couponPayment.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private UserInfoRepository userInfoRepository;

    @Mock
    private MyWalletInfoRepository myWalletInfoRepository;

    @Mock
    private TransactionInfoRepository transactionInfoRepository;

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
        if(tossBillingPaymentRes.getStatus().equals(PaymentStatus.DONE.name())){
            transactionInfo.approvalPayment(tossBillingPaymentRes);
        }

    }
}
