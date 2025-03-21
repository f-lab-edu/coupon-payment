package com.couponPayment.repository;

import com.couponPayment.dto.TossBillingPaymentCancelRes;
import com.couponPayment.dto.TossBillingPaymentRes;
import com.couponPayment.entity.TransactionInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
class TransactionInfoRepositoryTest {
    @Autowired
    private TransactionInfoRepository transactionInfoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void resetAutoIncrement() {
        testEntityManager.getEntityManager()
                .createNativeQuery("ALTER TABLE transactionInfo ALTER COLUMN transactionInfoId RESTART WITH 1")
                .executeUpdate();
    }

    @Test
    public void save() {
        TransactionInfo transactionInfo = new TransactionInfo(
                null,  // ID (자동 생성)
                null,
                null,
                null,
                null,
                "tranNum",
                "2025-03-10T09:23:27+09:00",
                1000,
                1000,
                "2025-03-10T09:23:27+09:00",
                "approvalNum",
                1000,
                "2025-03-10T09:23:27+09:00",
                0,
                "https://www.naver.com",
                "DONE"
        );

        transactionInfoRepository.save(transactionInfo);

        assertThat(transactionInfo.getId()).isEqualTo(1L);
        assertThat(transactionInfo.getTranNum()).isEqualTo("tranNum");
        assertThat(transactionInfo.getRequestDt()).isEqualTo("2025-03-10T09:23:27+09:00");
        assertThat(transactionInfo.getApprovalAmount()).isEqualTo(1000);
        assertThat(transactionInfo.getApprovalDt()).isEqualTo("2025-03-10T09:23:27+09:00");
        assertThat(transactionInfo.getCallbackUrl()).isEqualTo("https://www.naver.com");

    }

    @Test
    public void findById() {
        TransactionInfo transactionInfo = new TransactionInfo(
                null,  // ID (자동 생성)
                null,
                null,
                null,
                null,
                "tranNum",
                "2025-03-10T09:23:27+09:00",
                1000,
                1000,
                "2025-03-10T09:23:27+09:00",
                "approvalNum",
                1000,
                "2025-03-10T09:23:27+09:00",
                0,
                "https://www.naver.com",
                "DONE"
        );

        transactionInfoRepository.save(transactionInfo);

        transactionInfo = transactionInfoRepository.findById(1L).get();
        assertThat(transactionInfo.getId()).isEqualTo(1L);
        assertThat(transactionInfo.getTranNum()).isEqualTo("tranNum");
        assertThat(transactionInfo.getRequestDt()).isEqualTo("2025-03-10T09:23:27+09:00");
        assertThat(transactionInfo.getApprovalAmount()).isEqualTo(1000);
        assertThat(transactionInfo.getApprovalDt()).isEqualTo("2025-03-10T09:23:27+09:00");
        assertThat(transactionInfo.getCallbackUrl()).isEqualTo("https://www.naver.com");
    }

