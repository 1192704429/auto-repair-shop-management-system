# 基于 SpringBoot + Vue.js 的汽车维修企业管理系统的设计与实现

> 本文围绕汽车维修行业的实际管理需求，设计并实现了一套基于 SpringBoot、Vue.js 与 MySQL 的汽车维修企业管理系统，涵盖人员管理、车辆档案、维修工单及财务结算等核心业务模块。

## 一、项目背景

- 传统汽车维修企业普遍依赖纸质单据或 Excel 进行日常管理，存在数据分散、流程不透明、信息沟通滞后等问题。
- 维修工单派单效率低、配件库存难以实时掌控、财务结算与业务数据脱节，影响了企业的服务质量和经营决策效率。
- 本系统通过角色化权限控制与数据可视化手段，帮助企业实现从客户接待、维修工单管理、配件库存管理到财务结算的全流程数字化管理。

## 二、功能模块介绍

- 系统划分管理端与业务端，管理端由系统管理员使用，业务端包括前台接待人员、维修工程师与财务人员。
- 不同角色拥有不同的菜单与数据权限。

| 角色名称 | 所属端 | 核心职责 |
| :--- | :--- | :--- |
| 系统管理员 | 管理端 | 系统人员管理、客户信息管理、供应商管理、车辆档案管理、配件管理与数据统计 |
| 前台接待人员 | 业务端 | 接待管理、车辆档案管理、联系顾客、维修工单创建 |
| 维修工程师 | 业务端 | 工单处理、维修详情管理、工时与配件领用记录 |
| 财务人员 | 业务端 | 费用结算管理、结算订单管理 |


![功能模块图](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/azhou_promotion/img/module_structure/func_module_454333fa531547868f876d2ff0ffe627.png)


## 三、技术栈说明

前端：Vue.js + ElementUI

后端：Java 语言，SpringBoot

数据库与中间件：MySQL

## 四、核心模块介绍

###### 管理端

###### 系统管理员

- 系统人员管理：管理系统内部人员账号，包括用户账号新增、编辑、删除，角色分配（管理员 / 接待 / 维修工程师 / 财务），以及账号停用启用等
- 客户信息管理：管理所有客户信息
- 供应商管理
- 车辆档案管理：管理车辆档案信息，车辆维修与保养历史查看
- 维修项目分类管理：如保养、维修、检测等
- 配件分类管理：如发动机类-滤清器、底盘类-刹车片等
- 配件信息管理：管理配件的基础信息，包括配件编码，配件名称、规格型号、品牌，所属分类，计量单位，供应商信息，采购价，销售价，当前库存，库存预警阈值（最低库存），备注与图片等
- 数据查询与统计：维修、财务等多维度统计分析，图表可视化展示（折线图、柱状图、饼图等）

##### 界面展示

首页：可视化展示系统多维度业务统计数据

![首页：可视化展示系统多维度业务统计数据](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/919bcd96-d953-4da5-86ff-416e2619f084.jpg)

系统人员管理：管理系统内部人员账号，支持增删改查操作

![系统人员管理：管理系统内部人员账号，支持增删改查操作](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/68185536-b0cb-4223-81b5-69ce69124fa2.jpg)

客户信息管理：管理员查询并管理系统内所有客户信息

![客户信息管理：管理员查询并管理系统内所有客户信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/4e97ef96-8726-4d56-895d-aea14fd8d983.jpg)

供应商管理：查询和管理系统内的供应商信息

![供应商管理：查询和管理系统内的供应商信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/d45c3f29-1572-44ed-8385-467d51aa7f62.jpg)

车辆档案管理：展示并管理系统内的车辆档案信息

![车辆档案管理：展示并管理系统内的车辆档案信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/697eca0d-907e-493e-8059-9bea8fee6f23.jpg)

维修项目分类管理：管理汽车维修项目分类，支持增删改查操作

