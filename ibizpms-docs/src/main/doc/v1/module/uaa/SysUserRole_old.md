# 用户角色关系(SYS_USER_ROLE)

  

## 关系
{% plantuml %}
系统角色 *-- 用户角色关系 
系统用户 *-- 用户角色关系 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|用户角色关系标识|SYS_USER_ROLEID|GUID|&nbsp;|
|角色标识|SYS_ROLEID|PICKUP|&nbsp;|
|角色名称|SYS_ROLENAME|PICKUPTEXT|&nbsp;|
|用户标识|SYS_USERID|PICKUP|&nbsp;|
|用户名称|SYS_USERNAME|PICKUPTEXT|&nbsp;|
|登录名|LOGINNAME|PICKUPDATA|&nbsp;|
|单位|ORGNAME|PICKUPDATA|&nbsp;|
|主部门|MDEPTNAME|PICKUPDATA|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|用户角色关系标识|默认规则|内容长度必须小于等于[100]|
|角色标识|默认规则|内容长度必须小于等于[100]|
|角色名称|默认规则|内容长度必须小于等于[100]|
|用户标识|默认规则|内容长度必须小于等于[100]|
|用户名称|默认规则|内容长度必须小于等于[100]|
|登录名|默认规则|内容长度必须小于等于[100]|
|单位|默认规则|内容长度必须小于等于[100]|
|主部门|默认规则|内容长度必须小于等于[100]|
|建立时间|默认规则|默认规则|
|更新时间|默认规则|默认规则|

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
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#SysUserRole_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#SysUserRole_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|用户角色关系标识(SYS_USER_ROLEID)|EQ|
|角色标识(SYS_ROLEID)|EQ|
|角色名称(SYS_ROLENAME)|EQ|
|角色名称(SYS_ROLENAME)|IN|
|角色名称(SYS_ROLENAME)|LIKE|
|角色名称(SYS_ROLENAME)|NOTIN|
|用户标识(SYS_USERID)|EQ|
|用户标识(SYS_USERID)|IN|
|用户标识(SYS_USERID)|LIKE|
|用户名称(SYS_USERNAME)|EQ|
|用户名称(SYS_USERNAME)|IN|
|用户名称(SYS_USERNAME)|LIKE|
|用户名称(SYS_USERNAME)|NOTIN|

## 导入模式
无


## 导出模式
无
