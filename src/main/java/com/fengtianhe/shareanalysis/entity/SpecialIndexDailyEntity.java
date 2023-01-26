package com.fengtianhe.shareanalysis.entity;

import lombok.Data;

/**
 * 特殊指标
 */
@Data
public class SpecialIndexDailyEntity {
    private int id;

    private String date;
    /**
     * 一字涨停
     */
    private int directRiseLimit;
    /**
     * 一字跌停
     */
    private int directFallLimit;
}
