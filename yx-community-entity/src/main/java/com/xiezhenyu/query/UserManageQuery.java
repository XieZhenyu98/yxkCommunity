package com.xiezhenyu.query;

import lombok.Data;

/**
 * @author Tim
 * @date 2021/5/30
 */
@Data
public class UserManageQuery {
    private Integer pageNo;
    private Integer pageSize;
    private String name;
    private Long roleId;
}
