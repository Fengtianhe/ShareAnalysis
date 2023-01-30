package com.fengtianhe.shareanalysis.spider.tonghuashun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fengtianhe.shareanalysis.dto.tonghuashun.WenCaiResponse;
import com.fengtianhe.shareanalysis.dto.tonghuashun.WenCaiRobotAnswerTableDataDTO;
import com.fengtianhe.shareanalysis.entity.PriceEntity;
import com.fengtianhe.shareanalysis.mapper.PriceMapper;
import com.fengtianhe.shareanalysis.utils.HttpClientUtil;
import com.fengtianhe.shareanalysis.utils.WenCaiUtil;
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

    /**
     * 涨停数量
     *
     * @return object
     */
    public Integer getRiseLimitCnt() throws Exception {
        WenCaiResponse.Data wencaiData = WenCaiUtil.getRebotData("涨停");
        WenCaiRobotAnswerTableDataDTO tableData = wencaiData.getAnswer().get(0).getTableData();
        Integer rowCount = tableData.getMeta().getExtra().getRow_count();
        log.info("[同花顺 问财：涨停数量] count = {}", rowCount);
        return rowCount;
    }

    /**
     * 跌停数量
     *
     * @return object
     */
    public Integer getFallLimitCnt() throws Exception {
        WenCaiResponse.Data wencaiData = WenCaiUtil.getRebotData("跌停");
        WenCaiRobotAnswerTableDataDTO tableData = wencaiData.getAnswer().get(0).getTableData();
        Integer rowCount = tableData.getMeta().getExtra().getRow_count();
        log.info("[同花顺 问财：跌停数量] count = {}", rowCount);
        return rowCount;
    }

    /**
     * 一字涨停数量
     *
     * @return object
     */
    public Integer getDirectRiseLimitCnt() throws Exception {
        WenCaiResponse.Data wencaiData = WenCaiUtil.getRebotData("一字涨停");
        WenCaiRobotAnswerTableDataDTO tableData = wencaiData.getAnswer().get(0).getTableData();
        Integer rowCount = tableData.getMeta().getExtra().getRow_count();
        log.info("[同花顺 问财：一字涨停数量] count = {}", rowCount);
        return rowCount;
    }

    /**
     * 一字跌停数量
     *
     * @return object
     */
    public Integer getDirectFallLimitCnt() throws Exception {
        WenCaiResponse.Data wencaiData = WenCaiUtil.getRebotData("一字跌停");
        WenCaiRobotAnswerTableDataDTO tableData = wencaiData.getAnswer().get(0).getTableData();
        Integer rowCount = tableData.getMeta().getExtra().getRow_count();
        log.info("[同花顺 问财：一字跌停数量] count = {}", rowCount);
        return rowCount;
    }
}
