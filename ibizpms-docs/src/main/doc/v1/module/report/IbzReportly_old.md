# 汇报(IBZ_REPORTLY)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|附件|FILES|TEXT|&nbsp;|
|用户|ACCOUNT|SSCODELIST|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|抄送给|MAILTO|SMCODELIST|&nbsp;|
|汇报标识|IBZ_REPORTLYID|ACID|&nbsp;|
|汇报名称|IBZ_REPORTLYNAME|TEXT|&nbsp;|
|状态|REPORTSTATUS|SSCODELIST|&nbsp;|
|提交时间|SUBMITTIME|TIME|&nbsp;|
|汇报给（选择）|REPORTTOPK|TEXT|&nbsp;|
|汇报给|REPORTTO|SSCODELIST|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|是否提交|ISSUBMIT|SSCODELIST|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|工作内容|CONTENT|HTMLTEXT|&nbsp;|
|汇报日期|DATE|DATE|&nbsp;|
|抄送给（选择）|MAILTOPK|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|附件|默认规则|内容长度必须小于等于[100]|
|用户|默认规则|内容长度必须小于等于[60]|
|更新人|默认规则|内容长度必须小于等于[60]|
|建立时间|默认规则|默认规则|
|抄送给|默认规则|内容长度必须小于等于[100]|
|汇报标识|默认规则|默认规则|
|汇报名称|默认规则|内容长度必须小于等于[200]|
|状态|默认规则|内容长度必须小于等于[100]|
|提交时间|默认规则|默认规则|
|汇报给（选择）|默认规则|内容长度必须小于等于[200]|
|汇报给|默认规则|内容长度必须小于等于[100]|
|建立人|默认规则|内容长度必须小于等于[60]|
|是否提交|默认规则|内容长度必须小于等于[100]|
|更新时间|默认规则|默认规则|
|工作内容|默认规则|内容长度必须小于等于[1048576]|
|汇报日期|默认规则|默认规则|
|抄送给（选择）|默认规则|内容长度必须小于等于[200]|

## 状态控制

|是否提交|行为控制模式| 控制行为 | 操作标识控制模式 | 控制操作 |
| --------   | ------------|------------|------------|------------|
|Value| 允许|  | 不允许 |  |
|Value| 允许|  | 不允许 | SRFUR__REPORTLY_EDIT_BUT<br>SRFUR__REPORTLY_SUBMIT_BUT<br> |

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
|已读|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|
|提交|用户自定义|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#IbzReportly_Default))|是|&nbsp;|
|MyAllReportly|我所有的汇报([MYSQL5](../../appendix/query_MYSQL5.md#IbzReportly_MyAllReportly))|否|&nbsp;|
|MyReceived|我收到的汇报([MYSQL5](../../appendix/query_MYSQL5.md#IbzReportly_MyReceived))|否|&nbsp;|
|MyReportlyMob|我的未提交汇报([MYSQL5](../../appendix/query_MYSQL5.md#IbzReportly_MyReportlyMob))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IbzReportly_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|
|MyAllReportly|我所有的汇报|MyAllReportly|否|&nbsp;|
|MyReceived|我收到的汇报|MyReceived|否|&nbsp;|
|MyReportlyMob|我的未提交汇报|MyReportlyMob|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|用户(ACCOUNT)|EQ|
|汇报标识(IBZ_REPORTLYID)|EQ|
|汇报名称(IBZ_REPORTLYNAME)|LIKE|
|是否提交(ISSUBMIT)|EQ|

## 导入模式
无


## 导出模式
* 数据导出

|汇报标识|汇报名称|用户|日期|汇报给|是否提交|
| :------: | :------: | :------: | :------: | :------: | :------: |
| - | - | - | - | - | - |
