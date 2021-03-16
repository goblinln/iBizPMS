# extension(ZT_EXTENSION)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|dirs|DIRS|LONGTEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|status|STATUS|TEXT|&nbsp;|
|code|CODE|TEXT|&nbsp;|
|depends|DEPENDS|TEXT|&nbsp;|
|type|TYPE|TEXT|&nbsp;|
|files|FILES|LONGTEXT|&nbsp;|
|zentaoCompatible|ZENTAOCOMPATIBLE|TEXT|&nbsp;|
|license|LICENSE|LONGTEXT|&nbsp;|
|name|NAME|TEXT|&nbsp;|
|installedTime|INSTALLEDTIME|DATETIME|&nbsp;|
|site|SITE|TEXT|&nbsp;|
|author|AUTHOR|TEXT|&nbsp;|
|desc|DESC|LONGTEXT|&nbsp;|
|version|VERSION|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|dirs|默认规则|内容长度必须小于等于[16777215]|
|id|默认规则|默认规则|
|status|默认规则|内容长度必须小于等于[20]|
|code|默认规则|内容长度必须小于等于[30]|
|depends|默认规则|内容长度必须小于等于[100]|
|type|默认规则|内容长度必须小于等于[20]|
|files|默认规则|内容长度必须小于等于[16777215]|
|zentaoCompatible|默认规则|内容长度必须小于等于[100]|
|license|默认规则|内容长度必须小于等于[65535]|
|name|默认规则|内容长度必须小于等于[150]|
|installedTime|默认规则|默认规则|
|site|默认规则|内容长度必须小于等于[150]|
|author|默认规则|内容长度必须小于等于[100]|
|desc|默认规则|内容长度必须小于等于[65535]|
|version|默认规则|内容长度必须小于等于[50]|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Extension_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Extension_View))|否|&nbsp;|

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
