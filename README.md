# API 규격

## Controller

### 빌링키 발급 Toss화면 요청 ThymeLeaf: /api/v1/toss/billing/request
토스 JS SDK 활용
```json
request:
{
    "clientKey": "토스에서 발급받은 Key",
    "successUrl": "localhost:8080//api/v1/toss/billing/success",
    "failUrl": "localhost:8080//api/v1/toss/billing/fail"
}


```

### 키 발급 성공 API : /api/v1/toss/billing/success?customerKey=test_customer_key&authKey=bln_Dk0NGXzdDmB
```json
request:
{
}

response:
{
}
```

### 결제 : /api/v1/payment

```json
request:
{
    "merchantId": "가맹점",
    "merchantMemberId": "가맹점 회원",
    "orderNum": "주문번호",
    "installment": "할부",
    "amount": "금액"
}

response:
{
    "merchantId": "가맹점",
    "merchantMemberId": "가맹점 회원",
    "orderNum": "주문번호",
    "cardCompany": "결제한 카드 회사",
    "cardNum": "결제한 카드 번호",
    "cardName": "결제한 카드 이름",
    "approvalAmount": "승인 금액",
    "approvalNum": "승인 번호",
    "approvalDate": "승인 날짜",
    "paymentKey": "거래 번호",
    "resultCode": "결과 값",
    "resultMessage": "결과 메세지"
}
```
### 결제 취소 : /api/v1/payment/cancel
```json
request:
{
    "merchantId": "가맹점",
    "merchantMemberId": "가맹점 회원",
    "paymentKey": "거래 번호"
}

response:
{
    "merchantId": "가맹점",
    "merchantMemberId": "가맹점 회원",
    "paymentKey": "거래 번호",
    "cancelAmount": "취소 금액",
    "cancelDate": "취소 날짜",
    "resultCode": "결과 값",
    "resultMessage": "결과 메세지"
}
```
### 결제 내역 : /api/v1/payment/history
```json
request:
{
    "merchantId": "가맹점",
    "merchantMemberId": "가맹점 회원"
}

response:
{
    "payments": [
        {
        "amount": 10000,
        "status": "completed",
        "approvalDate": "승인 시간"
        },
        {
        "amount": 5000,
        "status": "cancel",
        "approvalDate": "승인 시간"
        }
    ]

}

```
## Serivece

### 키 발급 후 빌링키 요청 API : https://api.tosspayments.com/v1/billing/authorizations/issue
성공 API로 오면 QueryString으로 받은 데이터를 활용해 토스로 POST 요청하여 Billingkey발급 받는다
```json
request:
{
    "customerKey": "test_customer_key Key",
    "authKey" : "bln_Dk0NGXzdDmB"
}

response:
{
    "mId": "tosspayments",
    "customerKey": "aENcQAtPdYbTjGhtQnNVj",
    "authenticatedAt": "2020-09-25T14:38:41+09:00",
    "method": "카드",
    "billingKey": "Z_t5vOvQxrj4499PeiJcjen28-V2RyqgYTwN44Rdzk0=",
    "card": {
      "issuerCode": "61",
      "acquirerCode": "31",
      "number": "12345678****123*",
      "cardType": "신용",
      "ownerType": "개인"
    },
    "cardCompany": "현대",
    "cardNumber": "12345678****123*"
}
```
### 빌링키를 통해 결제 요청  API : https://api.tosspayments.com/v1/billing/{billingKey}
```json
request:
{
    "customerKey":"aENcQAtPdYbTjGhtQnNVj",
    "amount":4900,
    "orderId":"a4CWyWY5m89PNh7xJwhk1",
    "orderName":"토스 프라임 구독",
    "customerEmail":"customer@email.com",
    "customerName":"박토스",
    "taxFreeAmount":0,
    "taxExemptionAmount":0
}

response:
{
    "mId": "tosspayments",
    "lastTransactionKey": "798780972A6B30495E2DAB97839D1199",
    "paymentKey": "y05n91dEvLex6BJGQOVDpgDQ0gDv0QVW4w2zNbgaYRMPoqmD",
    "orderId": "a4CWyWY5m89PNh7xJwhk1",
    "orderName": "토스 티셔츠 외 2건",
    "taxExemptionAmount": 0,
    "status": "DONE",
    "requestedAt": "2024-02-13T14:54:34+09:00",
    "approvedAt": "2024-02-13T14:54:57+09:00",
    "useEscrow": false,
    "cultureExpense": false,
    "card": {
        "company": "롯데",
        "issuerCode": "71",
        "acquirerCode": "71",
        "number": "51379200****061*",
        "installmentPlanMonths": 0,
        "isInterestFree": false,
        "interestPayer": null,
        "approveNo": "00000000",
        "useCardPoint": false,
        "cardType": "신용",
        "ownerType": "개인",
        "acquireStatus": "READY",
        "receiptUrl": "https://dashboard.tosspayments.com/receipt/redirection?transactionId=tviva20240213145434Rycs6&ref=PX",
        "provider": "토스결제",
        "amount": 4900
    },
    "virtualAccount": null,
    "transfer": null,
    "mobilePhone": null,
    "giftCertificate": null,
    "cashReceipt": null,
    "cashReceipts": null,
    "discount": null,
    "cancels": null,
    "secret": null,
    "type": "BILLING",
    "easyPay": null,
    "country": "KR",
    "failure": null,
    "isPartialCancelable": true,
    "receipt": {
    "url": "https://dashboard.tosspayments.com/receipt/redirection?transactionId=tviva20240213145434Rycs6&ref=PX"
    },
    "checkout": {
    "url": "https://api.tosspayments.com/v1/payments/y05n91dEvLex6BJGQOVDpgDQ0gDv0QVW4w2zNbgaYRMPoqmD/checkout"
    },
    "transactionKey": "798780972A6B30495E2DAB97839D1199",
    "currency": "KRW",
    "totalAmount": 4900,
    "balanceAmount": 4900,
    "suppliedAmount": 4455,
    "vat": 455,
    "taxFreeAmount": 0,
    "metadata": null,
    "method": "카드",
    "version": "2022-11-16"
}


```

