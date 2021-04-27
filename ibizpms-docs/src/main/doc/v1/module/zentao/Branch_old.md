# 产品的分支和平台信息(ZT_BRANCH)

  记录产品的分支和平台信息

## 关系
{% plantuml %}
产品的分支和平台信息 *-- Bug 
产品的分支和平台信息 *-- 版本 
产品的分支和平台信息 *-- 测试用例 
产品的分支和平台信息 *-- 模块 
产品的分支和平台信息 *-- 产品计划 
产品的分支和平台信息 *-- 项目产品 
产品的分支和平台信息 *-- 发布 
产品的分支和平台信息 *-- 需求阶段 
产品的分支和平台信息 *-- 需求 
产品 *-- 产品的分支和平台信息 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|名称|NAME|TEXT|&nbsp;|
|已删除|DELETED|TEXT|&nbsp;|
|编号|ID|ACID|&nbsp;|
|排序|ORDER|INT|&nbsp;|
|所属产品|PRODUCT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|名称|默认规则|内容长度必须小于等于[255]|
|已删除|默认规则|内容长度必须小于等于[100]|
|编号|默认规则|默认规则|
|排序|默认规则|默认规则|
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
|排序|用户自定义|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|CurProduct|CurProduct([MYSQL5](../../appendix/query_MYSQL5.md#Branch_CurProduct))|否|&nbsp;|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Branch_Default))|是|&nbsp;|
|DefaultBranch|DefaultBranch([MYSQL5](../../appendix/query_MYSQL5.md#Branch_DefaultBranch))|否|&nbsp;所有平台，id为0|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Branch_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|CurProduct|CurProduct|CurProduct|否|&nbsp;自定义实现附加所有平台（0）数据|
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|名称(NAME)|LIKE|
|编号(ID)|EQ|
|所属产品(PRODUCT)|EQ|

## 导入模式
无


## 导出模式
* 数据导出

|编号|名称|排序|
| :------: | :------: | :------: |
| - | - | - |
