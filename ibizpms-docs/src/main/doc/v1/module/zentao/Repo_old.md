# repo(ZT_REPO)

  

## 关系
{% plantuml %}
repo *-- Bug 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|SCM|SCM|TEXT|&nbsp;|
|prefix|PREFIX|TEXT|&nbsp;|
|password|PASSWORD|TEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|encoding|ENCODING|TEXT|&nbsp;|
|commits|COMMITS|INT|&nbsp;|
|synced|SYNCED|INT|&nbsp;|
|lastSync|LASTSYNC|DATETIME|&nbsp;|
|client|CLIENT|TEXT|&nbsp;|
|acl|ACL|LONGTEXT|&nbsp;|
|encrypt|ENCRYPT|TEXT|&nbsp;|
|account|ACCOUNT|TEXT|&nbsp;|
|desc|DESC|LONGTEXT|&nbsp;|
|name|NAME|TEXT|&nbsp;|
|逻辑删除标志|DELETED|TEXT|&nbsp;|
|path|PATH|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|SCM|默认规则|内容长度必须小于等于[10]|
|prefix|默认规则|内容长度必须小于等于[100]|
|password|默认规则|内容长度必须小于等于[30]|
|id|默认规则|默认规则|
|encoding|默认规则|内容长度必须小于等于[20]|
|commits|默认规则|默认规则|
|synced|默认规则|默认规则|
|lastSync|默认规则|默认规则|
|client|默认规则|内容长度必须小于等于[100]|
|acl|默认规则|内容长度必须小于等于[65535]|
|encrypt|默认规则|内容长度必须小于等于[30]|
|account|默认规则|内容长度必须小于等于[30]|
|desc|默认规则|内容长度必须小于等于[65535]|
|name|默认规则|内容长度必须小于等于[255]|
|逻辑删除标志|默认规则|内容长度必须小于等于[100]|
|path|默认规则|内容长度必须小于等于[255]|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Repo_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Repo_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|name(NAME)|LIKE|

## 导入模式
无


## 导出模式
无
