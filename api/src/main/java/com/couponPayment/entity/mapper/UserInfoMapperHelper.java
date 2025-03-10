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
        if (userInfoId == null) {
            return null;  // JPA 예외 발생 방지
        }

        return userInfoRepository.findById(userInfoId).orElse(null);
    }
}
