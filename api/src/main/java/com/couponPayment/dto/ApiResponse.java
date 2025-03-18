package com.couponPayment.dto;

import com.couponPayment.consts.CommonResult;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class ApiResponse {
    public static final ApiResponse OK = new ApiResponse();

    private String resultCode;
    private String resultMessage;

    public ApiResponse() {
        this(CommonResult.E0000);
    }

    public ApiResponse(CommonResult commonResult) {
        resultCode = commonResult.getCode();
        resultMessage = commonResult.getMessage();
    }

    public ApiResponse(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMessage = resultMsg;
    }

    public static ApiResponse of(CommonResult commonResult) {
        return new ApiResponse(commonResult);
    }
}
