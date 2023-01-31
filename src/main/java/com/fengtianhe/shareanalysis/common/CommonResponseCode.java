package com.fengtianhe.shareanalysis.common;

public enum CommonResponseCode implements IResponseCode {
    SUCCESS("success", "操作成功"),
    FAILURE("failure", "操作失败"),
    ;
    private String code;

    private String msg;

    CommonResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public void setCode(String code) {

    }

    @Override
    public String getMsg() {
        return null;
    }

    @Override
    public void setMsg(String msg) {

    }
}
