# 周报(IBZ_WEEKLY)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|是否提交|ISSUBMIT|SSCODELIST|&nbsp;|
|下周计划|PLANNEXTWEEK|HTMLTEXT|&nbsp;|
|周报名称|IBZ_WEEKLYNAME|TEXT|&nbsp;|
|抄送给|MAILTO|SMCODELIST|&nbsp;|
|汇报给(选择)|REPORTTOPK|TEXT|&nbsp;|
|下周计划任务|NEXTWEEKTASK|SMCODELIST|&nbsp;|
|周报标识|IBZ_WEEKLYID|ACID|&nbsp;|
|提交时间|SUBMITTIME|TIME|&nbsp;|
|抄送给(选择)|MAILTOPK|TEXT|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|状态|REPORTSTATUS|SSCODELIST|&nbsp;|
|附件|FILES|TEXT|&nbsp;|
|本周工作|WORKTHISWEEK|HTMLTEXT|&nbsp;|
|汇报给|REPORTTO|SSCODELIST|&nbsp;|
|更新人名称|UPDATEMANNAME|TEXT|&nbsp;|
|用户|ACCOUNT|SSCODELIST|&nbsp;|
|本周完成任务|THISWEEKTASK|SMCODELIST|&nbsp;|
|其他事项|COMMENT|HTMLTEXT|&nbsp;|
|日期|DATE|DATE|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|建立人名称|CREATEMANNAME|TEXT|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|是否提交|默认规则|内容长度必须小于等于[60]|
|下周计划|默认规则|内容长度必须小于等于[1048576]|
|周报名称|默认规则|内容长度必须小于等于[200]|
|抄送给|默认规则|内容长度必须小于等于[2000]|
|汇报给(选择)|默认规则|内容长度必须小于等于[200]|
|下周计划任务|默认规则|内容长度必须小于等于[100]|
|周报标识|默认规则|默认规则|
|提交时间|默认规则|默认规则|
|抄送给(选择)|默认规则|内容长度必须小于等于[200]|
|建立人|默认规则|内容长度必须小于等于[60]|
|状态|默认规则|内容长度必须小于等于[60]|
|附件|默认规则|内容长度必须小于等于[100]|
|本周工作|默认规则|内容长度必须小于等于[1048576]|
|汇报给|默认规则|内容长度必须小于等于[60]|
|更新人名称|默认规则|内容长度必须小于等于[60]|
|用户|默认规则|内容长度必须小于等于[60]|
|本周完成任务|默认规则|内容长度必须小于等于[100]|
|其他事项|默认规则|内容长度必须小于等于[1048576]|
|日期|默认规则|默认规则|
|更新人|默认规则|内容长度必须小于等于[60]|
|建立人名称|默认规则|内容长度必须小于等于[60]|
|建立时间|默认规则|默认规则|
|更新时间|默认规则|默认规则|

## 状态控制

|是否提交|行为控制模式| 控制行为 | 操作标识控制模式 | 控制操作 |
| --------   | ------------|------------|------------|------------|
|Value| 允许|  | 不允许 | SRFUR__WEEKLY_NSUBMIT_BUT<br> |
|Value| 允许|  | 不允许 | SRFUR__WEEKLY_SUBMIT_BUT<br> |

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
|定时生成每周周报|用户自定义|&nbsp;|
|获取上周周报的下周计划|用户自定义|&nbsp;|
|编辑获取上周计划完成任务和本周已完成任务|用户自定义|&nbsp;|
|已读|用户自定义|&nbsp;|
|判断本周是否创建过周报|用户自定义|&nbsp;|
|定时推送待阅提醒用户周报提交|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|
|提交|用户自定义|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#IbzWeekly_Default))|是|&nbsp;|
|MyNotSubmit|我的周报([MYSQL5](../../appendix/query_MYSQL5.md#IbzWeekly_MyNotSubmit))|否|&nbsp;|
|MyWeekly|我收到的周报([MYSQL5](../../appendix/query_MYSQL5.md#IbzWeekly_MyWeekly))|否|&nbsp;|
|ProductTeamMemberWeekly|产品团队成员周报([MYSQL5](../../appendix/query_MYSQL5.md#IbzWeekly_ProductTeamMemberWeekly))|否|&nbsp;|
|ProjectWeekly|项目周报([MYSQL5](../../appendix/query_MYSQL5.md#IbzWeekly_ProjectWeekly))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IbzWeekly_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|
|MyNotSubmit|我的周报|MyNotSubmit|否|&nbsp;|
|MyWeekly|我收到的周报|MyWeekly|否|&nbsp;|
|ProductTeamMemberWeekly|产品团队成员周报|ProductTeamMemberWeekly|否|&nbsp;|
|ProjectWeekly|项目周报|ProjectWeekly|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|是否提交(ISSUBMIT)|EQ|
|周报名称(IBZ_WEEKLYNAME)|LIKE|
|周报标识(IBZ_WEEKLYID)|EQ|
|状态(REPORTSTATUS)|EQ|
|汇报给(REPORTTO)|EQ|
|用户(ACCOUNT)|EQ|

## 导入模式
无


## 导出模式
* 数据导出（拷贝无操作）

|周报标识|周报名称|用户|日期|汇报给|是否提交|
| :------: | :------: | :------: | :------: | :------: | :------: |
| - | - | - | - | - | - |
