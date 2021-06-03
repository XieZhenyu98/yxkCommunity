package com.xiezhenyu.utils.dingding.text;

import lombok.Data;

import java.util.List;

/**
 * @author Tim
 * @date 2021/6/1
 */
@Data
public class AtMobiles {

    /**
     * 被@人的手机号
     * @return
     */
    private List<String> atMobiles;

    /**
     * @所有人时:true,否则为:false
     */
    private Boolean isAtAll;
}
