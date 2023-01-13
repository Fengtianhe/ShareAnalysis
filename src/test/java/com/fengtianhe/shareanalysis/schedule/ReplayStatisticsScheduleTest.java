package com.fengtianhe.shareanalysis.schedule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fengtianhe.shareanalysis.ShareAnalysisApplication;
import com.fengtianhe.shareanalysis.spider.tonghuashun.ThxDashboardComponent;
import com.fengtianhe.shareanalysis.utils.HttpClientUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 冯天鹤
 * @date 2023/1/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShareAnalysisApplication.class)
public class ReplayStatisticsScheduleTest {
    @Autowired
    ReplayStatisticsSchedule replayStatisticsSchedule;

    @Test
    public void execute() throws Exception {
        HttpClientUtil httpClient = new HttpClientUtil();
        String response = httpClient.get("https://stock.xueqiu.com/v5/stock/realtime/quotec.json?symbol=SH601231").execute();
        JSONObject jsonObject = JSON.parseObject(response);
        System.out.println(jsonObject);
    }
}