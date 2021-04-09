# 我的地盘(IBZ_MYTERRITORY)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|我的过期bug数|MYEBUGS|TEXT|&nbsp;|
|nickname|NICKNAME|TEXT|&nbsp;|
|fails|FAILS|INT|&nbsp;|
|访问次数|VISITS|INT|&nbsp;|
|电话|PHONE|TEXT|&nbsp;|
|我的待办数|MYTODOCNT|INT|&nbsp;|
|我的地盘|MYTERRITORYCNT|INT|&nbsp;|
|我的bugs|MYBUGS|INT|&nbsp;|
|职位|ROLE|TEXT|&nbsp;|
|真实姓名|REALNAME|TEXT|&nbsp;|
|clientStatus|CLIENTSTATUS|SSCODELIST|&nbsp;|
|最后登录|LAST|INT|&nbsp;|
|zipcode|ZIPCODE|TEXT|&nbsp;|
|skype|SKYPE|TEXT|&nbsp;|
|我收藏的bugs|MYFAVORITEBUGS|INT|&nbsp;|
|入职日期|JOIN|DATE|&nbsp;|
|score|SCORE|INT|&nbsp;|
|所属部门|DEPT|INT|&nbsp;|
|账户|ACCOUNT|TEXT|&nbsp;|
|我的收藏|MYFAVORITES|INT|&nbsp;|
|我的需求数|MYSTORYS|INT|&nbsp;|
|源代码账户|COMMITER|TEXT|&nbsp;用于关联UAA账号|
|手机|MOBILE|TEXT|&nbsp;|
|locked|LOCKED|DATETIME|&nbsp;|
|项目成员|PROJECTTEAMCNT|INT|&nbsp;|
|通讯地址|ADDRESS|TEXT|&nbsp;|
|我的任务|MYTASKS|INT|&nbsp;|
|scoreLevel|SCORELEVEL|INT|&nbsp;|
|密码|PASSWORD|TEXT|&nbsp;|
|剩余里程碑（今日到期）|LEFTLCBJZCNT|TEXT|&nbsp;|
|ranzhi|RANZHI|TEXT|&nbsp;|
|今日截止待办|MYTODOCNTJZ|TEXT|&nbsp;|
|未关闭项目数|PROJECTS|INT|&nbsp;|
|slack|SLACK|TEXT|&nbsp;|
|未关闭产品数|PRODUCTS|INT|&nbsp;|
|微信|WEIXIN|TEXT|&nbsp;|
|我的过期任务数|MYETASKS|TEXT|&nbsp;|
|过期项目数|EPROJECTS|TEXT|&nbsp;|
|whatsapp|WHATSAPP|TEXT|&nbsp;|
|QQ|QQ|TEXT|&nbsp;|
|男女|GENDER|SSCODELIST|&nbsp;|
|clientLang|CLIENTLANG|TEXT|&nbsp;|
|逻辑删除标志|DELETED|TEXT|&nbsp;|
|我收藏的任务|MYFAVORITETASKS|INT|&nbsp;|
|birthday|BIRTHDAY|DATE|&nbsp;|
|ip|IP|TEXT|&nbsp;|
|我收藏的需求数|MYFAVORITESTORYS|INT|&nbsp;|
|邮箱|EMAIL|TEXT|&nbsp;|
|钉钉|DINGDING|TEXT|&nbsp;|
|avatar|AVATAR|TEXT|&nbsp;|
|ID|ID|ACID|&nbsp;|
|剩余里程碑|LEFTLCBCNT|INT|&nbsp;|
|项目成员（今日截止）|PROJECTTEAMJZCNT|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|我的过期bug数|默认规则|内容长度必须小于等于[100]|
|nickname|默认规则|内容长度必须小于等于[60]|
|fails|默认规则|默认规则|
|访问次数|默认规则|默认规则|
|电话|默认规则|内容长度必须小于等于[20]|
|我的待办数|默认规则|默认规则|
|我的地盘|默认规则|默认规则|
|我的bugs|默认规则|默认规则|
|职位|默认规则|内容长度必须小于等于[100]|
|真实姓名|默认规则|内容长度必须小于等于[100]|
|clientStatus|默认规则|内容长度必须小于等于[60]|
|最后登录|默认规则|默认规则|
|zipcode|默认规则|内容长度必须小于等于[10]|
|skype|默认规则|内容长度必须小于等于[90]|
|我收藏的bugs|默认规则|默认规则|
|入职日期|默认规则|默认规则|
|score|默认规则|默认规则|
|所属部门|默认规则|默认规则|
|账户|默认规则|内容长度必须小于等于[30]|
|我的收藏|默认规则|默认规则|
|我的需求数|默认规则|默认规则|
|源代码账户|默认规则|内容长度必须小于等于[100]|
|手机|默认规则|内容长度必须小于等于[11]|
|locked|默认规则|默认规则|
|项目成员|默认规则|默认规则|
|通讯地址|默认规则|内容长度必须小于等于[120]|
|我的任务|默认规则|默认规则|
|scoreLevel|默认规则|默认规则|
|密码|默认规则|内容长度必须小于等于[32]|
|剩余里程碑（今日到期）|默认规则|内容长度必须小于等于[100]|
|ranzhi|默认规则|内容长度必须小于等于[30]|
|今日截止待办|默认规则|内容长度必须小于等于[100]|
|未关闭项目数|默认规则|默认规则|
|slack|默认规则|内容长度必须小于等于[90]|
|未关闭产品数|默认规则|默认规则|
|微信|默认规则|内容长度必须小于等于[90]|
|我的过期任务数|默认规则|内容长度必须小于等于[100]|
|过期项目数|默认规则|内容长度必须小于等于[100]|
|whatsapp|默认规则|内容长度必须小于等于[90]|
|QQ|默认规则|内容长度必须小于等于[20]|
|男女|默认规则|内容长度必须小于等于[60]|
|clientLang|默认规则|内容长度必须小于等于[10]|
|逻辑删除标志|默认规则|内容长度必须小于等于[1]|
|我收藏的任务|默认规则|默认规则|
|birthday|默认规则|默认规则|
|ip|默认规则|内容长度必须小于等于[15]|
|我收藏的需求数|默认规则|默认规则|
|邮箱|默认规则|内容长度必须小于等于[90]|
|钉钉|默认规则|内容长度必须小于等于[90]|
|avatar|默认规则|内容长度必须小于等于[30]|
|ID|默认规则|默认规则|
|剩余里程碑|默认规则|默认规则|
|项目成员（今日截止）|默认规则|内容长度必须小于等于[100]|

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
|移动端菜单计数器|实体处理逻辑|&nbsp;|
|我的收藏计数器|实体处理逻辑|&nbsp;|
|我的地盘移动端计数器|实体处理逻辑|&nbsp;|
|Save|内置方法|&nbsp;|

