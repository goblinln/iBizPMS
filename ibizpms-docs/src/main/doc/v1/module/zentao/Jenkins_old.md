# jenkins(ZT_JENKINS)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|name|NAME|TEXT|&nbsp;|
|password|PASSWORD|TEXT|&nbsp;|
|逻辑删除标志|DELETED|TEXT|&nbsp;|
|account|ACCOUNT|TEXT|&nbsp;|
|url|URL|TEXT|&nbsp;|
|editedDate|EDITEDDATE|DATETIME|&nbsp;|
|token|TOKEN|TEXT|&nbsp;|
|createdBy|CREATEDBY|TEXT|&nbsp;|
|editedBy|EDITEDBY|TEXT|&nbsp;|
|createdDate|CREATEDDATE|DATETIME|&nbsp;|
|id|ID|ACID|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|name|默认规则|内容长度必须小于等于[50]|
|password|默认规则|内容长度必须小于等于[30]|
|逻辑删除标志|默认规则|内容长度必须小于等于[1]|
|account|默认规则|内容长度必须小于等于[30]|
|url|默认规则|内容长度必须小于等于[255]|
|editedDate|默认规则|默认规则|
|token|默认规则|内容长度必须小于等于[255]|
|createdBy|默认规则|内容长度必须小于等于[30]|
|editedBy|默认规则|内容长度必须小于等于[30]|
|createdDate|默认规则|默认规则|
|id|默认规则|默认规则|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Jenkins_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Jenkins_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|name(NAME)|LIKE|
|id(ID)|EQ|

## 导入模式
无


## 导出模式
无
