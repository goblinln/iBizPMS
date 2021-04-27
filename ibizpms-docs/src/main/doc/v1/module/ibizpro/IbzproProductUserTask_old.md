# 产品汇报用户任务(IBIZPRO_PRODUCTUSERTASK)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|任务类型|TASKTYPE|SSCODELIST|&nbsp;|
|用户|ACCOUNT|TEXT|&nbsp;|
|总计消耗|CONSUMED|FLOAT|&nbsp;|
|编号|ID|ACID|&nbsp;|
|任务名称|TASKNAME|TEXT|&nbsp;|
|进度|PROGRESSRATE|TEXT|&nbsp;|
|预计开始|ESTSTARTED|DATE|&nbsp;|
|日期|DATE|DATE|&nbsp;完成日期（指任务人的，不是任务的）|
|延期天数|DELAYDAYS|TEXT|&nbsp;|
|任务|TASK|BIGINT|&nbsp;|
|预计剩余|LEFT|FLOAT|&nbsp;|
|截止日期|DEADLINE|DATE|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|任务类型|默认规则|内容长度必须小于等于[200]|
|用户|默认规则|内容长度必须小于等于[30]|
|总计消耗|默认规则|默认规则|
|编号|默认规则|默认规则|
|任务名称|默认规则|内容长度必须小于等于[100]|
|进度|默认规则|内容长度必须小于等于[100]|
|预计开始|默认规则|默认规则|
|日期|默认规则|默认规则|
|延期天数|默认规则|内容长度必须小于等于[100]|
|任务|默认规则|默认规则|
|预计剩余|默认规则|默认规则|
|截止日期|默认规则|默认规则|

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
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#IbzproProductUserTask_Default))|是|&nbsp;|
|ProductDailyUserTaskStats|产品日报用户任务统计([MYSQL5](../../appendix/query_MYSQL5.md#IbzproProductUserTask_ProductDailyUserTaskStats))|否|&nbsp;|
|ProductMonthlyUserTaskStats|产品月报用户任务统计([MYSQL5](../../appendix/query_MYSQL5.md#IbzproProductUserTask_ProductMonthlyUserTaskStats))|否|&nbsp;|
|ProductWeeklyUserTaskStats|产品周报用户任务统计([MYSQL5](../../appendix/query_MYSQL5.md#IbzproProductUserTask_ProductWeeklyUserTaskStats))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IbzproProductUserTask_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|
|ProductDailyUserTaskStats|产品日报用户任务统计|ProductDailyUserTaskStats|否|&nbsp;|
|ProductMonthlyUserTaskStats|产品月报用户任务统计|ProductMonthlyUserTaskStats|否|&nbsp;|
|ProductWeeklyUserTaskStats|产品周报用户任务统计|ProductWeeklyUserTaskStats|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|任务类型(TASKTYPE)|EQ|
|编号(ID)|EQ|

## 导入模式
无


## 导出模式
无
