-- 客户联系记录表（邮件联系历史）
CREATE TABLE IF NOT EXISTS `customer_contact_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `to_email` varchar(100) NOT NULL COMMENT '收件邮箱',
  `email_subject` varchar(200) NOT NULL COMMENT '邮件主题',
  `email_content` text NULL COMMENT '邮件正文',
  `operator_id` bigint(20) NULL DEFAULT NULL COMMENT '操作客服用户ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_customer_id` (`customer_id`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户联系记录';
