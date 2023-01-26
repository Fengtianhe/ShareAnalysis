package com.fengtianhe.shareanalysis.spider.tonghuashun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fengtianhe.shareanalysis.entity.PriceEntity;
import com.fengtianhe.shareanalysis.mapper.PriceMapper;
import com.fengtianhe.shareanalysis.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 同花顺大盘数据
 *
 * @author 冯天鹤
 * @date 2023/1/11.
 */
@Slf4j
@Component
public class ThxDashboardComponent {
    @Autowired
    PriceMapper priceMapper;

    /**
     * 获取上涨数据
     *
     * @return object
     */
    public Map<String, String> getRiseData() throws Exception {
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

    /**
     * 昨日涨停表现
     *
     * @return object
     */
    public Double getYesterdayRiseFallRate() {
//        String currDate = DatetimeUtil.format(DatetimeUtil.FORMAT_DATE_NO_SEPARATOR);
        String currDate = "20230120";
        List<PriceEntity> pricesByDate = priceMapper.getByDate(currDate);
        if (CollectionUtils.isEmpty(pricesByDate)) {
            log.info("[ThxDashboardComponent getYesterdayRiseFallRate] 昨日涨停表现 无昨日数据 date = {}", currDate);
            return 0d;
        }

        //一字涨停
        List<String> riseLimitDirect = new ArrayList<>();
        // 涨停
        List<String> riseTop = new ArrayList<>();
        //一字跌停
        List<String> fallLimitDirect = new ArrayList<>();

        //
        pricesByDate.forEach(priceEntity -> {
            if (priceEntity.isDirectRiseLimit()) {
                riseLimitDirect.add(priceEntity.getTsCode());
            }
            if (priceEntity.isDirectFallLimit()) {
                fallLimitDirect.add(priceEntity.getTsCode());
            }
        });

        return null;
    }
}
