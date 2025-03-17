package com.couponPayment.repository;

import com.couponPayment.dto.TossBillingPaymentRes;
import com.couponPayment.entity.TransactionInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

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

        TossBillingPaymentRes.Card card = TossBillingPaymentRes.Card.builder()
                .installmentPlanMonths(1)
                .approveNo("00000000")
                .build();


        TossBillingPaymentRes tossBillingPaymentRes =
                TossBillingPaymentRes
                .builder()
                .paymentKey("결제 키 값")
                .requestedAt("요청 시간")
                .totalAmount(1000)
                .status("DONE")
                .approvedAt("승인 시간")
                .card(card)
                .build();

        transactionInfo.approvalPayment(tossBillingPaymentRes);

        assertThat(transactionInfo.getInstallment()).isEqualTo(tossBillingPaymentRes.getCard().getInstallmentPlanMonths());
        assertThat(transactionInfo.getApprovalNum()).isEqualTo(tossBillingPaymentRes.getCard().getApproveNo());
        assertThat(transactionInfo.getTranNum()).isEqualTo(tossBillingPaymentRes.getPaymentKey());
        assertThat(transactionInfo.getApprovalAmount()).isEqualTo(tossBillingPaymentRes.getTotalAmount());
        assertThat(transactionInfo.getStatus()).isEqualTo(tossBillingPaymentRes.getStatus());
        assertThat(transactionInfo.getApprovalDt()).isEqualTo(tossBillingPaymentRes.getApprovedAt());
    }
}
