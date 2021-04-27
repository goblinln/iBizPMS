# lang(ZT_LANG)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|module|MODULE|TEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|key|KEY|TEXT|&nbsp;|
|section|SECTION|TEXT|&nbsp;|
|lang|LANG|TEXT|&nbsp;|
|system|SYSTEM|SSCODELIST|&nbsp;|
|value|VALUE|LONGTEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|module|默认规则|内容长度必须小于等于[30]|
|id|默认规则|默认规则|
|key|默认规则|内容长度必须小于等于[60]|
|section|默认规则|内容长度必须小于等于[30]|
|lang|默认规则|内容长度必须小于等于[30]|
|system|默认规则|内容长度必须小于等于[1]|
|value|默认规则|内容长度必须小于等于[65535]|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Lang_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Lang_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|id(ID)|EQ|
|system(SYSTEM)|EQ|

## 导入模式
无


## 导出模式
无
