# 组成员(SYS_TEAMMEMBER)

  

## 关系
{% plantuml %}
人员 *-- 组成员 
岗位 *-- 组成员 
组 *-- 组成员 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|组成员标识|TEAMMEMBERID|TEXT|&nbsp;|
|组名称|TEAMNAME|PICKUPTEXT|&nbsp;|
|岗位|POSTNAME|PICKUPTEXT|&nbsp;|
|区属|DOMAINS|TEXT|&nbsp;|
|头像|USERICON|PICKUPDATA|&nbsp;|
|姓名|PERSONNAME|PICKUPTEXT|&nbsp;|
|账号|USERNAME|PICKUPDATA|&nbsp;|
|岗位标识|POSTID|PICKUP|&nbsp;|
|组标识|TEAMID|PICKUP|&nbsp;|
|用户标识|USERID|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|组成员标识|默认规则|内容长度必须小于等于[100]|
|组名称|默认规则|内容长度必须小于等于[100]|
|岗位|默认规则|内容长度必须小于等于[100]|
|区属|默认规则|内容长度必须小于等于[100]|
|头像|默认规则|内容长度必须小于等于[255]|
|姓名|默认规则|内容长度必须小于等于[100]|
|账号|默认规则|内容长度必须小于等于[100]|
|岗位标识|默认规则|内容长度必须小于等于[100]|
|组标识|默认规则|内容长度必须小于等于[100]|
|用户标识|默认规则|内容长度必须小于等于[100]|

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
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#SysTeamMember_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#SysTeamMember_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|组成员标识(TEAMMEMBERID)|EQ|
|组名称(TEAMNAME)|EQ|
|组名称(TEAMNAME)|LIKE|
|岗位(POSTNAME)|EQ|
|岗位(POSTNAME)|LIKE|
|姓名(PERSONNAME)|EQ|
|姓名(PERSONNAME)|LIKE|
|岗位标识(POSTID)|EQ|
|组标识(TEAMID)|EQ|
|用户标识(USERID)|EQ|

## 导入模式
无


## 导出模式
无
