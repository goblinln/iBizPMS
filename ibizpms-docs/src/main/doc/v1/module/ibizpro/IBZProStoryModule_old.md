# 需求模块(IBZPRO_STORYMODULE)

  

## 关系
{% plantuml %}
需求模块 *-- 需求模块 
需求模块 *-- 需求 
平台产品 *-- 需求模块 
需求模块 *-- 需求模块 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|级别|GRADE|INT|&nbsp;|
|collector|COLLECTOR|LONGTEXT|&nbsp;|
|owner|OWNER|TEXT|&nbsp;|
|需求模块类型|IBIZ_STORYTYPE|SSCODELIST|&nbsp;|
|id|ID|ACID|&nbsp;|
|类型|TYPE|SSCODELIST|&nbsp;|
|简称|SHORT|TEXT|&nbsp;|
|名称|NAME|TEXT|&nbsp;|
|已删除|DELETED|TEXT|&nbsp;|
|路径|PATH|TEXT|&nbsp;从根到自己|
|IBIZ标识|IBIZ_ID|TEXT|&nbsp;|
|产品|PRODUCTNAME|PICKUPTEXT|&nbsp;|
|编号|ROOT|PICKUP|&nbsp;|
|id|PARENT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|级别|默认规则|默认规则|
|collector|默认规则|内容长度必须小于等于[65535]|
|owner|默认规则|内容长度必须小于等于[30]|
|需求模块类型|默认规则|内容长度必须小于等于[60]|
|id|默认规则|默认规则|
|类型|默认规则|内容长度必须小于等于[30]|
|简称|默认规则|内容长度必须小于等于[30]|
|名称|默认规则|内容长度必须小于等于[60]|
|已删除|默认规则|内容长度必须小于等于[1]|
|路径|默认规则|内容长度必须小于等于[255]|
|IBIZ标识|默认规则|内容长度必须小于等于[100]|
|产品|默认规则|内容长度必须小于等于[90]|
|编号|默认规则|默认规则|
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

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#IBZProStoryModule_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IBZProStoryModule_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|需求模块类型(IBIZ_STORYTYPE)|EQ|
|id(ID)|EQ|
|类型(TYPE)|EQ|
|名称(NAME)|LIKE|
|IBIZ标识(IBIZ_ID)|EQ|
|产品(PRODUCTNAME)|EQ|
|产品(PRODUCTNAME)|LIKE|
|编号(ROOT)|EQ|
|id(PARENT)|EQ|

## 导入模式
无


## 导出模式
无
