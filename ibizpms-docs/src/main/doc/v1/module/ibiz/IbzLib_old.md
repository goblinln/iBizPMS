# 用例库(IBZ_LIB)

  

## 关系
{% plantuml %}
用例库 *-- 测试用例 
用例库 *-- 用例库模块 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|描述|DESC|LONGTEXT|&nbsp;|
|产品|PRODUCT|BIGINT|&nbsp;|
|创建时间|ADDEDDATE|DATETIME|&nbsp;|
|编号|ID|ACID|&nbsp;|
|最后编辑时间|LASTEDITEDDATE|DATETIME|&nbsp;|
|由谁创建|ADDEDBY|SSCODELIST|&nbsp;|
|已删除|DELETED|TEXT|&nbsp;|
|名称|NAME|TEXT|&nbsp;|
|类型|TYPE|SSCODELIST|&nbsp;|
|最后编辑人|LASTEDITEDBY|SSCODELIST|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|描述|默认规则|内容长度必须小于等于[65535]|
|产品|默认规则|默认规则|
|创建时间|默认规则|默认规则|
|编号|默认规则|默认规则|
|最后编辑时间|默认规则|默认规则|
|由谁创建|默认规则|内容长度必须小于等于[60]|
|已删除|默认规则|内容长度必须小于等于[1]|
|名称|默认规则|内容长度必须小于等于[255]|
|类型|默认规则|内容长度必须小于等于[60]|
|最后编辑人|默认规则|内容长度必须小于等于[60]|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#IbzLib_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IbzLib_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|由谁创建(ADDEDBY)|EQ|
|名称(NAME)|LIKE|
|类型(TYPE)|EQ|
|最后编辑人(LASTEDITEDBY)|EQ|

## 导入模式
无


## 导出模式
无
