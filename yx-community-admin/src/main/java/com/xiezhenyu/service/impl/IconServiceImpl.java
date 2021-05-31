package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.mapper.IconMapper;
import com.xiezhenyu.model.admin.Icon;
import com.xiezhenyu.query.IconQuery;
import com.xiezhenyu.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Tim
 * @date 2021/5/30
 */
@Service
public class IconServiceImpl implements IconService {

    @Autowired
    private IconMapper iconMapper;

    @Override
    public Page<Icon> getPage(IconQuery iconQuery) {
        Page<Icon> iconPage = iconMapper.selectPage(new Page<>(iconQuery.getPageNo(), iconQuery.getPageSize()),
                new QueryWrapper<Icon>().like("icon_code", iconQuery.getIconCode())
                        .like("icon_description", iconQuery.getIconDescription()));
        return iconPage;
    }

    @Override
    public boolean addIcon(Icon icon) {
        icon.setCtime(new Date());
        icon.setUtime(new Date());
        iconMapper.insert(icon);
        return true;
    }

    @Override
    public boolean updateIcon(Icon icon) {
        icon.setUtime(new Date());
        iconMapper.updateById(icon);
        return true;
    }

    @Override
    public boolean deleteIcon(Icon icon) {
        iconMapper.deleteById(icon.getId());
        return true;
    }
}