![维修项目分类管理：管理汽车维修项目分类，支持增删改查操作](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/ca467747-90e9-46ae-852b-badeaefa47ce.jpg)

配件分类管理：管理并查询汽车维修的各类配件分类信息

![配件分类管理：管理并查询汽车维修的各类配件分类信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/f1837499-7b25-4593-81fd-39928cf433ae.jpg)

配件信息管理：管理员管理汽车维修配件的基础信息

![配件信息管理：管理员管理汽车维修配件的基础信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/4eeacebc-df0d-480a-823f-68e5d02c586d.jpg)

配件入库管理：查看并管理配件入库订单，支持审核操作

![配件入库管理：查看并管理配件入库订单，支持审核操作](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/11853952-8c07-4bb6-867b-a3646d681406.jpg)

维修工单管理：展示并管理系统内所有汽车维修工单

![维修工单管理：展示并管理系统内所有汽车维修工单](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/ef7483bb-4e3f-44d3-826d-b94e7f86ae53.jpg)


###### 业务端

###### 前台接待人员

- 接待管理：负责客户账号等基础信息管理
- 车辆档案管理：管理车辆档案信息，车辆维修与保养历史查看
- 联系顾客：通过邮箱发送邮件与顾客联动
- 维修工单管理：创建维修工单（系统自动对工程师进行派单），工单状态管理，维修信息查询等

##### 界面展示

登录页：填写账号信息，验证后登录管理系统

![登录页：填写账号信息，验证后登录管理系统](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/a4cd0ae8-3d49-4780-808c-aa849a7860b8.png)

首页：可视化统计车辆类型与维修工单状态

![首页：可视化统计车辆类型与维修工单状态](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/45a2d8e9-78e8-4abe-88f5-1bd9f282534d.png)

客户信息管理：对客户信息进行查询、编辑、删除等管理操作

![客户信息管理：对客户信息进行查询、编辑、删除等管理操作](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/de039b29-efd6-4a6b-8e89-7a923d0c2699.png)

客户信息管理：管理客户信息，支持查询增删改操作

![客户信息管理：管理客户信息，支持查询增删改操作](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/779c5f2b-5278-423b-8190-085e51b3a87d.png)

车辆档案管理页：新增车辆档案，填写车辆相关信息

![车辆档案管理页：新增车辆档案，填写车辆相关信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/154e592d-4abe-43e7-88f6-ac63235a3fbb.png)

车辆档案管理：查看和管理所有车辆的档案信息

![车辆档案管理：查看和管理所有车辆的档案信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/46121dbd-6dee-4112-8661-afdcd47d8559.png)

维修工单管理页：展示维修工单列表，支持查询和工单操作

![维修工单管理页：展示维修工单列表，支持查询和工单操作](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/115059ab-d907-4ff7-8075-f60cc5a0dcae.png)

维修工单管理页：查看维修工单列表，可创建新的维修工单

![维修工单管理页：查看维修工单列表，可创建新的维修工单](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/a6ed9a19-0308-4602-87fe-c47292aaa872.png)

维修工单详情页：查看维修工单的维修详情、配件及财务信息

![维修工单详情页：查看维修工单的维修详情、配件及财务信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/4d296893-a9d4-4747-837d-54284311e134.png)

联系顾客页：撰写邮件发送给客户，记录邮件往来

![联系顾客页：撰写邮件发送给客户，记录邮件往来](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/28c5445d-98cb-4c4a-812a-d149b1cda0ff.png)


###### 维修工程师

- 工单处理：查看分配给自己的维修工单，接收 / 开始维修操作，更新维修进度与状态，填写维修结果与技术说明
- 维修详情管理：包含工时填报，维修内容记录，维修工单关联配件领用等

##### 界面展示

首页：展示各类业务数据的统计可视化图表

![首页：展示各类业务数据的统计可视化图表](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/a1d6a0e4-c9aa-4897-87bd-fa992142e9a4.jpg)

工单处理页：查看分配的维修工单，支持更新工单状态

