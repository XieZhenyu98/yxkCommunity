-- ----------------------------
-- Table structure for search_keywords
-- ----------------------------
DROP TABLE IF EXISTS `search_keywords`;
CREATE TABLE `search_keywords`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `keywords` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关键字',
  `count` int(11) NOT NULL DEFAULT 0 COMMENT '搜索次数',
  `ctime` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `last_search_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后搜索时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
