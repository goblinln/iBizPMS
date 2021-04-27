# 测试用例(IBZ_CASE)

  

## 关系
{% plantuml %}
测试用例 *-- 用例库用例步骤 
用例库模块 *-- 测试用例 
用例库 *-- 测试用例 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|最后修改者|LASTEDITEDBY|SSCODELIST|&nbsp;|
|path|PATH|INT|&nbsp;|
|用例编号|ID|ACID|&nbsp;|
|scriptedBy|SCRIPTEDBY|TEXT|&nbsp;应该是预留后期使用的字段|
|用例类型|TYPE|SSCODELIST|&nbsp;|
|scriptStatus|SCRIPTSTATUS|TEXT|&nbsp;应该是预留后期使用的字段|
|适用阶段|STAGE|SMCODELIST|&nbsp;|
|创建日期|OPENEDDATE|DATETIME|&nbsp;|
|修改日期|LASTEDITEDDATE|DATETIME|&nbsp;|
|auto|AUTO|TEXT|&nbsp;推测应该是自动测试的flag，代码中值unit、func、no|
|用例标题|TITLE|TEXT|&nbsp;|
|howRun|HOWRUN|TEXT|&nbsp;应该是预留后期使用的字段|
|优先级|PRI|SSCODELIST|&nbsp;|
|备注|COMMENT|HTMLTEXT|&nbsp;|
|关键词|KEYWORDS|TEXT|&nbsp;|
|scriptLocation|SCRIPTLOCATION|TEXT|&nbsp;应该是预留后期使用的字段|
|用例版本|VERSION|INT|&nbsp;|
|状态|STATUS|SSCODELIST|&nbsp;|
|前置条件|PRECONDITION|LONGTEXT|&nbsp;|
|已删除|DELETED|TEXT|&nbsp;|
|排序|ORDER|INT|&nbsp;|
|由谁创建|OPENEDBY|SSCODELIST|&nbsp;|
|scriptedDate|SCRIPTEDDATE|DATE|&nbsp;应该是预留后期使用的字段|
|用例库|LIBNAME|PICKUPTEXT|&nbsp;|
|所属模块|MODULENAME|PICKUPTEXT|&nbsp;|
|id|MODULE|PICKUP|&nbsp;|
|编号|LIB|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|最后修改者|默认规则|内容长度必须小于等于[60]|
|path|默认规则|默认规则|
|用例编号|默认规则|默认规则|
|scriptedBy|默认规则|内容长度必须小于等于[30]|
|用例类型|默认规则|内容长度必须小于等于[60]|
|scriptStatus|默认规则|内容长度必须小于等于[30]|
|适用阶段|默认规则|内容长度必须小于等于[2000]|
|创建日期|默认规则|默认规则|
|修改日期|默认规则|默认规则|
|auto|默认规则|内容长度必须小于等于[10]|
|用例标题|默认规则|内容长度必须小于等于[255]|
|howRun|默认规则|内容长度必须小于等于[30]|
|优先级|默认规则|内容长度必须小于等于[60]|
|备注|默认规则|内容长度必须小于等于[1048576]|
|关键词|默认规则|内容长度必须小于等于[255]|
|scriptLocation|默认规则|内容长度必须小于等于[255]|
|用例版本|默认规则|默认规则|
|状态|默认规则|内容长度必须小于等于[60]|
|前置条件|默认规则|内容长度必须小于等于[65535]|
|已删除|默认规则|内容长度必须小于等于[1]|
|排序|默认规则|默认规则|
|由谁创建|默认规则|内容长度必须小于等于[60]|
|scriptedDate|默认规则|默认规则|
|用例库|默认规则|内容长度必须小于等于[255]|
|所属模块|默认规则|内容长度必须小于等于[60]|
|id|默认规则|默认规则|
|编号|默认规则|默认规则|

## 状态控制

无


## 行为
| 行为    | 类型    |  说明  |
| --------   |------------| ----- | 
|Create|内置方法|&nbsp;|
|CreateTemp|内置方法|&nbsp;|
|CreateTempMajor|内置方法|&nbsp;|
|Update|内置方法|&nbsp;|
|UpdateTemp|内置方法|&nbsp;|
|UpdateTempMajor|内置方法|&nbsp;|
|Remove|内置方法|&nbsp;|
|RemoveTemp|内置方法|&nbsp;|
|RemoveTempMajor|内置方法|&nbsp;|
|Get|内置方法|&nbsp;|
|GetTemp|内置方法|&nbsp;|
|GetTempMajor|内置方法|&nbsp;|
|GetDraft|内置方法|&nbsp;|
|GetDraftTemp|内置方法|&nbsp;|
|GetDraftTempMajor|内置方法|&nbsp;|
|CheckKey|内置方法|&nbsp;|
|Save|内置方法|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#IbzCase_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IbzCase_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|最后修改者(LASTEDITEDBY)|EQ|
|用例编号(ID)|EQ|
|用例类型(TYPE)|EQ|
|用例标题(TITLE)|LIKE|
|优先级(PRI)|EQ|
|状态(STATUS)|EQ|
|由谁创建(OPENEDBY)|EQ|
|用例库(LIBNAME)|EQ|
|用例库(LIBNAME)|LIKE|
|所属模块(MODULENAME)|EQ|
|所属模块(MODULENAME)|LIKE|
|id(MODULE)|EQ|
|编号(LIB)|EQ|

## 导入模式
无


## 导出模式
无
