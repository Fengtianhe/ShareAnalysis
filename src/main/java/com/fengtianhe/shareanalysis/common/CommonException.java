package com.fengtianhe.shareanalysis.common;

import lombok.Data;
import lombok.Getter;

@Data
public class CommonException extends RuntimeException {
    private String code;
    private String msg;
    private IResponseCode responseCode;

    public CommonException() {
        this("1001", "接口错误");
    }

    public CommonException(String msg) {
        this("1001", msg);
    }

    public CommonException(IResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
        this.responseCode = responseCode;
    }

    public CommonException(IResponseCode responseCode, String msg) {
        super(msg);
        this.code = responseCode.getCode();
        this.msg = msg;
    }

    public CommonException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
