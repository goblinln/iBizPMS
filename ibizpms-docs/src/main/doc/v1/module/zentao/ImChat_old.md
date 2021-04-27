# ImChat(ZT_IM_CHAT)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|admins|ADMINS|TEXT|&nbsp;|
|createdBy|CREATEDBY|TEXT|&nbsp;|
|editedDate|EDITEDDATE|DATETIME|&nbsp;|
|dismissDate|DISMISSDATE|DATETIME|&nbsp;|
|createdDate|CREATEDDATE|DATETIME|&nbsp;|
|committers|COMMITTERS|TEXT|&nbsp;|
|public|PUBLIC|SSCODELIST|&nbsp;|
|id|ID|ACID|&nbsp;|
|name|NAME|TEXT|&nbsp;|
|type|TYPE|TEXT|&nbsp;|
|editedBy|EDITEDBY|TEXT|&nbsp;|
|subject|SUBJECT|INT|&nbsp;|
|lastActiveTime|LASTACTIVETIME|DATETIME|&nbsp;|
|gid|GID|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|admins|默认规则|内容长度必须小于等于[255]|
|createdBy|默认规则|内容长度必须小于等于[30]|
|editedDate|默认规则|默认规则|
|dismissDate|默认规则|默认规则|
|createdDate|默认规则|默认规则|
|committers|默认规则|内容长度必须小于等于[255]|
|public|默认规则|内容长度必须小于等于[1]|
|id|默认规则|默认规则|
|name|默认规则|内容长度必须小于等于[60]|
|type|默认规则|内容长度必须小于等于[20]|
|editedBy|默认规则|内容长度必须小于等于[30]|
|subject|默认规则|默认规则|
|lastActiveTime|默认规则|默认规则|
|gid|默认规则|内容长度必须小于等于[40]|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#ImChat_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#ImChat_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|public(PUBLIC)|EQ|
|id(ID)|EQ|
|name(NAME)|LIKE|

## 导入模式
无


## 导出模式
无
