# 人员(IBZEMP)

  

## 关系
{% plantuml %}
人员 *-- 组成员 
部门 *-- 人员 
单位 *-- 人员 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|用户标识|USERID|TEXT|&nbsp;|
|用户全局名|USERNAME|TEXT|&nbsp;|
|姓名|PERSONNAME|TEXT|&nbsp;|
|用户工号|USERCODE|TEXT|&nbsp;|
|登录名|LOGINNAME|TEXT|&nbsp;|
|密码|PASSWORD|TEXT|&nbsp;|
|区属|DOMAINS|TEXT|&nbsp;|
|主部门|MDEPTID|PICKUP|&nbsp;|
|主部门代码|MDEPTCODE|PICKUPDATA|&nbsp;|
|主部门名称|MDEPTNAME|PICKUPTEXT|&nbsp;|
|业务编码|BCODE|TEXT|&nbsp;|
|单位|ORGID|PICKUP|&nbsp;|
|单位代码|ORGCODE|PICKUPDATA|&nbsp;|
|单位名称|ORGNAME|PICKUPTEXT|&nbsp;|
|昵称别名|NICKNAME|TEXT|&nbsp;|
|性别|SEX|SSCODELIST|&nbsp;|
|联系方式|PHONE|TEXT|&nbsp;|
|邮件|EMAIL|TEXT|&nbsp;|
|社交账号|AVATAR|TEXT|&nbsp;|
|地址|ADDR|TEXT|&nbsp;|
|照片|USERICON|TEXT|&nbsp;|
|ip地址|IPADDR|TEXT|&nbsp;|
|语言|LANG|TEXT|&nbsp;|
|备注|MEMO|TEXT|&nbsp;|
|保留|RESERVER|TEXT|&nbsp;|
|排序|SHOWORDER|INT|&nbsp;|
|逻辑有效|ENABLE|YESNO|&nbsp;|
|创建时间|CREATEDATE|DATETIME|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|用户标识|默认规则|内容长度必须小于等于[100]|
|用户全局名|默认规则|内容长度必须小于等于[100]|
|姓名|默认规则|内容长度必须小于等于[100]|
|用户工号|默认规则|内容长度必须小于等于[100]|
|登录名|默认规则|内容长度必须小于等于[100]|
|密码|默认规则|内容长度必须小于等于[100]|
|区属|默认规则|内容长度必须小于等于[100]|
|主部门|默认规则|内容长度必须小于等于[100]|
|主部门代码|默认规则|内容长度必须小于等于[100]|
|主部门名称|默认规则|内容长度必须小于等于[100]|
|业务编码|默认规则|内容长度必须小于等于[100]|
|单位|默认规则|内容长度必须小于等于[100]|
|单位代码|默认规则|内容长度必须小于等于[100]|
|单位名称|默认规则|内容长度必须小于等于[100]|
|昵称别名|默认规则|内容长度必须小于等于[100]|
|性别|默认规则|内容长度必须小于等于[20]|
|联系方式|默认规则|内容长度必须小于等于[100]|
|邮件|默认规则|内容长度必须小于等于[100]|
|社交账号|默认规则|内容长度必须小于等于[100]|
|地址|默认规则|内容长度必须小于等于[255]|
|照片|默认规则|内容长度必须小于等于[255]|
|ip地址|默认规则|内容长度必须小于等于[100]|
|语言|默认规则|内容长度必须小于等于[100]|
|备注|默认规则|内容长度必须小于等于[255]|
|保留|默认规则|内容长度必须小于等于[255]|
|排序|默认规则|默认规则|
|逻辑有效|默认规则|默认规则|
|创建时间|默认规则|默认规则|
|更新时间|默认规则|默认规则|
|更新人|默认规则|内容长度必须小于等于[60]|
|建立人|默认规则|内容长度必须小于等于[60]|

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
|Save|内置方法|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|BugUser|Bug用户([MYSQL5](../../appendix/query_MYSQL5.md#SysEmployee_BugUser))|否|&nbsp;|
|ContActList|联系人用户([MYSQL5](../../appendix/query_MYSQL5.md#SysEmployee_ContActList))|否|&nbsp;|
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#SysEmployee_Default))|是|&nbsp;|
|ProductTeamM|项目团队管理([MYSQL5](../../appendix/query_MYSQL5.md#SysEmployee_ProductTeamM))|否|&nbsp;排除当前项目已经选择的用户|
|ProjectTeamM|项目团队管理([MYSQL5](../../appendix/query_MYSQL5.md#SysEmployee_ProjectTeamM))|否|&nbsp;排除当前项目已经选择的用户|
|ProjectTeamMProduct|项目团队管理([MYSQL5](../../appendix/query_MYSQL5.md#SysEmployee_ProjectTeamMProduct))|否|&nbsp;排除当前项目已经选择的用户|
|ProjectTeamTaskUserTemp|项目团队成员(临时)([MYSQL5](../../appendix/query_MYSQL5.md#SysEmployee_ProjectTeamTaskUserTemp))|否|&nbsp;|
|ProjectTeamUser|项目团队成员([MYSQL5](../../appendix/query_MYSQL5.md#SysEmployee_ProjectTeamUser))|否|&nbsp;|
|ProjectTeamUserTask|项目团队成员([MYSQL5](../../appendix/query_MYSQL5.md#SysEmployee_ProjectTeamUserTask))|否|&nbsp;|
|ProjectteamPk|项目团队成员选择([MYSQL5](../../appendix/query_MYSQL5.md#SysEmployee_ProjectteamPk))|否|&nbsp;|
|StoryProductTeamChoice|产品团队成员选择([MYSQL5](../../appendix/query_MYSQL5.md#SysEmployee_StoryProductTeamPK))|否|&nbsp;|
|TaskMTeam|任务多人团队([MYSQL5](../../appendix/query_MYSQL5.md#SysEmployee_TaskMTeam))|否|&nbsp;|
|TASKTEAM|任务团队([MYSQL5](../../appendix/query_MYSQL5.md#SysEmployee_TaskTeam))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#SysEmployee_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|BugUser|Bug用户|BugUser|否|&nbsp;|
|ContActList|联系人用户|ContActList|否|&nbsp;|
|DEFAULT|数据集|DEFAULT|是|&nbsp;|
|ProductTeamM|项目团队管理|ProductTeamM|否|&nbsp;|
|ProjectTeamM|项目团队管理|ProjectTeamM|否|&nbsp;|
|ProjectTeamMProduct|项目团队管理|ProjectTeamMProduct|否|&nbsp;|
|ProjectTeamTaskUserTemp|项目团队成员(临时)|ProjectTeamTaskUserTemp|否|&nbsp;|
|ProjectTeamUser|项目团队成员|ProjectTeamUser|否|&nbsp;|
|ProjectTeamUserTask|项目团队成员|ProjectTeamUserTask|否|&nbsp;|
|ProjectteamPk|项目团队成员选择|ProjectteamPk|否|&nbsp;|
|StoryProductTeamChoice|产品团队成员选择|StoryProductTeamChoice|否|&nbsp;|
|TaskMTeam|任务多人团队|TaskMTeam|否|&nbsp;|
|TASKTEAM|数据查询2|TASKTEAM|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|用户标识(USERID)|EQ|
|用户全局名(USERNAME)|EQ|
|用户全局名(USERNAME)|IN|
|用户全局名(USERNAME)|LIKE|
|用户全局名(USERNAME)|NOTIN|
|姓名(PERSONNAME)|LIKE|
|主部门(MDEPTID)|EQ|
|主部门名称(MDEPTNAME)|EQ|
|主部门名称(MDEPTNAME)|LIKE|
|单位(ORGID)|EQ|
|单位名称(ORGNAME)|EQ|
|单位名称(ORGNAME)|LIKE|
|性别(SEX)|EQ|

## 导入模式
无


## 导出模式
无
