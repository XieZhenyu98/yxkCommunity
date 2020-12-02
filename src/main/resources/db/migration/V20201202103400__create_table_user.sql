-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE IF NOT EXISTS `user`  (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `username` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `sex` int(11) NOT NULL COMMENT '性别',
  `city` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `personal_signature` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `image` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '/res/images/avatar/default.png' COMMENT '头像',
  `activate_mailbox` int(11) NOT NULL DEFAULT 0 COMMENT '是否激活邮箱',
  `diamonds` int(11) NOT NULL DEFAULT 0 COMMENT '钻石',
  `authentication` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '认证',
  `joining_time` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '加入时间',
  `last_time` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '最后登录时间',
  `level` int(10) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`, `email`) USING BTREE
) ENGINE=InnoDB CHARACTER SET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=Dynamic COMMENT='用户表';