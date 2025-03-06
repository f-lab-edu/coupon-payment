package com.couponPayment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class TossBillingPaymentRes {
    private String version; // 응답 버전
    private String paymentKey; // 결제 키값
    private String type; // 결제 타입 정보
    private String orderId; // 주문번호
    private String orderName; // 구매상품
    private String mId; // 상점아이디(MID)
    private String currency; // 결제할 때 사용한 통화
    private String method; // 결제수단
    private Double totalAmount; // 총 결제 금액
    private Double balanceAmount; // 취소할 수 있는 금액(잔고)
    private String status; // 결제 처리 상태
    private String requestedAt; // 결제가 일어난 날짜와 시간 정보
    private String approvedAt; // 결제 승인이 일어난 날짜와 시간 정보
    private Boolean useEscrow; // 에스크로 사용 여부
    private String lastTransactionKey; // 마지막 거래의 키값
    private Double suppliedAmount; // 공급가액
    private Double vat; // 부가세
    private Boolean cultureExpense; // 문화비(도서, 공연 티켓, 박물관·미술관 입장권 등) 지출 여부
    private Double taxFreeAmount; // 면세 금액
    private Integer taxExemptionAmount; // 과세를 제외한 결제 금액
    private List<CancelDto> cancels; // 결제 취소 이력
    private CardDto card; // 카드 결제 시 카드 정보
    private VirtualAccountDto virtualAccount; // 가상계좌 결제 시 가상계좌 정보
    private MobilePhoneDto mobilePhone; // 휴대폰 결제 시 휴대폰 결제 관련 정보
    private GiftCertificateDto giftCertificate; // 상품권 결제 시 상품권 정보
    private TransferDto transfer; // 계좌이체 결제 시 이체 정보
    private MetadataDto metadata; // 결제 요청 시 SDK에서 추가할 수 있는 결제 관련 정보
    private ReceiptDto receipt; // 발행된 영수증 정보
    private CheckoutDto checkout; // 결제창 정보
    private EasyPayDto easyPay; // 간편결제 정보
    private String country; // 결제한 국가
    private FailureDto failure; // 결제 승인 실패 시 에러 정보
    private CashReceiptDto cashReceipt; // 현금영수증 정보

    // Nested DTO classes for complex types
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Setter
    @Getter
    public static class CancelDto {
        private Double cancelAmount; // 취소된 금액
        private String cancelReason; // 취소 이유
        private Double taxFreeAmount; // 취소된 금액 중 면세 금액
        private Integer taxExemptionAmount; // 취소된 금액 중 과세 제외 금액
        private Double refundableAmount; // 환불 가능한 잔액
        private Double transferDiscountAmount; // 즉시할인에서 취소된 금액
        private Double easyPayDiscountAmount; // 간편결제에서 취소된 금액
        private String canceledAt; // 결제 취소 일시
        private String transactionKey; // 취소 거래의 키값
        private String receiptKey; // 현금영수증 키값
        private String cancelStatus; // 취소 상태
        private String cancelRequestId; // 취소 요청 ID
        private Boolean isPartialCancelable; // 부분 취소 가능 여부
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Setter
    @Getter
    public static class CardDto {
        private Double amount; // 카드사에 결제 요청한 금액
        private String issuerCode; // 카드 발급사 코드
        private String acquirerCode; // 카드 매입사 코드
        private String number; // 카드번호
        private Integer installmentPlanMonths; // 할부 개월 수
        private String approveNo; // 카드사 승인 번호
        private Boolean useCardPoint; // 카드사 포인트 사용 여부
        private String cardType; // 카드 종류 (신용, 체크 등)
        private String ownerType; // 카드 소유자 타입 (개인, 법인)
        private String acquireStatus; // 매입 상태
        private Boolean isInterestFree; // 무이자 할부 여부
        private String interestPayer; // 할부 수수료 부담 주체
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Setter
    @Getter
    public static class VirtualAccountDto {
        private String accountType; // 계좌 타입 (일반, 고정)
        private String accountNumber; // 계좌번호
        private String bankCode; // 은행 코드
        private String customerName; // 구매자명
        private String dueDate; // 입금 기한
        private String refundStatus; // 환불 처리 상태
        private Boolean expired; // 계좌 만료 여부
        private String settlementStatus; // 정산 상태
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Setter
    @Getter
    public static class MobilePhoneDto {
        private String customerMobilePhone; // 구매자가 결제에 사용한 휴대폰 번호
        private String receiptUrl; // 영수증 URL
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Setter
    @Getter
    public static class GiftCertificateDto {
        private String approveNo; // 승인번호
        private String settlementStatus; // 정산 상태
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Setter
    @Getter
    public static class TransferDto {
        private String bankCode; // 은행 코드
        private String settlementStatus; // 정산 상태
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Setter
    @Getter
    public static class MetadataDto {
        private String key; // 키 (최대 40자)
        private String value; // 값 (최대 500자)
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Setter
    @Getter
    public static class ReceiptDto {
        private String url; // 영수증 URL
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Setter
    @Getter
    public static class CheckoutDto {
        private String url; // 결제창 URL
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Setter
    @Getter
    public static class EasyPayDto {
        private String provider; // 간편결제사 코드
        private Double amount; // 결제한 금액
        private Double discountAmount; // 즉시 할인 금액
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Setter
    @Getter
    public static class FailureDto {
        private String code; // 오류 코드
        private String message; // 오류 메시지
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Setter
    @Getter
    public static class CashReceiptDto {
        private String type; // 현금영수증 종류
        private String receiptKey; // 현금영수증 키값
        private String issueNumber; // 발급 번호
        private String receiptUrl; // 영수증 URL
        private Double amount; // 처리된 금액
        private Double taxFreeAmount; // 면세 처리된 금액
        private List<CashReceiptHistoryDto> cashReceipts; // 현금영수증 발행 및 취소 이력
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Setter
    @Getter
    public static class CashReceiptHistoryDto {
        private String receiptKey; // 현금영수증 키값
        private String orderId; // 주문번호
    }
}
