package com.couponPayment.repository;

import com.couponPayment.entity.MyWalletInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MyWalletInfoRepositoryTest {
    @Autowired
    private MyWalletInfoRepository myWalletInfoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void resetAutoIncrement() {
        testEntityManager.getEntityManager()
                .createNativeQuery("ALTER TABLE myWalletInfo ALTER COLUMN myWalletInfoId RESTART WITH 1")
                .executeUpdate();
    }

    @Test
    public void save() {
        MyWalletInfo myWalletInfo = new MyWalletInfo(null, null, "cardId", "NH", "123", "1", "5", "2", "3", "4", null);
        myWalletInfoRepository.save(myWalletInfo);

        assertThat(myWalletInfo.getId()).isEqualTo(1L);
        assertThat(myWalletInfo.getCardId()).isEqualTo("cardId");
        assertThat(myWalletInfo.getCardCompany()).isEqualTo("NH");
        assertThat(myWalletInfo.getCardNumber()).isEqualTo("123");
    }

    @Test
    public void findById() {
        MyWalletInfo myWalletInfo = new MyWalletInfo(null, null, "cardId", "NH", "123", "1", "5", "2", "3", "4", null);
        myWalletInfoRepository.save(myWalletInfo);

        myWalletInfo = myWalletInfoRepository.findById(1L).get();

        assertThat(myWalletInfo.getId()).isEqualTo(1L);
        assertThat(myWalletInfo.getCardId()).isEqualTo("cardId");
        assertThat(myWalletInfo.getCardCompany()).isEqualTo("NH");
        assertThat(myWalletInfo.getCardNumber()).isEqualTo("123");
    }

}