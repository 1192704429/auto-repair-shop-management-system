package com.repair.manage.module.customer_contact_record.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 客户联系记录（邮件历史）
 */
@Data
@TableName("customer_contact_record")
public class CustomerContactRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 客户ID */
    private Long customerId;
    /** 收件邮箱 */
    private String toEmail;
    /** 邮件主题 */
    private String emailSubject;
    /** 邮件正文 */
    private String emailContent;
    /** 操作客服用户ID */
    private Long operatorId;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /** 客户姓名（列表关联查询，非表字段） */
    @TableField(exist = false)
    private String customerName;

    /** 操作人姓名（列表关联查询，非表字段） */
    @TableField(exist = false)
    private String operatorName;

}
