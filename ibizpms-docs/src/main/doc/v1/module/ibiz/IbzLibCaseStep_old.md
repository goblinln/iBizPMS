# 用例库用例步骤(IBZ_CASESTEP)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|预期|EXPECT|LONGTEXT|&nbsp;|
|类型|TYPE|SSCODELIST|&nbsp;|
|附件|FILES|TEXT|&nbsp;|
|编号|ID|ACID|&nbsp;|
|步骤|DESC|LONGTEXT|&nbsp;|
|实际情况|REALS|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|预期|默认规则|内容长度必须小于等于[65535]|
|类型|默认规则|内容长度必须小于等于[60]|
|附件|默认规则|内容长度必须小于等于[100]|
|编号|默认规则|默认规则|
|步骤|默认规则|内容长度必须小于等于[65535]|
|实际情况|默认规则|内容长度必须小于等于[100]|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#IbzLibCaseStep_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IbzLibCaseStep_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|预期(EXPECT)|LIKE|
|类型(TYPE)|EQ|
|编号(ID)|EQ|

## 导入模式
无


## 导出模式
无
