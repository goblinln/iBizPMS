# 月报(IBZ_MONTHLY)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|建立人|CREATEMAN|TEXT|&nbsp;|
|状态|REPORTSTATUS|SSCODELIST|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|下月计划|PLANSNEXTMONTH|HTMLTEXT|&nbsp;|
|汇报给（选择）|REPORTTOPK|TEXT|&nbsp;|
|是否提交|ISSUBMIT|SSCODELIST|&nbsp;|
|抄送给（选择）|MAILTOPK|TEXT|&nbsp;|
|更新人名称|UPDATEMANNAME|TEXT|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|日期|DATE|DATE|&nbsp;|
|提交时间|SUBMITTIME|TIME|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|建立人名称|CREATEMANNAME|TEXT|&nbsp;|
|附件|FILES|TEXT|&nbsp;|
|月报标识|IBZ_MONTHLYID|ACID|&nbsp;|
|用户|ACCOUNT|SSCODELIST|&nbsp;|
|下月计划任务|NEXTMONTHPLANSTASK|SMCODELIST|&nbsp;|
|本月完成任务|THISMONTHTASK|SMCODELIST|&nbsp;|
|本月工作|WORKTHISMONTH|HTMLTEXT|&nbsp;|
|汇报给|REPORTTO|SSCODELIST|&nbsp;|
|月报名称|IBZ_MONTHLYNAME|TEXT|&nbsp;|
|其他事项|COMMENT|HTMLTEXT|&nbsp;|
|抄送给|MAILTO|SMCODELIST|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|建立人|默认规则|内容长度必须小于等于[60]|
|状态|默认规则|内容长度必须小于等于[60]|
|更新时间|默认规则|默认规则|
|下月计划|默认规则|内容长度必须小于等于[1048576]|
|汇报给（选择）|默认规则|内容长度必须小于等于[200]|
|是否提交|默认规则|内容长度必须小于等于[100]|
|抄送给（选择）|默认规则|内容长度必须小于等于[200]|
|更新人名称|默认规则|内容长度必须小于等于[60]|
|更新人|默认规则|内容长度必须小于等于[60]|
|日期|默认规则|默认规则|
|提交时间|默认规则|默认规则|
|建立时间|默认规则|默认规则|
|建立人名称|默认规则|内容长度必须小于等于[60]|
|附件|默认规则|内容长度必须小于等于[100]|
|月报标识|默认规则|默认规则|
|用户|默认规则|内容长度必须小于等于[100]|
|下月计划任务|默认规则|内容长度必须小于等于[2000]|
|本月完成任务|默认规则|内容长度必须小于等于[2000]|
|本月工作|默认规则|内容长度必须小于等于[1048576]|
|汇报给|默认规则|内容长度必须小于等于[60]|
|月报名称|默认规则|内容长度必须小于等于[200]|
|其他事项|默认规则|内容长度必须小于等于[1048576]|
|抄送给|默认规则|内容长度必须小于等于[2000]|

## 状态控制

|是否提交|行为控制模式| 控制行为 | 操作标识控制模式 | 控制操作 |
| --------   | ------------|------------|------------|------------|
|Value| 允许|  | 不允许 |  |
|Value| 允许|  | 不允许 | SRFUR__MONTHLY_SUBMIT_BUT<br>SRFUR__MONTHLY_EDIT_BUT<br> |

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
|新建时获取信息|用户自定义|&nbsp;|
|定时生成用户月报|用户自定义|&nbsp;|
|编辑时获取完成任务|用户自定义|&nbsp;|
|已读|用户自定义|&nbsp;|
|定时推送待阅提醒用户月报|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|
|提交|用户自定义|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#IbzMonthly_Default))|是|&nbsp;|
|MyMonthly|我的月报([MYSQL5](../../appendix/query_MYSQL5.md#IbzMonthly_MyMonthly))|否|&nbsp;|
|MyMonthlyMob|我的月报（移动端）([MYSQL5](../../appendix/query_MYSQL5.md#IbzMonthly_MyMonthlyMob))|否|&nbsp;|
|MyReceivedMonthly|我收到的月报([MYSQL5](../../appendix/query_MYSQL5.md#IbzMonthly_MyReceivedMonthly))|否|&nbsp;|
|MySubmitMonthly|我提交的月报([MYSQL5](../../appendix/query_MYSQL5.md#IbzMonthly_MySubmitMonthly))|否|&nbsp;|
|ProductMonthly|产品月报([MYSQL5](../../appendix/query_MYSQL5.md#IbzMonthly_ProductMonthly))|否|&nbsp;|
|ProjectMonthly|项目月报([MYSQL5](../../appendix/query_MYSQL5.md#IbzMonthly_ProjectMonthly))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IbzMonthly_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|
|MyMonthly|我的月报|MyMonthly|否|&nbsp;|
|MyMonthlyMob|我的月报（移动端）|MyMonthlyMob|否|&nbsp;|
|MyReceivedMonthly|我收到的月报|MyReceivedMonthly|否|&nbsp;|
|MySubmitMonthly|我提交的月报|MySubmitMonthly|否|&nbsp;|
|ProductMonthly|产品月报|ProductMonthly|否|&nbsp;|
|ProjectMonthly|项目月报|ProjectMonthly|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|状态(REPORTSTATUS)|EQ|
|是否提交(ISSUBMIT)|EQ|
|用户(ACCOUNT)|EQ|
|汇报给(REPORTTO)|EQ|
|月报名称(IBZ_MONTHLYNAME)|LIKE|

## 导入模式
无


## 导出模式
* 数据导出

|月报标识|月报名称|用户|日期|汇报给|是否提交|
| :------: | :------: | :------: | :------: | :------: | :------: |
| - | - | - | - | - | - |
