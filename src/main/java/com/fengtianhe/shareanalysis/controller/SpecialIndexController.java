package com.fengtianhe.shareanalysis.controller;

import com.fengtianhe.shareanalysis.entity.SpecialIndexDailyEntity;
import com.fengtianhe.shareanalysis.service.ISpecialIndexService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/pagination")
    public PageInfo<SpecialIndexDailyEntity> pagination(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        PageInfo<SpecialIndexDailyEntity> ret = specialIndexService.getWithPagination(page, pageSize);
        return ret;
    }
}
