package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.entity.QuartzJob;
import com.xiezhenyu.manager.QuartzManage;
import com.xiezhenyu.query.QuartzJobQuery;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.QuartzJobService;
import com.xiezhenyu.service.TimerService;
import org.quartz.SchedulerException;
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
        job.setJobStatus(QuartzJob.STATUS_STOP);
        job.setJobName(job.getClassName());
        job.setTriggerName(job.getClassName());
        quartzJobService.addJob(job);
        return CommonResult.successCommonResult("添加成功");
    }

    @Override
    public CommonResult startTimer(Integer id) {
        QuartzJob quartzJob = quartzJobService.selectQuartzJob(id);
        try {
            quartzManage.addJob(quartzJob);
            quartzJob.setJobStatus(QuartzJob.STATUS_RUNNING);
            quartzJobService.updateJob(quartzJob);
        } catch (Exception e) {
            return CommonResult.errorCommonResult(e.getMessage(),"启动失败");
        }
        return CommonResult.successCommonResult("启动成功");
    }

    @Override
    public CommonResult getTimerPages(Integer limit, Integer offset) {
        return quartzJobService.getTimerPages(new Page(limit,offset));
    }

    @Override
    public CommonResult stopTimer(Integer id){
        QuartzJob quartzJob = quartzJobService.selectQuartzJob(id);
        try {
            quartzManage.deleteJob(quartzJob);
            quartzJob.setJobStatus(QuartzJob.STATUS_STOP);
            quartzJobService.updateJob(quartzJob);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return CommonResult.errorCommonResult(e.getMessage(),"任务停止失败");
        }
        return CommonResult.successCommonResult("任务停止成功");
    }

    @Override
    public CommonResult deleteTimer(QuartzJob quartzJob) {
        try {
            QuartzJob job = quartzJobService.selectQuartzJob(quartzJob.getId());
            quartzManage.deleteJob(job);
            quartzJobService.deleteJob(job.getId());
        } catch (SchedulerException e) {
            return CommonResult.errorCommonResult(e,"任务删除失败");
        }
        return CommonResult.successCommonResult("任务删除成功");
    }

    @Override
    public CommonResult pauseTimer(QuartzJob quartzJob) {
        try {
            QuartzJob job = quartzJobService.selectQuartzJob(quartzJob.getId());
            quartzManage.pauseJob(job);
            job.setJobStatus(QuartzJob.STATUS_SUSPEND);
            quartzJobService.updateJob(job);
        } catch (SchedulerException e) {
            return CommonResult.errorCommonResult(e,"任务暂停失败");
        }
        return CommonResult.successCommonResult("任务暂停成功");
    }

    @Override
    public CommonResult resumeTimer(QuartzJob quartzJob) {
        try {
            QuartzJob job = quartzJobService.selectQuartzJob(quartzJob.getId());
            quartzManage.resumeJob(job);
            job.setJobStatus(QuartzJob.STATUS_RUNNING);
            quartzJobService.updateJob(job);
        } catch (SchedulerException e) {
            return CommonResult.errorCommonResult(e,"任务恢复失败");
        }
        return CommonResult.successCommonResult("任务恢复成功");
    }

    @Override
    public Page<QuartzJob> getPage(QuartzJobQuery quartzJobQuery) {
        Page<QuartzJob> page = quartzJobService.getPage(quartzJobQuery);
        return page;
    }

    @Override
    public boolean updateQuartz(QuartzJob quartzJob) {
        stopTimer(quartzJob.getId());
        quartzJob.setJobStatus(QuartzJob.STATUS_STOP);
        quartzJobService.updateJob(quartzJob);
        return true;
    }
}
