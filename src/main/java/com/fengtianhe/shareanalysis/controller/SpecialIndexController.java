package com.fengtianhe.shareanalysis.controller;

import com.fengtianhe.shareanalysis.entity.SpecialIndexDailyEntity;
import com.fengtianhe.shareanalysis.mapper.RiseFallPlateMapper;
import com.fengtianhe.shareanalysis.schedule.SpecialIndexDailySchedule;
import com.fengtianhe.shareanalysis.service.ISpecialIndexService;
import com.fengtianhe.vo.resp.SpecialIndexDailyResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 特殊指标
 *
 * @author 冯天鹤
 */
@RequestMapping("/api/special-index")
@RestController
public class SpecialIndexController {
    @Autowired
    ISpecialIndexService specialIndexService;
    @Autowired
    SpecialIndexDailySchedule schedule;
    @Autowired
    RiseFallPlateMapper riseFallPlateMapper;

    @PostMapping("/pagination")
    public PageInfo<SpecialIndexDailyResponse> pagination(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        page = page == null ? 1 : page;
        pageSize = pageSize == null ? 15 : pageSize;
        PageHelper.startPage(page, pageSize);
        List<SpecialIndexDailyEntity> specialIndexDailyEntities = specialIndexService.getByCondition();
        List<SpecialIndexDailyResponse> responses = new ArrayList<>();
        SpecialIndexDailyResponse response;
        for (SpecialIndexDailyEntity specialIndexDailyEntity : specialIndexDailyEntities) {
            response = new SpecialIndexDailyResponse();
            BeanUtils.copyProperties(specialIndexDailyEntity, response);
            response.setRiseFallPlateEntities(riseFallPlateMapper.getByDate(response.getDate()));

            responses.add(response);
        }

        return new PageInfo<>(responses);
    }

    @GetMapping("/execute")
    public Boolean execute() {
        schedule.execute();
        return true;
    }
}
