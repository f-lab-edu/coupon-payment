package com.couponPayment.dto;

import com.couponPayment.consts.CommonResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class ApiResponse<T> {
    public static final ApiResponse OK = new ApiResponse();

    private final String resultCode;
    private final String resultMessage;
    private final T data;

    private ApiResponse() {
        this(CommonResult.E0000.getCode(), CommonResult.E0000.getMessage(), null);
    }

    private ApiResponse(CommonResult commonResult) {
        this(commonResult.getCode(), commonResult.getMessage(), null);
    }

    private ApiResponse(CommonResult commonResult, T data) {
        this(commonResult.getCode(), commonResult.getMessage(), data);
    }

    private ApiResponse(String resultCode, String resultMsg, T data) {
        this.resultCode = resultCode;
        this.resultMessage = resultMsg;
        this.data = data;
    }

    public static final ApiResponse fail(CommonResult commonResult) {
        return new ApiResponse(commonResult);
    }

    public static <T> ApiResponse<T> of(CommonResult commonResult, T data) {
        return new ApiResponse<>(commonResult, data);
    }

    public static <T> ApiResponse<T> of(String resultCode,String resultMsg, T data) {
        return new ApiResponse<>(resultCode, resultMsg, data);
    }
}
