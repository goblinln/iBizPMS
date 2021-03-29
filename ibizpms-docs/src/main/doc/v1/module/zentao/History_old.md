# 操作历史(ZT_HISTORY)

  

## 关系
{% plantuml %}
系统日志 *-- 操作历史 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|不同|DIFF|LONGTEXT|&nbsp;|
|字段|FIELD|TEXT|&nbsp;|
|新值|NEW|LONGTEXT|&nbsp;|
|旧值|OLD|LONGTEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|关联日志|ACTION|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|不同|默认规则|内容长度必须小于等于[16777215]|
|字段|默认规则|内容长度必须小于等于[30]|
|新值|默认规则|内容长度必须小于等于[65535]|
|旧值|默认规则|内容长度必须小于等于[65535]|
|id|默认规则|默认规则|
|关联日志|默认规则|默认规则|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#History_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#History_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|字段(FIELD)|LEFTLIKE|
|关联日志(ACTION)|EQ|

## 导入模式
无


## 导出模式
无
