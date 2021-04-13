-- ----------------------------
-- Table structure for registration
-- ----------------------------
CREATE TABLE IF NOT EXISTS `user_manage`  (
  `id` bigint(20) NOT NULL COMMENT '管理员id',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员用户名',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '帐号创建时间',
  `last_login_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
  `level` int(4) NOT NULL DEFAULT 1 COMMENT '管理员权限级别,0超级管理员,1普通管理员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB CHARACTER SET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=Dynamic COMMENT='管理员表';