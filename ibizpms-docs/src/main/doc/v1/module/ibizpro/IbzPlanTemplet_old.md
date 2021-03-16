# 计划模板(IBZ_PLANTEMPLET)

  

## 关系
{% plantuml %}
计划模板 *-- 计划模板详情 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|计划|PLANS|SMCODELIST|&nbsp;|
|权限|ACL|SSCODELIST|&nbsp;|
|创建人姓名|CREATEMANNAME|TEXT|&nbsp;|
|产品|PRODUCT|BIGINT|&nbsp;|
|产品计划模板标识|IBZ_PLANTEMPLETID|GUID|&nbsp;|
|计划项|PLANTEMPLETDETAIL|ONE2MANYDATA|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|模板名称|IBZ_PLANTEMPLETNAME|TEXT|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|建立时间|默认规则|默认规则|
|计划|默认规则|内容长度必须小于等于[2000]|
|权限|默认规则|内容长度必须小于等于[60]|
|创建人姓名|默认规则|内容长度必须小于等于[100]|
|产品|默认规则|默认规则|
|产品计划模板标识|默认规则|内容长度必须小于等于[100]|
|计划项|默认规则|内容长度必须小于等于[1048576]|
|更新人|默认规则|内容长度必须小于等于[60]|
|模板名称|默认规则|内容长度必须小于等于[200]|
|更新时间|默认规则|默认规则|
|建立人|默认规则|内容长度必须小于等于[60]|

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
|获取计划|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|CurUserTemplet|CurUserTemplet([MYSQL5](../../appendix/query_MYSQL5.md#IbzPlanTemplet_CurUserTemplet))|否|&nbsp;|
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#IbzPlanTemplet_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IbzPlanTemplet_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|CurUserTemplet|CurUserTemplet|CurUserTemplet|否|&nbsp;|
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|权限(ACL)|EQ|
|创建人姓名(CREATEMANNAME)|EQ|
|模板名称(IBZ_PLANTEMPLETNAME)|LIKE|

## 导入模式
无


## 导出模式
* 数据导出

|模板名称|更新时间|
| :------: | :------: |
| - | - |
