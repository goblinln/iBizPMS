# 用户年度工作内容统计(IBZ_USERYEARWORKSTATS)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|密码|PASSWORD|TEXT|&nbsp;|
|累计参与产品数|YEARPRODUCTCNT|INT|&nbsp;|
|累计创建Bug数|YEARBUGCNT|INT|&nbsp;|
|微信|WEIXIN|TEXT|&nbsp;|
|累计创建用例数|YEARCASECNT|INT|&nbsp;|
|账号|ACCOUNT|TEXT|&nbsp;|
|QQ|QQ|TEXT|&nbsp;|
|ranzhi|RANZHI|TEXT|&nbsp;|
|nickname|NICKNAME|TEXT|&nbsp;|
|avatar|AVATAR|TEXT|&nbsp;|
|月完成任务数|MONTHFINISHTASK|INT|&nbsp;|
|fails|FAILS|INT|&nbsp;|
|入职日期|JOIN|DATETIME|&nbsp;|
|累计创建需求数|YEARSTORYCNT|INT|&nbsp;|
|累计创建计划数|YEARPLANCNT|INT|&nbsp;|
|钉钉|DINGDING|TEXT|&nbsp;|
|ip|IP|TEXT|&nbsp;|
|累计动态数|YEARACTIONCNT|INT|&nbsp;|
|手机|MOBILE|TEXT|&nbsp;|
|whatsapp|WHATSAPP|TEXT|&nbsp;|
|用户编号|ID|ACID|&nbsp;|
|邮箱|EMAIL|TEXT|&nbsp;|
|累计工时数|YEARESTIMATECNT|INT|&nbsp;|
|clientLang|CLIENTLANG|TEXT|&nbsp;|
|月累计工时|MONTESTIMATE|INT|&nbsp;|
|真实用户名|REALNAME|TEXT|&nbsp;|
|源代码账户|COMMITER|TEXT|&nbsp;|
|slack|SLACK|TEXT|&nbsp;|
|逻辑删除标志|DELETED|TEXT|&nbsp;|
|最后登录|LAST|INT|&nbsp;|
|年度|CURYEAR|TEXT|&nbsp;|
|判断角色|JUDGEROLE|TEXT|&nbsp;|
|skype|SKYPE|TEXT|&nbsp;|
|birthday|BIRTHDAY|DATETIME|&nbsp;|
|电话|PHONE|TEXT|&nbsp;|
|累计登录次数|YEARVISITS|INT|&nbsp;|
|score|SCORE|INT|&nbsp;|
|角色|ROLE|TEXT|&nbsp;|
|clientStatus|CLIENTSTATUS|TEXT|&nbsp;|
|部门编号|DEPT|SSCODELIST|&nbsp;|
|标题|TITLE|TEXT|&nbsp;|
|当前月|CURMONTH|TEXT|&nbsp;|
|月解决Bug数|MONTRESOLVEDBUG|INT|&nbsp;|
|通讯地址|ADDRESS|TEXT|&nbsp;|
|scoreLevel|SCORELEVEL|INT|&nbsp;|
|累计登录次数|VISITS|INT|&nbsp;|
|累计日志数|YEARLOGCNT|INT|&nbsp;|
|locked|LOCKED|DATETIME|&nbsp;|
|性别|GENDER|TEXT|&nbsp;|
|zipcode|ZIPCODE|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|密码|默认规则|内容长度必须小于等于[32]|
|累计参与产品数|默认规则|默认规则|
|累计创建Bug数|默认规则|默认规则|
|微信|默认规则|内容长度必须小于等于[90]|
|累计创建用例数|默认规则|默认规则|
|账号|默认规则|内容长度必须小于等于[100]|
|QQ|默认规则|内容长度必须小于等于[20]|
|ranzhi|默认规则|内容长度必须小于等于[30]|
|nickname|默认规则|内容长度必须小于等于[60]|
|avatar|默认规则|内容长度必须小于等于[30]|
|月完成任务数|默认规则|默认规则|
|fails|默认规则|默认规则|
|入职日期|默认规则|默认规则|
|累计创建需求数|默认规则|默认规则|
|累计创建计划数|默认规则|默认规则|
|钉钉|默认规则|内容长度必须小于等于[90]|
|ip|默认规则|内容长度必须小于等于[15]|
|累计动态数|默认规则|默认规则|
|手机|默认规则|内容长度必须小于等于[11]|
|whatsapp|默认规则|内容长度必须小于等于[90]|
|用户编号|默认规则|默认规则|
|邮箱|默认规则|内容长度必须小于等于[90]|
|累计工时数|默认规则|默认规则|
|clientLang|默认规则|内容长度必须小于等于[10]|
|月累计工时|默认规则|默认规则|
|真实用户名|默认规则|内容长度必须小于等于[100]|
|源代码账户|默认规则|内容长度必须小于等于[100]|
|slack|默认规则|内容长度必须小于等于[90]|
|逻辑删除标志|默认规则|内容长度必须小于等于[1]|
|最后登录|默认规则|默认规则|
|年度|默认规则|内容长度必须小于等于[200]|
|判断角色|默认规则|内容长度必须小于等于[100]|
|skype|默认规则|内容长度必须小于等于[90]|
|birthday|默认规则|默认规则|
|电话|默认规则|内容长度必须小于等于[20]|
|累计登录次数|默认规则|默认规则|
|score|默认规则|默认规则|
|角色|默认规则|内容长度必须小于等于[100]|
|clientStatus|默认规则|内容长度必须小于等于[7]|
|部门编号|默认规则|内容长度必须小于等于[60]|
|标题|默认规则|内容长度必须小于等于[200]|
|当前月|默认规则|内容长度必须小于等于[200]|
|月解决Bug数|默认规则|默认规则|
|通讯地址|默认规则|内容长度必须小于等于[120]|
|scoreLevel|默认规则|默认规则|
|累计登录次数|默认规则|默认规则|
|累计日志数|默认规则|默认规则|
|locked|默认规则|默认规则|
|性别|默认规则|内容长度必须小于等于[1]|
|zipcode|默认规则|内容长度必须小于等于[10]|

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
|获取研发人员相关数据|实体处理逻辑|&nbsp;|
|获取产品经理相关数据|实体处理逻辑|&nbsp;|
|获取测试人员相关数据|实体处理逻辑|&nbsp;|
|获取用户所选年度的动作|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|
|更新标题|用户自定义|&nbsp;|

