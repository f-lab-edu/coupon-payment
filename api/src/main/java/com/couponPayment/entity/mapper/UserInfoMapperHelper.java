package com.couponPayment.entity.mapper;

import com.couponPayment.entity.StoreInfoTb;
import com.couponPayment.entity.UserInfoTb;
import com.couponPayment.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoMapperHelper {
    private final UserInfoRepository userInfoRepository;

    @Named("mapUserInfo")
    public UserInfoTb mapUserInfo(Long userInfoId){
        return userInfoRepository.findById(userInfoId).orElse(null);
    }
}
