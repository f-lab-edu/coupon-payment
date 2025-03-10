package com.couponPayment.entity.mapper;

import com.couponPayment.entity.WalletReqTb;
import com.couponPayment.repository.WalletReqRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalletReqMapperHelper {
    private final WalletReqRepository walletReqRepository;

    @Named("mapWalletReq")
    public WalletReqTb mapWalletReq(Long walletReqId){
        if(walletReqId == null){
            return null;
        }
        return walletReqRepository.findById(walletReqId).orElse(null);
    }
}
