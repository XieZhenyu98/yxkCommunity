-- ----------------------------
-- Table structure for son_module
-- ----------------------------
CREATE TABLE IF NOT EXISTS `son_module`  (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父板块名称',
  `father_module_id` bigint(20) UNSIGNED NOT NULL COMMENT '父板块ID',
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `user_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '板块管理人ID',
  `sort` int(16) UNSIGNED NOT NULL DEFAULT 0 COMMENT '权重',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic COMMENT='子版块表';
