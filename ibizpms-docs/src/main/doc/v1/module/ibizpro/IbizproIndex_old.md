# 索引检索(IBIZPRO_INDEX)

  

## 关系
{% plantuml %}
索引检索 <|-- Bug 
索引检索 <|-- 测试用例 
索引检索 <|-- 产品 
索引检索 <|-- 项目 
索引检索 <|-- 需求 
索引检索 <|-- 任务 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|主键|INDEXID|BIGINT|&nbsp;|
|权限|ACL|TEXT|&nbsp;|
|docid|DOCID|TEXT|&nbsp;|
|标题[需求、任务等]|INDEXNAME|TEXT|&nbsp;|
|颜色|COLOR|TEXT|&nbsp;|
|逻辑标识|DELETED|YESNO|&nbsp;|
|权限列表|ACLLIST|TEXT|&nbsp;|
|内容[需求、任务等]|INDEXDESC|LONGTEXT|&nbsp;|
|产品|PRODUCT|BIGINT|&nbsp;|
|类型|INDEX_TYPE|SSCODELIST|&nbsp;|
|部门标识|MDEPTID|TEXT|&nbsp;|
|项目|PROJECT|BIGINT|&nbsp;|
|组织标识|ORGID|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|主键|默认规则|默认规则|
|权限|默认规则|内容长度必须小于等于[200]|
|docid|默认规则|内容长度必须小于等于[100]|
|标题[需求、任务等]|默认规则|内容长度必须小于等于[200]|
|颜色|默认规则|内容长度必须小于等于[200]|
|逻辑标识|默认规则|内容长度必须小于等于[200]|
|权限列表|默认规则|内容长度必须小于等于[200]|
|内容[需求、任务等]|默认规则|内容长度必须小于等于[200]|
|产品|默认规则|默认规则|
|类型|默认规则|内容长度必须小于等于[200]|
|部门标识|默认规则|内容长度必须小于等于[200]|
|项目|默认规则|默认规则|
|组织标识|默认规则|内容长度必须小于等于[200]|

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
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#IbizproIndex_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IbizproIndex_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|
|ESquery|全文检索|DEFAULT|否|&nbsp;|
|IndexDER|数据集2||否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|主键(INDEXID)|EQ|
|标题[需求、任务等](INDEXNAME)|LIKE|
|类型(INDEX_TYPE)|EQ|

## 导入模式
无


## 导出模式
无
