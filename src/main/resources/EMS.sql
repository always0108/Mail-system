-- 创建数据库
DROP DATABASE IF EXISTS EMS;
CREATE DATABASE EMS;
USE EMS;

-- 用户表
create TABLE users (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '用户id',
  email_address varchar(50) NOT NULL COMMENT '邮件地址',
  name varchar(30) NOT NULL COMMENT '用户名',
  passwd varchar(30) NOT NULL COMMENT '密码'
) ENGINE=InnoDB;

-- 联系人表
create TABLE contacts (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '关系id',
  self_id int(11) COMMENT '用户id',
  contact_id int(11) COMMENT '联系人id'
) ENGINE=InnoDB; 

-- 邮件表
create TABLE email (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '邮件id',
  send_id int(11) COMMENT '发件人id',
  receive_id int(11) COMMENT '收件人id',
  dir_id int(11) COMMENT '所属文件夹id',
  subject varchar(255) COMMENT '主题',
  content varchar(2000) COMMENT '邮件内容', 
  is_read tinyint(1) COMMENT '是否已读',
  star    tinyint(1) COMMENT '星标',
  time    datetime  COMMENT '时间'    
) ENGINE=InnoDB;  

-- 文件表
create TABLE files (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '文件id',
  name varchar(255) NOT NULL COMMENT '文件名',
  address varchar(255) NOT NULL COMMENT '存储地址'
) ENGINE=InnoDB; 

-- 附件关系表
create TABLE enclosure (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '关系id',
  email_id int(11) NOT NULL COMMENT '邮件id',
  file_id int(11) NOT NULL COMMENT '文件id'
) ENGINE=InnoDB; 

-- 文件夹表
create TABLE folder (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '文件夹id',
  name varchar(127) NOT NULL COMMENT '文件夹名',
  owner_id int(11) NOT NULL COMMENT '所属者id'
) ENGINE=InnoDB; 
