package com.fengtianhe.shareanalysis.entity;

import lombok.Data;

/**
 * 股票基础信息
 * @author 冯天鹤
 * @date 2023/1/13.
 */
@Data
public class NameEntity {
    /**
     * 股票编码 601966.SH
     */
    private String code;
    /**
     * 股票名称
     */
    private String name;
    /**
     * 地区
     */
    private String area;
    /**
     * 行业
     */
    private String industry;
    /**
     * 所在上市板块 主板，科创板
     */
    private String market;
}
