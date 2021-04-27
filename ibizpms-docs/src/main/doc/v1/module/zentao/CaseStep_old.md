# 用例步骤(ZT_CASESTEP)

  

## 关系
{% plantuml %}
用例步骤 *-- 用例步骤 
测试用例 *-- 用例步骤 
用例步骤 *-- 用例步骤 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|用例步骤编号|CASESTEPID|BIGINT|&nbsp;|
|实际情况|REALS|TEXT|&nbsp;|
|测试结果|STEPS|SSCODELIST|&nbsp;|
|用例步骤类型|TYPE|SSCODELIST|&nbsp;|
|编号|ID|ACID|&nbsp;|
|步骤|DESC|LONGTEXT|&nbsp;|
|预期|EXPECT|LONGTEXT|&nbsp;|
|附件|FILES|TEXT|&nbsp;|
|执行编号|RUNID|INT|&nbsp;|
|用例版本|VERSION|PICKUPDATA|&nbsp;|
|用例|CASE|PICKUP|&nbsp;|
|分组用例步骤的组编号|PARENT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|用例步骤编号|默认规则|默认规则|
|实际情况|默认规则|内容长度必须小于等于[100]|
|测试结果|默认规则|内容长度必须小于等于[200]|
|用例步骤类型|默认规则|内容长度必须小于等于[10]|
|编号|默认规则|默认规则|
|步骤|默认规则|内容长度必须小于等于[65535]|
|预期|默认规则|内容长度必须小于等于[65535]|
|附件|默认规则|内容长度必须小于等于[100]|
|执行编号|默认规则|默认规则|
|用例版本|默认规则|默认规则|
|用例|默认规则|默认规则|
|分组用例步骤的组编号|默认规则|默认规则|

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
|CurTest|当前测试步骤([MYSQL5](../../appendix/query_MYSQL5.md#CaseStep_CurTest))|否|&nbsp;|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#CaseStep_Default))|是|&nbsp;|
|DEFAULT1|DEFAULT1([MYSQL5](../../appendix/query_MYSQL5.md#CaseStep_Default1))|否|&nbsp;|
|Mob|Mob([MYSQL5](../../appendix/query_MYSQL5.md#CaseStep_Mob))|否|&nbsp;|
|Version|版本([MYSQL5](../../appendix/query_MYSQL5.md#CaseStep_Version))|否|&nbsp;|
|Versions|版本1([MYSQL5](../../appendix/query_MYSQL5.md#CaseStep_Versions))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#CaseStep_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|CurTest|当前测试步骤|CurTest|否|&nbsp;|
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|DEFAULT1|DEFAULT1|DEFAULT1|否|&nbsp;|
|Mob|Mob|Mob|否|&nbsp;|
|Version|版本|Version|否|&nbsp;|
|Versions|版本1|Versions|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|测试结果(STEPS)|EQ|
|用例步骤类型(TYPE)|EQ|
|编号(ID)|EQ|
|预期(EXPECT)|LIKE|
|用例版本(VERSION)|EQ|
|用例(CASE)|EQ|
|分组用例步骤的组编号(PARENT)|EQ|

## 导入模式
无


## 导出模式
无
