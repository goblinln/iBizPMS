# ImMessage(ZT_IM_MESSAGE)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|user|USER|TEXT|&nbsp;|
|gid|GID|TEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|date|DATE|DATETIME|&nbsp;|
|逻辑删除标志|DELETED|TEXT|&nbsp;|
|data|DATA|LONGTEXT|&nbsp;|
|type|TYPE|SSCODELIST|&nbsp;|
|content|CONTENT|LONGTEXT|&nbsp;|
|cgid|CGID|TEXT|&nbsp;|
|order|ORDER|BIGINT|&nbsp;|
|contentType|CONTENTTYPE|SSCODELIST|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|user|默认规则|内容长度必须小于等于[30]|
|gid|默认规则|内容长度必须小于等于[40]|
|id|默认规则|默认规则|
|date|默认规则|默认规则|
|逻辑删除标志|默认规则|内容长度必须小于等于[1]|
|data|默认规则|内容长度必须小于等于[65535]|
|type|默认规则|内容长度必须小于等于[9]|
|content|默认规则|内容长度必须小于等于[65535]|
|cgid|默认规则|内容长度必须小于等于[40]|
|order|默认规则|默认规则|
|contentType|默认规则|内容长度必须小于等于[7]|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#ImMessage_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#ImMessage_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|type(TYPE)|EQ|
|contentType(CONTENTTYPE)|EQ|

## 导入模式
无


## 导出模式
无
