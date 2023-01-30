package com.fengtianhe.shareanalysis.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fengtianhe.shareanalysis.dto.tonghuashun.WenCaiResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 同花顺问财接口
 * @author 冯天鹤
 */
@Component
public class WenCaiUtil {

    private static final String ROBOT_URL = "http://iwencai.com/customized/chart/get-robot-data";
    public static WenCaiResponse.Data getRebotData(String question) throws Exception {
        String ticket = getTicket();
        Map<String, String> headers = getHeaders(ticket);

        JSONObject body = new JSONObject();
        body.put("question", question);
        body.put("perpage", 10000);
        body.put("page", 1);
        body.put("source", "Ths_iwencai_Xuangu");
        body.put("version", "2.0");
        HttpClientUtil clientUtil = new HttpClientUtil();
        WenCaiResponse response = clientUtil.post(ROBOT_URL).addAllHeader(headers).addJsonBody(body.toJSONString()).execute(WenCaiResponse.class);
        return response.getData();
    }

    private static Map<String, String> getHeaders(String ticket) {
        Map<String, String> headers = new HashMap<>(6);
        headers.put("Accept", "application/json, text/plain, */*");
        headers.put("Content-Type", "application/json");
        headers.put("Cookie", "v=" + ticket);
        headers.put("Host", "iwencai.com");
        headers.put("Origin", "http://iwencai.com");
        headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36");
        return headers;
    }

    private static String getTicket() throws Exception {
        HttpClientUtil clientUtil = new HttpClientUtil();
        String response = clientUtil.get("http://47.242.67.95:8000/wencai-ticket").execute();

        JSONObject jsonObject = JSON.parseObject(response);
        if (jsonObject.getString("message").equals("success")) {
            return jsonObject.getString("data");
        }
        return null;
    }
}
