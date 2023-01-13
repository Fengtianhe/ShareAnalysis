package com.fengtianhe.shareanalysis.spider.xueqiu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fengtianhe.shareanalysis.dto.xueqiu.XqRealtimeQuotationDTO;
import com.fengtianhe.shareanalysis.utils.HttpClientUtil;
import org.springframework.stereotype.Component;

/**
 * 雪球股票基础信息
 *
 * @author 冯天鹤
 * @date 2023/1/13.
 */
@Component
public class XqBasicComponent {
    /**
     * 获取实时报价
     *
     * @param symbol 股票代码 例：002299.SZ
     * @return
     */
    public XqRealtimeQuotationDTO getRealtimeQuotation(String symbol) throws Exception {
        String[] split = symbol.split("\\.");
        String shareCode = split[1] + split[0];
        HttpClientUtil httpClient = new HttpClientUtil();
        String response = httpClient.get("https://stock.xueqiu.com/v5/stock/realtime/quotec.json?symbol=" + shareCode).execute();
        JSONObject jsonObject = JSON.parseObject(response);
        if (jsonObject.getIntValue("error_code") == 0) {
            return ((JSONObject) jsonObject.getJSONArray("data").get(0)).toJavaObject(XqRealtimeQuotationDTO.class);
        }
        return null;
    }
}
