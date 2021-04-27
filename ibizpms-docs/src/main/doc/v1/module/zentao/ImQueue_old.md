# ImQueue(ZT_IM_QUEUE)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|processDate|PROCESSDATE|DATETIME|&nbsp;|
|addDate|ADDDATE|DATETIME|&nbsp;|
|content|CONTENT|LONGTEXT|&nbsp;|
|type|TYPE|TEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|status|STATUS|TEXT|&nbsp;|
|result|RESULT|LONGTEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|processDate|默认规则|默认规则|
|addDate|默认规则|默认规则|
|content|默认规则|内容长度必须小于等于[65535]|
|type|默认规则|内容长度必须小于等于[30]|
|id|默认规则|默认规则|
|status|默认规则|内容长度必须小于等于[30]|
|result|默认规则|内容长度必须小于等于[65535]|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#ImQueue_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#ImQueue_View))|否|&nbsp;|

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
