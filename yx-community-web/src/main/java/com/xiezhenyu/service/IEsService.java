package com.xiezhenyu.service;

import com.xiezhenyu.model.ContentDo;

import java.util.List;
import java.util.Map;

/**
 * @author Tim
 */
public interface IEsService {

    /**
     * 通过关键字查找帖子
     * @param keywords 关键字
     * @param pageNo 第几页
     * @param pageSize 每页大小
     * @return
     */
    List<Map<String,Object>> getContentByKeywords(String keywords, Integer pageNo, Integer pageSize);

}