## 处理逻辑
* 获取研发人员相关数据 (GetDevInfomation)
  
   

{% plantuml %}
hide footbox

用户年度工作内容统计 -> 用户年度工作内容统计: 获取日志数
用户年度工作内容统计 -> 用户年度工作内容统计: 获取动态数
用户年度工作内容统计 -> 用户年度工作内容统计: 获取工时数
用户年度工作内容统计 -> 用户年度工作内容统计: 获取登录数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|获取日志数 |
|1|开始 | 
|2|获取动态数 |
|3|获取工时数 |
|4|获取登录数 |
<center>获取研发人员相关数据</center>
* 获取相关信息 (GetInfo)
  
   

{% plantuml %}
hide footbox

用户年度工作内容统计 -> 用户年度工作内容统计: 获取用户信息
用户年度工作内容统计 -> 用户年度工作内容统计: 获取测试用户信息
用户年度工作内容统计 -> 用户年度工作内容统计: 获取研发用户信息
用户年度工作内容统计 -> 用户年度工作内容统计: 获取产品经理信息
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|获取用户信息 |
|2|获取测试用户信息 |
|3|获取研发用户信息 |
|4|获取产品经理信息 |
<center>获取相关信息</center>
* 获取产品经理相关数据 (GetPOInfomation)
  
   

{% plantuml %}
hide footbox

用户年度工作内容统计 -> 用户年度工作内容统计: 获取需求数
用户年度工作内容统计 -> 用户年度工作内容统计: 获取计划数
用户年度工作内容统计 -> 用户年度工作内容统计: 获取登录数
用户年度工作内容统计 -> 用户年度工作内容统计: 获取产品数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|获取需求数 |
|2|获取计划数 |
|3|获取登录数 |
|3|开始 | 
|4|获取产品数 |
<center>获取产品经理相关数据</center>
* 获取测试人员相关数据 (GetQAInformation)
  
   

{% plantuml %}
hide footbox

用户年度工作内容统计 -> 用户年度工作内容统计: 获取创建用例数
用户年度工作内容统计 -> 用户年度工作内容统计: 获取登录次数
用户年度工作内容统计 -> 用户年度工作内容统计: 获取动态数
用户年度工作内容统计 -> 用户年度工作内容统计: 获取创建bug数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|获取创建用例数 |
|2|获取登录次数 |
|2|开始 | 
|3|获取动态数 |
|4|获取创建bug数 |
<center>获取测试人员相关数据</center>
* 更新相关信息 (UpdateInfo)
  
   

{% plantuml %}
hide footbox

用户年度工作内容统计 -> 用户年度工作内容统计: 获取研发用户信息
用户年度工作内容统计 -> 用户年度工作内容统计: 获取测试用户信息
用户年度工作内容统计 -> 用户年度工作内容统计: 获取产品经理信息
用户年度工作内容统计 -> 用户年度工作内容统计: 获取标题
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|获取研发用户信息 |
|2|获取测试用户信息 |
|3|获取产品经理信息 |
|3|开始 | 
|4|获取标题 |
<center>更新相关信息</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#UserYearWorkStats_Default))|是|&nbsp;|
|MonthFinishTaskAndBug|月完成任务数及累计工时和解决Bug数([MYSQL5](../../appendix/query_MYSQL5.md#UserYearWorkStats_MonthFinishTaskAndBug))|否|&nbsp;|
|MonthOpenedBugAndCase|月创建Bug数和创建用例数([MYSQL5](../../appendix/query_MYSQL5.md#UserYearWorkStats_MonthOpenedBugAndCase))|否|&nbsp;|
|MonthOpenedStory|月创建需求数([MYSQL5](../../appendix/query_MYSQL5.md#UserYearWorkStats_MonthOpenedStory))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#UserYearWorkStats_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|
|MonthFinishTaskAndBug|月完成任务数及累计工时和解决Bug数|MonthFinishTaskAndBug|否|&nbsp;|
|MonthOpenedBugAndCase|月创建Bug数和创建用例数|MonthOpenedBugAndCase|否|&nbsp;|
|MonthOpenedStory|月创建需求数|MonthOpenedStory|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|用户编号(ID)|EQ|
|真实用户名(REALNAME)|LIKE|
|部门编号(DEPT)|EQ|

## 导入模式
无


## 导出模式
无
