package com.fengtianhe.shareanalysis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fengtianhe.shareanalysis.service.ICommonShareService;
import com.fengtianhe.shareanalysis.utils.DatetimeUtil;
import com.fengtianhe.shareanalysis.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class CommonShareServiceImpl implements ICommonShareService {
    /**
     * 是否开盘
     *
     * @return 不是周末 && 是工作日
     * @throws IOException http请求异常
     */
    @Override
    public boolean isOpening() throws IOException {
        String date = DatetimeUtil.format(DatetimeUtil.FORMAT_DATE_NO_SEPARATOR);
        HttpClientUtil clientUtil = new HttpClientUtil();
        String response = clientUtil.get("https://api.apihubs.cn/holiday/get?cn=1&date=" + date).execute();
        JSONObject jsonObject = JSONObject.parseObject(response);
        int weekend = jsonObject.getIntValue("weekend");
        int workday = jsonObject.getIntValue("workday");
        boolean et = weekend == 2 && workday == 1;
        if (et) {
            log.info("[CommonShareServiceImpl isOpening] 日期{}为开盘日", date);
        } else {
            log.info("[CommonShareServiceImpl isOpening] 日期{}为非开盘日", date);
        }
        return et;
    }
}
