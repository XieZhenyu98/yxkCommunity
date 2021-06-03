package com.xiezhenyu.construct;


/**
 * @author Tim
 * @date 2021/5/30
 */
public class CommonConfig {
    /**
     * 用户
     */
    // 用户配置前缀
    public static final String USER_PREFIX = "user";
    // 用户默认密码
    public static final String USER_DEFAULT_PASSWORD_KEY = "default_password";

    /**
     * 钉钉
     */
    // 钉钉配置前缀
    public static final String DING_PREFIX = "ding";
    // 监控报警机器人secret
    public static final String DING_ALARM_REBOOT_SECRET_KEY = "alarm_reboot_secret";
    // 监控报警机器人webhook
    public static final String DING_ALARM_REBOOT_WEBHOOK_KEY = "alarm_reboot_webhook";
    // 定时任务提醒机器人secret
    public static final String DING_TIMER_REBOOT_SECRET_KEY = "timer_reboot_secret";
    // 定时任务提醒机器人webhook
    public static final String DING_TIMER_REBOOT_WEBHOOK_KEY = "timer_reboot_webhook";
}
