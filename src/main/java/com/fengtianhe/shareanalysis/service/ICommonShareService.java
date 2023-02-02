package com.fengtianhe.shareanalysis.service;

import java.io.IOException;

/**
 * 一些公用的股票方法
 */
public interface ICommonShareService {
    /**
     * 是否开盘
     * @return true 开盘
     * @throws IOException
     */
    boolean isOpening() throws IOException;

    /**
     * 把带.的股票代码转为不带点的
     * @param shareCode 股票代码 -> 002842.SZ
     * @return SZ002842
     */
    String covertShareCodeByPoint(String shareCode);
}
