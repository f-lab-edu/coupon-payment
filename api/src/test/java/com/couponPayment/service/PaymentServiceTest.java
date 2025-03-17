package com.couponPayment.service;

import com.couponPayment.dto.PaymentReq;
import com.couponPayment.dto.TossBillingPaymentReq;
import com.couponPayment.entity.MyWalletInfo;
import com.couponPayment.entity.StoreInfo;
import com.couponPayment.entity.UserInfo;
import com.couponPayment.repository.MyWalletInfoRepository;
import com.couponPayment.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private UserInfoRepository userInfoRepository;

    @Mock
    private MyWalletInfoRepository myWalletInfoRepository;

    @Value("${toss.paymentUrl}")
    private String paymentUrl;


    @Test
    public void payment() {
        PaymentReq paymentReq = PaymentReq
                .builder()
                .merchantId("bbq")
                .merchantMemberId("young")
                .orderNum("orderNum")
                .installment(0)
                .cardId("cardId")
                .amount(2000)
                .build();

        StoreInfo storeInfo = new StoreInfo(
                null, "bbq", "toss", null, null);
        UserInfo userInfo = new UserInfo(null, storeInfo, "young", "010", "naver.com", 0, "young",
                "1234", null, null);

        /*MyWalletInfo myWalletInfo = myWalletInfoRepository.findById(1L).get();
        myWalletInfo.getUserInfo().getStoreInfo().getTossPaymentId();*/
        when(myWalletInfoRepository.findByCardIdAndUseFlag("cardId", 0))
                .thenReturn(Optional.of(new MyWalletInfo(null, userInfo, "cardId", "NH", "123", "1", "5", "2", "3", "4", 0,null)));
        
        //사용 중 카드 찾기
        MyWalletInfo myWalletInfo = myWalletInfoRepository.findByCardIdAndUseFlag("cardId", 0).get();

        String tossPaymentId = myWalletInfo.getUserInfo().getStoreInfo().getTossPaymentId();
        String customerKey = myWalletInfo.getUserInfo().getCustomerKey();

        TossBillingPaymentReq tossBillingPaymentReq = TossBillingPaymentReq
                .builder()
                .amount(1000)
                .customerKey(customerKey)
                .secretKey(tossPaymentId)
                .orderId("orderId10")
                .orderName("orderName")
                .build();

        //모킹으로 서비스 로직

    }
}
