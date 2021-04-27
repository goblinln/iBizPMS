# 需求模块(IBZ_PRODUCTMODULE)

  产品需求模块

## 关系
{% plantuml %}
需求模块 *-- 需求模块 
需求模块 *-- 需求 
需求模块 *-- 需求模块 
产品 *-- 需求模块 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|path|PATH|TEXT|&nbsp;|
|数据选择排序|ORDERPK|TEXT|&nbsp;|
|逻辑删除标志|DELETED|TEXT|&nbsp;|
|名称|NAME|TEXT|&nbsp;|
|branch|BRANCH|INT|&nbsp;|
|简称|SHORT|TEXT|&nbsp;|
|排序值|ORDER|INT|&nbsp;|
|grade|GRADE|INT|&nbsp;|
|类型（story）|TYPE|TEXT|&nbsp;|
|owner|OWNER|TEXT|&nbsp;|
|叶子模块|ISLEAF|TEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|collector|COLLECTOR|LONGTEXT|&nbsp;|
|所属产品|ROOTNAME|PICKUPTEXT|&nbsp;|
|上级模块|PARENTNAME|PICKUPTEXT|&nbsp;|
|产品|ROOT|PICKUP|&nbsp;|
|id|PARENT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|path|默认规则|内容长度必须小于等于[255]|
|数据选择排序|默认规则|内容长度必须小于等于[100]|
|逻辑删除标志|默认规则|内容长度必须小于等于[1]|
|名称|默认规则|内容长度必须小于等于[60]|
|branch|默认规则|默认规则|
|简称|默认规则|内容长度必须小于等于[30]|
|排序值|默认规则|默认规则|
|grade|默认规则|默认规则|
|类型（story）|默认规则|内容长度必须小于等于[30]|
|owner|默认规则|内容长度必须小于等于[30]|
|叶子模块|默认规则|内容长度必须小于等于[200]|
|id|默认规则|默认规则|
|collector|默认规则|内容长度必须小于等于[65535]|
|所属产品|默认规则|内容长度必须小于等于[90]|
|上级模块|默认规则|内容长度必须小于等于[60]|
|产品|默认规则|默认规则|
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
|重建模块路径|实体处理逻辑|&nbsp;|
|删除模块|实体处理逻辑|&nbsp;|
|Save|内置方法|&nbsp;|
|同步Ibz平台模块|用户自定义|&nbsp;|

## 处理逻辑
* 重建模块路径 (FixPath)
  
   

{% plantuml %}
hide footbox

需求模块 -> 需求模块: 准备参数
需求模块 -> 模块: 执行重建模块路径行为
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

需求模块 -> 需求模块: 设置模块参数
需求模块 -> 模块: 模块删除
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
|BYPATH|BYPATH([MYSQL5](../../appendix/query_MYSQL5.md#ProductModule_ByPath))|否|&nbsp;通过模块路径包含查询|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#ProductModule_Default))|是|&nbsp;需求|
|ParentModule|父模块([MYSQL5](../../appendix/query_MYSQL5.md#ProductModule_ParentModule))|否|&nbsp;|
|ROOT|根模块([MYSQL5](../../appendix/query_MYSQL5.md#ProductModule_Root))|否|&nbsp;需求|
|Root_NoBranch|根模块_无分支([MYSQL5](../../appendix/query_MYSQL5.md#ProductModule_Root_NoBranch))|否|&nbsp;需求|
|StoryModule|StoryModule([MYSQL5](../../appendix/query_MYSQL5.md#ProductModule_StoryModule))|否|&nbsp;需求|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#ProductModule_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|BYPATH|BYPATH|BYPATH|否|&nbsp;|
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|ParentModule|父模块|ParentModule|否|&nbsp;|
|ROOT|根模块|ROOT|否|&nbsp;|
|Root_NoBranch|根模块_无分支|Root_NoBranch|否|&nbsp;|
|StoryModule|StoryModule|StoryModule|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|名称(NAME)|LIKE|
|branch(BRANCH)|EQ|
|branch(BRANCH)|NOTEQ|
|类型（story）(TYPE)|EQ|
|id(ID)|EQ|
|所属产品(ROOTNAME)|EQ|
|所属产品(ROOTNAME)|LIKE|
|上级模块(PARENTNAME)|EQ|
|上级模块(PARENTNAME)|LIKE|
|产品(ROOT)|EQ|
|id(PARENT)|EQ|

## 导入模式
无


## 导出模式
无
