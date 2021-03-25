package com.xiezhenyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiezhenyu.entity.QuartzJob;
import com.xiezhenyu.manager.QuartzManage;
import com.xiezhenyu.mapper.QuartzJobMapper;
import com.xiezhenyu.response.CommonResult;
import com.xiezhenyu.service.QuartzJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 关于Job实体类和数据库中对应表的操作
 * @author Tim
 * @date 2021/3/24
 */
@Slf4j
@Service
public class QuartzJobServiceImpl implements QuartzJobService, CommandLineRunner {

    @Autowired
    QuartzJobMapper quartzJobMapper;

    @Autowired
    QuartzManage quartzManage;


    @Override
    public void run(String... args) throws Exception {
        init();
    }

    public void init(){
        allStatusStop();
    }

    void allStatusStop(){
        QuartzJob job = new QuartzJob();
        job.setJobStatus(QuartzJob.STATUS_STOP);
        quartzJobMapper.update(job,null);
        List<QuartzJob> quartzJobs = followRunList();
        for(QuartzJob quartzJob : quartzJobs){
            try {
                quartzManage.addJob(quartzJob);
                quartzJob.setJobStatus(QuartzJob.STATUS_RUNNING);
                updateJob(quartzJob);
            } catch (Exception e) {
                log.error("启动任务"+quartzJob.getJobName()+"失败",e);
            }
        }
    }

    List<QuartzJob> followRunList(){
        return quartzJobMapper.selectList(new QueryWrapper<QuartzJob>().eq("start_up",QuartzJob.FOLLOW_RUN));
    }

    @Override
    public QuartzJob selectQuartzJob(Integer id) {
        QuartzJob quartzJob = quartzJobMapper.selectById(id);
        return quartzJob;
    }

    @Override
    public void updateLastRunTime(String jobName) {
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setJobName(jobName);
        quartzJob.setLastRunTime(new Date());
        quartzJobMapper.update(quartzJob,new QueryWrapper<QuartzJob>().eq("job_name",jobName));
    }

    @Override
    public void updateJob(QuartzJob quartzJob) {
        quartzJobMapper.updateById(quartzJob);
    }

    @Override
    public void addJob(QuartzJob quartzJob) {
        quartzJobMapper.insert(quartzJob);
    }

    @Override
    public CommonResult getTimerPages(Page page) {
        Page page1 = quartzJobMapper.selectPage(page, null);
        return CommonResult.successCommonResult(page1,"查询成功");
    }

    @Override
    public void deleteJob(Integer id) {
        quartzJobMapper.deleteById(id);
    }

}
