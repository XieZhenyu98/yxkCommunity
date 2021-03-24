package com.xiezhenyu.service.impl;

import com.xiezhenyu.entity.QuartzJob;
import com.xiezhenyu.manager.QuartzManage;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.QuartzJobService;
import com.xiezhenyu.service.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tim
 * @date 2021/3/24
 */
@Service
public class TimerServiceImpl implements TimerService {
    @Autowired
    private QuartzJobService quartzJobService;
    @Autowired
    private QuartzManage quartzManage;
    @Override
    public CommonResult addTimer(QuartzJob job) {

        return null;
    }

    @Override
    public CommonResult startTimer(Integer id) {
        QuartzJob quartzJob = quartzJobService.selectQuartzJob(id);
        try {
            quartzManage.addJob(quartzJob);
        } catch (Exception e) {
            return CommonResult.errorCommonResult(e.getMessage(),"启动失败");
        }
        return CommonResult.successCommonResult("启动成功");
    }
}
