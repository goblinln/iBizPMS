# 通知(ZT_NOTIFY)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|createdDate|CREATEDDATE|DATETIME|&nbsp;|
|ccList|CCLIST|LONGTEXT|&nbsp;|
|failReason|FAILREASON|LONGTEXT|&nbsp;|
|action|ACTION|INT|&nbsp;|
|id|ID|ACID|&nbsp;|
|createdBy|CREATEDBY|TEXT|&nbsp;|
|status|STATUS|TEXT|&nbsp;|
|subject|SUBJECT|TEXT|&nbsp;|
|objectID|OBJECTID|INT|&nbsp;|
|data|DATA|LONGTEXT|&nbsp;|
|toList|TOLIST|TEXT|&nbsp;|
|sendTime|SENDTIME|DATETIME|&nbsp;|
|objectType|OBJECTTYPE|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|createdDate|默认规则|默认规则|
|ccList|默认规则|内容长度必须小于等于[65535]|
|failReason|默认规则|内容长度必须小于等于[65535]|
|action|默认规则|默认规则|
|id|默认规则|默认规则|
|createdBy|默认规则|内容长度必须小于等于[30]|
|status|默认规则|内容长度必须小于等于[10]|
|subject|默认规则|内容长度必须小于等于[255]|
|objectID|默认规则|默认规则|
|data|默认规则|内容长度必须小于等于[65535]|
|toList|默认规则|内容长度必须小于等于[255]|
|sendTime|默认规则|默认规则|
|objectType|默认规则|内容长度必须小于等于[50]|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Notify_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Notify_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|id(ID)|EQ|

## 导入模式
无


## 导出模式
无
