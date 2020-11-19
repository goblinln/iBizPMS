# 我的地盘(IBZ_MYTERRITORY)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|fails|FAILS|INT|&nbsp;|
|通讯地址|ADDRESS|TEXT|&nbsp;|
|密码|PASSWORD|TEXT|&nbsp;|
|微信|WEIXIN|TEXT|&nbsp;|
|钉钉|DINGDING|TEXT|&nbsp;|
|账户|ACCOUNT|TEXT|&nbsp;|
|ranzhi|RANZHI|TEXT|&nbsp;|
|slack|SLACK|TEXT|&nbsp;|
|真实姓名|REALNAME|TEXT|&nbsp;|
|locked|LOCKED|DATETIME|&nbsp;|
|scoreLevel|SCORELEVEL|INT|&nbsp;|
|avatar|AVATAR|TEXT|&nbsp;|
|zipcode|ZIPCODE|TEXT|&nbsp;|
|所属部门|DEPT|INT|&nbsp;|
|源代码账户|COMMITER|TEXT|&nbsp;用于关联UAA账号|
|逻辑删除标志|DELETED|TEXT|&nbsp;|
|最后登录|LAST|INT|&nbsp;|
|skype|SKYPE|TEXT|&nbsp;|
|score|SCORE|INT|&nbsp;|
|whatsapp|WHATSAPP|TEXT|&nbsp;|
|访问次数|VISITS|INT|&nbsp;|
|手机|MOBILE|TEXT|&nbsp;|
|clientLang|CLIENTLANG|TEXT|&nbsp;|
|入职日期|JOIN|DATE|&nbsp;|
|ip|IP|TEXT|&nbsp;|
|邮箱|EMAIL|TEXT|&nbsp;|
|nickname|NICKNAME|TEXT|&nbsp;|
|电话|PHONE|TEXT|&nbsp;|
|birthday|BIRTHDAY|DATE|&nbsp;|
|ID|ID|ACID|&nbsp;|
|QQ|QQ|TEXT|&nbsp;|
|男女|GENDER|SSCODELIST|&nbsp;|
|职位|ROLE|TEXT|&nbsp;|
|clientStatus|CLIENTSTATUS|SSCODELIST|&nbsp;|
|我的任务|MYTASKS|INT|&nbsp;|
|我的bugs|MYBUGS|INT|&nbsp;|
|我的过期bug数|MYEBUGS|TEXT|&nbsp;|
|我的需求数|MYSTORYS|INT|&nbsp;|
|未关闭产品数|PRODUCTS|INT|&nbsp;|
|过期项目数|EPROJECTS|TEXT|&nbsp;|
|未关闭项目数|PROJECTS|INT|&nbsp;|
|我的过期任务数|MYETASKS|TEXT|&nbsp;|
|我的待办数|MYTODOCNT|INT|&nbsp;|
|我收藏的需求数|MYFAVORITESTORYS|INT|&nbsp;|
|我收藏的bugs|MYFAVORITEBUGS|INT|&nbsp;|
|我收藏的任务|MYFAVORITETASKS|INT|&nbsp;|
|我的收藏|MYFAVORITES|INT|&nbsp;|
|我的地盘|MYTERRITORYCNT|INT|&nbsp;|
|项目名称|PROJECTNAME|TEXT|&nbsp;|
|完成者|FINISHEDBY|TEXT|&nbsp;|
|预计总工时|TOTALESTIMATE|INT|&nbsp;|
|消耗总工时|TOTALCONSUMED|INT|&nbsp;|
|剩余总工时|TOTALLEFT|INT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|fails|默认规则|默认规则|
|通讯地址|默认规则|内容长度必须小于等于[120]|
|密码|默认规则|内容长度必须小于等于[32]|
|微信|默认规则|内容长度必须小于等于[90]|
|钉钉|默认规则|内容长度必须小于等于[90]|
|账户|默认规则|内容长度必须小于等于[30]|
|ranzhi|默认规则|内容长度必须小于等于[30]|
|slack|默认规则|内容长度必须小于等于[90]|
|真实姓名|默认规则|内容长度必须小于等于[100]|
|locked|默认规则|默认规则|
|scoreLevel|默认规则|默认规则|
|avatar|默认规则|内容长度必须小于等于[30]|
|zipcode|默认规则|内容长度必须小于等于[10]|
|所属部门|默认规则|默认规则|
|源代码账户|默认规则|内容长度必须小于等于[100]|
|逻辑删除标志|默认规则|内容长度必须小于等于[1]|
|最后登录|默认规则|默认规则|
|skype|默认规则|内容长度必须小于等于[90]|
|score|默认规则|默认规则|
|whatsapp|默认规则|内容长度必须小于等于[90]|
|访问次数|默认规则|默认规则|
|手机|默认规则|内容长度必须小于等于[11]|
|clientLang|默认规则|内容长度必须小于等于[10]|
|入职日期|默认规则|默认规则|
|ip|默认规则|内容长度必须小于等于[15]|
|邮箱|默认规则|内容长度必须小于等于[90]|
|nickname|默认规则|内容长度必须小于等于[60]|
|电话|默认规则|内容长度必须小于等于[20]|
|birthday|默认规则|默认规则|
|ID|默认规则|默认规则|
|QQ|默认规则|内容长度必须小于等于[20]|
|男女|默认规则|内容长度必须小于等于[60]|
|职位|默认规则|内容长度必须小于等于[100]|
|clientStatus|默认规则|内容长度必须小于等于[60]|
|我的任务|默认规则|默认规则|
|我的bugs|默认规则|默认规则|
|我的过期bug数|默认规则|内容长度必须小于等于[100]|
|我的需求数|默认规则|默认规则|
|未关闭产品数|默认规则|默认规则|
|过期项目数|默认规则|内容长度必须小于等于[100]|
|未关闭项目数|默认规则|默认规则|
|我的过期任务数|默认规则|内容长度必须小于等于[100]|
|我的待办数|默认规则|默认规则|
|我收藏的需求数|默认规则|默认规则|
|我收藏的bugs|默认规则|默认规则|
|我收藏的任务|默认规则|默认规则|
|我的收藏|默认规则|默认规则|
|我的地盘|默认规则|默认规则|
|项目名称|默认规则|内容长度必须小于等于[100]|
|完成者|默认规则|内容长度必须小于等于[100]|
|预计总工时|默认规则|默认规则|
|消耗总工时|默认规则|默认规则|
|剩余总工时|默认规则|默认规则|

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

