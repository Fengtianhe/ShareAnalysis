package com.fengtianhe.shareanalysis.dto.tonghuashun;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 问财响应体
 *
 * @author 冯天鹤
 */
@lombok.Data
public class WenCaiResponse {
    private Integer status_code;

    private Data data;

    private String status_msg;

    private String logid;

    @lombok.Data
    public static class Data {
        private JSONObject parser_data;

        private Boolean showSwitch;

        private Integer status_code;

        private String question;

        private Integer evaluateScore;

        private String question_id;

        private String version;

        private List<WenCaiRobotAnswerDTO> answer;

        private String user_id;

        private String rewriteQuestion;

        private String status_msg;

        private String logid;

        private JSONArray logs;
    }
}
