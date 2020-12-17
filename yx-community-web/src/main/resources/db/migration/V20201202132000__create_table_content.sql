-- ----------------------------
-- Table structure for content
-- ----------------------------
CREATE TABLE IF NOT EXISTS `content`  (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `module_id` bigint(20) NOT NULL COMMENT '所属板块',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发帖时间',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '发帖人',
  `times` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '阅读数',
  `topping` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '置顶',
  `marvellous` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '加精',
  `adopt_reply_id` bigint(20) NULL DEFAULT NULL COMMENT '采纳回复的id',
  `money` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '价值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic COMMENT='帖子内容表';
