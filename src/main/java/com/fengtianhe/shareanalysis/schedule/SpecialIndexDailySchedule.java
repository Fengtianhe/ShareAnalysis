package com.fengtianhe.shareanalysis.schedule;

import com.fengtianhe.shareanalysis.entity.SpecialIndexDailyEntity;
import com.fengtianhe.shareanalysis.mapper.SpecialIndexDailyMapper;
import com.fengtianhe.shareanalysis.spider.tonghuashun.ThxDashboardComponent;
import com.fengtianhe.shareanalysis.utils.DatetimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 特色指标每日统计
 *
 * @author 冯天鹤
 * @date 2023/1/11.
 */
@Slf4j
@Component
public class SpecialIndexDailySchedule {
    @Autowired
    ThxDashboardComponent thxDashboardComponent;
    @Autowired
    SpecialIndexDailyMapper specialIndexDailyMapper;

    public void execute() {
        String date = DatetimeUtil.format(DatetimeUtil.FORMAT_DATE_NO_SEPARATOR);
        SpecialIndexDailyEntity specialIndexDailyEntity = null;
        try {
            Integer directRiseLimitCnt = thxDashboardComponent.getDirectRiseLimitCnt();
            Integer directFallLimitCnt = thxDashboardComponent.getDirectFallLimitCnt();
            Integer fallLimitCnt = thxDashboardComponent.getFallLimitCnt();
            Integer riseLimitCnt = thxDashboardComponent.getRiseLimitCnt();

            specialIndexDailyEntity = specialIndexDailyMapper.getByDate(date);
            if (specialIndexDailyEntity == null) {
                specialIndexDailyEntity = new SpecialIndexDailyEntity();
                specialIndexDailyEntity.setDate(date);
            }
            specialIndexDailyEntity.setDirectRiseLimit(directRiseLimitCnt);
            specialIndexDailyEntity.setDirectFallLimit(directFallLimitCnt);
            specialIndexDailyEntity.setRiseLimit(riseLimitCnt);
            specialIndexDailyEntity.setFallLimit(fallLimitCnt);

            if (specialIndexDailyEntity.getId() != null) {
                specialIndexDailyMapper.updateById(specialIndexDailyEntity);
            } else {
                specialIndexDailyMapper.save(specialIndexDailyEntity);
            }
        } catch (Exception e) {
            log.error("[特色指标每日统计] 异常", e);
        }
    }
}
