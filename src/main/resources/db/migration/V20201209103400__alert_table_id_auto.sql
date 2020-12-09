-- ----------------------------
-- 修改所有创建过的表的ID生成策略为自动增长
-- ----------------------------

ALTER TABLE content change id id bigint auto_increment;
ALTER TABLE father_module change id id bigint auto_increment;
ALTER TABLE registration change id id bigint auto_increment;
ALTER TABLE reply change id id bigint auto_increment;
ALTER TABLE son_module change id id bigint auto_increment;
ALTER TABLE user change id id bigint auto_increment;