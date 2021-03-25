package com.xiezhenyu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Tim
 * @date 2021/3/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("quartz_job")
public class QuartzJob {

    /**
     * 任务状态
     * 0代表停止状态；
     * 1代表运行状态。
     */
    public static final Integer STATUS_RUNNING = 2;
    public static final Integer STATUS_SUSPEND = 1;
    public static final Integer STATUS_STOP = 0;

    /**
     * 任务类型
     * 0代表可以编辑，可以启动和停止；
     * 1代表不可以编辑，可以启动和停止；
     * 2代表可以编辑，不可以启动和停止；
     * 3代表不可以编辑，不可以启动和停止。
     */
    public static final Integer CAN_EDIT_CAN_RUN_AND_STOP = 0;
    public static final Integer NOT_EDIT_CAN_RUN_AND_STOP = 1;
    public static final Integer CAN_EDIT_NOT_RUN_AND_STOP = 2;
    public static final Integer NOT_EDIT_NOT_RUN_AND_STOP = 3;

    /**
     * 是否随着项目启动
     * 0代表不随着项目启动；
     * 1代表随着项目启动；
     */
    public static final Integer FOLLOW_RUN = 1;
    public static final Integer NOT_FOLLOW_RUN = 0;

    /**
     * 定时任务id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 定时任务名称
     */
    @TableField("name")
    private String name;

    /**
     * 定时任务包名.定时任务类名
     */
    @TableField("class_name")
    private String className;

    /**
     * 定时任务名
     */
    @TableField("job_name")
    private String jobName;

    /**
     * 定时任务组名
     */
    @TableField("job_group")
    private String jobGroup;

    /**
     * 触发器名
     */
    @TableField("trigger_name")
    private String triggerName;

    /**
     * 触发器组
     */
    @TableField("trigger_group")
    private String triggerGroup;

    /**
     * 触发器类型,0代表cronTrigger，1代表simpleTrigger
     */
    @TableField("trigger_type")
    private Integer triggerType;

    /**
     * cron表达式
     */
    @TableField("cron_expression")
    private String cronExpression;

    /**
     * simpleTrigger的间隔时间，秒
     */
    @TableField("simple_trigger_interval")
    private Integer simpleTriggerInterval;

    /**
     * simpleTrigger的运行次数
     */
    @TableField("simple_trigger_count")
    private Integer simpleTriggerCount;

    /**
     * 延时
     */
    @TableField("delay")
    private Integer delay;

    /**
     * 任务的类型，
     * 0代表可以编辑，可以启动和停止；
     * 1代表不可以编辑，可以启动和停止；
     * 2代表可以编辑，不可以启动和停止；
     * 3代表不可以编辑，不可以启动和停止。
     */
    @TableField("type")
    private Integer type;

    /**
     * 任务的状态，0代表停止状态；1代表运行状态。
     */
    @TableField("job_status")
    private Integer jobStatus;

    /**
     * 任务的描述
     */
    @TableField("description")
    private String description;

    /**
     * 任务的创建时间
     */
    @TableField("create_time")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 任务的修改时间
     */
    @TableField("update_time")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 上次运行的时间
     */
    @TableField("last_run_time")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastRunTime;

    /**
     * 是否随着项目启动，0代表不随着项目启动，1代表随着项目启动
     */
    @TableField("start_up")
    private Integer startUp;
}
