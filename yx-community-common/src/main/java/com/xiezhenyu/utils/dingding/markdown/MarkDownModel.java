package com.xiezhenyu.utils.dingding.markdown;

import lombok.Data;

/**
 * @author Tim
 * @date 2021/6/1
 */
@Data
public class MarkDownModel {
    /**
     * 首屏会话透出的展示内容
     */
    private String title;

    /**
     * markdown格式的消息
     */
    private String text;
}
