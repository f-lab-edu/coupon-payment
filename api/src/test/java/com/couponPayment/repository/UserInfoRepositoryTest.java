package com.couponPayment.repository;

import com.couponPayment.entity.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserInfoRepositoryTest {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void resetAutoIncrement() {
        testEntityManager.getEntityManager()
                .createNativeQuery("ALTER TABLE userInfo ALTER COLUMN userInfoId RESTART WITH 1")
                .executeUpdate();
    }

    @Test
    public void save() {
        UserInfo userInfo = new UserInfo(null, null, "young", "010", "naver.com", 0, null, null);

        userInfoRepository.save(userInfo);

        assertThat(userInfo.getId()).isEqualTo(1L);
        assertThat(userInfo.getName()).isEqualTo("young");
        assertThat(userInfo.getPhone()).isEqualTo("010");
        assertThat(userInfo.getMail()).isEqualTo("naver.com");
        assertThat(userInfo.getUseFlag()).isEqualTo(0);
    }

    @Test
    public void findById() {
        UserInfo userInfo = new UserInfo(null, null, "young", "010", "naver.com", 0, null, null);
        userInfoRepository.save(userInfo);

        userInfo = userInfoRepository.findById(1L).get();

        assertThat(userInfo.getId()).isEqualTo(1L);
        assertThat(userInfo.getName()).isEqualTo("young");
        assertThat(userInfo.getPhone()).isEqualTo("010");
        assertThat(userInfo.getMail()).isEqualTo("naver.com");
        assertThat(userInfo.getUseFlag()).isEqualTo(0);
    }

}