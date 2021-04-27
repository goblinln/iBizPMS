# 群组(ZT_GROUP)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|acl|ACL|LONGTEXT|&nbsp;|
|分组描述|DESC|TEXT|&nbsp;|
|ID|ID|ACID|&nbsp;|
|分组名称|NAME|TEXT|&nbsp;|
|role|ROLE|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|acl|默认规则|内容长度必须小于等于[65535]|
|分组描述|默认规则|内容长度必须小于等于[255]|
|ID|默认规则|默认规则|
|分组名称|默认规则|内容长度必须小于等于[30]|
|role|默认规则|内容长度必须小于等于[30]|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Group_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Group_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|ID(ID)|EQ|
|分组名称(NAME)|LIKE|

## 导入模式
无


## 导出模式
无
