-- 创建数据库
DROP DATABASE IF EXISTS EMS;
CREATE DATABASE EMS;
USE EMS;

-- 用户表
CREATE TABLE users (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '用户id',
  email_address varchar(50) NOT NULL COMMENT '邮件地址',
  name varchar(30) NOT NULL COMMENT '用户名',
  passwd varchar(30) NOT NULL COMMENT '密码'
) ENGINE=InnoDB;

-- 通讯录表
create TABLE contacts (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '通讯录id',
  self_id int(11) COMMENT '用户id',
  contact_id int(11) COMMENT '联系人id'
) ENGINE=InnoDB; 

-- 邮件表
create TABLE email (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '邮件id',
  send_id int(11) COMMENT '发件人id',
  receive_id int(11) COMMENT '收件人id',
  dir_id int(11) COMMENT '所属文件夹id',
  title varchar(255) COMMENT '标题名',
  content varchar(2000) COMMENT '邮件内容'
) ENGINE=InnoDB; 

-- 文件夹表
create TABLE folder (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '文件夹id',
  name varchar(127) NOT NULL COMMENT '文件夹名',
  owner_id int(11) NOT NULL COMMENT '所属者id'
) ENGINE=InnoDB; 

-- 附件表
create TABLE enclosure (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '附件id',
  address varchar(255) NOT NULL COMMENT '暂存地址',
  owner_id int(11) NOT NULL COMMENT '所属邮件id'
) ENGINE=InnoDB; 
