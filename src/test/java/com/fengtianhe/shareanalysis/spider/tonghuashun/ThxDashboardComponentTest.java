package com.fengtianhe.shareanalysis.spider.tonghuashun;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ThxDashboardComponentTest {
@Autowired ThxDashboardComponent thxDashboardComponent;

    @Test
    public void getRiseData() {
    }

    @Test
    public void getYesterdayRiseFallRate() {
        thxDashboardComponent.getYesterdayRiseFallRate();
    }
}