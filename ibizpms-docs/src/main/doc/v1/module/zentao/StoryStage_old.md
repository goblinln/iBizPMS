# 需求阶段(ZT_STORYSTAGE)

  还不知道该表的插入时机

## 关系
{% plantuml %}
产品的分支和平台信息 *-- 需求阶段 
需求 *-- 需求阶段 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|虚拟主键|ID|TEXT|&nbsp;|
|设置阶段者|STAGEDBY|PICKUPDATA|&nbsp;|
|所处阶段|STAGE|PICKUPDATA|&nbsp;|
|需求|STORY|PICKUP|&nbsp;|
|平台/分支|BRANCH|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|虚拟主键|默认规则|内容长度必须小于等于[200]|
|设置阶段者|默认规则|内容长度必须小于等于[30]|
|所处阶段|默认规则|内容长度必须小于等于[50]|
|需求|默认规则|默认规则|
|平台/分支|默认规则|默认规则|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#StoryStage_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#StoryStage_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|虚拟主键(ID)|EQ|
|需求(STORY)|EQ|
|平台/分支(BRANCH)|EQ|

## 导入模式
无


## 导出模式
无
