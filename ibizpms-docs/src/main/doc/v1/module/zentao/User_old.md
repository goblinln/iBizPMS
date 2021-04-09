# 用户(ZT_USER)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|密码|PASSWORD|TEXT|&nbsp;|
|通讯地址|ADDRESS|TEXT|&nbsp;|
|微信|WEIXIN|TEXT|&nbsp;|
|钉钉|DINGDING|TEXT|&nbsp;|
|fails|FAILS|INT|&nbsp;|
|slack|SLACK|TEXT|&nbsp;|
|ranzhi|RANZHI|TEXT|&nbsp;|
|账户|ACCOUNT|TEXT|&nbsp;|
|locked|LOCKED|DATETIME|&nbsp;|
|avatar|AVATAR|TEXT|&nbsp;|
|scoreLevel|SCORELEVEL|INT|&nbsp;|
|真实姓名|REALNAME|TEXT|&nbsp;|
|zipcode|ZIPCODE|TEXT|&nbsp;|
|所属部门|DEPT|INT|&nbsp;|
|源代码账户|COMMITER|TEXT|&nbsp;用于关联UAA账号|
|职位|ROLE|SSCODELIST|&nbsp;|
|逻辑删除标志|DELETED|TEXT|&nbsp;|
|最后登录|LAST|INT|&nbsp;|
|clientStatus|CLIENTSTATUS|SSCODELIST|&nbsp;|
|skype|SKYPE|TEXT|&nbsp;|
|whatsapp|WHATSAPP|TEXT|&nbsp;|
|score|SCORE|INT|&nbsp;|
|性别|GENDER|SSCODELIST|&nbsp;|
|手机|MOBILE|TEXT|&nbsp;|
|clientLang|CLIENTLANG|TEXT|&nbsp;|
|访问次数|VISITS|INT|&nbsp;|
|入职日期|JOIN|DATE|&nbsp;|
|邮箱|EMAIL|TEXT|&nbsp;|
|ip|IP|TEXT|&nbsp;|
|birthday|BIRTHDAY|DATE|&nbsp;|
|nickname|NICKNAME|TEXT|&nbsp;|
|电话|PHONE|TEXT|&nbsp;|
|ID|ID|ACID|&nbsp;|
|QQ|QQ|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|密码|默认规则|内容长度必须小于等于[32]|
|通讯地址|默认规则|内容长度必须小于等于[120]|
|微信|默认规则|内容长度必须小于等于[90]|
|钉钉|默认规则|内容长度必须小于等于[90]|
|fails|默认规则|默认规则|
|slack|默认规则|内容长度必须小于等于[90]|
|ranzhi|默认规则|内容长度必须小于等于[30]|
|账户|默认规则|内容长度必须小于等于[30]|
|locked|默认规则|默认规则|
|avatar|默认规则|内容长度必须小于等于[30]|
|scoreLevel|默认规则|默认规则|
|真实姓名|默认规则|内容长度必须小于等于[100]|
|zipcode|默认规则|内容长度必须小于等于[10]|
|所属部门|默认规则|默认规则|
|源代码账户|默认规则|内容长度必须小于等于[100]|
|职位|默认规则|内容长度必须小于等于[10]|
|逻辑删除标志|默认规则|内容长度必须小于等于[1]|
|最后登录|默认规则|默认规则|
|clientStatus|默认规则|内容长度必须小于等于[7]|
|skype|默认规则|内容长度必须小于等于[90]|
|whatsapp|默认规则|内容长度必须小于等于[90]|
|score|默认规则|默认规则|
|性别|默认规则|内容长度必须小于等于[1]|
|手机|默认规则|内容长度必须小于等于[11]|
|clientLang|默认规则|内容长度必须小于等于[10]|
|访问次数|默认规则|默认规则|
|入职日期|默认规则|默认规则|
|邮箱|默认规则|内容长度必须小于等于[90]|
|ip|默认规则|内容长度必须小于等于[15]|
|birthday|默认规则|默认规则|
|nickname|默认规则|内容长度必须小于等于[60]|
|电话|默认规则|内容长度必须小于等于[20]|
|ID|默认规则|默认规则|
|QQ|默认规则|内容长度必须小于等于[20]|

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
|根据代码账户查询用户信息|内置方法|&nbsp;|
|Save|内置方法|&nbsp;|
|同步账号|用户自定义|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|BugUser|Bug用户([MYSQL5](../../appendix/query_MYSQL5.md#User_BugUser))|否|&nbsp;|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#User_Default))|是|&nbsp;|
|getByLogin|账户获取登录名([MYSQL5](../../appendix/query_MYSQL5.md#User_GetByLogin))|否|&nbsp;根据ZT账户标识（account、邮箱、手机号、源代码账户）获取登录名，用于关联UAA登录与ZT登录|
|ProjectTeamM|项目团队管理([MYSQL5](../../appendix/query_MYSQL5.md#User_ProjectTeamM))|否|&nbsp;排除当前项目已经选择的用户|
|ProjectTeamUser|项目团队成员([MYSQL5](../../appendix/query_MYSQL5.md#User_ProjectTeamUser))|否|&nbsp;|
|ProjectTeamUserTask|项目团队成员([MYSQL5](../../appendix/query_MYSQL5.md#User_ProjectTeamUserTask))|否|&nbsp;|
|TASKTEAM|TASKTEAM([MYSQL5](../../appendix/query_MYSQL5.md#User_TaskTeam))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#User_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|BugUser|Bug用户|BugUser|否|&nbsp;|
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|getByCommiter|根据源代码账户获取登录名|getByLogin|否|&nbsp;|
|ProjectTeamM|项目团队管理|ProjectTeamM|否|&nbsp;|
|ProjectTeamUser|项目团队成员|ProjectTeamUser|否|&nbsp;|
|ProjectTeamUserTask|项目团队成员|ProjectTeamUserTask|否|&nbsp;|
|TASKTEAM|TASKTEAM|TASKTEAM|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|账户(ACCOUNT)|IN|
|账户(ACCOUNT)|NOTIN|
|真实姓名(REALNAME)|LIKE|
|所属部门(DEPT)|EQ|
|职位(ROLE)|EQ|
|clientStatus(CLIENTSTATUS)|EQ|
|性别(GENDER)|EQ|

## 导入模式
无


## 导出模式
无
