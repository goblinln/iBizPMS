# 系统日志(ZT_ACTION)

  

## 关系
{% plantuml %}
系统日志 *-- 操作历史 
项目 *-- 系统日志 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|本月|THISMONTH|TEXT|&nbsp;|
|昨天|YESTERDAY|TEXT|&nbsp;|
|附加值|EXTRA|LONGTEXT|&nbsp;细分需求、任务。任务再次分配|
|文件|FILES|LONGTEXT|&nbsp;|
|上月|LASTMONTH|TEXT|&nbsp;|
|当前用户|ISACTORSS|BIGINT|&nbsp;|
|本周|THISWEEK|TEXT|&nbsp;|
|今天|TODAY|TEXT|&nbsp;|
|显示日期|DATE1|TEXT|&nbsp;|
|对象类型|OBJECTTYPE|SSCODELIST|&nbsp;|
|id|ID|ACID|&nbsp;|
|消息通知用户|NOTICEUSERS|TEXT|&nbsp;|
|备注|COMMENT|LONGTEXT|&nbsp;|
|已读|READ|SSCODELIST|&nbsp;发生变更之后的确认行为|
|动作|ACTION|SSCODELIST|&nbsp;|
|日期|DATE|DATETIME|&nbsp;|
|产品|PRODUCT|TEXT|&nbsp;|
|备注|LASTCOMMENT|HTMLTEXT|&nbsp;|
|前端键值|SRFKEY|BIGINT|&nbsp;|
|操作方式|ACTIONMANNER|SSCODELIST|&nbsp;|
|上周|LASTWEEK|TEXT|&nbsp;|
|对象ID|OBJECTID|BIGINT|&nbsp;|
|操作者|ACTOR|TEXT|&nbsp;|
|项目|PROJECT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|本月|默认规则|内容长度必须小于等于[200]|
|昨天|默认规则|内容长度必须小于等于[200]|
|附加值|默认规则|内容长度必须小于等于[65535]|
|文件|默认规则|内容长度必须小于等于[1048576]|
|上月|默认规则|内容长度必须小于等于[200]|
|当前用户|默认规则|默认规则|
|本周|默认规则|内容长度必须小于等于[200]|
|今天|默认规则|内容长度必须小于等于[200]|
|显示日期|默认规则|内容长度必须小于等于[200]|
|对象类型|默认规则|内容长度必须小于等于[30]|
|id|默认规则|默认规则|
|消息通知用户|默认规则|内容长度必须小于等于[100]|
|备注|默认规则|内容长度必须小于等于[65535]|
|已读|默认规则|内容长度必须小于等于[1]|
|动作|默认规则|内容长度必须小于等于[30]|
|日期|默认规则|默认规则|
|产品|默认规则|内容长度必须小于等于[255]|
|备注|默认规则|内容长度必须小于等于[200]|
|前端键值|默认规则|默认规则|
|操作方式|默认规则|内容长度必须小于等于[200]|
|上周|默认规则|内容长度必须小于等于[200]|
|对象ID|默认规则|默认规则|
|操作者|默认规则|内容长度必须小于等于[100]|
|项目|默认规则|默认规则|

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
|添加备注|实体处理逻辑|&nbsp;|
|编辑备注信息|用户自定义|&nbsp;|
|Pms企业专用|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|

## 处理逻辑
* Comment (Comment)
  
   

{% plantuml %}
hide footbox

系统日志 -> 系统日志: 设置默认参数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|设置默认参数 |
<center>Comment</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|BianGengLineHistory|项目立项变更线路操作历史记录([MYSQL5](../../appendix/query_MYSQL5.md#Action_BianGengLineHistory))|否|&nbsp;|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Action_Default))|是|&nbsp;|
|MobType|动态(根据类型过滤)([MYSQL5](../../appendix/query_MYSQL5.md#Action_MobType))|否|&nbsp;|
|MyTrends|项目动态(我的)([MYSQL5](../../appendix/query_MYSQL5.md#Action_MyTrends))|否|&nbsp;|
|ProductTrends|产品动态(产品相关所有)([MYSQL5](../../appendix/query_MYSQL5.md#Action_ProductTrends))|否|&nbsp;|
|ProjectTrends|项目动态(项目相关所有)([MYSQL5](../../appendix/query_MYSQL5.md#Action_ProjectTrends))|否|&nbsp;|
|QueryUserYEAR|查询用户使用年([MYSQL5](../../appendix/query_MYSQL5.md#Action_QueryUserYEAR))|否|&nbsp;|
|Type|动态(根据类型过滤)([MYSQL5](../../appendix/query_MYSQL5.md#Action_Type))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Action_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|MobType|MobType|MobType|否|&nbsp;|
|MyTrends|项目动态(我的)|MyTrends|否|&nbsp;|
|ProductTrends|ProductTrends|ProductTrends|否|&nbsp;|
|ProjectTrends|项目动态(项目相关所有)|ProjectTrends|否|&nbsp;|
|QueryUserYEAR|查询用户使用年|QueryUserYEAR|否|&nbsp;|
|Type|Type|Type|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|本月(THISMONTH)|EQ|
|昨天(YESTERDAY)|EQ|
|上月(LASTMONTH)|EQ|
|本周(THISWEEK)|EQ|
|今天(TODAY)|EQ|
|对象类型(OBJECTTYPE)|EQ|
|备注(COMMENT)|LIKE|
|已读(READ)|EQ|
|动作(ACTION)|EQ|
|操作方式(ACTIONMANNER)|EQ|
|上周(LASTWEEK)|EQ|
|对象ID(OBJECTID)|EQ|
|项目(PROJECT)|EQ|

## 导入模式
无


## 导出模式
无
