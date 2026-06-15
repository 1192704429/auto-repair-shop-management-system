package com.repair.manage.schedule;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.repair.manage.config.MaintenanceReminderProperties;
import com.repair.manage.module.customer.entity.Customer;
import com.repair.manage.module.customer.service.CustomerService;
import com.repair.manage.module.vehicle.entity.Vehicle;
import com.repair.manage.module.vehicle.service.VehicleService;
import com.repair.manage.util.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 全局定时任务：扫描车辆购车日期，按配置间隔向车主发送保养提醒邮件
 */
@Slf4j
@Component
public class MaintenanceReminderTask {

    @Autowired
    private MaintenanceReminderProperties reminderProperties;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MailUtil mailUtil;

    @Scheduled(cron = "${maintenance.reminder.cron:0 0 8 * * ?}")
    public void scanAndNotify() {
        if (!reminderProperties.isEnabled()) {
            log.debug("maintenance.reminder.enabled=false，跳过定时任务");
            return;
        }
        int interval = reminderProperties.getIntervalDays();
        if (interval <= 0) {
            log.warn("maintenance.reminder.interval-days 未配置或非法，跳过定时任务");
            return;
        }
        MaintenanceScanResult r = runScanCore(interval);
        log.info("保养提醒任务完成: intervalDays={}, 命中发送={}, 跳过/未命中={}", interval, r.getSent(), r.getSkipped());
    }

    /**
     * 手动触发（测试接口）：忽略 maintenance.reminder.enabled，仍要求 interval-days &gt; 0
     */
    public MaintenanceScanResult scanAndNotifyManual() {
        int interval = reminderProperties.getIntervalDays();
        if (interval <= 0) {
            return new MaintenanceScanResult(0, 0);
        }
        MaintenanceScanResult r = runScanCore(interval);
        log.info("【手动】保养提醒扫描完成: intervalDays={}, 命中发送={}, 跳过/未命中={}", interval, r.getSent(), r.getSkipped());
        return r;
    }

    private MaintenanceScanResult runScanCore(int interval) {
        LocalDate today = LocalDate.now();
        List<Vehicle> vehicles = vehicleService.list(
                new LambdaQueryWrapper<Vehicle>().isNotNull(Vehicle::getPurchaseDate));

        int sent = 0;
        int skipped = 0;
        for (Vehicle v : vehicles) {
            if (v.getPurchaseDate() == null || v.getCustomerId() == null) {
                skipped++;
                continue;
            }
            LocalDate purchase = v.getPurchaseDate().toLocalDate();
            long days = ChronoUnit.DAYS.between(purchase, today);
            if (days < interval) {
                continue;
            }
            if (days % interval != 0) {
                continue;
            }

            Customer customer = customerService.getById(v.getCustomerId());
            if (customer == null) {
                log.debug("车辆 id={} 无对应客户，跳过", v.getId());
                skipped++;
                continue;
            }
            String email = customer.getEmail();
            if (!StringUtils.hasText(email)) {
                log.debug("客户 id={} 未维护邮箱，车辆 id={} 跳过邮件", customer.getId(), v.getId());
                skipped++;
                continue;
            }

            String plate = StringUtils.hasText(v.getPlateNumber()) ? v.getPlateNumber() : "（未填车牌）";
            String subject = "【保养提醒】您的爱车 " + plate + " 已到建议保养周期";
            String body = buildMailBody(customer.getCustomerName(), v, days, interval);
            try {
                mailUtil.sendSimpleMail(subject, body, email.trim());
                sent++;
                log.info("保养提醒邮件已发送: vehicleId={}, customerId={}, email={}", v.getId(), customer.getId(), email);
            } catch (Exception e) {
                log.error("保养提醒邮件发送失败 vehicleId={}, email={}", v.getId(), email, e);
            }
        }
        return new MaintenanceScanResult(sent, skipped);
    }

    private String buildMailBody(String customerName, Vehicle v, long daysSincePurchase, int interval) {
        String name = StringUtils.hasText(customerName) ? customerName : "车主";
        String plate = StringUtils.hasText(v.getPlateNumber()) ? v.getPlateNumber() : "您的车辆";
        String brandModel = (StringUtils.hasText(v.getBrand()) ? v.getBrand() : "")
                + " "
                + (StringUtils.hasText(v.getModel()) ? v.getModel() : "");
        brandModel = brandModel.trim();
        if (!StringUtils.hasText(brandModel)) {
            brandModel = "—";
        }
        return String.format(
                "%s 您好！%n%n"
                        + "您在我店登记的车辆「%s」（%s）自购车日起已满 %d 天，"
                        + "按每 %d 天一次的保养提醒规则，今日为建议回店保养的时间节点之一。%n%n"
                        + "为保障行车安全与车辆状态，建议您预约到店进行例行保养。%n%n"
                        + "本邮件由系统自动发送，如有疑问请联系门店客服。%n",
                name, plate, brandModel, daysSincePurchase, interval);
    }

}
