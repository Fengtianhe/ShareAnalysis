package com.fengtianhe.shareanalysis.common;

import lombok.Data;

@Data
public class CommonResponse<T> {
    /**
     * 状态码，比如0代表响应成功
     */
    private String code;
    /**
     * 响应信息，用来说明响应情况
     */
    private String message;
    /**
     * 响应的具体数据
     */
    private T data;

    public CommonResponse(T data) {
        this(CommonResponseCode.SUCCESS, data);
    }

    public CommonResponse(IResponseCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMsg();
        this.data = data;
    }

    public CommonResponse(String resultCode, String msg) {
        this.code = resultCode;
        this.message = msg;
    }

    public CommonResponse(IResponseCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMsg();
    }
}
