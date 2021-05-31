package com.xiezhenyu.query;

import lombok.Data;

/**
 * @author Tim
 * @date 2021/5/30
 */
@Data
public class UserQuery {
    private String username;
    private String email;
    private Integer pageNo;
    private Integer pageSize;
    private Integer isDelete;
}
