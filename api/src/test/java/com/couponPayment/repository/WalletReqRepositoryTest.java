package com.couponPayment.repository;

import com.couponPayment.entity.WalletReq;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class WalletReqRepositoryTest {
    @Autowired
    private WalletReqRepository walletReqRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void resetAutoIncrement() {
        testEntityManager.getEntityManager()
                .createNativeQuery("ALTER TABLE walletReq ALTER COLUMN walletReqId RESTART WITH 1")
                .executeUpdate();
    }


    @Test
    public void save() {
        WalletReq walletReq = new WalletReq(null, null, "bbq", "young",
                "orderId", "orderNum", 1000);

        walletReqRepository.save(walletReq);

        assertThat(walletReq.getId()).isEqualTo(1L);
        assertThat(walletReq.getMerchantId()).isEqualTo("bbq");
        assertThat(walletReq.getMerchantMemberId()).isEqualTo("young");
        assertThat(walletReq.getOrderId()).isEqualTo("orderId");
        assertThat(walletReq.getOrderNum()).isEqualTo("orderNum");
    }

    @Test
    public void findById() {
        WalletReq walletReq = new WalletReq(null, null, "bbq", "young",
                "orderId", "orderNum", 1000);
        walletReqRepository.save(walletReq);

        walletReq = walletReqRepository.findById(1L).get();

        assertThat(walletReq.getId()).isEqualTo(1L);
        assertThat(walletReq.getMerchantId()).isEqualTo("bbq");
        assertThat(walletReq.getMerchantMemberId()).isEqualTo("young");
        assertThat(walletReq.getOrderId()).isEqualTo("orderId");
        assertThat(walletReq.getOrderNum()).isEqualTo("orderNum");
    }
}