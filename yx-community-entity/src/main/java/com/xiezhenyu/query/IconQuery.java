package com.xiezhenyu.query;

import lombok.Data;

/**
 * @author Tim
 * @date 2021/5/30
 */
@Data
public class IconQuery {
    private Integer pageNo;
    private Integer pageSize;
    private String iconCode;
    private String iconDescription;
}
