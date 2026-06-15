package com.repair.manage.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 保养提醒定时任务配置：按购车日起每隔若干天向车主发邮件
 */
@Data
@Component
@ConfigurationProperties(prefix = "maintenance.reminder")
public class MaintenanceReminderProperties {

    /** 是否启用定时扫描与邮件推送 */
    private boolean enabled = true;

    /**
     * 间隔天数 N：购车日至今天数 days 满足 days &gt;= N 且 days % N == 0 时触发提醒（即第 N、2N、3N… 天）
     */
    private int intervalDays = 180;

    /** Spring Cron：默认每天 8:00 执行一次 */
    private String cron = "0 0 8 * * ?";

}
