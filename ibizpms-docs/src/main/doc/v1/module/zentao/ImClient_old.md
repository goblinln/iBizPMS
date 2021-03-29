# ImClient(ZT_IM_CLIENT)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|editedDate|EDITEDDATE|DATETIME|&nbsp;|
|strategy|STRATEGY|TEXT|&nbsp;|
|createdDate|CREATEDDATE|DATETIME|&nbsp;|
|changeLog|CHANGELOG|LONGTEXT|&nbsp;|
|version|VERSION|TEXT|&nbsp;|
|downloads|DOWNLOADS|LONGTEXT|&nbsp;|
|status|STATUS|SSCODELIST|&nbsp;|
|desc|DESC|TEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|createdBy|CREATEDBY|TEXT|&nbsp;|
|editedBy|EDITEDBY|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|editedDate|默认规则|默认规则|
|strategy|默认规则|内容长度必须小于等于[10]|
|createdDate|默认规则|默认规则|
|changeLog|默认规则|内容长度必须小于等于[65535]|
|version|默认规则|内容长度必须小于等于[30]|
|downloads|默认规则|内容长度必须小于等于[65535]|
|status|默认规则|内容长度必须小于等于[8]|
|desc|默认规则|内容长度必须小于等于[100]|
|id|默认规则|默认规则|
|createdBy|默认规则|内容长度必须小于等于[30]|
|editedBy|默认规则|内容长度必须小于等于[30]|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#ImClient_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#ImClient_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|status(STATUS)|EQ|

## 导入模式
无


## 导出模式
无
