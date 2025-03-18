package com.couponPayment.service;

import com.couponPayment.config.TossCommonHeaderConfig;
import com.couponPayment.dto.TossBillingPaymentCancelReq;
import com.couponPayment.dto.TossBillingPaymentCancelRes;
import com.couponPayment.dto.TossBillingPaymentReq;
import com.couponPayment.dto.TossBillingPaymentRes;
import com.couponPayment.dto.TossBillingReq;
import com.couponPayment.dto.TossBillingRes;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Base64;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TossBillingServiceTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Value("${toss.secretKey}")
    private String secretTossKey;
    @Value("${toss.paymentUrl}")
    private String paymentUrl;
    @Value("${toss.paymentCancelUrl}")
    private String paymentCancelUrl;

    @Test
    public void getBillingKey(){
        //http://localhost:8081/api/v1/toss/billing/success?customerKey=xFl5CrMdIL&authKey=bln_p6XnzaNmdEB
        //sucess요청이 올때마다 값이 달라지는데 어떻게 테스트 코드를 짜야할까?....

        System.out.println(secretTossKey);
        TossBillingReq tossBillingReq = TossBillingReq
                .builder()
                .customerKey("Y5zNpssBs1")
                .secretKey(secretTossKey)
                .authKey("bln_5KnkWkREXW1")
                .build();

        // Authorization에 사용될 시크릿 키
        String secretKey = secretTossKey;

        HttpHeaders headers = TossCommonHeaderConfig.tossHeader(secretKey);
        // HttpEntity를 통해 요청 본문과 헤더를 설정
        HttpEntity<TossBillingReq> entity = new HttpEntity<>(tossBillingReq, headers);

        ResponseEntity<TossBillingRes> tossBillingRes =
                testRestTemplate.exchange("https://api.tosspayments.com/v1/billing/authorizations/issue",
                    HttpMethod.POST,
                    entity,
                        TossBillingRes.class
                );

        System.out.println(tossBillingRes.getBody());
        //TossBillingRes(mId=null, customerKey=Y5zNpssBs1, authenticatedAt=2025-03-06T16:58:17+09:00, method=카드, billingKey=Hy_l357saUspeqnf2eoePY84C6p9dUGR7-q1hT8EZDo=,
        // card=TossBillingRes.CardInfo(issuerCode=91, acquirerCode=91, number=94411702****274*, cardType=체크, ownerType=개인),
        // cardCompany=농협, cardNumber=94411702****274*)
    }


    @Test
    public void payment(){
        String customerKey = "Y5zNpssBs1";
        String billingKey = "Hy_l357saUspeqnf2eoePY84C6p9dUGR7-q1hT8EZDo=";
        TossBillingPaymentReq tossBillingPaymentReq = TossBillingPaymentReq
                .builder()
                .customerKey(customerKey)
                .amount(1000)
                .cardId(billingKey)
                .secretKey(secretTossKey)
                .orderId("orderId11")
                .orderName("orderName")
                .build();
        //
        String url = paymentUrl+billingKey;

        // Authorization에 사용될 시크릿 키
        String secretKey = secretTossKey;

        HttpHeaders headers = TossCommonHeaderConfig.tossHeader(secretKey);

        // HttpEntity를 통해 요청 본문과 헤더를 설정
        HttpEntity<TossBillingPaymentReq> entity = new HttpEntity<>(tossBillingPaymentReq, headers);

        ResponseEntity<TossBillingPaymentRes> tossBillingPaymentResResponse =
                testRestTemplate.exchange(url,
                        HttpMethod.POST,
                        entity,
                        TossBillingPaymentRes.class
                );
        //TossBillingPaymentRes(mId=tvivarepublica2, lastTransactionKey=txrd_a01jpkyan6sg1k1ma4h4b8anhhq, paymentKey=tviva20250318151619bNOC4, orderId=orderId11, orderName=orderName, taxExemptionAmount=0, status=DONE, requestedAt=2025-03-18T15:16:19+09:00, approvedAt=2025-03-18T15:16:19+09:00, useEscrow=false, cultureExpense=false, type=BILLING, country=KR, isPartialCancelable=false, transactionKey=null, currency=KRW, totalAmount=1000, balanceAmount=1000, suppliedAmount=909, vat=91, taxFreeAmount=0, method=카드, version=2022-11-16, receipt=TossBillingPaymentRes.Receipt(url=https://dashboard.tosspayments.com/receipt/redirection?transactionId=tviva20250318151619bNOC4&ref=PX), checkout=TossBillingPaymentRes.Checkout(url=https://api.tosspayments.com/v1/payments/tviva20250318151619bNOC4/checkout), card=TossBillingPaymentRes.Card(company=null, issuerCode=91, acquirerCode=91, number=94411702****274*, installmentPlanMonths=0, isInterestFree=false, interestPayer=null, approveNo=00000000, useCardPoint=false, cardType=체크, ownerType=개인, acquireStatus=READY, receiptUrl=null, provider=null, amount=1000), failure=null, code=null, message=null)
        System.out.println(tossBillingPaymentResResponse.getBody());
    }

    @Test
    public void paymentCancel(){

        TossBillingPaymentCancelReq tossBillingPaymentCancelReq = TossBillingPaymentCancelReq
                .builder()
                .paymentKey("tviva20250311153221DLaG0")
                .cancelReason("취소 이유")
                .build();
        //
        String url = paymentCancelUrl.replace("{paymentKey}", tossBillingPaymentCancelReq.getPaymentKey());

        // Authorization에 사용될 시크릿 키
        String secretKey = secretTossKey;

        // Basic 인증 헤더 생성 (secretKey: 뒤에 :을 붙여서 base64로 인코딩)
        HttpHeaders headers = TossCommonHeaderConfig.tossHeader(secretKey);
        // HttpEntity를 통해 요청 본문과 헤더를 설정
        HttpEntity<TossBillingPaymentCancelReq> entity = new HttpEntity<>(tossBillingPaymentCancelReq, headers);

        ResponseEntity<TossBillingPaymentCancelRes> tossBillingPaymentCancelRes =
                testRestTemplate.exchange(url,
                        HttpMethod.POST,
                        entity,
                        TossBillingPaymentCancelRes.class
                );

        System.out.println(tossBillingPaymentCancelRes.getBody());

    }
}