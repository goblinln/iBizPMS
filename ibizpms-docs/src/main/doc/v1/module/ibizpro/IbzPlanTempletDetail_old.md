# 计划模板详情(IBZ_PLANTEMPLETDETAIL)

  

## 关系
{% plantuml %}
计划模板 *-- 计划模板详情 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|类型|TYPE|SSCODELIST|&nbsp;|
|计划编号|PLANCODE|TEXT|&nbsp;|
|计划名称|DESC|HTMLTEXT|&nbsp;|
|排序|ORDER|INT|&nbsp;|
|计划模板详情标识|IBZ_PLANTEMPLETDETAILID|GUID|&nbsp;|
|计划模板详情名称|IBZ_PLANTEMPLETDETAILNAME|TEXT|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|描述|EXPECT|HTMLTEXT|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|产品计划模板标识|PLANTEMPLETID|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|类型|默认规则|内容长度必须小于等于[60]|
|计划编号|默认规则|内容长度必须小于等于[100]|
|计划名称|默认规则|内容长度必须小于等于[1048576]|
|排序|默认规则|默认规则|
|计划模板详情标识|默认规则|内容长度必须小于等于[100]|
|计划模板详情名称|默认规则|内容长度必须小于等于[200]|
|建立人|默认规则|内容长度必须小于等于[60]|
|建立时间|默认规则|默认规则|
|更新人|默认规则|内容长度必须小于等于[60]|
|描述|默认规则|内容长度必须小于等于[1048576]|
|更新时间|默认规则|默认规则|
|产品计划模板标识|默认规则|内容长度必须小于等于[100]|

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
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#IbzPlanTempletDetail_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IbzPlanTempletDetail_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|类型(TYPE)|EQ|
|计划模板详情名称(IBZ_PLANTEMPLETDETAILNAME)|LIKE|
|产品计划模板标识(PLANTEMPLETID)|EQ|

## 导入模式
无


## 导出模式
无
