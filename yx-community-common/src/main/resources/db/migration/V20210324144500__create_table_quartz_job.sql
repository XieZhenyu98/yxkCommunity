-- ----------------------------
-- Table structure for registration
-- ----------------------------
CREATE TABLE IF NOT EXISTS `quartz_job`  (
  `id` bigint(20) NOT NULL COMMENT '定时任务id',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '任务名',
  `class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '定时任务包.类名',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '定时任务名',
  `job_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '定时任务组',
  `trigger_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '触发器名',
  `trigger_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '触发器组',
  `trigger_type` int(11) DEFAULT NULL COMMENT '触发器类型,0代表cronTrigger，1代表simpleTrigger',
  `cron_expression` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'cron表达式',
  `simple_trigger_interval` int(16) NULL COMMENT 'simpleTrigger的间隔时间，秒',
  `simple_trigger_count` int(16) NULL COMMENT 'simpleTrigger的运行次数',
  `delay` int(16) NULL COMMENT '延时',
  `type` int(11) NULL DEFAULT 2 COMMENT '任务的类型，0代表可以编辑，可以启动和停止；1代表不可以编辑，可以启动和停止；2代表可以编辑，不可以启动和停止；3代表不可以编辑，不可以启动和停止。',
  `job_status` int(11) NULL COMMENT '任务的状态，0代表停止状态；1代表暂停状态；2代表运行状态',
  `description` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '任务的描述',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '任务的创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '任务的修改时间',
  `last_run_time` timestamp NULL COMMENT '上次运行的时间',
  `start_up` int(11) NULL COMMENT '是否随着项目启动，0代表不随着项目启动，1代表随着项目启动',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB CHARACTER SET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=Dynamic COMMENT='定时任务表';