-- ----------------------------
-- Table structure for yxk_icon
-- ----------------------------
DROP TABLE IF EXISTS `yxk_icon`;
CREATE TABLE `yxk_icon`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `icon_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图标码',
  `icon_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标描述',
  `ctime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `utime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'icon图标表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;