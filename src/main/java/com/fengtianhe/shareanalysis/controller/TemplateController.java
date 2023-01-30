package com.fengtianhe.shareanalysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TemplateController {
    /**
     * 每日特殊指标
     * @return
     */
    @RequestMapping(value = "/special-index-daily", method = RequestMethod.GET)
    public String specialIndexDaily() {
        return "SpecialIndexDaily";
    }
}