### 토스 결제 취소 api: https://api.tosspayments.com/v1/payments/{paymentKey}/cancel
```json
request:
{
  "cancelReason":"구매자 변심"(필수),
  "cancelAmount" : "없으면 전액"
}

response:
{
    "mId": "tosspayments",
    "lastTransactionKey": "090A796806E726BBB929F4A2CA7DB9A7",
    "paymentKey": "5EnNZRJGvaBX7zk2yd8ydw26XvwXkLrx9POLqKQjmAw4b0e1",
    "orderId": "a4CWyWY5m89PNh7xJwhk1",
    "orderName": "토스 티셔츠 외 2건",
    "taxExemptionAmount": 0,
    "status": "CANCELED",
    "requestedAt": "2024-02-13T12:17:57+09:00",
    "approvedAt": "2024-02-13T12:18:14+09:00",
    "useEscrow": false,
    "cultureExpense": false,
    "card": {
        "issuerCode": "71",
        "acquirerCode": "71",
        "number": "12345678****000*",
        "installmentPlanMonths": 0,
        "isInterestFree": false,
        "interestPayer": null,
        "approveNo": "00000000",
        "useCardPoint": false,
        "cardType": "신용",
        "ownerType": "개인",
        "acquireStatus": "READY",
        "amount": 1000
    },
    "virtualAccount": null,
    "transfer": null,
    "mobilePhone": null,
    "giftCertificate": null,
    "cashReceipt": null,
    "cashReceipts": null,
    "discount": null,
    "cancels": [
    {
        "transactionKey": "090A796806E726BBB929F4A2CA7DB9A7",
        "cancelReason": "테스트 결제 취소",
        "taxExemptionAmount": 0,
        "canceledAt": "2024-02-13T12:20:23+09:00",
        "transferDiscountAmount": 0,
        "easyPayDiscountAmount": 0,
        "receiptKey": null,
        "cancelAmount": 1000,
        "taxFreeAmount": 0,
        "refundableAmount": 0,
        "cancelStatus": "DONE",
        "cancelRequestId": null
    }],
    "secret": null,
    "type": "NORMAL",
    "easyPay": {
        "provider": "토스페이",
        "amount": 0,
        "discountAmount": 0
    },
    "country": "KR",
    "failure": null,
    "isPartialCancelable": true,
    "receipt": {
      "url": "https://dashboard.tosspayments.com/receipt/redirection?transactionId=tviva20240213121757MvuS8&ref=PX"
    },
    "checkout": {
      "url": "https://api.tosspayments.com/v1/payments/5EnNZRJGvaBX7zk2yd8ydw26XvwXkLrx9POLqKQjmAw4b0e1/checkout"
    },
    "currency": "KRW",
    "totalAmount": 1000,
    "balanceAmount": 0,
    "suppliedAmount": 0,
    "vat": 0,
    "taxFreeAmount": 0,
    "method": "카드",
    "version": "2022-11-16",
    "metadata": null

}

### 결제 내역 api : https://api.tosspayments.com/v1/transactions?startDate=2022-01-01T00:00:00&endDate=2022-01-02T23:59:59
조회 원하는 시간대 queryString으로 요청         
```json
request:
    "startDate"="검색 시작 시간" & "endDate"="검색 종료 시간"


response:
{
  [
    {
        "mId": "tosspayments",
        "transactionKey": "AC45CD421C4E49D7B878522128B8D9C6",
        "paymentKey": "lOR1ZwdkQD5GePWvyJnrKXP6nDMRb8gLzN97EoqYA60XKx4a",
        "orderId": "fMgvolnZP6_ZdEYAaWF_1",
        "method": "카드",
        "customerKey": "cG_EdskNV1JgX7Y16S6vo",
        "useEscrow": false,
        "receiptUrl": "https://dashboard.tosspayments.com/receipt/redirection?transactionId=tosspayments01202301021537455ezz1&ref=PX",
        "status": "DONE",
        "transactionAt": "2023-01-02T15:37:45+09:00",
        "currency": "KRW",
        "amount": 1500
    },
    {
        "mId": "tosspayments",
        "transactionKey": "E41141562F42C887DB9F45D0FA4101D1",
        "paymentKey": "XJxNkgDKzOEP59LybZ8BJkX7ky5Y9B86GYo7pRe10BMQwla2",
        "orderId": "CwNjZgeiXkvmqnzjip78m",
        "method": "간편결제",
        "customerKey": null,
        "useEscrow": false,
        "receiptUrl": "https://dashboard.tosspayments.com/receipt/redirection?transactionId=tosspayments20230522104630vJoC6&ref=PX",
        "status": "DONE",
        "transactionAt": "2023-01-02T15:37:45+09:00",
        "currency": "KRW",
        "amount": 15000
    }
  ]
    
}
```