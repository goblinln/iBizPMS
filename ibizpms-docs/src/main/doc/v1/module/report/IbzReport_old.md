# 汇报汇总(IBZ_REPORT)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|更新人名称|UPDATEMANNAME|TEXT|&nbsp;|
|抄送给|MAILTO|SMCODELIST|&nbsp;|
|汇报标识|IBZ_DAILYID|ACID|&nbsp;|
|未读汇报数|REPORTLYCNT|INT|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|工作|WORKTODAY|HTMLTEXT|&nbsp;|
|未读月报数|MONTHLYCNT|INT|&nbsp;|
|未读日报数|DAILYCNT|INT|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|完成任务|TODAYTASK|SMCODELIST|&nbsp;|
|用户|ACCOUNT|SSCODELIST|&nbsp;|
|类型|TYPE|SSCODELIST|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|汇报名称|IBZ_DAILYNAME|TEXT|&nbsp;|
|汇报给|REPORTTO|SSCODELIST|&nbsp;|
|提交时间|SUBMITTIME|TEXT|&nbsp;|
|附件|FILES|TEXT|&nbsp;|
|建立人名称|CREATEMANNAME|TEXT|&nbsp;|
|计划|PLANSTOMORROW|HTMLTEXT|&nbsp;|
|状态|REPORTSTATUS|SSCODELIST|&nbsp;|
|日期|DATE|DATE|&nbsp;|
|是否提交|ISSUBMIT|SSCODELIST|&nbsp;|
|其他事项|COMMENT|HTMLTEXT|&nbsp;|
|计划任务|TOMORROWPLANSTASK|SMCODELIST|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|更新人名称|默认规则|内容长度必须小于等于[60]|
|抄送给|默认规则|内容长度必须小于等于[2000]|
|汇报标识|默认规则|默认规则|
|未读汇报数|默认规则|默认规则|
|更新时间|默认规则|默认规则|
|工作|默认规则|内容长度必须小于等于[1048576]|
|未读月报数|默认规则|默认规则|
|未读日报数|默认规则|默认规则|
|建立时间|默认规则|默认规则|
|完成任务|默认规则|内容长度必须小于等于[2000]|
|用户|默认规则|内容长度必须小于等于[60]|
|类型|默认规则|内容长度必须小于等于[200]|
|建立人|默认规则|内容长度必须小于等于[60]|
|更新人|默认规则|内容长度必须小于等于[60]|
|汇报名称|默认规则|内容长度必须小于等于[200]|
|汇报给|默认规则|内容长度必须小于等于[60]|
|提交时间|默认规则|内容长度必须小于等于[100]|
|附件|默认规则|内容长度必须小于等于[100]|
|建立人名称|默认规则|内容长度必须小于等于[60]|
|计划|默认规则|内容长度必须小于等于[1048576]|
|状态|默认规则|内容长度必须小于等于[60]|
|日期|默认规则|默认规则|
|是否提交|默认规则|内容长度必须小于等于[60]|
|其他事项|默认规则|内容长度必须小于等于[1048576]|
|计划任务|默认规则|内容长度必须小于等于[2000]|

## 状态控制

|类型|行为控制模式| 控制行为 | 操作标识控制模式 | 控制操作 |
| --------   | ------------|------------|------------|------------|
|Value| 允许|  | 不允许 | MONTHLY<br>WEEKLY<br>REPORTLY<br> |
|Value| 允许|  | 不允许 | WEEKLY<br>REPORTLY<br>DAILY<br> |
|Value| 允许|  | 不允许 | DAILY<br>MONTHLY<br>WEEKLY<br> |
|Value| 允许|  | 不允许 | REPORTLY<br>MONTHLY<br>DAILY<br> |

类型

| 状态        |    状态值   |
| --------   |------------|
|周报|weekly|
|日报|daily|
|月报|monthly|
|汇报|reportly|

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

汇报汇总 -> 汇报汇总: 统计我未提交的月报
汇报汇总 -> 汇报汇总: 统计我收到的（未读）
汇报汇总 -> 汇报汇总: 统计我未提交的周报
汇报汇总 -> 汇报汇总: 统计我未提交的汇报数
汇报汇总 -> 汇报汇总: 统计我未提交的日报数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|统计我未提交的月报 |
|2|统计我收到的（未读） |
|3|统计我未提交的周报 |
|4|统计我未提交的汇报数 |
|4|开始 | 
|5|统计我未提交的日报数 |
<center>我未提交的（计数器）</center>
* 我收到的汇报（计数器） (ReportIReceived)
  
   

{% plantuml %}
hide footbox

汇报汇总 -> 汇报汇总: 统计我收到的月报数（未读）
汇报汇总 -> 汇报汇总: 统计我收到的汇报数（未读）
汇报汇总 -> 汇报汇总: 获取未读周报
汇报汇总 -> 汇报汇总: 统计我收到的日报数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|统计我收到的月报数（未读） |
|2|统计我收到的汇报数（未读） |
|3|获取未读周报 |
|3|开始 | 
|4|统计我收到的日报数 |
<center>我收到的汇报（计数器）</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|AllReport|汇报汇总([MYSQL5](../../appendix/query_MYSQL5.md#IbzReport_AllReport))|否|&nbsp;|
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#IbzReport_Default))|是|&nbsp;|
|MyReAllReport|汇报汇总（我收到的）([MYSQL5](../../appendix/query_MYSQL5.md#IbzReport_MyReAllReport))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IbzReport_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|AllReport|汇报汇总|AllReport|否|&nbsp;|
|DEFAULT|数据集|DEFAULT|是|&nbsp;|
|MyReAllReport|汇报汇总（我收到的）|MyReAllReport|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|汇报标识(IBZ_DAILYID)|EQ|
|用户(ACCOUNT)|EQ|
|类型(TYPE)|EQ|
|汇报名称(IBZ_DAILYNAME)|LIKE|
|汇报给(REPORTTO)|EQ|
|状态(REPORTSTATUS)|EQ|
|是否提交(ISSUBMIT)|EQ|

## 导入模式
无


## 导出模式
无
