package com.xiezhenyu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.model.admin.Icon;
import com.xiezhenyu.query.IconQuery;

/**
 * @author Tim
 */
public interface IconService {

    /**
     * 图标分页
     * @param iconQuery
     * @return
     */
    Page<Icon> getPage(IconQuery iconQuery);

    /**
     * 添加图标
     * @param icon
     * @return
     */
    boolean addIcon(Icon icon);

    /**
     * 修改图标
     * @param icon
     * @return
     */
    boolean updateIcon(Icon icon);

    /**
     * 删除图标
     * @param icon
     * @return
     */
    boolean deleteIcon(Icon icon);

}
