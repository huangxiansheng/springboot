			  
--数据库
CREATE TABLE `hs_user` (
  `uuid` VARCHAR(40) NOT NULL COMMENT 'uuid',
  `user_name` VARCHAR(40) NULL COMMENT '用户名',
  `cn_name` VARCHAR(45) NULL COMMENT '中文名',
  `password` VARCHAR(128) NULL COMMENT '密码',
  PRIMARY KEY (`uuid`));


CREATE TABLE `hs_user_role` (
  `uuid` VARCHAR(40) NOT NULL COMMENT 'uuid',
  `user_name` VARCHAR(40) NULL COMMENT '用户名',
  `role` VARCHAR(45) NULL COMMENT '角色',
  PRIMARY KEY (`uuid`));

-- 仓库定义表 hs_store_def
-- uuid,area 区域,area_id 编号
CREATE TABLE hs_store_def (
  `uuid` VARCHAR(40) NOT NULL COMMENT 'uuid',
  `area` VARCHAR(40) NULL COMMENT '区域',
  `area_id` VARCHAR(45) NULL COMMENT '编号',
  PRIMARY KEY (`uuid`));

-- 仓库数据表 hs_store_data
-- store_uuid,smoke_id 烟编号.create_date 生产日期,store_time 入库时间,store_user 入库用户
CREATE TABLE hs_store_data (
  `uuid` VARCHAR(40) NOT NULL COMMENT '仓库uuid',
  `tray` VARCHAR(40) NOT NULL COMMENT '托盘编号',
  `smoke_id` VARCHAR(40) NULL COMMENT '烟编号',
  `create_date` date NULL COMMENT '生产日期',
  `smoke_number` integer NULL COMMENT '数量',
  `store_time` timestamp NULL COMMENT '入库时间',
  `store_user` VARCHAR(40) NULL COMMENT '入库用户',
  PRIMARY KEY (`uuid`));

-- 仓库历史表  hs_store_his
-- store_uuid,smoke_id 烟编号.create_date 生产日期,store_time 入库时间,store_user 入库用户,out_time,out_user
CREATE TABLE hs_store_his (
  `uuid` VARCHAR(40) NOT NULL COMMENT '仓库uuid',
  `tray` VARCHAR(40) NOT NULL COMMENT '托盘编号',
  `smoke_id` VARCHAR(40) NULL COMMENT '烟编号',
  `create_date` date NULL COMMENT '生产日期',
  `store_time` timestamp NULL COMMENT '入库时间',
  `store_user` VARCHAR(40) NULL COMMENT '入库用户',
  `out_time` timestamp NULL COMMENT '出库时间',
  `out_user` VARCHAR(40) NULL COMMENT '出库用户',
  PRIMARY KEY (`uuid`));

INSERT INTO hs_user(uuid, user_name, cn_name, password) VALUES
('asdasdasd', '000001', '阿达', '$2a$10$BcI3l7/UMH.OD9TuVkuhV.IJDrJVkKFgxpDWiunwSLsslpkO6BONW');
INSERT INTO hs_user(uuid, user_name, cn_name, password) VALUES
('sdafdsfdsf', '000002', '撒地方', '$2a$10$BcI3l7/UMH.OD9TuVkuhV.IJDrJVkKFgxpDWiunwSLsslpkO6BONW');
  
 
INSERT INTO hs_user_role(uuid, user_name, role) VALUES
('SADFSDAF', '000002', 'ROLE_LEADER');
INSERT INTO hs_user_role(uuid, user_name, role) VALUES
('sdfdfd', '000001', 'ROLE_ADMIN');
 
  
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-01','1','01');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-02','1','02');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-03','1','03');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-04','1','04');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-05','1','05');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-06','1','06');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-07','1','07');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-08','1','08');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-09','1','09');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-10','1','10');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-11','1','11');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-12','1','12');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-13','1','13');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-14','1','14');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-15','1','15');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-16','1','16');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-17','1','17');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-18','1','18');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-19','1','19');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-20','1','20');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-21','1','21');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-22','1','22');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-23','1','23');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-24','1','24');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-25','1','25');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-26','1','26');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-27','1','27');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-28','1','28');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-29','1','29');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-30','1','30');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-31','1','31');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-32','1','32');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-33','1','33');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-34','1','34');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-35','1','35');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-36','1','36');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-37','1','37');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-38','1','38');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-39','1','39');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-40','1','40');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-41','1','41');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-42','1','42');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-43','1','43');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-44','1','44');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-45','1','45');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-46','1','46');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-47','1','47');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-48','1','48');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-49','1','49');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-50','1','50');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-51','1','51');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-52','1','52');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-53','1','53');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-54','1','54');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-55','1','55');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-56','1','56');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-57','1','57');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-58','1','58');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-59','1','59');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('1-60','1','60');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-01','2','01');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-02','2','02');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-03','2','03');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-04','2','04');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-05','2','05');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-06','2','06');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-07','2','07');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-08','2','08');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-09','2','09');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-10','2','10');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-11','2','11');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-12','2','12');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-13','2','13');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-14','2','14');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-15','2','15');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-16','2','16');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-17','2','17');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-18','2','18');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-19','2','19');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-20','2','20');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-21','2','21');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-22','2','22');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-23','2','23');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-24','2','24');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-25','2','25');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-26','2','26');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-27','2','27');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-28','2','28');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-29','2','29');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-30','2','30');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-31','2','31');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-32','2','32');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-33','2','33');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-34','2','34');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-35','2','35');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-36','2','36');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-37','2','37');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-38','2','38');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-39','2','39');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-40','2','40');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-41','2','41');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-42','2','42');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-43','2','43');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-44','2','44');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-45','2','45');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-46','2','46');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-47','2','47');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-48','2','48');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-49','2','49');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-50','2','50');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-51','2','51');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-52','2','52');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-53','2','53');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-54','2','54');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-55','2','55');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-56','2','56');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-57','2','57');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-58','2','58');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-59','2','59');
INSERT INTO hs_store_def (uuid,area,area_id) VALUE('2-60','2','60');



INSERT INTO hs_store_data (uuid,tray,smoke_id,create_date,smoke_number,store_time,store_user) VALUE('1-33','12345','A001',date'2017-01-03',30,date'2018-02-03','000001');
INSERT INTO hs_store_data (uuid,tray,smoke_id,create_date,smoke_number,store_time,store_user) VALUE('1-32','22345','A001',date'2017-01-03',10,date'2018-02-03','000001');
INSERT INTO hs_store_data (uuid,tray,smoke_id,create_date,smoke_number,store_time,store_user) VALUE('2-14','32345','B001',date'2017-08-03',30,date'2018-02-03','000001');
INSERT INTO hs_store_data (uuid,tray,smoke_id,create_date,smoke_number,store_time,store_user) VALUE('2-15','42345','B002',date'2018-03-02',30,date'2018-02-03','000001');
