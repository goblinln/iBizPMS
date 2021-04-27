# 项目周报(IBZPRO_PROJECTWEEKLY)

  

## 关系
{% plantuml %}
项目 *-- 项目周报 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|年|YEAR|TEXT|&nbsp;|
|结束统计|ENDDATESTATS|TEXT|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|任务|TASKS|TEXT|&nbsp;|
|项目周报名称|IBZPRO_PROJECTWEEKLYNAME|TEXT|&nbsp;|
|周|WEEK|TEXT|&nbsp;|
|项目周报标识|IBZPRO_PROJECTWEEKLYID|GUID|&nbsp;|
|项目负责人|PM|TEXT|&nbsp;|
|日期|DATE|DATE|&nbsp;|
|总工时|TOTALESTIMATES|FLOAT|&nbsp;|
|开始统计|BEGINDATESTATS|TEXT|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|月|MONTH|TEXT|&nbsp;|
|项目名称|PROJECTNAME|PICKUPTEXT|&nbsp;|
|项目编号|PROJECT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|建立时间|默认规则|默认规则|
|更新人|默认规则|内容长度必须小于等于[60]|
|年|默认规则|内容长度必须小于等于[100]|
|结束统计|默认规则|内容长度必须小于等于[100]|
|建立人|默认规则|内容长度必须小于等于[60]|
|任务|默认规则|内容长度必须小于等于[2000]|
|项目周报名称|默认规则|内容长度必须小于等于[200]|
|周|默认规则|内容长度必须小于等于[100]|
|项目周报标识|默认规则|内容长度必须小于等于[100]|
|项目负责人|默认规则|内容长度必须小于等于[100]|
|日期|默认规则|默认规则|
|总工时|默认规则|默认规则|
|开始统计|默认规则|内容长度必须小于等于[100]|
|更新时间|默认规则|默认规则|
|月|默认规则|内容长度必须小于等于[100]|
|项目名称|默认规则|内容长度必须小于等于[90]|
|项目编号|默认规则|默认规则|

## 状态控制

无

项目周报标识


## 行为
| 行为    | 类型    |  说明  |
| --------   |------------| ----- | 
|Create|内置方法|&nbsp;|
|Update|内置方法|&nbsp;|
|Remove|内置方法|&nbsp;|
|Get|内置方法|&nbsp;|
|GetDraft|内置方法|&nbsp;|
|CheckKey|内置方法|&nbsp;|
|定时推送项目周报|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#IbizproProjectWeekly_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IbizproProjectWeekly_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|项目周报名称(IBZPRO_PROJECTWEEKLYNAME)|LIKE|
|项目周报标识(IBZPRO_PROJECTWEEKLYID)|EQ|
|项目负责人(PM)|EQ|
|项目编号(PROJECT)|EQ|

## 导入模式
无


## 导出模式
无
