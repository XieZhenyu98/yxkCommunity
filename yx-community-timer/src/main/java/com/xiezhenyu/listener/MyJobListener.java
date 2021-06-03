package com.xiezhenyu.listener;

import com.xiezhenyu.construct.CommonConfig;
import com.xiezhenyu.model.CommonConfigDo;
import com.xiezhenyu.service.ICommonConfigService;
import com.xiezhenyu.service.QuartzJobService;
import com.xiezhenyu.utils.SpringUtil;
import com.xiezhenyu.utils.dingding.RebootUtil;
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
        String message = "【黑龙江科技大学社区】 \n 定时任务: " + jobExecutionContext.getJobDetail().getJobClass() + ", 执行开始！";
        sendMessage(message);
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
            String message = "【黑龙江科技大学社区】 \n 定时任务: " + jobExecutionContext.getJobDetail().getJobClass() + ", 执行结束！";
            sendMessage(message);
        }else {
            String message = "【黑龙江科技大学社区】 \n 定时任务: " + jobExecutionContext.getJobDetail().getJobClass() + ", 执行失败！";
            sendMessage(message);
            log.error(e+" job has exception");
        }
    }

    public void sendMessage(String message) {
        ICommonConfigService commonConfigService = SpringUtil.getBean(ICommonConfigService.class);
        CommonConfigDo timerSecret = commonConfigService.getByPrefixAndKey(CommonConfig.DING_PREFIX, CommonConfig.DING_TIMER_REBOOT_SECRET_KEY);
        CommonConfigDo timerWebhook = commonConfigService.getByPrefixAndKey(CommonConfig.DING_PREFIX, CommonConfig.DING_TIMER_REBOOT_WEBHOOK_KEY);
        String secret = timerSecret.getConfigValue();
        String webWebhook = timerWebhook.getConfigValue();
        RebootUtil.sendReboot(secret,webWebhook,message,false,null);
    }
}
