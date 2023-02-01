package com.fengtianhe.shareanalysis.schedule;

import com.alibaba.fastjson.JSON;
import com.fengtianhe.shareanalysis.entity.SpecialIndexDailyEntity;
import com.fengtianhe.shareanalysis.mapper.SpecialIndexDailyMapper;
import com.fengtianhe.shareanalysis.service.ICommonShareService;
import com.fengtianhe.shareanalysis.spider.tonghuashun.ThxDashboardComponent;
import com.fengtianhe.shareanalysis.utils.DatetimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
    @Autowired
    ICommonShareService commonShareService;

    /**
     * 每天4点获取特色指标
     */
    @Scheduled(cron = "0 0 16 * * *")
    public void execute() {
        String date = DatetimeUtil.format(DatetimeUtil.FORMAT_DATE_NO_SEPARATOR);

        SpecialIndexDailyEntity specialIndexDailyEntity = null;
        try {
            if (!commonShareService.isOpening()) {
                log.info("[SpecialIndexDailySchedule execute] 未开盘，不获取当天数据");
                return;
            }

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
                log.info("[SpecialIndexDailySchedule execute] 更新特色指标: {}", JSON.toJSONString(specialIndexDailyEntity));
                specialIndexDailyMapper.updateById(specialIndexDailyEntity);
            } else {
                log.info("[SpecialIndexDailySchedule execute] 新增特色指标: {}", JSON.toJSONString(specialIndexDailyEntity));
                specialIndexDailyMapper.save(specialIndexDailyEntity);
            }
        } catch (Exception e) {
            log.error("[特色指标每日统计] 异常", e);
        }
    }
}
