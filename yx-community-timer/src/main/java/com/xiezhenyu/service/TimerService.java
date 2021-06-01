package com.xiezhenyu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.entity.QuartzJob;
import com.xiezhenyu.query.QuartzJobQuery;
import com.xiezhenyu.response.CommonResult;
import io.swagger.models.auth.In;

/**
 * @author Tim
 * @date 2021/03/24
 */
public interface TimerService {

    /**
     * 添加定时任务
     * @param job
     * @return
     */
    CommonResult addTimer(QuartzJob job);

    /**
     * 启动定时任务
     * @param id 定时任务id
     * @return
     */
    CommonResult startTimer(Integer id);

    /**
     * 获取定时任务列表
     * @param limit 第几页
     * @param offset 偏移量
     * @return
     */
    CommonResult getTimerPages(Integer limit,Integer offset);

    /**
     * 停止定时任务
     * @param id 定时任务id
     * @return
     */
    CommonResult stopTimer(Integer id);

    /**
     * 删除一个定时任务
     * @param quartzJob
     * @return
     */
    CommonResult deleteTimer(QuartzJob quartzJob);

    /**
     * 暂停一个定时任务
     * @param quartzJob
     * @return
     */
    CommonResult pauseTimer(QuartzJob quartzJob);

    /**
     * 恢复一个定时任务
     * @param quartzJob
     * @return
     */
    CommonResult resumeTimer(QuartzJob quartzJob);

    /**
     * 定时任务分页
     * @param quartzJobQuery
     * @return
     */
    Page<QuartzJob> getPage(QuartzJobQuery quartzJobQuery);

    /**
     * 修改定时任务
     * @param quartzJob
     * @return
     */
    boolean updateQuartz(QuartzJob quartzJob);
}
