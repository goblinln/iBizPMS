# 任务预计(ZT_TASKESTIMATE)

  

## 关系
{% plantuml %}
任务 *-- 任务预计 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|月（显示）|MONTHNAME|TEXT|&nbsp;|
|年|YEAR|TEXT|&nbsp;|
|用户|ACCOUNT|TEXT|&nbsp;|
|预计剩余|LEFT|FLOAT|&nbsp;|
|总计消耗|CONSUMED|FLOAT|&nbsp;|
|评估成本|EVALUATIONCOST|FLOAT|&nbsp;|
|月（排序）|MONTHORDER|INT|&nbsp;|
|附件|FILES|LONGTEXT_1000|&nbsp;|
|编号|ID|ACID|&nbsp;|
|评估状态|EVALUATIONSTATUS|TEXT|&nbsp;|
|年（显示）|YEARNAME|TEXT|&nbsp;|
|日期|DATE|DATE|&nbsp;完成日期（指任务人的，不是任务的）|
|评估工时|EVALUATIONTIME|FLOAT|&nbsp;|
|投入成本|INPUTCOST|FLOAT|&nbsp;|
|日期|DATES|DATE|&nbsp;|
|月|MONTH|TEXT|&nbsp;|
|work|WORK|LONGTEXT|&nbsp;暂时还不知道做什么用的，没有任何处理的代码|
|评估说明|EVALUATIONDESC|LONGTEXT|&nbsp;|
|任务种别|TASKSPECIES|PICKUPDATA|&nbsp;|
|任务名称|TASKNAME|PICKUPTEXT|&nbsp;|
|所属项目|PROJECTNAME|PICKUPDATA|&nbsp;|
|任务类型|TYPE|PICKUPDATA|&nbsp;|
|任务删除标识|DELETED|PICKUPDATA|&nbsp;|
|项目|PROJECT|PICKUPDATA|&nbsp;|
|任务|TASK|PICKUP|&nbsp;|
|天|DAY|TEXT|&nbsp;|
|天（显示）|DAYNAME|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|月（显示）|默认规则|内容长度必须小于等于[100]|
|年|默认规则|内容长度必须小于等于[100]|
|用户|默认规则|内容长度必须小于等于[30]|
|预计剩余|预计剩余大于等于0|预计剩余大于等于0|
|预计剩余|默认规则|默认规则|
|总计消耗|总计消耗大于等于0|总计消耗大于等于0|
|总计消耗|默认规则|默认规则|
|评估成本|默认规则|默认规则|
|月（排序）|默认规则|默认规则|
|附件|默认规则|内容长度必须小于等于[2000]|
|编号|默认规则|默认规则|
|评估状态|默认规则|内容长度必须小于等于[100]|
|年（显示）|默认规则|内容长度必须小于等于[100]|
|日期|默认规则|默认规则|
|评估工时|默认规则|默认规则|
|投入成本|默认规则|默认规则|
|日期|默认规则|默认规则|
|月|默认规则|内容长度必须小于等于[100]|
|work|默认规则|内容长度必须小于等于[65535]|
|评估说明|默认规则|内容长度必须小于等于[1048576]|
|任务种别|默认规则|内容长度必须小于等于[60]|
|任务名称|默认规则|内容长度必须小于等于[255]|
|所属项目|默认规则|内容长度必须小于等于[90]|
|任务类型|默认规则|内容长度必须小于等于[20]|
|任务删除标识|默认规则|内容长度必须小于等于[1]|
|项目|默认规则|默认规则|
|任务|默认规则|默认规则|
|天|默认规则|内容长度必须小于等于[100]|
|天（显示）|默认规则|内容长度必须小于等于[100]|

## 状态控制

无


## 行为
| 行为    | 类型    |  说明  |
| --------   |------------| ----- | 
|Create|内置方法|&nbsp;|
|CreateTemp|内置方法|&nbsp;|
|CreateTempMajor|内置方法|&nbsp;|
|Update|内置方法|&nbsp;|
|UpdateTemp|内置方法|&nbsp;|
|UpdateTempMajor|内置方法|&nbsp;|
|Remove|内置方法|&nbsp;|
|RemoveTemp|内置方法|&nbsp;|
|RemoveTempMajor|内置方法|&nbsp;|
|Get|内置方法|&nbsp;|
|GetTemp|内置方法|&nbsp;|
|GetTempMajor|内置方法|&nbsp;|
|GetDraft|内置方法|&nbsp;|
|GetDraftTemp|内置方法|&nbsp;|
|GetDraftTempMajor|内置方法|&nbsp;|
|CheckKey|内置方法|&nbsp;|
|项目经理评估|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|ActionDate|日志日([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimate_ActionDate))|否|&nbsp;|
|ActionDateByProject|日志日（项目）([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimate_ActionDateByProject))|否|&nbsp;|
|ActionMonth|日志月([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimate_ActionMonth))|否|&nbsp;|
|ActionYear|日志年([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimate_ActionYear))|否|&nbsp;|
|AllAccounts|所有人员([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimate_AllAccounts))|否|&nbsp;|
|AllProjects|所有项目([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimate_AllProjects))|否|&nbsp;|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimate_Default))|是|&nbsp;|
|DEFAULT1|DEFAULT1([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimate_Defaults))|否|&nbsp;|
|MyAccounts|我管辖的所有人员([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimate_MyAccounts))|否|&nbsp;|
|MyActionDate|日志日（我的）([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimate_MyActionDate))|否|&nbsp;|
|PersonalEstimate|人员日志([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimate_PersonalEstimate))|否|&nbsp;|
|ProjectActionMonth|日志月（项目）([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimate_ProjectActionMonth))|否|&nbsp;|
|ProjectActionYear|日志年（项目）([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimate_ProjectActionYear))|否|&nbsp;|
|ProjectTaskEstimate|项目日志([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimate_ProjectTaskEstimate))|否|&nbsp;|
|SomeoneJoinAllPros|人员参与的所有项目([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimate_SomeoneJoinAllPros))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimate_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|ActionDate|日志日|ActionDate|否|&nbsp;|
|ActionDateByProject|日志日（项目）|ActionDateByProject|否|&nbsp;|
|ActionMonth|日志月|ActionMonth|否|&nbsp;|
|ActionYear|日志年|ActionYear|否|&nbsp;|
|AllAccounts|所有人员|AllAccounts|否|&nbsp;|
|AllProjects|所有项目|AllProjects|否|&nbsp;|
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|DEFAULT1|DEFAULT1|DEFAULT1|否|&nbsp;|
|MyAccounts|我管辖的所有人员|MyAccounts|否|&nbsp;|
|MyActionDate|日志日（我的）|MyActionDate|否|&nbsp;|
|PersonalEstimate|人员日志|PersonalEstimate|否|&nbsp;|
|ProjectActionMonth|日志月（项目）|ProjectActionMonth|否|&nbsp;|
|ProjectActionYear|日志年（项目）|ProjectActionYear|否|&nbsp;|
|ProjectTaskEstimate|项目日志|ProjectTaskEstimate|否|&nbsp;|
|SomeoneJoinAllPros|人员参与的所有项目|SomeoneJoinAllPros|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|年(YEAR)|EQ|
|编号(ID)|LIKE|
|编号(ID)|EQ|
|月(MONTH)|EQ|
|任务名称(TASKNAME)|EQ|
|任务名称(TASKNAME)|LIKE|
|项目(PROJECT)|EQ|
|任务(TASK)|EQ|

## 导入模式
无


## 导出模式
无
