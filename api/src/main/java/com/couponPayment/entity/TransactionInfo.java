package com.couponPayment.entity;

import com.couponPayment.consts.PaymentStatus;
import com.couponPayment.dto.TossBillingPaymentCancelRes;
import com.couponPayment.dto.TossBillingPaymentRes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Entity
public class TransactionInfo extends BaseEntity{
    @Id
    @Column(name = "transactionInfoId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myWalletInfoId")
    private MyWalletInfo myWalletInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "walletReqId")
    private WalletReq walletReq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeInfoId")
    private StoreInfo storeInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInfoId")
    private UserInfo userInfo;

    /*@Column(length = 128)
    private String cardId;

    @Column(length = 64)
    private String orderNum;

    @Column(length = 64)
    private String orderId;*/
    @Column(length = 64)
    private String tranNum;

    private String requestDt;
    private Integer amount;

    private Integer approvalAmount;
    private String approvalDt;

    @Column(length = 64)
    private String approvalNum;
    private Integer cancelAmount;
    private String cancelDt;
    /** 할부 */
    private Integer installment;

    @Column(length = 128)
    private String callbackUrl;

    //결제 상태
    private String status;
    protected TransactionInfo() {

    }

    public void approvalPayment(TossBillingPaymentRes tossBillingPaymentRes){
        this.tranNum = tossBillingPaymentRes.getPaymentKey();
        this.approvalAmount = tossBillingPaymentRes.getTotalAmount();
        this.approvalDt = tossBillingPaymentRes.getApprovedAt();
        this.approvalNum = tossBillingPaymentRes.getCard().getApproveNo();
        this.installment = tossBillingPaymentRes.getCard().getInstallmentPlanMonths();
        this.status = tossBillingPaymentRes.getStatus();
    }

    public void cancelPayment(TossBillingPaymentCancelRes tossBillingPaymentCancelRes){
        this.cancelAmount = tossBillingPaymentCancelRes.getCancels().get(0).getCancelAmount();
        this.cancelDt = tossBillingPaymentCancelRes.getCancels().get(0).getCanceledAt();
        this.status = tossBillingPaymentCancelRes.getCancels().get(0).getCancelStatus();
    }

    public void cancelFail(){
        this.status = PaymentStatus.FAIL.name();

    }
}
