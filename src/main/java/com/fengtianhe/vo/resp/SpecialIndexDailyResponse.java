package com.fengtianhe.vo.resp;

import com.fengtianhe.shareanalysis.entity.RiseFallPlateEntity;
import lombok.Data;

import java.util.List;
@Data
public class SpecialIndexDailyResponse {
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

    /**
     * 板块数据
     */
    private List<RiseFallPlateEntity> riseFallPlateEntities;
}
