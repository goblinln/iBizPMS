# 任务模块(IBZ_PROJECTMODULE)

  项目任务模块

## 关系
{% plantuml %}
任务模块 *-- 任务模块 
任务模块 *-- 任务 
任务模块 *-- 任务模块 
项目 *-- 任务模块 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|简称|SHORT|TEXT|&nbsp;|
|叶子模块|ISLEAF|TEXT|&nbsp;|
|类型（task）|TYPE|TEXT|&nbsp;|
|数据选择排序|ORDERPK|TEXT|&nbsp;|
|模块名称|NAME|TEXT|&nbsp;|
|排序值|ORDER|INT|&nbsp;|
|collector|COLLECTOR|LONGTEXT|&nbsp;|
|grade|GRADE|INT|&nbsp;|
|branch|BRANCH|INT|&nbsp;|
|path|PATH|TEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|owner|OWNER|TEXT|&nbsp;|
|逻辑删除标志|DELETED|TEXT|&nbsp;|
|所属项目|ROOTNAME|PICKUPTEXT|&nbsp;|
|上级模块|PARENTNAME|PICKUPTEXT|&nbsp;|
|项目|ROOT|PICKUP|&nbsp;|
|id|PARENT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|简称|默认规则|内容长度必须小于等于[30]|
|叶子模块|默认规则|内容长度必须小于等于[200]|
|类型（task）|默认规则|内容长度必须小于等于[30]|
|数据选择排序|默认规则|内容长度必须小于等于[100]|
|模块名称|默认规则|内容长度必须小于等于[60]|
|排序值|默认规则|默认规则|
|collector|默认规则|内容长度必须小于等于[65535]|
|grade|默认规则|默认规则|
|branch|默认规则|默认规则|
|path|默认规则|内容长度必须小于等于[255]|
|id|默认规则|默认规则|
|owner|默认规则|内容长度必须小于等于[30]|
|逻辑删除标志|默认规则|内容长度必须小于等于[1]|
|所属项目|默认规则|内容长度必须小于等于[90]|
|上级模块|默认规则|内容长度必须小于等于[60]|
|项目|默认规则|默认规则|
|id|默认规则|默认规则|

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
|重建模块路径|实体处理逻辑|&nbsp;前台行为|
|删除模块|实体处理逻辑|&nbsp;|
|Save|内置方法|&nbsp;|

## 处理逻辑
* 重建模块路径 (FixPath)
  
   

{% plantuml %}
hide footbox

任务模块 -> 任务模块: 准备参数
任务模块 -> 模块: 执行重建模块路径行为
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|准备参数 |
|2|执行重建模块路径行为 |
<center>重建模块路径</center>
* 删除模块 (RemoveModule)
  
   

{% plantuml %}
hide footbox

任务模块 -> 任务模块: 设置模块参数
任务模块 -> 模块: 模块删除
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|设置模块参数 |
|2|模块删除 |
<center>删除模块</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|BYPATH|BYPATH([MYSQL5](../../appendix/query_MYSQL5.md#ProjectModule_ByPath))|否|&nbsp;通过模块路径包含查询|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#ProjectModule_Default))|是|&nbsp;|
|ParentModule|父模块([MYSQL5](../../appendix/query_MYSQL5.md#ProjectModule_ParentModule))|否|&nbsp;|
|ROOT|根需求模块([MYSQL5](../../appendix/query_MYSQL5.md#ProjectModule_Root))|否|&nbsp;含任务的产品需求及项目需求的模块|
|Root_NoBranch|根需求模块_无分支([MYSQL5](../../appendix/query_MYSQL5.md#ProjectModule_Root_NoBranch))|否|&nbsp;含任务的产品需求及项目需求的模块|
|ROOT_TASK|根任务模块([MYSQL5](../../appendix/query_MYSQL5.md#ProjectModule_Root_Task))|否|&nbsp;|
|TaskModules|任务模块([MYSQL5](../../appendix/query_MYSQL5.md#ProjectModule_TaskModules))|否|&nbsp;任务选择所有模块时，模块为当前项目+产品模块|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#ProjectModule_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|BYPATH|BYPATH|BYPATH|否|&nbsp;|
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|ParentModule|父模块|ParentModule|否|&nbsp;|
|ROOT|根模块|ROOT|否|&nbsp;|
|Root_NoBranch|根模块_无分支|Root_NoBranch|否|&nbsp;|
|ROOT_TASK|根模块|ROOT_TASK|否|&nbsp;|
|TaskModules|任务模块|TaskModules|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|类型（task）(TYPE)|EQ|
|模块名称(NAME)|LIKE|
|所属项目(ROOTNAME)|EQ|
|所属项目(ROOTNAME)|LIKE|
|上级模块(PARENTNAME)|EQ|
|上级模块(PARENTNAME)|LIKE|
|项目(ROOT)|EQ|
|id(PARENT)|EQ|

## 导入模式
无


## 导出模式
无
