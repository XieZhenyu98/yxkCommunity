-- ----------------------------
-- Table structure for yxk_menu
-- ----------------------------
DROP TABLE IF EXISTS `yxk_menu`;
CREATE TABLE `yxk_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL COMMENT '父菜单id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名字',
  `level` int(10) NOT NULL DEFAULT 0 COMMENT '菜单等级',
  `sort` int(10) NOT NULL DEFAULT 0 COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'icon图标',
  `hidden` int(10) NOT NULL DEFAULT 0 COMMENT '是否隐藏',
  `ctime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '菜单创建时间',
  `utime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '菜单修改时间',
  `notes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台管理菜单' ROW_FORMAT = Dynamic;
SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for yxk_role
-- ----------------------------
DROP TABLE IF EXISTS `yxk_role`;
CREATE TABLE `yxk_role`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名字',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `user_count` int(10) NOT NULL DEFAULT 0 COMMENT '数量',
  `ctime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `utime` datetime(0) NOT NULL COMMENT '更新时间',
  `sort` int(10) NOT NULL DEFAULT 0 COMMENT '排序',
  `status` int(255) NOT NULL DEFAULT 1 COMMENT '当前状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;
SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for yxk_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `yxk_role_menu_relation`;
CREATE TABLE `yxk_role_menu_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '权限id',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限对应能能访问的菜单表' ROW_FORMAT = Dynamic;
SET FOREIGN_KEY_CHECKS = 1;
