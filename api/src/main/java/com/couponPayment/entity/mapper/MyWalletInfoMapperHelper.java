package com.couponPayment.entity.mapper;

import com.couponPayment.entity.MyWalletInfoTb;
import com.couponPayment.repository.MyWalletInfoRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyWalletInfoMapperHelper {
    private final MyWalletInfoRepository myWalletInfoRepository;

    @Named("mapMyWalletInfo")
    public MyWalletInfoTb mapMyWalletInfo(Long myWalletInfoId){
        if(myWalletInfoId == null){
            return null;
        }

        return myWalletInfoRepository.findById(myWalletInfoId).orElse(null);
    }
}
