# Bug统计(IBZ_BUGSTATS)

  

## 关系
{% plantuml %}
产品 *-- Bug统计 
项目 *-- Bug统计 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|激活Bug|BUGACTIVE|INT|&nbsp;|
|不予解决|BUGWILLNOTFIX|INT|&nbsp;|
|由谁解决|RESOLVEDBY|SSCODELIST|&nbsp;|
|Bug解决方案|BUGRESOLUTION|TEXT|&nbsp;|
|部门|DEPT|SSCODELIST|&nbsp;|
|设计如此|BUGBYDESIGN|INT|&nbsp;|
|bug创建人|BUGOPENEDBY|TEXT|&nbsp;|
|Bug状态|BUGSTATUS|TEXT|&nbsp;|
|已关闭Bug|BUGCLOSED|INT|&nbsp;|
|指派给|ASSIGNEDTO|SSCODELIST|&nbsp;|
|外部原因|BUGEXTERNAL|INT|&nbsp;|
|开始|BEGIN|DATE|&nbsp;|
|bug解决日期|BUGRESOLVEDDATE|DATE|&nbsp;|
|结束|END|DATE|&nbsp;|
|Bug编号|BUGID|INT|&nbsp;|
|项目名称|PROJECTNAME|TEXT|&nbsp;|
|已解决|BUGFIXED|INT|&nbsp;|
|标识|ID|ACID|&nbsp;|
|延期处理|BUGPOSTPONED|INT|&nbsp;|
|Bug标题|BUGTITLE|TEXT|&nbsp;|
|Bug|BUGCNT|INT|&nbsp;|
|Bug创建日期|BUGOPENEDDATE|DATE|&nbsp;|
|未解决|BUGWJJ|INT|&nbsp;|
|名称|TITLE|TEXT|&nbsp;|
|Bug优先级|BUGPRI|INT|&nbsp;|
|无法重现|BUGNOTREPRO|INT|&nbsp;|
|项目名称|PROJECTNAME1|TEXT|&nbsp;|
|已解决Bug|BUGRESOLVED|INT|&nbsp;|
|重复Bug|BUGDUPLICATE|INT|&nbsp;|
|由谁创建|OPENEDBY|SSCODELIST|&nbsp;|
|有效率|BUGEFFICIENT|TEXT|&nbsp;|
|转为需求|BUGTOSTORY|INT|&nbsp;|
|Bug严重程度|BUGSEVERITY|INT|&nbsp;|
|总计|BUGTOTAL|INT|&nbsp;|
|产品名称|PRODUCTNAME|PICKUPTEXT|&nbsp;|
|编号|PRODUCT|PICKUP|&nbsp;|
|项目编号|PROJECT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|激活Bug|默认规则|默认规则|
|不予解决|默认规则|默认规则|
|由谁解决|默认规则|内容长度必须小于等于[100]|
|Bug解决方案|默认规则|内容长度必须小于等于[100]|
|部门|默认规则|内容长度必须小于等于[200]|
|设计如此|默认规则|默认规则|
|bug创建人|默认规则|内容长度必须小于等于[100]|
|Bug状态|默认规则|内容长度必须小于等于[100]|
|已关闭Bug|默认规则|默认规则|
|指派给|默认规则|内容长度必须小于等于[60]|
|外部原因|默认规则|默认规则|
|开始|默认规则|默认规则|
|bug解决日期|默认规则|默认规则|
|结束|默认规则|默认规则|
|Bug编号|默认规则|默认规则|
|项目名称|默认规则|内容长度必须小于等于[100]|
|已解决|默认规则|默认规则|
|标识|默认规则|默认规则|
|延期处理|默认规则|默认规则|
|Bug标题|默认规则|内容长度必须小于等于[100]|
|Bug|默认规则|默认规则|
|Bug创建日期|默认规则|默认规则|
|未解决|默认规则|默认规则|
|名称|默认规则|内容长度必须小于等于[100]|
|Bug优先级|默认规则|默认规则|
|无法重现|默认规则|默认规则|
|项目名称|默认规则|内容长度必须小于等于[100]|
|已解决Bug|默认规则|默认规则|
|重复Bug|默认规则|默认规则|
|由谁创建|默认规则|内容长度必须小于等于[60]|
|有效率|默认规则|内容长度必须小于等于[200]|
|转为需求|默认规则|默认规则|
|Bug严重程度|默认规则|默认规则|
|总计|默认规则|默认规则|
|产品名称|默认规则|内容长度必须小于等于[90]|
|编号|默认规则|默认规则|
|项目编号|默认规则|默认规则|

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
|BugCountInResolution|Bug在每个解决方案的Bug数([MYSQL5](../../appendix/query_MYSQL5.md#BugStats_BugCountInResolution))|否|&nbsp;|
|BugResolvedBy|Bug完成表([MYSQL5](../../appendix/query_MYSQL5.md#BugStats_BugResolvedBy))|否|&nbsp;|
|BugResolvedGird|bug解决汇总表([MYSQL5](../../appendix/query_MYSQL5.md#BugStats_BugResolvedGird))|否|&nbsp;|
|BugassignedTo|Bug指派表([MYSQL5](../../appendix/query_MYSQL5.md#BugStats_BugassignedTo))|否|&nbsp;|
|DEFAULT|Bug创建表([MYSQL5](../../appendix/query_MYSQL5.md#BugStats_Default))|是|&nbsp;|
|ProductBugResolutionStats|产品Bug解决方案汇总([MYSQL5](../../appendix/query_MYSQL5.md#BugStats_ProductBugResolutionStats))|否|&nbsp;|
|ProductBugStatusSum|产品Bug状态汇总([MYSQL5](../../appendix/query_MYSQL5.md#BugStats_ProductBugStatusSum))|否|&nbsp;|
|ProductCreateBug|产品创建bug占比([MYSQL5](../../appendix/query_MYSQL5.md#BugStats_ProductCreateBug))|否|&nbsp;|
|ProjectBugStatusCount|项目bug状态统计([MYSQL5](../../appendix/query_MYSQL5.md#BugStats_ProjectBugStatusCount))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#BugStats_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|BugCountInResolution|Bug在每个解决方案的Bug数|BugCountInResolution|否|&nbsp;|
|BugResolvedBy|Bug完成表|BugResolvedBy|否|&nbsp;|
|BugResolvedGird|bug解决汇总表|BugResolvedGird|否|&nbsp;|
|BugassignedTo|Bug指派表|BugassignedTo|否|&nbsp;|
|DEFAULT|数据集|DEFAULT|是|&nbsp;|
|ProductBugResolutionStats|产品Bug解决方案汇总|ProductBugResolutionStats|否|&nbsp;|
|ProductBugStatusSum|产品Bug状态汇总|ProductBugStatusSum|否|&nbsp;|
|ProductCreateBug|产品创建bug占比|ProductCreateBug|否|&nbsp;|
|ProjectBugStatusCount|项目bug状态统计|ProjectBugStatusCount|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|不予解决(BUGWILLNOTFIX)|LTANDEQ|
|由谁解决(RESOLVEDBY)|EQ|
|部门(DEPT)|EQ|
|指派给(ASSIGNEDTO)|EQ|
|开始(BEGIN)|GTANDEQ|
|bug解决日期(BUGRESOLVEDDATE)|GTANDEQ|
|bug解决日期(BUGRESOLVEDDATE)|LTANDEQ|
|结束(END)|LTANDEQ|
|名称(TITLE)|LIKE|
|由谁创建(OPENEDBY)|EQ|
|编号(PRODUCT)|EQ|
|项目编号(PROJECT)|EQ|

## 导入模式
无


## 导出模式
* Bug解决数据导出

|由谁解决|Bug编号|Bug标题|优先级|严重程度|由谁创建|创建|解决方案|解决日期|Bug状态|
| :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: |
| - | - | - | - | - | - | - | - | - | - |
