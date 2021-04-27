# 公司动态汇总(IBZ_COMPANYSTATS)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|登录次数|LOGINCNT|INT|&nbsp;|
|新增需求数|OPENEDSTORYCNT|INT|&nbsp;|
|日期|ZTDATE|DATE|&nbsp;|
|日志日期|DATE|DATE|&nbsp;|
|关闭需求数|CLOSEDSTORYCNT|INT|&nbsp;|
|新增Bug数|OPENEDBUGCNT|INT|&nbsp;|
|日志工时|LOGHOURS|INT|&nbsp;|
|动态数|DYNAMICCNT|INT|&nbsp;|
|完成任务数|FINISHEDTASKCNT|INT|&nbsp;|
|解决Bug数|RESOLVEDBUGCNT|INT|&nbsp;|
|备注|COMMENT|LONGTEXT|&nbsp;|
|新增任务数|OPENEDTASKCNT|INT|&nbsp;|
|标识|ID|ACID|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|登录次数|默认规则|默认规则|
|新增需求数|默认规则|默认规则|
|日期|默认规则|默认规则|
|日志日期|默认规则|默认规则|
|关闭需求数|默认规则|默认规则|
|新增Bug数|默认规则|默认规则|
|日志工时|默认规则|默认规则|
|动态数|默认规则|默认规则|
|完成任务数|默认规则|默认规则|
|解决Bug数|默认规则|默认规则|
|备注|默认规则|内容长度必须小于等于[65535]|
|新增任务数|默认规则|默认规则|
|标识|默认规则|默认规则|

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
|CompanyDynamicStats|公司动态统计([MYSQL5](../../appendix/query_MYSQL5.md#CompanyStats_CompanyDynamicStats))|否|&nbsp;|
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#CompanyStats_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#CompanyStats_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|CompanyDynamicStats|公司动态统计|CompanyDynamicStats|否|&nbsp;|
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|日志日期(DATE)|GTANDEQ|
|日志日期(DATE)|LTANDEQ|
|备注(COMMENT)|LIKE|
|标识(ID)|EQ|

## 导入模式
无


## 导出模式
无
