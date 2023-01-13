package com.fengtianhe.shareanalysis.schedule;

import com.fengtianhe.shareanalysis.ShareAnalysisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 冯天鹤
 * @date 2023/1/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShareAnalysisApplication.class)
public class DailyPriceSyncScheduleTest {
    @Autowired
    DailyPriceSyncSchedule dailyPriceSyncSchedule;

    @Test
    public void execute() {
        dailyPriceSyncSchedule.execute();
    }
}