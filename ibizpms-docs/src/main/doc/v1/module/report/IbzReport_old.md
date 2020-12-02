# 汇报(IBZ_REPORT)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|工作|WORKTODAY|HTMLTEXT|&nbsp;|
|完成任务|TODAYTASK|SMCODELIST|&nbsp;|
|计划任务|TOMORROWPLANSTASK|SMCODELIST|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|汇报给|REPORTTO|SSCODELIST|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|日期|DATE|DATE|&nbsp;|
|是否提交|ISSUBMIT|SSCODELIST|&nbsp;|
|更新人名称|UPDATEMANNAME|TEXT|&nbsp;|
|附件|FILES|TEXT|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|状态|REPORTSTATUS|SSCODELIST|&nbsp;|
|其他事项|COMMENT|HTMLTEXT|&nbsp;|
|抄送给|MAILTO|SMCODELIST|&nbsp;|
|汇报标识|IBZ_DAILYID|ACID|&nbsp;|
|计划|PLANSTOMORROW|HTMLTEXT|&nbsp;|
|用户|ACCOUNT|SSCODELIST|&nbsp;|
|建立人名称|CREATEMANNAME|TEXT|&nbsp;|
|汇报名称|IBZ_DAILYNAME|TEXT|&nbsp;|
|类型|TYPE|SSCODELIST|&nbsp;|
|未读日报数|DAILYCNT|INT|&nbsp;|
|未读月报数|MONTHLYCNT|INT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|更新时间|默认规则|默认规则|
|工作|默认规则|内容长度必须小于等于[1048576]|
|完成任务|默认规则|内容长度必须小于等于[2000]|
|计划任务|默认规则|内容长度必须小于等于[2000]|
|建立人|默认规则|内容长度必须小于等于[60]|
|汇报给|默认规则|内容长度必须小于等于[60]|
|建立时间|默认规则|默认规则|
|日期|默认规则|默认规则|
|是否提交|默认规则|内容长度必须小于等于[60]|
|更新人名称|默认规则|内容长度必须小于等于[60]|
|附件|默认规则|内容长度必须小于等于[100]|
|更新人|默认规则|内容长度必须小于等于[60]|
|状态|默认规则|内容长度必须小于等于[60]|
|其他事项|默认规则|内容长度必须小于等于[1048576]|
|抄送给|默认规则|内容长度必须小于等于[2000]|
|汇报标识|默认规则|默认规则|
|计划|默认规则|内容长度必须小于等于[1048576]|
|用户|默认规则|内容长度必须小于等于[60]|
|建立人名称|默认规则|内容长度必须小于等于[60]|
|汇报名称|默认规则|内容长度必须小于等于[200]|
|类型|默认规则|内容长度必须小于等于[200]|
|未读日报数|默认规则|默认规则|
|未读月报数|默认规则|默认规则|

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
|我未提交的（计数器）|实体处理逻辑|&nbsp;|
|我收到的汇报（计数器）|实体处理逻辑|&nbsp;|
|Save|内置方法|&nbsp;|

## 处理逻辑
* 我未提交的（计数器） (MyReportINotSubmit)
  
   

{% plantuml %}
hide footbox

汇报 -> 汇报: 统计我收到的日报数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|统计我收到的日报数 |
<center>我未提交的（计数器）</center>
* 我收到的汇报（计数器） (ReportIReceived)
  
   

{% plantuml %}
hide footbox

汇报 -> 汇报: 统计我收到的月报数
汇报 -> 汇报: 统计我收到的日报数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|统计我收到的月报数 |
|1|开始 | 
|2|统计我收到的日报数 |
<center>我收到的汇报（计数器）</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#IbzReport_Default))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IbzReport_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|汇报给(REPORTTO)|EQ|
|是否提交(ISSUBMIT)|EQ|
|状态(REPORTSTATUS)|EQ|
|用户(ACCOUNT)|EQ|
|汇报名称(IBZ_DAILYNAME)|LIKE|
|类型(TYPE)|EQ|

## 导入模式
无


## 导出模式
无
