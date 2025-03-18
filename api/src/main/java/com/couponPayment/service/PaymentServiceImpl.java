package com.couponPayment.service;

import com.couponPayment.consts.CommonResult;
import com.couponPayment.consts.PaymentStatus;
import com.couponPayment.dto.PaymentCancelReq;
import com.couponPayment.dto.PaymentCancelRes;
import com.couponPayment.dto.PaymentReq;
import com.couponPayment.dto.PaymentRes;
import com.couponPayment.dto.TossBillingPaymentCancelReq;
import com.couponPayment.dto.TossBillingPaymentCancelRes;
import com.couponPayment.dto.TossBillingPaymentReq;
import com.couponPayment.dto.TossBillingPaymentRes;
import com.couponPayment.entity.MyWalletInfo;
import com.couponPayment.entity.StoreInfo;
import com.couponPayment.entity.TransactionInfo;
import com.couponPayment.entity.UserInfo;
import com.couponPayment.entity.dto.TransactionInfoDto;
import com.couponPayment.entity.mapper.TransactionMapper;
import com.couponPayment.repository.MyWalletInfoRepository;
import com.couponPayment.repository.TransactionInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final MyWalletInfoRepository myWalletInfoRepository;
    private final TransactionInfoRepository transactionInfoRepository;
    private final TransactionMapper transactionMapper;
    private final TossBillingService tossBillingService;

    @Override
    @Transactional
    public PaymentRes payment(PaymentReq paymentReq) {
        MyWalletInfo myWalletInfo = myWalletInfoRepository.findByCardIdAndUseFlag("cardId", 0).get();
        UserInfo userInfo = myWalletInfo.getUserInfo();
        StoreInfo storeInfo = userInfo.getStoreInfo();

        String tossPaymentId = storeInfo.getTossPaymentId();
        String customerKey = userInfo.getCustomerKey();

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        TransactionInfoDto transactionInfoDto = TransactionInfoDto
                .builder()
                .requestDt(formattedDate)
                .amount(1000)
                .myWalletInfoId(myWalletInfo.getId())
                .userInfoId(userInfo.getId())
                .storeInfoId(storeInfo.getId())
                .build();

        TransactionInfo transactionInfo = transactionInfoRepository.save(transactionMapper.toEntity(transactionInfoDto));

        TossBillingPaymentReq tossBillingPaymentReq = TossBillingPaymentReq
                .builder()
                .amount(1000)
                .cardId(paymentReq.getCardId())
                .customerKey(customerKey)
                .cardInstallmentPlan(paymentReq.getInstallment())
                .secretKey(tossPaymentId)
                .orderId(paymentReq.getOrderId())
                .orderName(paymentReq.getOrderNum())
                .build();

        TossBillingPaymentRes tossBillingPaymentRes =
                tossBillingService.billingPayment(tossBillingPaymentReq);

        if (tossBillingPaymentRes.getStatus().equals(PaymentStatus.DONE.name())) {
            transactionInfo.approvalPayment(tossBillingPaymentRes);
        }

        PaymentRes paymentRes = PaymentRes
                .builder()
                .merchantId(paymentReq.getMerchantId())
                .merchantMemberId(paymentReq.getMerchantMemberId())
                .orderNum(paymentReq.getOrderNum())
                .cardCompany(myWalletInfo.getCardCompany())
                .cardNum(tossBillingPaymentRes.getCard().getNumber())
                .approvalAmount(tossBillingPaymentRes.getCard().getAmount())
                .approvalNum(tossBillingPaymentRes.getCard().getApproveNo())
                .approvalDate(tossBillingPaymentRes.getApprovedAt())
                .tranNum(tossBillingPaymentRes.getPaymentKey())
                .resultCode(CommonResult.E0000.getCode())
                .resultMessage(CommonResult.E0000.getMessage())
                .build();

        return paymentRes;
    }

    @Override
    public PaymentCancelRes paymentCancel(PaymentCancelReq paymentCancelReq) {
        TransactionInfo transactionInfo = transactionInfoRepository.findByTranNumAndStatus(paymentCancelReq.getTranNum(),"DONE").get();
        StoreInfo storeInfo = transactionInfo.getStoreInfo();
        UserInfo userInfo = transactionInfo.getUserInfo();

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        /**
         * TODO
         * 공용 Exception 처리 예정
         * */
        if(!transactionInfo.getTranNum().equals(paymentCancelReq.getTranNum())){
            transactionInfo.cancelFail();
        }

        TransactionInfoDto transactionInfoDto = TransactionInfoDto
                .builder()
                .requestDt(formattedDate)
                .amount(transactionInfo.getAmount())
                .tranNum(transactionInfo.getTranNum())
                .myWalletInfoId(transactionInfo.getMyWalletInfo().getId())
                .userInfoId(transactionInfo.getUserInfo().getId())
                .storeInfoId(transactionInfo.getStoreInfo().getId())
                .build();

        TransactionInfo cancelTransactionInfo = transactionInfoRepository.save(transactionMapper.toEntity(transactionInfoDto));

        TossBillingPaymentCancelReq tossBillingPaymentCancelReq = TossBillingPaymentCancelReq
                .builder()
                .paymentKey(transactionInfo.getTranNum())
                .secretKey(storeInfo.getTossPaymentId())
                .cancelReason("취소 이유")
                .build();

        TossBillingPaymentCancelRes tossBillingPaymentCancelRes =
                tossBillingService.billingPaymentCancel(tossBillingPaymentCancelReq);

        //분할 취소를 위해 list로 있지만 내 서비스에 분할 취소는 없다
        if(tossBillingPaymentCancelRes.getCancels().get(0).getCancelStatus().equals(PaymentStatus.DONE.name())){
            cancelTransactionInfo.cancelPayment(tossBillingPaymentCancelRes);
        }

        PaymentCancelRes paymentCancelRes = PaymentCancelRes
                .builder()
                .merchantId(storeInfo.getMerchantId())
                .merchantMemberId(userInfo.getMerchantMemberId())
                .tranNum(tossBillingPaymentCancelRes.getPaymentKey())
                .orderNum(tossBillingPaymentCancelRes.getOrderName())
                .cancelAmount(tossBillingPaymentCancelRes.getCancels().get(0).getCancelAmount())
                .cancelDate(tossBillingPaymentCancelRes.getCancels().get(0).getCanceledAt())
                .resultCode(CommonResult.E0000.getCode())
                .resultMessage(CommonResult.E0000.getMessage())
                .build();

        return paymentCancelRes;
    }
}
