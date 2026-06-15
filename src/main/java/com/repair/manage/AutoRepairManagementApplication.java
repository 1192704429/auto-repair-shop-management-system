package com.repair.manage;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wuming
 * @description: 基于SpringBoot的汽车维修企业管理系统的设计与实现服务端启动入口
 */
@SpringBootApplication
@Slf4j
@MapperScan("com.repair.manage.**.mapper")
@EnableScheduling
public class AutoRepairManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoRepairManagementApplication.class, args);
        log.info("JAVA后端服务启动成功！！！！！！");
    }

}
