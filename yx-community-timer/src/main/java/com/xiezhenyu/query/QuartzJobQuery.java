package com.xiezhenyu.query;

import lombok.Data;

/**
 * @author Tim
 * @date 2021/6/1
 */
@Data
public class QuartzJobQuery {
    private Integer pageNo;
    private Integer pageSize;
    private String name;
    private Integer triggerType;
}
