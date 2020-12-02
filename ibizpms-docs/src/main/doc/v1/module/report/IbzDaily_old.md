# 日报(IBZ_DAILY)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|建立人|CREATEMAN|TEXT|&nbsp;|
|日报标识|IBZ_DAILYID|ACID|&nbsp;|
|日报名称|IBZ_DAILYNAME|TEXT|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|日期|DATE|DATE|&nbsp;|
|用户|ACCOUNT|SSCODELIST|&nbsp;|
|今日工作|WORKTODAY|HTMLTEXT|&nbsp;|
|抄送给|MAILTO|SMCODELIST|&nbsp;|
|附件|FILES|TEXT|&nbsp;|
|完成任务|TODAYTASK|SMCODELIST|&nbsp;|
|是否提交|ISSUBMIT|SSCODELIST|&nbsp;|
|明日计划|PLANSTOMORROW|HTMLTEXT|&nbsp;|
|明日计划任务|TOMORROWPLANSTASK|SMCODELIST|&nbsp;|
|汇报给|REPORTTO|SSCODELIST|&nbsp;|
|其他事项|COMMENT|HTMLTEXT|&nbsp;|
|建立人名称|CREATEMANNAME|TEXT|&nbsp;|
|更新人名称|UPDATEMANNAME|TEXT|&nbsp;|
|状态|REPORTSTATUS|SSCODELIST|&nbsp;|
|提交时间|SUBMITTIME|TIME|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|建立人|默认规则|内容长度必须小于等于[60]|
|日报标识|默认规则|默认规则|
|日报名称|默认规则|内容长度必须小于等于[200]|
|更新时间|默认规则|默认规则|
|建立时间|默认规则|默认规则|
|更新人|默认规则|内容长度必须小于等于[60]|
|日期|默认规则|默认规则|
|用户|默认规则|内容长度必须小于等于[60]|
|今日工作|默认规则|内容长度必须小于等于[1048576]|
|抄送给|默认规则|内容长度必须小于等于[2000]|
|附件|默认规则|内容长度必须小于等于[100]|
|完成任务|默认规则|内容长度必须小于等于[2000]|
|是否提交|默认规则|内容长度必须小于等于[60]|
|明日计划|默认规则|内容长度必须小于等于[1048576]|
|明日计划任务|默认规则|内容长度必须小于等于[2000]|
|汇报给|默认规则|内容长度必须小于等于[60]|
|其他事项|默认规则|内容长度必须小于等于[1048576]|
|建立人名称|默认规则|内容长度必须小于等于[60]|
|更新人名称|默认规则|内容长度必须小于等于[60]|
|状态|默认规则|内容长度必须小于等于[60]|
|提交时间|默认规则|默认规则|

## 状态控制

|是否提交|行为控制模式| 控制行为 | 操作标识控制模式 | 控制操作 |
| --------   | ------------|------------|------------|------------|
|Value| 允许|  | 不允许 | SRFUR__DAILY_SUBMIT_BUT<br> |
|Value| 允许|  | 不允许 |  |

是否提交

| 状态        |    状态值   |
| --------   |------------|
|是|1|
|否|0|

## 行为
| 行为    | 类型    |  说明  |
| --------   |------------| ----- | 
|Create|内置方法|&nbsp;|
|Update|内置方法|&nbsp;|
|Remove|内置方法|&nbsp;|
|Get|内置方法|&nbsp;|
|GetDraft|内置方法|&nbsp;|
|CheckKey|内置方法|&nbsp;|
|定时生成用户日报|用户自定义|&nbsp;|
|已读|用户自定义|&nbsp;|
|关联完成任务|用户自定义|&nbsp;|
|定时推送待阅提醒用户日报|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|
|提交|用户自定义|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#IbzDaily_Default))|否|&nbsp;|
|MyDaily|我收到的日报([MYSQL5](../../appendix/query_MYSQL5.md#IbzDaily_MyDaily))|否|&nbsp;|
|MyNotSubmit|我的日报([MYSQL5](../../appendix/query_MYSQL5.md#IbzDaily_MyNotSubmit))|否|&nbsp;|
|MySubmitDaily|我提交的日报([MYSQL5](../../appendix/query_MYSQL5.md#IbzDaily_MySubmitDaily))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IbzDaily_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|
|MyDaily|我收到的日报|MyDaily|否|&nbsp;|
|MyNotSubmit|我的日报|MyNotSubmit|否|&nbsp;|
|MySubmitDaily|我提交的日报|MySubmitDaily|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|日报名称(IBZ_DAILYNAME)|LIKE|
|日期(DATE)|EQ|
|用户(ACCOUNT)|EQ|
|是否提交(ISSUBMIT)|EQ|
|汇报给(REPORTTO)|EQ|
|状态(REPORTSTATUS)|EQ|

## 导入模式
无


## 导出模式
无
