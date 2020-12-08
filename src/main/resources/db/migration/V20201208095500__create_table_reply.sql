-- ----------------------------
-- Table structure for reply
-- ----------------------------
CREATE TABLE IF NOT EXISTS `reply`  (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `content_id` bigint(20) NOT NULL COMMENT '帖子编号',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '内容',
  `quote_id` bigint(20) NOT NULL COMMENT '引用回复ID',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '回复时间',
  `user_id` bigint(20) NOT NULL COMMENT '回复人ID'
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB CHARACTER SET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=Dynamic COMMENT='回复';