package com.couponPayment.repository;

import com.couponPayment.entity.TransactionInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

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
                "2025-03-10T09:23:27+09:00",
                "approvalNum",
                1000,
                "2025-03-10T09:23:27+09:00",
                0,
                "https://www.naver.com"
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
                "2025-03-10T09:23:27+09:00",
                "approvalNum",
                1000,
                "2025-03-10T09:23:27+09:00",
                0,
                "https://www.naver.com"
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
}
