-- ----------------------------
-- Table structure for registration
-- ----------------------------
CREATE TABLE IF NOT EXISTS `registration`  (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `date` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '时间',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '内容',
  `user_id` bigint(20) NOT NULL COMMENT '用户编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB CHARACTER SET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=Dynamic COMMENT='签到表';