![工单处理页：查看分配的维修工单，支持更新工单状态](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/a2c62bf5-aaef-41cf-82c9-9b288f978d72.jpg)

工单处理页：填写维修详情，更新维修工单状态

![工单处理页：填写维修详情，更新维修工单状态](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/f3978483-6d17-42b8-8895-8149ac41fd1b.jpg)

维修详情页：查看维修详情，添加本次维修使用的配件

![维修详情页：查看维修详情，添加本次维修使用的配件](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/5569f318-da26-44a7-86e6-e5a2b3f44004.jpg)

详情页：查看汽车维修配件的详细信息

![详情页：查看汽车维修配件的详细信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/90387c26-7004-4014-806a-d842af5bbd09.jpg)

列表页：查看不同状态的配件入库订单列表

![列表页：查看不同状态的配件入库订单列表](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/baf0b112-37bd-4206-8eef-645c0dc27ef2.jpg)

配件信息管理页：展示各类维修配件，支持配件信息管理操作

![配件信息管理页：展示各类维修配件，支持配件信息管理操作](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/eba42169-5409-4ef6-8565-a7c5b6c2eaf6.jpg)

配件入库页：新建配件入库单，填写入库配件相关信息

![配件入库页：新建配件入库单，填写入库配件相关信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/8bf25785-005d-40aa-8bee-726a73eb60aa.jpg)

入库订单详情页：查看配件入库订单的详细信息

![入库订单详情页：查看配件入库订单的详细信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/1aa65224-9fda-42ff-84d0-c10000aacda6.jpg)

配件信息管理页：添加汽车维修配件的基础信息

![配件信息管理页：添加汽车维修配件的基础信息](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/c5e8f6bf-b51e-4446-82d7-a015b0bb7cc8.jpg)


###### 财务人员

- 费用结算管理：查看已完成维修，生成结算订单
- 订单管理：对维修结算订单进行管理

##### 界面展示

首页：展示维修工单状态和财务收支统计数据

![首页：展示维修工单状态和财务收支统计数据](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/2b83ff40-8382-479b-8a2e-d42ff028788a.jpg)

费用结算页：查看已完成维修工单，进行费用结算

![费用结算页：查看已完成维修工单，进行费用结算](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/f949a006-823d-48f0-8224-fcf0436baaa6.jpg)

结算详情页：查看已完成维修工单的费用结算详情

![结算详情页：查看已完成维修工单的费用结算详情](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/a4d8eaaa-43a2-4915-80e7-0573089d5438.jpg)

订单管理页：统计财务收支，管理汽车维修财务订单

![订单管理页：统计财务收支，管理汽车维修财务订单](https://code-graden-server-dev.oss-cn-beijing.aliyuncs.com/2026-06-15/87fd5b87-e7f2-4038-8a2f-b4b1a28fd659.jpg)


## 五、主要业务流程

1. 前台接待人员创建维修工单，系统自动匹配维修工程师进行派单
2. 维修工程师接收工单，开始维修操作，更新维修进度并记录维修结果
3. 维修过程中，工程师填报工时并关联配件领用信息
4. 维修完成后，工单状态变更为已完成
5. 财务人员查看已完成维修记录，生成并管理结算订单

## 六、项目拓展性

- 可对接第三方支付网关，实现在线结算与发票管理
- 可扩展为多门店连锁管理模式，支持独立数据权限与统一报表
- 可集成微信小程序或公众号，为客户提供维修进度查询与在线预约服务
- 可引入智能配件推荐与库存预测算法，提升备件管理效率

## 代码说明 
本仓库为项目的 **后端核心代码部分** 主要用于展示系统的业务设计思路与后端实现方式 出于学习展示与版权保护考虑 前端实现、完整数据库脚本及部署配置未在本仓库中公开 

##  项目交流 
本项目用于学习展示 如需项目完整功能演示、答辩说明或技术交流 请前往作者 GitHub 主页查看联系方式