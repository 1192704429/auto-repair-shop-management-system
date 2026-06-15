package com.repair.manage.module.customer_contact_record.controller;

import com.repair.manage.common.Result;
import com.repair.manage.module.customer_contact_record.entity.CustomerContactRecord;
import com.repair.manage.module.customer_contact_record.service.CustomerContactRecordService;
import com.repair.manage.vo.CustomerContactSendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 客户联系记录 Controller
 */
@RestController
@RequestMapping("/customer-contact-record")
public class CustomerContactRecordController {

    @Autowired
    private CustomerContactRecordService customerContactRecordService;

    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) CustomerContactRecord entity) {
        if (entity == null) {
            entity = new CustomerContactRecord();
        }
        return Result.success(customerContactRecordService.pageQuery(current, size, entity));
    }

    @PostMapping("/list")
    public Result list(@RequestBody(required = false) CustomerContactRecord entity) {
        if (entity == null) {
            entity = new CustomerContactRecord();
        }
        List<CustomerContactRecord> list = customerContactRecordService.listQuery(entity);
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        CustomerContactRecord record = customerContactRecordService.getById(id);
        if (record == null) {
            return Result.error("联系记录不存在");
        }
        return Result.success(record);
    }

    /**
     * 「联系顾客」页发送邮件：发信并保存联系记录（与 /customer/contact-record?customerId= 配套）
     */
    @PostMapping("/send")
    public Result send(@RequestBody CustomerContactSendVO vo) {
        try {
            CustomerContactRecord record = customerContactRecordService.sendContactEmail(vo);
            return Result.success("发送成功", record);
        } catch (IllegalArgumentException e) {
            return Result.error(e.getMessage());
        } catch (Exception e) {
            String msg = e.getMessage() != null ? e.getMessage() : "未知错误";
            return Result.error("邮件发送失败：" + msg);
        }
    }

    @PostMapping
    public Result save(@RequestBody CustomerContactRecord record) {
        record.setCreateTime(LocalDateTime.now());
        boolean success = customerContactRecordService.save(record);
        if (success) {
            return Result.success("新增成功", record);
        }
        return Result.error("新增失败");
    }

    @PutMapping
    public Result update(@RequestBody CustomerContactRecord record) {
        boolean success = customerContactRecordService.updateById(record);
        if (success) {
            return Result.success("更新成功", record);
        }
        return Result.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = customerContactRecordService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = customerContactRecordService.removeByIds(ids);
        if (success) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

}
