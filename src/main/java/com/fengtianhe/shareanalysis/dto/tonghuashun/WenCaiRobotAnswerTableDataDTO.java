package com.fengtianhe.shareanalysis.dto.tonghuashun;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

/**
 * 问财答案列表数据
 *
 * @author 冯天鹤
 */
@Data
public class WenCaiRobotAnswerTableDataDTO {
    private JSONArray datas;

    private Meta meta;

    @Data
    public static class Meta {
        private Extra extra;

        @Data
        public static class Extra {
            private Integer row_count;
        }
    }
}
