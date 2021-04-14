# 项目中需要做的需求(ZT_PROJECTSTORY)

  

## 关系
{% plantuml %}
产品 *-- 项目中需要做的需求 
项目 *-- 项目中需要做的需求 
需求 *-- 项目中需要做的需求 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|虚拟主键|ID|TEXT|&nbsp;|
|排序|ORDER|INT|&nbsp;“自增”排序，从0开始，先到先得|
|需求版本|VERSION|PICKUPDATA|&nbsp;需求版本，物理字段|
|需求|STORY|PICKUP|&nbsp;|
|项目|PROJECT|PICKUP|&nbsp;|
|所属产品|PRODUCT|PICKUP|&nbsp;|
|由谁创建|CREATEBY|TEXT|&nbsp;|
|由谁更新|UPDATEBY|TEXT|&nbsp;|
|归属组织|ORG|TEXT|&nbsp;|
|归属部门|DEPT|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|虚拟主键|默认规则|内容长度必须小于等于[200]|
|排序|默认规则|默认规则|
|需求版本|默认规则|默认规则|
|需求|默认规则|默认规则|
|项目|默认规则|默认规则|
|所属产品|默认规则|默认规则|
|由谁创建|默认规则|内容长度必须小于等于[100]|
|由谁更新|默认规则|内容长度必须小于等于[100]|
|归属组织|默认规则|内容长度必须小于等于[100]|
|归属部门|默认规则|内容长度必须小于等于[100]|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#ProjectStory_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#ProjectStory_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|需求(STORY)|EQ|
|项目(PROJECT)|EQ|
|所属产品(PRODUCT)|EQ|

## 导入模式
无


## 导出模式
无
