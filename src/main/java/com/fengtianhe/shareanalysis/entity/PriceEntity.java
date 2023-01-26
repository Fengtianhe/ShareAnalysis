package com.fengtianhe.shareanalysis.entity;

import com.fengtianhe.shareanalysis.utils.NumberUtil;
import lombok.Data;

import java.util.Objects;

/**
 * 股票价格信息
 *
 * @author 冯天鹤
 * @date 2023/1/13.
 */
@Data
public class PriceEntity {
    /**
     * 股票代码
     */
    private String tsCode;
    /**
     * 数据日期
     */
    private String tradeDate;
    /**
     * 开盘价格
     */
    private Double open;
    /**
     * 最高价
     */
    private Double high;
    /**
     * 最低价
     */
    private Double low;
    /**
     * 收盘价
     */
    private Double close;
    /**
     * 前一天收盘价
     */
    private Double preClose;
    /**
     * 涨跌额
     */
    private Double change;
    /**
     * 涨跌幅度
     */
    private Double pctChg;
    /**
     * 成交量
     */
    private Double vol;
    /**
     * 成交额(千万)
     */
    private Double amount;
    /**
     * 今年的涨跌幅
     */
    private Double currentYearPercent;

    /**
     * 一字是否涨停
     * 描述：当天开盘价等于收盘价，最高价等于最低价，且涨停幅为10%
     *
     * @return true/false
     */
    public boolean isDirectRiseLimit() {
        return Objects.equals(open, close) && Objects.equals(high, low) && NumberUtil.roundToInt(pctChg) == 10;
    }

    /**
     * 一字跌停
     * 描述：当天开盘价等于收盘价，最高价等于最低价，且涨停幅为-10%
     *
     * @return true/false
     */
    public boolean isDirectFallLimit() {
        return Objects.equals(open, close) && Objects.equals(high, low) && NumberUtil.roundToInt(pctChg) == -10;
    }
}
