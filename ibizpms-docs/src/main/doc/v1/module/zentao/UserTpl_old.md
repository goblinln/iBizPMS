# 用户模板(ZT_USERTPL)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|模板标题|TITLE|TEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|content|CONTENT|LONGTEXT|&nbsp;|
|type|TYPE|SSCODELIST|&nbsp;|
|account|ACCOUNT|TEXT|&nbsp;|
|公开|PUBLIC|SSCODELIST|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|模板标题|默认规则|内容长度必须小于等于[150]|
|id|默认规则|默认规则|
|content|默认规则|内容长度必须小于等于[65535]|
|type|默认规则|内容长度必须小于等于[30]|
|account|默认规则|内容长度必须小于等于[30]|
|公开|默认规则|内容长度必须小于等于[1]|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#UserTpl_Default))|是|&nbsp;|
|MyUserTpl|我的模板([MYSQL5](../../appendix/query_MYSQL5.md#UserTpl_MyUserTpl))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#UserTpl_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|MyUserTpl|我的模板|MyUserTpl|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|模板标题(TITLE)|LIKE|
|id(ID)|EQ|
|type(TYPE)|EQ|
|公开(PUBLIC)|EQ|

## 导入模式
无


## 导出模式
无
