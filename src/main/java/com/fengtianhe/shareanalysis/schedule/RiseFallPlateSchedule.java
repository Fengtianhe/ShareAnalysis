package com.fengtianhe.shareanalysis.schedule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fengtianhe.shareanalysis.dto.tonghuashun.WenCaiResponse;
import com.fengtianhe.shareanalysis.dto.tonghuashun.WenCaiRobotAnswerTableDataDTO;
import com.fengtianhe.shareanalysis.entity.RiseFallPlateEntity;
import com.fengtianhe.shareanalysis.entity.SpecialIndexDailyEntity;
import com.fengtianhe.shareanalysis.mapper.RiseFallPlateMapper;
import com.fengtianhe.shareanalysis.mapper.SpecialIndexDailyMapper;
import com.fengtianhe.shareanalysis.service.ICommonShareService;
import com.fengtianhe.shareanalysis.spider.tonghuashun.ThxDashboardComponent;
import com.fengtianhe.shareanalysis.utils.DatetimeUtil;
import com.fengtianhe.shareanalysis.utils.WenCaiUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 涨跌板块数据
 *
 * @author 冯天鹤
 * @date 2023/1/11.
 */
@Slf4j
@Component
public class RiseFallPlateSchedule {
    @Autowired
    RiseFallPlateMapper riseFallPlateMapper;
    @Autowired
    ICommonShareService commonShareService;

    /**
     * 每天4点获取
     */
    @Scheduled(cron = "0 0 16 * * *")
    public void execute() {
        String date = DatetimeUtil.format(DatetimeUtil.FORMAT_DATE_NO_SEPARATOR);

        try {
            if (!commonShareService.isOpening()) {
                log.info("[RiseFallPlateSchedule execute] 未开盘，不获取当天数据");
                return;
            }

            WenCaiResponse.Data wencaiData = WenCaiUtil.getRebotData("涨停");
            WenCaiRobotAnswerTableDataDTO tableData = wencaiData.getAnswer().get(0).getTableData();
            JSONArray datas = tableData.getDatas();
            Map<String, List<String>> categoryMap = new HashMap<>();
            for (Object data : datas) {
                JSONObject jsonObject = (JSONObject) data;
                //例子：涨停原因类别[20230202] -> 小金属+钨+稀缺资源
                String categoryStr = jsonObject.getString("涨停原因类别[" + date + "]");
                if (StringUtils.isEmpty(categoryStr)) {
                    categoryStr = "--";
                }
                String[] categories = categoryStr.split("\\+");
                //例子：股票代码 -> 002842.SZ
                String code = commonShareService.covertShareCodeByPoint(jsonObject.getString("股票代码"));
                for (String category : categories) {
                    categoryMap.computeIfAbsent(category, k -> new ArrayList<>());
                    if (!categoryMap.get(category).contains(code)) {
                        categoryMap.get(category).add(code);
                    }
                }
            }

            // 清除历史数据
            riseFallPlateMapper.deleteByDate(date);

            RiseFallPlateEntity entity = null;
            for (String category : categoryMap.keySet()) {
                entity = new RiseFallPlateEntity();
                entity.setDate(date);
                entity.setPlateName(category);
                entity.setShares(String.join(",", categoryMap.get(category)));
                log.info("[RiseFallPlateSchedule execute] 保存涨停盘板块数据 entity = {}", JSON.toJSONString(entity));
                riseFallPlateMapper.save(entity);
            }
        } catch (Exception e) {
            log.error("[RiseFallPlateSchedule] 异常", e);
        }
    }
}
