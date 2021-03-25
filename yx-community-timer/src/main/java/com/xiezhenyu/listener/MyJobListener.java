package com.xiezhenyu.listener;

import com.xiezhenyu.service.QuartzJobService;
import com.xiezhenyu.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * @author Tim
 * @date 2021/3/24
 */
@Slf4j
public class MyJobListener implements JobListener {
    private String name;
    @Override
    public String getName() {
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    /**
     * 执行前
     * @param jobExecutionContext
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        log.debug(jobExecutionContext.getJobDetail().getJobClass()+" start to run");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        if(e == null){
            // 任务正常结束
            log.debug(jobExecutionContext.getJobDetail().getJobClass()+" has finished");
            QuartzJobService quartzJobService = SpringUtil.getBean(QuartzJobService.class);
            quartzJobService.updateLastRunTime(jobExecutionContext.getJobDetail().getKey().getName());
        }else {
            log.error(e+" job has exception");
        }
    }
}
