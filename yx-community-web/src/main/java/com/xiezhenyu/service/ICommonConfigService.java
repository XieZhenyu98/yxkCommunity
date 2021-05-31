package com.xiezhenyu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.model.CommonConfigDo;
import com.xiezhenyu.query.CommonConfigQuery;

/**
 * @author Tim
 */
public interface ICommonConfigService {

    /**
     * 通过前缀和key获取配置
     * @param prefix 前缀
     * @param key key值
     * @return
     */
    CommonConfigDo getByPrefixAndKey(String prefix, String key);

    /**
     * 分页获取系统环境变量配置
     * @param commonConfigQuery
     * @return
     */
    Page<CommonConfigDo> getPage(CommonConfigQuery commonConfigQuery);

    /**
     * 添加系统环境变量配置
     * @param commonConfigDo
     * @return
     */
    boolean addCommonConfig(CommonConfigDo commonConfigDo);

    /**
     * 修改系统环境变量配置
     * @param commonConfigDo
     * @return
     */
    boolean updateCommonConfig(CommonConfigDo commonConfigDo);

    /**
     * 删除系统环境变量配置
     * @param commonConfigDo
     * @return
     */
    boolean deleteCommonConfig(CommonConfigDo commonConfigDo);
}