## 处理逻辑
* 移动端菜单计数器 (MobMenuCount)
  
   

{% plantuml %}
hide footbox

我的地盘 -> 我的地盘: 收藏
我的地盘 -> 我的地盘: 我的
我的地盘 -> 我的地盘: 计算我的
我的地盘 -> 我的地盘: 产品数
我的地盘 -> 我的地盘: 项目数
我的地盘 -> 我的地盘: 计算我的收藏数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|收藏 |
|2|我的 |
|3|计算我的 |
|3|开始 | 
|4|产品数 |
|5|项目数 |
|6|计算我的收藏数 |
<center>移动端菜单计数器</center>
* 我的收藏计数器 (MyFavoriteCount)
  
   

{% plantuml %}
hide footbox

我的地盘 -> 我的地盘: 我的收藏任务数
我的地盘 -> 我的地盘: 我的收藏Bug数
我的地盘 -> 我的地盘: 获取我的收藏需求数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|我的收藏任务数 |
|2|我的收藏Bug数 |
|2|开始 | 
|3|获取我的收藏需求数 |
<center>我的收藏计数器</center>
* 我的地盘移动端计数器 (MyTerritoryCount)
  
   

{% plantuml %}
hide footbox

我的地盘 -> 我的地盘: 获取我的Bug数
我的地盘 -> 我的地盘: 我的待办数
我的地盘 -> 我的地盘: 获取我的Bug数
我的地盘 -> 我的地盘: 获取我的需求数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|获取我的Bug数 |
|2|我的待办数 |
|3|获取我的Bug数 |
|3|开始 | 
|4|获取我的需求数 |
<center>我的地盘移动端计数器</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#IbzMyTerritory_Default))|是|&nbsp;|
|MyWork|我的工作([MYSQL5](../../appendix/query_MYSQL5.md#IbzMyTerritory_MyWork))|否|&nbsp;|
|MyWorkMob|我的工作([MYSQL5](../../appendix/query_MYSQL5.md#IbzMyTerritory_MyWorkMob))|否|&nbsp;|
|MyWorkPm|我的工作（项目经理）([MYSQL5](../../appendix/query_MYSQL5.md#IbzMyTerritory_MyWorkPm))|否|&nbsp;|
|PersonInfo|个人信息-个人贡献([MYSQL5](../../appendix/query_MYSQL5.md#IbzMyTerritory_PersonInfo))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IbzMyTerritory_View))|否|&nbsp;|
|welcome|欢迎([MYSQL5](../../appendix/query_MYSQL5.md#IbzMyTerritory_Welcome))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|MyWork|我的工作|MyWork|否|&nbsp;|
|MyWorkMob|我的工作|MyWorkMob|否|&nbsp;|
|MyWorkPm|我的工作（项目经理）|MyWorkPm|否|&nbsp;|
|PersonInfo|个人信息-个人贡献|PersonInfo|否|&nbsp;|
|welcome|欢迎|welcome|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|真实姓名(REALNAME)|LIKE|
|clientStatus(CLIENTSTATUS)|EQ|
|账户(ACCOUNT)|EQ|
|男女(GENDER)|EQ|

## 导入模式
无


## 导出模式
无
