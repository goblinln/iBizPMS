# compile(ZT_COMPILE)

  

## 关系
{% plantuml %}
compile *-- 测试结果 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|queue|QUEUE|INT|&nbsp;|
|createdDate|CREATEDDATE|DATETIME|&nbsp;|
|atTime|ATTIME|TEXT|&nbsp;|
|updateDate|UPDATEDATE|DATETIME|&nbsp;|
|id|ID|ACID|&nbsp;|
|tag|TAG|TEXT|&nbsp;|
|testtask|TESTTASK|INT|&nbsp;|
|job|JOB|INT|&nbsp;|
|name|NAME|TEXT|&nbsp;|
|logs|LOGS|LONGTEXT|&nbsp;|
|status|STATUS|TEXT|&nbsp;|
|逻辑删除标志|DELETED|TEXT|&nbsp;|
|createdBy|CREATEDBY|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|queue|默认规则|默认规则|
|createdDate|默认规则|默认规则|
|atTime|默认规则|内容长度必须小于等于[10]|
|updateDate|默认规则|默认规则|
|id|默认规则|默认规则|
|tag|默认规则|内容长度必须小于等于[255]|
|testtask|默认规则|默认规则|
|job|默认规则|默认规则|
|name|默认规则|内容长度必须小于等于[50]|
|logs|默认规则|内容长度必须小于等于[65535]|
|status|默认规则|内容长度必须小于等于[255]|
|逻辑删除标志|默认规则|内容长度必须小于等于[1]|
|createdBy|默认规则|内容长度必须小于等于[30]|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Compile_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Compile_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|id(ID)|EQ|
|name(NAME)|LIKE|

## 导入模式
无


## 导出模式
无
