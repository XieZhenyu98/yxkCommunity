package com.xiezhenyu.service;

import com.xiezhenyu.entity.QuartzJob;

/**
 * @author Tim
 * @date 2021/03/24
 */
public interface QuartzJobService {
    /**
     * 通过id查询定时任务
     * @param id
     * @return
     */
    QuartzJob selectQuartzJob(Integer id);

    /**
     * 通过jobName修改最后执行的时间为当前时间
     * @param jobName
     */
    void updateLastRunTime(String jobName);
}
