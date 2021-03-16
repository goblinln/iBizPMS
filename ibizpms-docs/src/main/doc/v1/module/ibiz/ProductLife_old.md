# 产品生命周期(IBZ_PRODUCTLIFE)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|平台/分支|BRANCH|TEXT|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|产品生命周期名称|IBZ_PRODUCTLIFENAME|TEXT|&nbsp;|
|产品|PRODUCT|INT|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|父对象|PARENT|TEXT|&nbsp;|
|年|YEAR|TEXT|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|属性|TYPE|TEXT|&nbsp;|
|里程碑|MARKER|YESNO|&nbsp;|
|开始日期|BEGIN|DATE|&nbsp;|
|产品生命周期标识|IBZ_PRODUCTLIFEID|GUID|&nbsp;|
|结束日期|END|DATE|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|平台/分支|默认规则|内容长度必须小于等于[8]|
|建立人|默认规则|内容长度必须小于等于[60]|
|更新时间|默认规则|默认规则|
|产品生命周期名称|默认规则|内容长度必须小于等于[200]|
|产品|默认规则|默认规则|
|建立时间|默认规则|默认规则|
|父对象|默认规则|内容长度必须小于等于[8]|
|年|默认规则|内容长度必须小于等于[100]|
|更新人|默认规则|内容长度必须小于等于[60]|
|属性|默认规则|内容长度必须小于等于[100]|
|里程碑|默认规则|默认规则|
|开始日期|默认规则|默认规则|
|产品生命周期标识|默认规则|内容长度必须小于等于[100]|
|结束日期|默认规则|默认规则|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#ProductLife_Default))|是|&nbsp;|
|GetRoadmap|获取产品路线([MYSQL5](../../appendix/query_MYSQL5.md#ProductLife_GetRoadmap))|否|&nbsp;|
|GetRoadmapS|获取产品路线([MYSQL5](../../appendix/query_MYSQL5.md#ProductLife_GetRoadmapS))|否|&nbsp;|
|RoadMapYear|获取路线图年份([MYSQL5](../../appendix/query_MYSQL5.md#ProductLife_RoadMapYear))|否|&nbsp;注意，前期因为禅道会将未设置的时间置为2030-01-01，所以针对大于等于2030年的均不作展示处理|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#ProductLife_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|GetRoadmap|GetRoadmap|GetRoadmap|否|&nbsp;|
|GetRoadmapS|获取产品路线|GetRoadmapS|否|&nbsp;|
|RoadMapYear|RoadMapYear|RoadMapYear|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|产品生命周期名称(IBZ_PRODUCTLIFENAME)|LIKE|
|年(YEAR)|EQ|

## 导入模式
无


## 导出模式
无
