package com.fengtianhe.shareanalysis.dto.xueqiu;

import lombok.Data;

/**
 * 雪球实时报价返回体
 * @author 冯天鹤
 * @date 2023/1/13.
 */
@Data
public class XqRealtimeQuotationDTO {
    private String symbol;

    private Double current;

    private Double percent;

    private Double chg;

    private Long timestamp;
    /**
     * 成交量，单位()
     */
    private Long volume;

    private Long amount;

    private Long market_capital;

    private Long float_market_capital;

    private Double turnover_rate;

    private Double amplitude;

    private Double open;

    private Double last_close;

    private Double high;

    private Double low;

    private Double avg_price;

    private Double trace_volume;

    private Integer side;

    private Boolean is_trade;

    private Double current_year_percent;
}
