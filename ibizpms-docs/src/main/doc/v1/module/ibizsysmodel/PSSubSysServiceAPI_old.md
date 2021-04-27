# 外部服务接口(PSSUBSYSSERVICEAPI)

  

## 关系
{% plantuml %}
外部服务接口 *-- 实体 
外部服务接口 *-- 外部接口实体 
系统模块 *-- 外部服务接口 
系统服务接口 *-- 外部服务接口 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|外部服务接口名称|PSSUBSYSSERVICEAPINAME|TEXT|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|系统|PSSYSTEMID|TEXT|&nbsp;|
|外部服务接口标识|PSSUBSYSSERVICEAPIID|GUID|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|系统|PSSYSTEMNAME|TEXT|&nbsp;|
|系统服务接口|PSSYSSERVICEAPINAME|PICKUPTEXT|&nbsp;|
|系统模块|PSMODULENAME|PICKUPTEXT|&nbsp;|
|系统模块|PSMODULEID|PICKUP|&nbsp;|
|系统服务接口|PSSYSSERVICEAPIID|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|外部服务接口名称|默认规则|内容长度必须小于等于[200]|
|建立人|默认规则|内容长度必须小于等于[60]|
|建立时间|默认规则|默认规则|
|系统|默认规则|内容长度必须小于等于[100]|
|外部服务接口标识|默认规则|内容长度必须小于等于[100]|
|更新时间|默认规则|默认规则|
|更新人|默认规则|内容长度必须小于等于[60]|
|系统|默认规则|内容长度必须小于等于[100]|
|系统服务接口|默认规则|内容长度必须小于等于[200]|
|系统模块|默认规则|内容长度必须小于等于[200]|
|系统模块|默认规则|内容长度必须小于等于[100]|
|系统服务接口|默认规则|内容长度必须小于等于[100]|

## 状态控制

无


## 行为
| 行为    | 类型    |  说明  |
| --------   |------------| ----- | 
|Create|内置方法|&nbsp;|
|Update|内置方法|&nbsp;|
|Remove|内置方法|&nbsp;|
|Get|内置方法|&nbsp;|
|GetDraft|内置方法|&nbsp;|
|CheckKey|内置方法|&nbsp;|
|Save|内置方法|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#PSSubSysServiceAPI_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#PSSubSysServiceAPI_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|外部服务接口名称(PSSUBSYSSERVICEAPINAME)|LIKE|
|外部服务接口标识(PSSUBSYSSERVICEAPIID)|EQ|
|系统服务接口(PSSYSSERVICEAPINAME)|EQ|
|系统服务接口(PSSYSSERVICEAPINAME)|LIKE|
|系统模块(PSMODULENAME)|EQ|
|系统模块(PSMODULENAME)|LIKE|
|系统模块(PSMODULEID)|EQ|
|系统服务接口(PSSYSSERVICEAPIID)|EQ|

## 导入模式
无


## 导出模式
无
