package com.xiezhenyu.query;

import lombok.Data;

/**
 * @author Tim
 * @date 2021/5/30
 */
@Data
public class CommonConfigQuery {
    private Integer pageNo;
    private Integer pageSize;
    private String prefix;
    private String configKey;
    private String description;
}
