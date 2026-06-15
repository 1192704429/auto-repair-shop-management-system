package com.repair.manage.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 保养提醒扫描结果
 */
@Data
@AllArgsConstructor
public class MaintenanceScanResult {

    /** 实际发送邮件数 */
    private int sent;

    /** 遍历中跳过或未命中规则的车辆数 */
    private int skipped;

}
