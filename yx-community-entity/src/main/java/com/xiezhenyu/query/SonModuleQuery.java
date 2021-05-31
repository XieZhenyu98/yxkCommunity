package com.xiezhenyu.query;

import lombok.Data;

/**
 * @author Tim
 * @date 2021/5/31
 */
@Data
public class SonModuleQuery {
    private Integer pageNo;
    private Integer pageSize;
    private String moduleName;
    private Long fatherModuleId;
}
