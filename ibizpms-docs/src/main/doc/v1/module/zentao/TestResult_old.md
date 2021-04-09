# 测试结果(ZT_TESTRESULT)

  

## 关系
{% plantuml %}
测试用例 *-- 测试结果 
compile *-- 测试结果 
job *-- 测试结果 
测试运行 *-- 测试结果 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|最后执行人|LASTRUNNER|TEXT|&nbsp;|
|步骤结果|STEPRESULTS|LONGTEXT|&nbsp;|
|测试结果|CASERESULT|SSCODELIST|&nbsp;|
|结果文件|XML|LONGTEXT|&nbsp;|
|属性|TASK|TEXT|&nbsp;|
|持续时间|DURATION|FLOAT|&nbsp;|
|测试时间|DATE|DATETIME|&nbsp;|
|编号|ID|ACID|&nbsp;|
|用例版本|VERSION|PICKUPDATA|&nbsp;|
|相关需求|STORY|PICKUPDATA|&nbsp;|
|用例名称|TITLE|PICKUPDATA|&nbsp;|
|所属模块|MODULENAME|PICKUPDATA|&nbsp;|
|所属模块|MODULE|PICKUPDATA|&nbsp;|
|前置条件|PRECONDITION|PICKUPDATA|&nbsp;|
|所属产品|PRODUCT|PICKUPTEXT|&nbsp;|
|构建任务|JOB|PICKUP|&nbsp;|
|用例|CASE|PICKUP|&nbsp;|
|测试执行|RUN|PICKUP|&nbsp;|
|代码编译|COMPILE|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|最后执行人|默认规则|内容长度必须小于等于[30]|
|步骤结果|默认规则|内容长度必须小于等于[65535]|
|测试结果|默认规则|内容长度必须小于等于[30]|
|结果文件|默认规则|内容长度必须小于等于[65535]|
|属性|默认规则|内容长度必须小于等于[100]|
|持续时间|默认规则|默认规则|
|测试时间|默认规则|默认规则|
|编号|默认规则|默认规则|
|用例版本|默认规则|默认规则|
|相关需求|默认规则|默认规则|
|用例名称|默认规则|内容长度必须小于等于[255]|
|所属模块|默认规则|内容长度必须小于等于[60]|
|所属模块|默认规则|默认规则|
|前置条件|默认规则|内容长度必须小于等于[65535]|
|所属产品|默认规则|默认规则|
|构建任务|默认规则|默认规则|
|用例|默认规则|默认规则|
|测试执行|默认规则|默认规则|
|代码编译|默认规则|默认规则|

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
|CurTestRun|CurTestRun([MYSQL5](../../appendix/query_MYSQL5.md#TestResult_CurTestRun))|否|&nbsp;|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#TestResult_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#TestResult_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|CurTestRun|CurTestRun|CurTestRun|否|&nbsp;|
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|所属产品(PRODUCT)|EQ|
|所属产品(PRODUCT)|LIKE|
|构建任务(JOB)|EQ|
|用例(CASE)|EQ|
|测试执行(RUN)|EQ|
|代码编译(COMPILE)|EQ|

## 导入模式
无


## 导出模式
无
