package com.fengtianhe.shareanalysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TemplateController {
    /**
     * 主页
     * @return
     */
    @RequestMapping(value = "/")
    public String index() {
        return "Index";
    }
    /**
     * 每日特殊指标
     * @return
     */
    @RequestMapping(value = "/special-index-daily")
    public String specialIndexDaily() {
        return "SpecialIndexDaily";
    }
}
