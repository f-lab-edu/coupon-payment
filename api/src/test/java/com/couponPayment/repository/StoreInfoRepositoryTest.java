package com.couponPayment.repository;

import com.couponPayment.entity.StoreInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StoreInfoRepositoryTest{
    @Autowired
    private StoreInfoRepository storeInfoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void resetAutoIncrement() {
        testEntityManager.getEntityManager()
                .createNativeQuery("ALTER TABLE storeInfo ALTER COLUMN storeInfoId RESTART WITH 1")
                .executeUpdate();
    }

    @Test
    public void save() {
        StoreInfo storeInfo = new StoreInfo(
                null,"bbq","toss",null,null);

        storeInfoRepository.save(storeInfo);

        assertThat(storeInfo.getId()).isEqualTo(1L);
        assertThat(storeInfo.getMerchantId()).isEqualTo("bbq");
        assertThat(storeInfo.getTossPaymentId()).isEqualTo("toss");
    }

    @Test
    public void findById() {
        StoreInfo storeInfo = new StoreInfo(
                null,"bbq","toss",null,null);

        storeInfoRepository.save(storeInfo);

        storeInfo = storeInfoRepository.findById(1L).get();

        assertThat(storeInfo.getId()).isEqualTo(1L);
        assertThat(storeInfo.getMerchantId()).isEqualTo("bbq");
        assertThat(storeInfo.getTossPaymentId()).isEqualTo("toss");
    }
}