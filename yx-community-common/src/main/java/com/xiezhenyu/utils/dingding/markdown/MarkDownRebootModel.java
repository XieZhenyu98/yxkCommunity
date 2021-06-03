package com.xiezhenyu.utils.dingding.markdown;

import com.xiezhenyu.utils.dingding.text.AtMobiles;
import lombok.Data;

/**
 * @author Tim
 * @date 2021/6/1
 */
@Data
public class MarkDownRebootModel {
    /**
     * 此消息类型为固定markdown
     */
    public String msgtype = "markdown";

    public MarkDownModel markdown;

    public AtMobiles at;
}
