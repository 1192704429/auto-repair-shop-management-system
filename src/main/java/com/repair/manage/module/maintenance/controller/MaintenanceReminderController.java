package com.repair.manage.module.maintenance.controller;

import com.repair.manage.common.Result;
import com.repair.manage.config.MaintenanceReminderProperties;
import com.repair.manage.schedule.MaintenanceReminderTask;
import com.repair.manage.schedule.MaintenanceScanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 保养提醒：手动触发扫描（便于联调/测试）
 */
@RestController
@RequestMapping("/maintenance/reminder")
public class MaintenanceReminderController {

    @Autowired
    private MaintenanceReminderTask maintenanceReminderTask;

    @Autowired
    private MaintenanceReminderProperties maintenanceReminderProperties;

    /**
     * 立即执行一次扫描逻辑（与 scanAndNotify 相同规则，但忽略 enabled 开关）
     */
    @PostMapping("/scan")
    public Result scanManual() {
        if (maintenanceReminderProperties.getIntervalDays() <= 0) {
            return Result.error("interval-days 未配置或非法");
        }
        MaintenanceScanResult r = maintenanceReminderTask.scanAndNotifyManual();
        Map<String, Object> data = new HashMap<>(4);
        data.put("sent", r.getSent());
        data.put("skipped", r.getSkipped());
        return Result.success("保养提醒扫描已执行", data);
    }

}
