package com.repair.manage.module.customer_contact_record.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.manage.module.customer_contact_record.entity.CustomerContactRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客户联系记录 Mapper
 */
@Mapper
public interface CustomerContactRecordMapper extends BaseMapper<CustomerContactRecord> {

    List<CustomerContactRecord> pageQuery(@Param("entity") CustomerContactRecord entity,
                                          @Param("offset") Long offset,
                                          @Param("limit") Long limit);

    Long countQuery(@Param("entity") CustomerContactRecord entity);

    List<CustomerContactRecord> listQuery(@Param("entity") CustomerContactRecord entity);

    CustomerContactRecord getById(@Param("id") Long id);

}
