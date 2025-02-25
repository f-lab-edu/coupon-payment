# API 규격


### 결제 : /api/v1/payment

```json
request:
{
    "merchantId": "가맹점",
    "merchantMemberId": "가맹점 회원",
    "orderNum": "주문번호",
    "installment": "할부",
    "amount": "금액",
    "card_info" : {
        "card_number": "4111111111111111",
        "expiry_date": "12/25",
        "cvv": "123"
    }
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
    "tranNum": "거래 번호",
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
    "tranNum": "거래 번호",
    "amount": "취소 금액"
}

response:
{
    "merchantId": "가맹점",
    "merchantMemberId": "가맹점 회원",
    "tranNum": "거래 번호",
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
        "tranNum": "거래 번호",
        "amount": 10000,
        "status": "completed",
        "approvalDate": "승인 시간"
        },
        {
        "tranNum": "거래 번호",
        "amount": 5000,
        "status": "cancel",
        "approvalDate": "승인 시간"
        }
    ]
    
}
```