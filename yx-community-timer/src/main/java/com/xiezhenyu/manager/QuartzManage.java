package com.xiezhenyu.manager;

import com.xiezhenyu.entity.QuartzJob;
import com.xiezhenyu.listener.MyJobListener;
import org.quartz.*;
import org.quartz.impl.matchers.KeyMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author Tim
 * @date 2021/3/24
 */
@Component
public class QuartzManage {
    @Autowired
    Scheduler scheduler;

    /**
     * 添加job(启动job)
     * @param job
     * @throws Exception
     */
    public void addJob(QuartzJob job) throws Exception {
        Class<?> clazz = Class.forName(job.getClassName());
        Job jobPojo = (Job)clazz.newInstance();
        JobDetail jobDetail = newJob(jobPojo.getClass()).withIdentity(job.getJobName()).build();
        Optional<Integer> delay = Optional.ofNullable(job.getDelay());
        Date startTime = DateBuilder.futureDate(delay.orElse(0), DateBuilder.IntervalUnit.SECOND);
        Trigger trigger;
        if(job.getTriggerType() == 0 && !job.getCronExpression().equals("") && job.getCronExpression()!=null){
            trigger = newTrigger()
                    .withIdentity(job.getTriggerName())
                    .startAt(startTime)
                    .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
                    .build();
        }else if(job.getTriggerType() == 1 && job.getSimpleTriggerInterval() > 0){
            if(job.getSimpleTriggerCount()>0){
                trigger = newTrigger()
                        .withIdentity(job.getTriggerName())
                        .startAt(startTime)
                        .withSchedule(simpleSchedule().withRepeatCount(job.getSimpleTriggerCount()-1).withIntervalInSeconds(job.getSimpleTriggerInterval()))
                        .build();
            }else{
                trigger = newTrigger()
                        .withIdentity(job.getTriggerName())
                        .startAt(startTime)
                        .withSchedule(simpleSchedule().repeatForever().withIntervalInSeconds(job.getSimpleTriggerInterval()))
                        .build();
            }
        }else {
            throw new IllegalArgumentException();
        }
        MyJobListener myJobListener = new MyJobListener();
        myJobListener.setName(jobDetail.getKey().getName());
        KeyMatcher<JobKey> jobKeyKeyMatcher = KeyMatcher.keyEquals(jobDetail.getKey());
        scheduler.getListenerManager().addJobListener(myJobListener,jobKeyKeyMatcher);
        // 执行
        scheduler.scheduleJob(jobDetail,trigger);
    }

    /**
     * 删除一个job
     * @param quartzJob job实体类
     * @return 是否删除成功
     * @throws SchedulerException
     */
    public boolean deleteJob(QuartzJob quartzJob) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(quartzJob.getJobName());
        return scheduler.deleteJob(jobKey);
    }

    /**
     * 暂停一个job
     * @param quartzJob job实体类
     * @throws SchedulerException
     */
    public void pauseJob(QuartzJob quartzJob) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(quartzJob.getJobName());
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复一个job
     * @param quartzJob job实体类
     * @throws SchedulerException
     */
    public void resumeJob(QuartzJob quartzJob) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(quartzJob.getJobName());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 更新job的cron表达式
     * @param quartzJob job实体类
     * @throws SchedulerException
     */
    public void updateJobCron(QuartzJob quartzJob) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(quartzJob.getTriggerName());
        CronTrigger cronTrigger = (CronTrigger)scheduler.getTrigger(triggerKey);
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression());
        CronTrigger trigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
        scheduler.rescheduleJob(triggerKey,trigger);
    }
}
