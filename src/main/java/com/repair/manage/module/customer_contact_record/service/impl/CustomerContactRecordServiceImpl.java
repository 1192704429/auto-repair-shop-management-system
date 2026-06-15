package com.repair.manage.module.customer_contact_record.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.manage.module.customer.entity.Customer;
import com.repair.manage.module.customer.service.CustomerService;
import com.repair.manage.module.customer_contact_record.entity.CustomerContactRecord;
import com.repair.manage.module.customer_contact_record.mapper.CustomerContactRecordMapper;
import com.repair.manage.module.customer_contact_record.service.CustomerContactRecordService;
import com.repair.manage.util.MailUtil;
import com.repair.manage.vo.CustomerContactSendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 客户联系记录 Service 实现
 */
@Service
public class CustomerContactRecordServiceImpl extends ServiceImpl<CustomerContactRecordMapper, CustomerContactRecord>
        implements CustomerContactRecordService {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");

    @Autowired
    private CustomerContactRecordMapper customerContactRecordMapper;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MailUtil mailUtil;

    @Override
    public IPage<CustomerContactRecord> pageQuery(Integer current, Integer size, CustomerContactRecord entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<CustomerContactRecord> records = customerContactRecordMapper.pageQuery(entity, offset, limit);
        Long total = customerContactRecordMapper.countQuery(entity);

        Page<CustomerContactRecord> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<CustomerContactRecord> listQuery(CustomerContactRecord entity) {
        return customerContactRecordMapper.listQuery(entity);
    }

    @Override
    public CustomerContactRecord getById(Long id) {
        return customerContactRecordMapper.getById(id);
    }

    @Override
    public CustomerContactRecord sendContactEmail(CustomerContactSendVO vo) {
        if (vo == null || vo.getCustomerId() == null) {
            throw new IllegalArgumentException("客户ID不能为空");
        }
        Customer customer = customerService.getById(vo.getCustomerId());
        if (customer == null) {
            throw new IllegalArgumentException("客户不存在");
        }
        String subject = vo.getEmailSubject() != null ? vo.getEmailSubject().trim() : "";
        String content = vo.getEmailContent() != null ? vo.getEmailContent().trim() : "";
        if (!StringUtils.hasText(subject)) {
            throw new IllegalArgumentException("邮件主题不能为空");
        }
        if (!StringUtils.hasText(content)) {
            throw new IllegalArgumentException("邮件正文不能为空");
        }
        String to = vo.getToEmail() != null && StringUtils.hasText(vo.getToEmail().trim())
                ? vo.getToEmail().trim()
                : (customer.getEmail() != null ? customer.getEmail().trim() : "");
        if (!StringUtils.hasText(to)) {
            throw new IllegalArgumentException("收件邮箱不能为空，请在客户档案中维护邮箱或在发送时填写");
        }
        if (!EMAIL_PATTERN.matcher(to).matches()) {
            throw new IllegalArgumentException("收件邮箱格式不正确");
        }
        mailUtil.sendSimpleMail(subject, content, to);

        CustomerContactRecord record = new CustomerContactRecord();
        record.setCustomerId(vo.getCustomerId());
        record.setToEmail(to);
        record.setEmailSubject(subject);
        record.setEmailContent(content);
        record.setOperatorId(vo.getOperatorId());
        record.setCreateTime(LocalDateTime.now());
        this.save(record);
        return record;
    }

}