    @Test
    public void approvalPayment() {
        TransactionInfo transactionInfo = new TransactionInfo(
                null,  // ID (자동 생성)
                null,
                null,
                null,
                null,
                "PK123456",
                "2025-03-10T09:23:27+09:00",
                1000,
                100000,
                "2025-03-10T09:23:27+09:00",
                "approvalNum",
                1000,
                "2025-03-10T09:23:27+09:00",
                0,
                "https://www.naver.com",
                "DONE"
        );
        transactionInfoRepository.save(transactionInfo);

        /*TossBillingPaymentRes.Card card = TossBillingPaymentRes.Card.builder()
                .installmentPlanMonths(1)
                .approveNo("00000000")
                .build();*/
        TossBillingPaymentRes.Card card = new TossBillingPaymentRes.Card("company", "issueCd", "acCd", "number", 0, false, "payer", "apNo", false, "cdType"
                , "ownerType", "acStatus", "reUrl", "provider", 1000);


        TossBillingPaymentRes tossBillingPaymentRes = new TossBillingPaymentRes(
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
        );

        //transactionInfo.approvalPayment(tossBillingPaymentRes);
        transactionInfo.setTranNum(tossBillingPaymentRes.getPaymentKey());
        transactionInfo.setApprovalAmount(tossBillingPaymentRes.getTotalAmount());
        transactionInfo.setApprovalDt(tossBillingPaymentRes.getApprovedAt());
        transactionInfo.setApprovalNum(tossBillingPaymentRes.getCard().getApproveNo());
        transactionInfo.setInstallment(tossBillingPaymentRes.getCard().getInstallmentPlanMonths());
        transactionInfo.setStatus(tossBillingPaymentRes.getStatus());

        assertThat(transactionInfo.getInstallment()).isEqualTo(tossBillingPaymentRes.getCard().getInstallmentPlanMonths());
        assertThat(transactionInfo.getApprovalNum()).isEqualTo(tossBillingPaymentRes.getCard().getApproveNo());
        assertThat(transactionInfo.getTranNum()).isEqualTo(tossBillingPaymentRes.getPaymentKey());
        assertThat(transactionInfo.getApprovalAmount()).isEqualTo(tossBillingPaymentRes.getTotalAmount());
        assertThat(transactionInfo.getStatus()).isEqualTo(tossBillingPaymentRes.getStatus());
        assertThat(transactionInfo.getApprovalDt()).isEqualTo(tossBillingPaymentRes.getApprovedAt());
    }

    @Test
    public void cancelPayment() {
        TransactionInfo transactionInfo = new TransactionInfo(
                null,  // ID (자동 생성)
                null,
                null,
                null,
                null,
                "tranNum",
                "2025-03-10T09:23:27+09:00",
                1000,
                1000,
                "2025-03-10T09:23:27+09:00",
                "approvalNum",
                1000,
                "2025-03-10T09:23:27+09:00",
                0,
                "https://www.naver.com",
                "DONE"
        );
        transactionInfoRepository.save(transactionInfo);

        List<TossBillingPaymentCancelRes.Cancels> cancels = new ArrayList<>();
        /*TossBillingPaymentCancelRes.Cancels cancel = TossBillingPaymentCancelRes.Cancels
                .builder()
                .canceledAt("2024-02-13T12:20:23+09:00")
                .cancelAmount(1000)
                .cancelStatus("DONE")
                .build();*/

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

        /*TossBillingPaymentCancelRes tossBillingPaymentRes = TossBillingPaymentCancelRes
                .builder()
                .cancels(cancels)
                .build();*/
        TossBillingPaymentCancelRes tossBillingPaymentCancelRes = new TossBillingPaymentCancelRes(cancels);

        transactionInfo.setCancelAmount(tossBillingPaymentCancelRes.getCancels().get(0).getCancelAmount());
        transactionInfo.setCancelDt(tossBillingPaymentCancelRes.getCancels().get(0).getCanceledAt());
        transactionInfo.setStatus(tossBillingPaymentCancelRes.getCancels().get(0).getCancelStatus());

        assertThat(transactionInfo.getCancelDt().equals(cancel.getCanceledAt()));
        assertThat(transactionInfo.getCancelAmount().equals(cancel.getCancelAmount()));
        assertThat(transactionInfo.getStatus().equals(cancel.getCancelStatus()));

    }

    @Test
    public void findByTranNumAndStatus() {
        TransactionInfo transactionInfo = new TransactionInfo(
                null,  // ID (자동 생성)
                null,
                null,
                null,
                null,
                "tranNum",
                "2025-03-10T09:23:27+09:00",
                1000,
                1000,
                "2025-03-10T09:23:27+09:00",
                "approvalNum",
                1000,
                "2025-03-10T09:23:27+09:00",
                0,
                "https://www.naver.com",
                "DONE"
        );
        transactionInfoRepository.save(transactionInfo);

        transactionInfo = transactionInfoRepository.findByTranNumAndStatus("tranNum", "DONE").get();
        assertThat(transactionInfo.getTranNum()).isEqualTo("tranNum");
        assertThat(transactionInfo.getStatus()).isEqualTo("DONE");
    }
}
