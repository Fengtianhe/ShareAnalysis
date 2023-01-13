package com.fengtianhe.shareanalysis.schedule;

import com.fengtianhe.shareanalysis.spider.tonghuashun.ThxDashboardComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 复盘统计
 *
 * @author 冯天鹤
 * @date 2023/1/11.
 */
@Slf4j
@Component
public class ReplayStatisticsSchedule {
    @Autowired
    ThxDashboardComponent thxDashboardComponent;

    public void execute() {
        try {
            Map<String, String> riseData = thxDashboardComponent.getRiseData();
            System.out.println(riseData);
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
