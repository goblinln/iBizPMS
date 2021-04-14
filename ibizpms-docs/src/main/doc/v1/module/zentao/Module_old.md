# 模块(ZT_MODULE)

  

## 关系
{% plantuml %}
模块 *-- 测试用例统计 
模块 *-- Bug 
模块 *-- 测试用例 
模块 *-- 文档 
模块 *-- 产品 
模块 *-- 模块 
产品的分支和平台信息 *-- 模块 
模块 *-- 模块 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|所属根|ROOT|TEXT|&nbsp;|
|级别|GRADE|INT|&nbsp;|
|类型|TYPE|SSCODELIST|&nbsp;|
|模块名称|NAME|TEXT|&nbsp;|
|排序|ORDER|INT|&nbsp;|
|负责人|OWNER|TEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|数据选择排序|ORDERPK|TEXT|&nbsp;|
|收藏者|COLLECTOR|LONGTEXT|&nbsp;|
|简称|SHORT|TEXT|&nbsp;|
|路径|PATH|TEXT|&nbsp;从根到自己|
|部门标识|MDEPTID|TEXT|&nbsp;|
|组织标识|ORGID|TEXT|&nbsp;|
|已删除|DELETED|TEXT|&nbsp;|
|上级模块|PARENTNAME|PICKUPTEXT|&nbsp;|
|平台/分支|BRANCH|PICKUP|&nbsp;|
|上级模块|PARENT|PICKUP|&nbsp;|
|由谁创建|CREATEBY|TEXT|&nbsp;|
|由谁更新|UPDATEBY|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|所属根|默认规则|内容长度必须小于等于[8]|
|级别|默认规则|默认规则|
|类型|默认规则|内容长度必须小于等于[30]|
|模块名称|默认规则|内容长度必须小于等于[60]|
|排序|默认规则|默认规则|
|负责人|默认规则|内容长度必须小于等于[30]|
|id|默认规则|默认规则|
|数据选择排序|默认规则|内容长度必须小于等于[100]|
|收藏者|默认规则|内容长度必须小于等于[65535]|
|简称|默认规则|内容长度必须小于等于[30]|
|路径|默认规则|内容长度必须小于等于[255]|
|部门标识|默认规则|内容长度必须小于等于[100]|
|组织标识|默认规则|内容长度必须小于等于[100]|
|已删除|默认规则|内容长度必须小于等于[1]|
|上级模块|默认规则|内容长度必须小于等于[60]|
|平台/分支|默认规则|默认规则|
|上级模块|默认规则|默认规则|
|由谁创建|默认规则|内容长度必须小于等于[100]|
|由谁更新|默认规则|内容长度必须小于等于[100]|

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
|重建模块路径|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|BugModule|BugModule([MYSQL5](../../appendix/query_MYSQL5.md#Module_BugModule))|否|&nbsp;|
|BugModuleCodeList|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#Module_BugModuleCodeList))|否|&nbsp;|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Module_Default))|是|&nbsp;|
|DocModule|文档目录查询([MYSQL5](../../appendix/query_MYSQL5.md#Module_DocModule))|否|&nbsp;|
|Line|产品线([MYSQL5](../../appendix/query_MYSQL5.md#Module_Line))|否|&nbsp;|
|StoryModule|需求模块([MYSQL5](../../appendix/query_MYSQL5.md#Module_StoryModule))|否|&nbsp;|
|TaskModule|任务模块([MYSQL5](../../appendix/query_MYSQL5.md#Module_TaskModule))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Module_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|BugModule|BugModule|BugModule|否|&nbsp;|
|BugModuleCodeList|数据集|BugModuleCodeList|否|&nbsp;|
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|DocModule|文档目录|DocModule|否|&nbsp;|
|Line|产品线|Line|否|&nbsp;|
|StoryModule|需求模块|StoryModule|否|&nbsp;|
|TaskModule|任务模块|TaskModule|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|所属根(ROOT)|EQ|
|类型(TYPE)|EQ|
|模块名称(NAME)|LIKE|
|上级模块(PARENTNAME)|EQ|
|上级模块(PARENTNAME)|LIKE|
|平台/分支(BRANCH)|EQ|
|上级模块(PARENT)|EQ|

## 导入模式
无


## 导出模式
无
