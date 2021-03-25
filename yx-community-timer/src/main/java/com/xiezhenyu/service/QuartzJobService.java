package com.xiezhenyu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.entity.QuartzJob;
import com.xiezhenyu.response.CommonResult;

/**
 * 关于Job实体类和数据库中对应表的操作
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

    /**
     * 更新job
     * @param quartzJob
     */
    void updateJob(QuartzJob quartzJob);

    /**
     * 添加job
     * @param quartzJob
     */
    void addJob(QuartzJob quartzJob);

    /**
     * 获取job列表
     * @param page
     * @return
     */
    CommonResult getTimerPages(Page page);

    /**
     * 删除job
     * @param id
     */
    void deleteJob(Integer id);
}
