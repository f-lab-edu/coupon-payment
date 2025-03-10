package com.couponPayment.repository;

import com.couponPayment.entity.UserInfoTb;
import com.couponPayment.entity.dto.UserInfoDto;
import com.couponPayment.entity.mapper.UserInfoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class UserInfoRepositoryTest extends CommonReposity{
    @Autowired
    private UserInfoRepository userInfoRepository;
    @MockitoBean
    private UserInfoMapper userInfoMapper;

    private UserInfoTb userInfoTb;
    private UserInfoDto userInfoDto;

    @BeforeEach
    public void setting(){
        userInfoDto = UserInfoDto
                .builder()
                .name("young")
                .phone("010")
                .mail("naver.com")
                .useFlag(0)
                .storeInfoId(null)
                .build();
        userInfoTb = new UserInfoTb(null,null,userInfoDto.getName(),userInfoDto.getPhone(),userInfoDto.getMail(),userInfoDto.getUseFlag(),null,null);

        when(userInfoMapper.toEntity(userInfoDto)).thenReturn(userInfoTb);
        when(userInfoMapper.toDto(userInfoTb)).thenReturn(userInfoDto);

        userInfoTb = userInfoRepository.save(userInfoMapper.toEntity(userInfoDto));
        assertThat(userInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("storeInfoId")
                .isEqualTo(userInfoTb);
    }

    @Test
    public void findById(){
        userInfoTb = userInfoRepository.findById(1L).get();
        userInfoDto = userInfoMapper.toDto(userInfoTb);
        // 검증
        assertThat(userInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("storeInfoId")
                .isEqualTo(userInfoTb);
    }
}