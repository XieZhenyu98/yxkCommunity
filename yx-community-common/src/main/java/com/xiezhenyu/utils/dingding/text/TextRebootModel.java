package com.xiezhenyu.utils.dingding.text;

import lombok.Data;

/**
 * @author Tim
 * @date 2021/6/1
 */
@Data
public class TextRebootModel {
    /**
     * 此消息类型为固定text
     */
    public String msgtype = "text";

    public ContentModel text;

    public AtMobiles at;
}
