package com.xiezhenyu.service;

/**
 * @author Tim
 */
public interface ContentToEsService {

    /**
     * 将mysql中的数据更新到Es中去
     * @return
     */
    boolean contentToEs();

}
