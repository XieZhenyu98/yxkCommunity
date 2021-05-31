package com.xiezhenyu.query;

import com.xiezhenyu.model.admin.Menu;
import com.xiezhenyu.model.admin.Role;
import lombok.Data;

/**
 * @author Tim
 * @date 2021/5/30
 */
@Data
public class RoleMenuRelationQuery {
    private Role role;
    private Menu menu;
    private Integer pageNo;
    private Integer pageSize;
}
