package com.fengtianhe.shareanalysis.schedule;

import com.fengtianhe.shareanalysis.dto.xueqiu.XqRealtimeQuotationDTO;
import com.fengtianhe.shareanalysis.entity.NameEntity;
import com.fengtianhe.shareanalysis.entity.PriceEntity;
import com.fengtianhe.shareanalysis.mapper.NameMapper;
import com.fengtianhe.shareanalysis.mapper.PriceMapper;
import com.fengtianhe.shareanalysis.spider.xueqiu.XqBasicComponent;
import com.fengtianhe.shareanalysis.utils.DatetimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 每日价格同步
 *
 * @author 冯天鹤
 * @date 2023/1/13.
 */
@Slf4j
@Component
public class DailyPriceSyncSchedule {
    @Autowired
    XqBasicComponent xqBasicComponent;
    @Autowired
    NameMapper nameMapper;
    @Autowired
    PriceMapper priceMapper;

    @Scheduled(cron = "0 0 16 * * *")
    public void execute() {
        try {
            long st = System.currentTimeMillis();
            String date = DatetimeUtil.format(DatetimeUtil.FORMAT_DATE_NO_SEPARATOR);

            List<NameEntity> shareList = nameMapper.getAll();
            for (NameEntity share : shareList) {
                String code = share.getCode();
                XqRealtimeQuotationDTO realtimeQuotation = xqBasicComponent.getRealtimeQuotation(code);

                PriceEntity priceEntity = priceMapper.getByNameAndDate(code, date);
                if (priceEntity == null) {
                    savePriceEntity(code, date, realtimeQuotation);
                } else {
                    updatePriceEntity(code, date, realtimeQuotation, priceEntity);
                }
            }

            log.info("[DailyPriceSyncSchedule execute] 每日价格同步 date = {} 耗时 = {}ms", date, System.currentTimeMillis() - st);
        } catch (Exception e) {
            log.error("[DailyPriceSyncSchedule execute]", e);
        }
    }

    private void savePriceEntity(String shareCode, String date, XqRealtimeQuotationDTO realtimeQuotation) {
        PriceEntity entity = new PriceEntity();
        entity.setTsCode(shareCode);
        entity.setTradeDate(date);
        entity.setOpen(realtimeQuotation.getOpen());
        entity.setHigh(realtimeQuotation.getHigh());
        entity.setLow(realtimeQuotation.getLow());
        entity.setClose(realtimeQuotation.getCurrent());
        entity.setPreClose(realtimeQuotation.getLast_close());
        entity.setChange(realtimeQuotation.getChg());
        entity.setPctChg(realtimeQuotation.getPercent());
        entity.setVol(realtimeQuotation.getVolume() / 100.00);
        entity.setAmount(realtimeQuotation.getAmount() / 1000.00);
        entity.setCurrentYearPercent(realtimeQuotation.getCurrent_year_percent());
        priceMapper.save(entity);
    }

    private void updatePriceEntity(String shareCode, String date, XqRealtimeQuotationDTO realtimeQuotation, PriceEntity entity) {
        entity.setOpen(realtimeQuotation.getOpen());
        entity.setHigh(realtimeQuotation.getHigh());
        entity.setLow(realtimeQuotation.getLow());
        entity.setClose(realtimeQuotation.getCurrent());
        entity.setPreClose(realtimeQuotation.getLast_close());
        entity.setChange(realtimeQuotation.getChg());
        entity.setPctChg(realtimeQuotation.getPercent());
        entity.setVol(realtimeQuotation.getVolume() / 100.00);
        entity.setAmount(Double.valueOf(realtimeQuotation.getAmount()));
        entity.setCurrentYearPercent(realtimeQuotation.getCurrent_year_percent());
        priceMapper.update(entity);
    }
}
