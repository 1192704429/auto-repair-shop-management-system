package com.repair.manage.module.customer_contact_record.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.repair.manage.module.customer_contact_record.entity.CustomerContactRecord;
import com.repair.manage.vo.CustomerContactSendVO;

import java.util.List;

/**
 * 客户联系记录 Service
 */
public interface CustomerContactRecordService extends IService<CustomerContactRecord> {

    IPage<CustomerContactRecord> pageQuery(Integer current, Integer size, CustomerContactRecord entity);

    List<CustomerContactRecord> listQuery(CustomerContactRecord entity);

    CustomerContactRecord getById(Long id);

    /**
     * 发送邮件并写入联系记录（先发送，成功后再落库）
     */
    CustomerContactRecord sendContactEmail(CustomerContactSendVO vo);

}
