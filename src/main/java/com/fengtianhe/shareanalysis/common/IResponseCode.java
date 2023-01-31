package com.fengtianhe.shareanalysis.common;

public interface IResponseCode {
    String SUCCESS = "success";

    String getCode();

    void setCode(String code);

    String getMsg();

    void setMsg(String msg);
}
