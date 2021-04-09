# 任务工时统计(TASKESTIMATESTATS)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|日期|DATE|TEXT|&nbsp;|
|年|YEAR|TEXT|&nbsp;|
|消耗的工时|CONSUMED|FLOAT|&nbsp;|
|评估状态|EVALUATIONSTATUS|TEXT|&nbsp;|
|用户|ACCOUNT|TEXT|&nbsp;|
|编号|ID|ACID|&nbsp;|
|月（显示）|MONTHNAME|TEXT|&nbsp;|
|项目名称|NAME|TEXT|&nbsp;|
|年（显示）|YEARNAME|TEXT|&nbsp;|
|评估工时|EVALUATIONTIME|FLOAT|&nbsp;|
|评估成本|EVALUATIONCOST|FLOAT|&nbsp;|
|投入成本|INPUTCOST|FLOAT|&nbsp;|
|月|MONTH|TEXT|&nbsp;|
|任务数|TASKCNT|INT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|日期|默认规则|内容长度必须小于等于[100]|
|年|默认规则|内容长度必须小于等于[100]|
|消耗的工时|默认规则|默认规则|
|评估状态|默认规则|内容长度必须小于等于[100]|
|用户|默认规则|内容长度必须小于等于[100]|
|编号|默认规则|默认规则|
|月（显示）|默认规则|内容长度必须小于等于[100]|
|项目名称|默认规则|内容长度必须小于等于[100]|
|年（显示）|默认规则|内容长度必须小于等于[100]|
|评估工时|默认规则|默认规则|
|评估成本|默认规则|默认规则|
|投入成本|默认规则|默认规则|
|月|默认规则|内容长度必须小于等于[100]|
|任务数|默认规则|默认规则|

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
|ActionMonth|日志月([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimateStats_ActionMonth))|否|&nbsp;|
|ActionYear|日志年([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimateStats_ActionYear))|否|&nbsp;|
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimateStats_Default))|是|&nbsp;|
|TaskEstimateStatsSum|任务工时统计([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimateStats_TaskEstimateStatsSum))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#TaskEstimateStats_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|ActionMonth|日志月|ActionMonth|否|&nbsp;|
|ActionYear|日志年|ActionYear|否|&nbsp;|
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|

## 导入模式
无


## 导出模式
无
