# 项目产品(ZT_PROJECTPRODUCT)

  

## 关系
{% plantuml %}
产品的分支和平台信息 *-- 项目产品 
产品计划 *-- 项目产品 
产品 *-- 项目产品 
项目 *-- 项目产品 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|虚拟主键|ID|TEXT|&nbsp;|
|产品|PRODUCTNAME|PICKUPTEXT|&nbsp;|
|项目|PROJECTNAME|PICKUPTEXT|&nbsp;|
|计划名称|PLANNAME|PICKUPTEXT|&nbsp;|
|产品|PRODUCT|PICKUP|&nbsp;|
|产品计划|PLAN|PICKUP|&nbsp;|
|平台/分支|BRANCH|PICKUP|&nbsp;|
|项目|PROJECT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|虚拟主键|默认规则|内容长度必须小于等于[200]|
|产品|默认规则|内容长度必须小于等于[90]|
|项目|默认规则|内容长度必须小于等于[90]|
|计划名称|默认规则|内容长度必须小于等于[90]|
|产品|默认规则|默认规则|
|产品计划|默认规则|默认规则|
|平台/分支|默认规则|默认规则|
|项目|默认规则|默认规则|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#ProjectProduct_Default))|是|&nbsp;|
|RelationPlan|关联计划([MYSQL5](../../appendix/query_MYSQL5.md#ProjectProduct_RelationPlan))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#ProjectProduct_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|RelationPlan|关联计划|RelationPlan|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|虚拟主键(ID)|EQ|
|产品(PRODUCTNAME)|EQ|
|产品(PRODUCTNAME)|LIKE|
|项目(PROJECTNAME)|EQ|
|项目(PROJECTNAME)|LIKE|
|计划名称(PLANNAME)|EQ|
|计划名称(PLANNAME)|LIKE|
|产品(PRODUCT)|EQ|
|产品计划(PLAN)|EQ|
|平台/分支(BRANCH)|EQ|
|项目(PROJECT)|EQ|

## 导入模式
无


## 导出模式
无
