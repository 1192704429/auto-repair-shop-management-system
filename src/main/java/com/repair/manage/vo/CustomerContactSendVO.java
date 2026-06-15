package com.repair.manage.vo;

import lombok.Data;

/**
 * 「联系顾客」页发送邮件请求（与 /customer/contact-record?customerId= 配套）
 */
@Data
public class CustomerContactSendVO {

    /** 客户ID（必填） */
    private Long customerId;

    /** 邮件主题 */
    private String emailSubject;

    /** 邮件正文 */
    private String emailContent;

    /**
     * 收件邮箱；为空时使用客户档案中的邮箱
     */
    private String toEmail;

    /** 操作客服用户ID（可选，一般由前端传入当前登录用户） */
    private Long operatorId;

}
