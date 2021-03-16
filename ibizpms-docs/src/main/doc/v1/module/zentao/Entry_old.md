# entry(ZT_ENTRY)

  

## 关系
{% plantuml %}
entry *-- Bug 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|逻辑删除标志|DELETED|TEXT|&nbsp;|
|code|CODE|TEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|editedDate|EDITEDDATE|DATETIME|&nbsp;|
|createdDate|CREATEDDATE|DATETIME|&nbsp;|
|freePasswd|FREEPASSWD|SSCODELIST|&nbsp;|
|createdBy|CREATEDBY|TEXT|&nbsp;|
|account|ACCOUNT|TEXT|&nbsp;|
|calledTime|CALLEDTIME|INT|&nbsp;|
|key|KEY|TEXT|&nbsp;|
|editedBy|EDITEDBY|TEXT|&nbsp;|
|ip|IP|TEXT|&nbsp;|
|desc|DESC|LONGTEXT|&nbsp;|
|name|NAME|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|逻辑删除标志|默认规则|内容长度必须小于等于[1]|
|code|默认规则|内容长度必须小于等于[20]|
|id|默认规则|默认规则|
|editedDate|默认规则|默认规则|
|createdDate|默认规则|默认规则|
|freePasswd|默认规则|内容长度必须小于等于[1]|
|createdBy|默认规则|内容长度必须小于等于[30]|
|account|默认规则|内容长度必须小于等于[30]|
|calledTime|默认规则|默认规则|
|key|默认规则|内容长度必须小于等于[32]|
|editedBy|默认规则|内容长度必须小于等于[30]|
|ip|默认规则|内容长度必须小于等于[100]|
|desc|默认规则|内容长度必须小于等于[65535]|
|name|默认规则|内容长度必须小于等于[50]|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Entry_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Entry_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|freePasswd(FREEPASSWD)|EQ|
|name(NAME)|LIKE|

## 导入模式
无


## 导出模式
无
