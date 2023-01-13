package com.fengtianhe.shareanalysis.spider.tonghuashun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fengtianhe.shareanalysis.utils.HttpClientUtil;
import org.apache.http.client.utils.HttpClientUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 同花顺大盘数据
 * @author 冯天鹤
 * @date 2023/1/11.
 */
@Component
public class ThxDashboardComponent {
    /**
     * 获取上涨数据
     * @return
     */
    public Map<String, String> getRiseData() throws Exception{
        HttpClientUtil httpClient = new HttpClientUtil();
        String response = httpClient.get("http://q.10jqka.com.cn/api.php?t=indexflash").execute();
        JSONObject jsonObject = JSON.parseObject(response);
        Map<String, String> ret = new HashMap<>(2);
        // 上涨数量
        ret.put("riseNum", jsonObject.getJSONObject("zdt_data").getJSONObject("last_zdt").getString("ztzs"));
        // 涨停数量
        ret.put("riseTopNum", jsonObject.getJSONObject("zdfb_data").getString("znum"));

        return ret;
    }
}
