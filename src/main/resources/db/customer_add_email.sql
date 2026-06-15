-- 客户表增加邮箱字段（联系客户发邮件用）
ALTER TABLE `customer` ADD COLUMN `email` varchar(100) NULL DEFAULT NULL COMMENT '电子邮箱' AFTER `phone`;
