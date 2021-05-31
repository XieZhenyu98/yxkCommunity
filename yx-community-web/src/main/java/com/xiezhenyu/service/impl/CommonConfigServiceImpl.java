package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.mapper.CommonConfigMapper;
import com.xiezhenyu.model.CommonConfigDo;
import com.xiezhenyu.query.CommonConfigQuery;
import com.xiezhenyu.service.ICommonConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Tim
 * @date 2021/5/30
 */
@Service
public class CommonConfigServiceImpl implements ICommonConfigService {

    @Autowired
    private CommonConfigMapper commonConfigMapper;

    @Override
    public CommonConfigDo getByPrefixAndKey(String prefix, String key) {
        CommonConfigDo commonConfigDo = commonConfigMapper.selectOne(new QueryWrapper<CommonConfigDo>().eq("prefix", prefix).eq("key", key));
        return commonConfigDo;
    }

    @Override
    public Page<CommonConfigDo> getPage(CommonConfigQuery commonConfigQuery) {
        Page<CommonConfigDo> commonConfigDoPage = commonConfigMapper.selectPage(
                new Page<>(commonConfigQuery.getPageNo(), commonConfigQuery.getPageSize()),
                new QueryWrapper<CommonConfigDo>()
                        .like("prefix", commonConfigQuery.getPrefix())
                        .like("config_key", commonConfigQuery.getConfigKey())
                        .like("description", commonConfigQuery.getDescription()));
        return commonConfigDoPage;
    }

    @Override
    public boolean addCommonConfig(CommonConfigDo commonConfigDo) {
        commonConfigDo.setGmtCreate(new Date());
        commonConfigDo.setGmtModify(new Date());
        commonConfigMapper.insert(commonConfigDo);
        return true;
    }

    @Override
    public boolean updateCommonConfig(CommonConfigDo commonConfigDo) {
        commonConfigDo.setGmtModify(new Date());
        commonConfigMapper.updateById(commonConfigDo);
        return true;
    }

    @Override
    public boolean deleteCommonConfig(CommonConfigDo commonConfigDo) {
        commonConfigMapper.deleteById(commonConfigDo.getId());
        return true;
    }
}
