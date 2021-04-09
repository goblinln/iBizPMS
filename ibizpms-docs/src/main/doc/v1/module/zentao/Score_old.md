# score(ZT_SCORE)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|method|METHOD|TEXT|&nbsp;|
|score|SCORE|INT|&nbsp;|
|time|TIME|DATETIME|&nbsp;|
|account|ACCOUNT|TEXT|&nbsp;|
|before|BEFORE|INT|&nbsp;|
|desc|DESC|TEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|after|AFTER|INT|&nbsp;|
|module|MODULE|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|method|默认规则|内容长度必须小于等于[30]|
|score|默认规则|默认规则|
|time|默认规则|默认规则|
|account|默认规则|内容长度必须小于等于[30]|
|before|默认规则|默认规则|
|desc|默认规则|内容长度必须小于等于[250]|
|id|默认规则|默认规则|
|after|默认规则|默认规则|
|module|默认规则|内容长度必须小于等于[30]|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Score_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Score_View))|否|&nbsp;|

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
