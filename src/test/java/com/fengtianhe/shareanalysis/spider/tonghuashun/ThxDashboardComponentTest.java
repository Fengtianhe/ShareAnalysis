package com.fengtianhe.shareanalysis.spider.tonghuashun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fengtianhe.shareanalysis.dto.tonghuashun.WenCaiResponse;
import com.fengtianhe.shareanalysis.schedule.SpecialIndexDailySchedule;
import com.fengtianhe.shareanalysis.utils.HttpClientUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ThxDashboardComponentTest {
@Autowired
SpecialIndexDailySchedule schedule;

    @Test
    public void test() throws Exception {
        schedule.execute();
    }
}