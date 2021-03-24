package com.xiezhenyu.service;

import com.xiezhenyu.entity.QuartzJob;
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

}
