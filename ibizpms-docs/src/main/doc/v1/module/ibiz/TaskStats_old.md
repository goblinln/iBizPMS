# 任务统计(IBZ_TASKSTATS)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|任务截至日期|TASKDEADLINE|DATE|&nbsp;|
|任务编号|TASKID|INT|&nbsp;|
|任务实际开始时间|TASKREALSTART|DATE|&nbsp;|
|效率|TASKEFFICIENT|TEXT|&nbsp;|
|任务预计消耗|TASKESTIMATE|INT|&nbsp;|
|结束|END|DATE|&nbsp;|
|用户总消耗|USERCONSUMED|INT|&nbsp;|
|剩余总工时|TOTALLEFT|INT|&nbsp;|
|项目总消耗|PROJECTCONSUMED|INT|&nbsp;|
|属性|BEGIN|DATE|&nbsp;|
|消耗总工时|TOTALCONSUMED|INT|&nbsp;|
|预计总工时|TOTALESTIMATE|INT|&nbsp;|
|任务预计开始日期|TASKESTSTARTED|DATE|&nbsp;|
|编号|ID|ACID|&nbsp;|
|部门|DEPT|SSCODELIST|&nbsp;|
|名称|NAME|TEXT|&nbsp;|
|项目名称|PROJECTNAME|TEXT|&nbsp;|
|项目|PROJECT|TEXT|&nbsp;|
|任务实际完成日期|TASKFINISHEDDATE|DATE|&nbsp;|
|任务名称|TASKNAME|TEXT|&nbsp;|
|总任务数|TASKCNT|INT|&nbsp;|
|任务优先级|TASKPRI|INT|&nbsp;|
|任务延期|TASKDELAY|TEXT|&nbsp;|
|完成者|FINISHEDBY|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|任务截至日期|默认规则|默认规则|
|任务编号|默认规则|默认规则|
|任务实际开始时间|默认规则|默认规则|
|效率|默认规则|内容长度必须小于等于[200]|
|任务预计消耗|默认规则|默认规则|
|结束|默认规则|默认规则|
|用户总消耗|默认规则|默认规则|
|剩余总工时|默认规则|默认规则|
|项目总消耗|默认规则|默认规则|
|属性|默认规则|默认规则|
|消耗总工时|默认规则|默认规则|
|预计总工时|默认规则|默认规则|
|任务预计开始日期|默认规则|默认规则|
|编号|默认规则|默认规则|
|部门|默认规则|内容长度必须小于等于[200]|
|名称|默认规则|内容长度必须小于等于[100]|
|项目名称|默认规则|内容长度必须小于等于[100]|
|项目|默认规则|内容长度必须小于等于[100]|
|任务实际完成日期|默认规则|默认规则|
|任务名称|默认规则|内容长度必须小于等于[100]|
|总任务数|默认规则|默认规则|
|任务优先级|默认规则|默认规则|
|任务延期|默认规则|内容长度必须小于等于[100]|
|完成者|默认规则|内容长度必须小于等于[100]|

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
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#TaskStats_Default))|是|&nbsp;|
|TaskFinishHuiZong|任务完成汇总表([MYSQL5](../../appendix/query_MYSQL5.md#TaskStats_TaskFinishHuiZong))|否|&nbsp;|
|UserFinishTaskSum|用户完成任务统计([MYSQL5](../../appendix/query_MYSQL5.md#TaskStats_UserFinishTaskSum))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#TaskStats_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|
|TaskFinishHuiZong|任务完成汇总表|TaskFinishHuiZong|否|&nbsp;|
|UserFinishTaskSum|用户完成任务统计|UserFinishTaskSum|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|结束(END)|LTANDEQ|
|属性(BEGIN)|GTANDEQ|
|编号(ID)|EQ|
|部门(DEPT)|EQ|
|名称(NAME)|LIKE|
|项目(PROJECT)|EQ|
|完成者(FINISHEDBY)|EQ|

## 导入模式
无


## 导出模式
* 数据导出-任务完成汇总表

|完成者|所属项目|编号|任务名称|优先级|预计开始|实际开始|截至日期|实际完成|延期|最初预计|任务总消耗|总任务数|项目总消耗|用户总消耗|
| :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: |
| - | - | - | - | - | - | - | - | - | - | - | - | - | - | - |
