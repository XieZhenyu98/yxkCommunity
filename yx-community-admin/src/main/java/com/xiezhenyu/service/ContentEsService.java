package com.xiezhenyu.service;

import java.util.List;
import java.util.Map;

/**
 * @author Tim
 */
public interface ContentEsService {

    /**
     * 将mysql中的数据更新到Es中去
     * @return
     */
    boolean contentToEs();

    /**
     * 通过关键字搜索
     * @param keywords 关键字
     * @param pageNo   第几页
     * @param pageSize 每页大小
     * @return
     */
    List<Map<String,Object>> searchPage(String keywords, int pageNo, int pageSize);

}
