package com.fengtianhe.shareanalysis.entity;

import lombok.Data;

/**
 * 特殊指标
 *
 * @author 冯天鹤
 */
@Data
public class SpecialIndexDailyEntity {
    private Integer id;

    private String date;
    /**
     * 一字涨停
     */
    private int directRiseLimit;
    /**
     * 一字跌停
     */
    private int directFallLimit;
    /**
     * 涨停
     */
    private int riseLimit;
    /**
     * 跌停
     */
    private int fallLimit;
}
