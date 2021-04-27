# 员工负载表(IBZ_EMPLOYEELOAD)

  

## 关系
{% plantuml %}
项目 *-- 员工负载表 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|任务名|NAME|TEXT|&nbsp;|
|任务数|TASKCNT|INT|&nbsp;|
|部门|DEPT|SSCODELIST|&nbsp;|
|主键|ID|ACID|&nbsp;|
|工作日天数|WORKDAY|INT|&nbsp;|
|总任务数|TOTALTASKCNT|INT|&nbsp;|
|指派给|ASSIGNEDTO|SSCODELIST|&nbsp;|
|剩余工时|LEFT|INT|&nbsp;|
|是否指派|ASSIGN|TEXT|&nbsp;|
|属性|BEGIN|DATE|&nbsp;|
|总工时|TOTALLEFT|INT|&nbsp;|
|工作负载|WORKLOAD|TEXT|&nbsp;|
|结束|END|DATE|&nbsp;|
|项目|PROJECTNAME|PICKUPTEXT|&nbsp;|
|项目编号|PROJECT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|任务名|默认规则|内容长度必须小于等于[100]|
|任务数|默认规则|默认规则|
|部门|默认规则|内容长度必须小于等于[200]|
|主键|默认规则|默认规则|
|工作日天数|默认规则|默认规则|
|总任务数|默认规则|默认规则|
|指派给|默认规则|内容长度必须小于等于[100]|
|剩余工时|默认规则|默认规则|
|是否指派|默认规则|内容长度必须小于等于[100]|
|属性|默认规则|默认规则|
|总工时|默认规则|默认规则|
|工作负载|默认规则|内容长度必须小于等于[200]|
|结束|默认规则|默认规则|
|项目|默认规则|内容长度必须小于等于[90]|
|项目编号|默认规则|默认规则|

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
|UpdateTime|用户自定义|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#EmpLoyeeload_Default))|是|&nbsp;|
|GETWOERKLOAD|获取员工负载表([MYSQL5](../../appendix/query_MYSQL5.md#EmpLoyeeload_GETWOERKLOAD))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#EmpLoyeeload_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|
|GETWOERKLOAD|获取员工负载表|GETWOERKLOAD|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|任务名(NAME)|LIKE|
|部门(DEPT)|EQ|
|主键(ID)|EQ|
|工作日天数(WORKDAY)|EQ|
|是否指派(ASSIGN)|EQ|
|属性(BEGIN)|LTANDEQ|
|结束(END)|GTANDEQ|
|项目(PROJECTNAME)|EQ|
|项目(PROJECTNAME)|LIKE|
|项目编号(PROJECT)|EQ|

## 导入模式
无


## 导出模式
无