我的地盘 -> 我的地盘: 我的
我的地盘 -> 我的地盘: 收藏
我的地盘 -> 我的地盘: 计算我的
我的地盘 -> 我的地盘: 项目数
我的地盘 -> 我的地盘: 计算我的收藏数
我的地盘 -> 我的地盘: 产品数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|我的 |
|2|收藏 |
|3|计算我的 |
|4|项目数 |
|5|计算我的收藏数 |
|5|开始 | 
|6|产品数 |
<center>移动端菜单计数器</center>
* 我的收藏计数器 (MyFavoriteCount)
  
   

{% plantuml %}
hide footbox

我的地盘 -> 我的地盘: 获取我的收藏需求数
我的地盘 -> 我的地盘: 我的收藏Bug数
我的地盘 -> 我的地盘: 我的收藏任务数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|获取我的收藏需求数 |
|2|我的收藏Bug数 |
|3|我的收藏任务数 |
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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#IbzMyTerritory_Default))|否|&nbsp;|
|MyWork|我的工作([MYSQL5](../../appendix/query_MYSQL5.md#IbzMyTerritory_MyWork))|否|&nbsp;|
|MyWorkMob|我的工作([MYSQL5](../../appendix/query_MYSQL5.md#IbzMyTerritory_MyWorkMob))|否|&nbsp;|
|UserFinishTaskSum|用户完成任务统计([MYSQL5](../../appendix/query_MYSQL5.md#IbzMyTerritory_UserFinishTaskSum))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IbzMyTerritory_View))|否|&nbsp;|
|welcome|欢迎([MYSQL5](../../appendix/query_MYSQL5.md#IbzMyTerritory_Welcome))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|MyWork|我的工作|MyWork|否|&nbsp;|
|MyWorkMob|我的工作|MyWorkMob|否|&nbsp;|
|UserFinishTaskSum|用户完成任务统计|UserFinishTaskSum|否|&nbsp;|
|welcome|欢迎|welcome|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|账户(ACCOUNT)|EQ|
|真实姓名(REALNAME)|LIKE|
|男女(GENDER)|EQ|
|clientStatus(CLIENTSTATUS)|EQ|
|项目名称(PROJECTNAME)|EQ|
|完成者(FINISHEDBY)|EQ|

## 导入模式
无


## 导出模式
无
