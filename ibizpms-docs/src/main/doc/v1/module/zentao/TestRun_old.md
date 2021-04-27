# 测试运行(ZT_TESTRUN)

  

## 关系
{% plantuml %}
测试运行 *-- 测试结果 
测试用例 *-- 测试运行 
测试版本 *-- 测试运行 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|结果|LASTRUNRESULT|SSCODELIST|&nbsp;|
|最后执行时间|LASTRUNDATE|DATETIME|&nbsp;|
|指派给|ASSIGNEDTO|TEXT|&nbsp;|
|最后执行人|LASTRUNNER|TEXT|&nbsp;|
|当前状态|STATUS|SSCODELIST|&nbsp;|
|编号|ID|ACID|&nbsp;|
|用例版本|VERSION|PICKUPDATA|&nbsp;|
|测试用例|CASE|PICKUP|&nbsp;|
|测试单|TASK|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|结果|默认规则|内容长度必须小于等于[30]|
|最后执行时间|默认规则|默认规则|
|指派给|默认规则|内容长度必须小于等于[30]|
|最后执行人|默认规则|内容长度必须小于等于[30]|
|当前状态|默认规则|内容长度必须小于等于[30]|
|编号|默认规则|默认规则|
|用例版本|默认规则|默认规则|
|测试用例|默认规则|默认规则|
|测试单|默认规则|默认规则|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#TestRun_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#TestRun_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|编号(ID)|EQ|
|测试用例(CASE)|EQ|
|测试单(TASK)|EQ|

## 导入模式
无


## 导出模式
无
