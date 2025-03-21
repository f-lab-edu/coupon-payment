package com.couponPayment.util;

import com.couponPayment.consts.CommonResult;
import com.couponPayment.dto.ApiResponse;
import com.couponPayment.dto.PaymentRes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ApiResponseTest {

    @Test
    public void ok_test(){
        ApiResponse apiResponse = ApiResponse.OK;
        assertThat(apiResponse.getResultCode()).isEqualTo("0000");
        assertThat(apiResponse.getResultMessage()).isEqualTo("성공");
    }

    @Test
    public void fail_test(){
        ApiResponse apiResponse = ApiResponse.fail(CommonResult.E9000);
        assertThat(apiResponse.getResultCode()).isEqualTo("9000");
        assertThat(apiResponse.getResultMessage()).isEqualTo("해당 가맹점이 존재하지 않습니다.");
    }

    @Test
    public void of_test(){
        ApiResponse<Object> apiResponse = ApiResponse.of(CommonResult.E0000, null);
        assertThat(apiResponse.getResultCode()).isEqualTo("0000");
        assertThat(apiResponse.getResultMessage()).isEqualTo("성공");
        assertThat(apiResponse.getData()).isNull();
    }

    @Test
    public void of_test2(){
        ApiResponse<Object> apiResponse = ApiResponse.of(CommonResult.E0000.getCode(), CommonResult.E0000.getMessage(), null);
        assertThat(apiResponse.getResultCode()).isEqualTo("0000");
        assertThat(apiResponse.getResultMessage()).isEqualTo("성공");
        assertThat(apiResponse.getData()).isNull();
    }

    @Test
    public void of_test3(){
        PaymentRes paymentRes = new PaymentRes("가맹점","young","12345","NH","123",1000,"aNum","adt","tran");
        ApiResponse<PaymentRes> apiResponse = ApiResponse.of(CommonResult.E0000, paymentRes);

        assertThat(apiResponse.getResultCode()).isEqualTo("0000");
        assertThat(apiResponse.getResultMessage()).isEqualTo("성공");
        assertThat(apiResponse.getData()).isNotNull();

        assertThat(paymentRes).usingRecursiveComparison().isEqualTo(apiResponse.getData());
    }

    @Test
    public void of_test4(){
        PaymentRes paymentRes = new PaymentRes("가맹점","young","12345","NH","123",1000,"aNum","adt","tran");
        ApiResponse<PaymentRes> apiResponse = ApiResponse.of(CommonResult.E0000.getCode(), CommonResult.E0000.getMessage(), paymentRes);

        assertThat(apiResponse.getResultCode()).isEqualTo("0000");
        assertThat(apiResponse.getResultMessage()).isEqualTo("성공");
        assertThat(apiResponse.getData()).isNotNull();

        assertThat(paymentRes).usingRecursiveComparison().isEqualTo(apiResponse.getData());
    }
}
