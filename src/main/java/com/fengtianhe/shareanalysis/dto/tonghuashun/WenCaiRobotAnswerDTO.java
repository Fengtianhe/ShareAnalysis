package com.fengtianhe.shareanalysis.dto.tonghuashun;

import lombok.Data;

import java.util.List;

/**
 * 问财答案
 *
 * @author 冯天鹤
 */
@Data
public class WenCaiRobotAnswerDTO {
    private List<Txt> txt;

    @Data
    public static class Txt {
        private Content content;

        @Data
        private static class Content {
            private List<Component> components;

            @Data
            private static class Component {
                private WenCaiRobotAnswerTableDataDTO data;
            }
        }
    }

    public WenCaiRobotAnswerTableDataDTO getTableData() {
        return this.getTxt().get(0).getContent().getComponents().get(0).getData();
    }
}
