# 需求(IBZPRO_STORY)

  

## 关系
{% plantuml %}
平台产品 *-- 需求 
需求模块 *-- 需求 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|版本号|VERSION|INT|&nbsp;每次更新内容，会自动加1（只有真正更新数据才会更新，例如打开编辑/变更需求，没有进行任何修改点击保存，是不会更新该笔数据）|
|来源备注|SOURCENOTE|TEXT|&nbsp;|
|抄送给|MAILTO|TEXT|&nbsp;|
|设置阶段者|STAGEDBY|SSCODELIST|&nbsp;|
|已删除|DELETED|TEXT|&nbsp;|
|来源对象名称|IBIZ_SOURCENAME|TEXT|&nbsp;|
|优先级|PRI|INT|&nbsp;|
|需求阶段|STAGE|SSCODELIST|&nbsp;|
|由谁关闭|CLOSEDBY|TEXT|&nbsp;|
|需求描述|SPEC|TEXT|&nbsp;|
|关闭日期	|CLOSEDDATE|DATETIME|&nbsp;|
|相关需求|LINKSTORIES|TEXT|&nbsp;|
|需求类型|TYPE|SSCODELIST|&nbsp;|
|重复需求|DUPLICATESTORY|INT|&nbsp;|
|平台|BRANCH|INT|&nbsp;|
|关键词|KEYWORDS|TEXT|&nbsp;|
|需求细分|CHILDSTORIES|TEXT|&nbsp;|
|创建日期|OPENEDDATE|DATETIME|&nbsp;|
|转Bug|TOBUG|INT|&nbsp;|
|备注|COMMENT|HTMLTEXT|&nbsp;|
|编号|ID|ACID|&nbsp;|
|关闭原因|CLOSEDREASON|TEXT|&nbsp;|
|项目|PROJECT|TEXT|&nbsp;|
|来源Bug|FROMBUG|INT|&nbsp;|
|最后修改者|LASTEDITEDBY|TEXT|&nbsp;|
|指派日期|ASSIGNEDDATE|DATETIME|&nbsp;|
|子状态|SUBSTATUS|TEXT|&nbsp;|
|来源对象标识|IBIZ_SOURCEID|TEXT|&nbsp;|
|需求名称|TITLE|TEXT|&nbsp;|
|最后修改日期|LASTEDITEDDATE|DATETIME|&nbsp;|
|来源对象|IBIZ_SOURCEOBJECT|SSCODELIST|&nbsp;|
|IBIZ标识|IBIZ_ID|TEXT|&nbsp;|
|验收标准|VERIFY|TEXT|&nbsp;|
|由谁评审|REVIEWEDBY|SMCODELIST|&nbsp;|
|指派给|ASSIGNEDTO|TEXT|&nbsp;|
|需求来源|SOURCE|SSCODELIST|&nbsp;|
|预计工时|ESTIMATE|FLOAT|&nbsp;|
|由谁创建|OPENEDBY|TEXT|&nbsp;|
|评审时间|REVIEWEDDATE|DATE|&nbsp;|
|颜色|COLOR|SSCODELIST|&nbsp;|
|状态|STATUS|SSCODELIST|&nbsp;|
|编号|PRODUCT|PICKUP|&nbsp;|
|id|MODULE|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|版本号|默认规则|默认规则|
|来源备注|默认规则|内容长度必须小于等于[255]|
|抄送给|默认规则|内容长度必须小于等于[100]|
|设置阶段者|默认规则|内容长度必须小于等于[60]|
|已删除|默认规则|内容长度必须小于等于[1]|
|来源对象名称|默认规则|内容长度必须小于等于[100]|
|优先级|默认规则|默认规则|
|需求阶段|默认规则|内容长度必须小于等于[60]|
|由谁关闭|默认规则|内容长度必须小于等于[100]|
|需求描述|默认规则|内容长度必须小于等于[100]|
|关闭日期	|默认规则|默认规则|
|相关需求|默认规则|内容长度必须小于等于[100]|
|需求类型|默认规则|内容长度必须小于等于[60]|
|重复需求|默认规则|默认规则|
|平台|默认规则|默认规则|
|关键词|默认规则|内容长度必须小于等于[255]|
|需求细分|默认规则|内容长度必须小于等于[100]|
|创建日期|默认规则|默认规则|
|转Bug|默认规则|默认规则|
|备注|默认规则|内容长度必须小于等于[1048576]|
|编号|默认规则|默认规则|
|关闭原因|默认规则|内容长度必须小于等于[100]|
|项目|默认规则|内容长度必须小于等于[100]|
|来源Bug|默认规则|默认规则|
|最后修改者|默认规则|内容长度必须小于等于[100]|
|指派日期|默认规则|默认规则|
|子状态|默认规则|内容长度必须小于等于[100]|
|来源对象标识|默认规则|内容长度必须小于等于[100]|
|需求名称|默认规则|内容长度必须小于等于[255]|
|最后修改日期|默认规则|默认规则|
|来源对象|默认规则|内容长度必须小于等于[100]|
|IBIZ标识|默认规则|内容长度必须小于等于[100]|
|验收标准|默认规则|内容长度必须小于等于[100]|
|由谁评审|默认规则|内容长度必须小于等于[2000]|
|指派给|默认规则|内容长度必须小于等于[100]|
|需求来源|默认规则|内容长度必须小于等于[20]|
|预计工时|默认规则|默认规则|
|由谁创建|默认规则|内容长度必须小于等于[100]|
|评审时间|默认规则|默认规则|
|颜色|默认规则|内容长度必须小于等于[60]|
|状态|默认规则|内容长度必须小于等于[60]|
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
|同步Ibz平台需求|用户自定义|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#IBZProStory_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IBZProStory_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|来源备注(SOURCENOTE)|EQ|
|设置阶段者(STAGEDBY)|EQ|
|优先级(PRI)|EQ|
|需求阶段(STAGE)|EQ|
|需求类型(TYPE)|EQ|
|需求名称(TITLE)|LIKE|
|来源对象(IBIZ_SOURCEOBJECT)|EQ|
|IBIZ标识(IBIZ_ID)|EQ|
|需求来源(SOURCE)|EQ|
|颜色(COLOR)|EQ|
|状态(STATUS)|EQ|
|编号(PRODUCT)|EQ|
|id(MODULE)|EQ|

## 导入模式
无


## 导出模式
无
