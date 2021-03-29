# relation(ZT_RELATION)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|BID|BID|INT|&nbsp;|
|AVersion|AVERSION|TEXT|&nbsp;|
|BType|BTYPE|TEXT|&nbsp;|
|project|PROJECT|INT|&nbsp;|
|extra|EXTRA|TEXT|&nbsp;|
|relation|RELATION|TEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|program|PROGRAM|INT|&nbsp;|
|BVersion|BVERSION|TEXT|&nbsp;|
|AType|ATYPE|TEXT|&nbsp;|
|product|PRODUCT|INT|&nbsp;|
|AID|AID|INT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|BID|默认规则|默认规则|
|AVersion|默认规则|内容长度必须小于等于[30]|
|BType|默认规则|内容长度必须小于等于[30]|
|project|默认规则|默认规则|
|extra|默认规则|内容长度必须小于等于[30]|
|relation|默认规则|内容长度必须小于等于[30]|
|id|默认规则|默认规则|
|program|默认规则|默认规则|
|BVersion|默认规则|内容长度必须小于等于[30]|
|AType|默认规则|内容长度必须小于等于[30]|
|product|默认规则|默认规则|
|AID|默认规则|默认规则|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Relation_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Relation_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|

## 导入模式
无


## 导出模式
无
