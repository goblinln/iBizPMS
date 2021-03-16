# 测试用例统计(IBZ_CASESTATS)

  

## 关系
{% plantuml %}
模块 *-- 测试用例统计 
产品 *-- 测试用例统计 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|通过用例数|PASSCASE|INT|&nbsp;|
|阻塞用例数|BLOCKEDCASE|INT|&nbsp;|
|总执行数|TOTALRUNCASE|INT|&nbsp;|
|失败用例数|FAILCASE|INT|&nbsp;|
|用例标题|TITLE|TEXT|&nbsp;|
|总用例数|TOTALCASE|INT|&nbsp;|
|用例通过率|PASSRATE|TEXT|&nbsp;|
|用例编号|ID|ACID|&nbsp;|
|模块名称|MODULENAME|PICKUPTEXT|&nbsp;|
|产品|PRODUCT|PICKUP|&nbsp;|
|模块|MODULE|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|通过用例数|默认规则|默认规则|
|阻塞用例数|默认规则|默认规则|
|总执行数|默认规则|默认规则|
|失败用例数|默认规则|默认规则|
|用例标题|默认规则|内容长度必须小于等于[100]|
|总用例数|默认规则|默认规则|
|用例通过率|默认规则|内容长度必须小于等于[100]|
|用例编号|默认规则|默认规则|
|模块名称|默认规则|内容长度必须小于等于[60]|
|产品|默认规则|默认规则|
|模块|默认规则|默认规则|

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
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#CaseStats_Default))|是|&nbsp;|
|TestCaseStats|测试用例统计([MYSQL5](../../appendix/query_MYSQL5.md#CaseStats_TestCaseStats))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#CaseStats_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|
|TestCaseStats|测试用例统计|TestCaseStats|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|用例标题(TITLE)|LIKE|
|模块名称(MODULENAME)|EQ|
|模块名称(MODULENAME)|LIKE|
|产品(PRODUCT)|EQ|
|模块(MODULE)|EQ|

## 导入模式
无


## 导出模式
无
