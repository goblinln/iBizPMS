# 套件用例(ZT_SUITECASE)

  

## 关系
{% plantuml %}
测试用例 *-- 套件用例 
产品 *-- 套件用例 
测试套件 *-- 套件用例 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|虚拟主键|ID|TEXT|&nbsp;|
|用例版本|VERSION|PICKUPDATA|&nbsp;|
|测试套件|SUITE|PICKUP|&nbsp;|
|用例|CASE|PICKUP|&nbsp;|
|所属产品|PRODUCT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|虚拟主键|默认规则|内容长度必须小于等于[200]|
|用例版本|默认规则|默认规则|
|测试套件|默认规则|默认规则|
|用例|默认规则|默认规则|
|所属产品|默认规则|默认规则|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#SuiteCase_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#SuiteCase_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|虚拟主键(ID)|EQ|
|测试套件(SUITE)|EQ|
|用例(CASE)|EQ|
|所属产品(PRODUCT)|EQ|

## 导入模式
无


## 导出模式
无
