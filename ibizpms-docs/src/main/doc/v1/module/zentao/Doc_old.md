# 文档(ZT_DOC)

  

## 关系
{% plantuml %}
文档 *-- 文档内容 
文档库 *-- 文档 
模块 *-- 文档 
产品 *-- 文档 
项目 *-- 文档 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|分组|GROUPS|TEXT|&nbsp;选择权限为自定义生效|
|文档正文|CONTENT|HTMLTEXT|&nbsp;|
|文档查询类型|DOCQTYPE|TEXT|&nbsp;|
|更新时间|EDITEDDATE|DATETIME|&nbsp;|
|views|VIEWS|INT|&nbsp;|
|版本号|VERSION|INT|&nbsp;|
|由谁更新|EDITEDBY|TEXT|&nbsp;|
|文档编号|ID|ACID|&nbsp;|
|文档标题|TITLE|TEXT|&nbsp;|
|已删除|DELETED|TEXT|&nbsp;|
|最近更新数量|RECENTUPDATECNT|INT|&nbsp;|
|文档类型|TYPE|SSCODELIST|&nbsp;|
|所有文档数量|ALLDOCCNT|INT|&nbsp;|
|添加时间|ADDEDDATE|DATETIME|&nbsp;|
|权限|ACL|SSCODELIST|&nbsp;|
|我的文档数量|MYDOCCNT|INT|&nbsp;|
|文档链接|URL|TEXT|&nbsp;|
|文档数|DOCCNT|INT|&nbsp;|
|我的收藏数量|MYFAVOURITECNT|INT|&nbsp;|
|用户|USERS|LONGTEXT|&nbsp;选择权限为自定义生效|
|最近添加数量|RECENTADDCNT|INT|&nbsp;|
|关键字|KEYWORDS|TEXT|&nbsp;|
|是否收藏|ISFAVOURITES|TEXT|&nbsp;|
|收藏者|COLLECTOR|LONGTEXT|&nbsp;|
|由谁添加|ADDEDBY|TEXT|&nbsp;|
|今日更新数量|TODAYUPDATECNT|INT|&nbsp;|
|附件|FILES|TEXT|&nbsp;|
|所属产品|PRODUCTNAME|PICKUPTEXT|&nbsp;|
|所属项目|PROJECTNAME|PICKUPTEXT|&nbsp;|
|所属文档库|LIBNAME|PICKUPTEXT|&nbsp;|
|模块分类|MODULENAME|PICKUPTEXT|&nbsp;|
|所属文档库|LIB|PICKUP|&nbsp;|
|所属项目|PROJECT|PICKUP|&nbsp;|
|所属产品|PRODUCT|PICKUP|&nbsp;|
|所属分类|MODULE|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|分组|默认规则|内容长度必须小于等于[255]|
|文档正文|默认规则|内容长度必须小于等于[1048576]|
|文档查询类型|默认规则|内容长度必须小于等于[200]|
|更新时间|默认规则|默认规则|
|views|默认规则|默认规则|
|版本号|默认规则|默认规则|
|由谁更新|默认规则|内容长度必须小于等于[30]|
|文档编号|默认规则|默认规则|
|文档标题|默认规则|内容长度必须小于等于[255]|
|已删除|默认规则|内容长度必须小于等于[1]|
|最近更新数量|默认规则|默认规则|
|文档类型|默认规则|内容长度必须小于等于[30]|
|所有文档数量|默认规则|默认规则|
|添加时间|默认规则|默认规则|
|权限|默认规则|内容长度必须小于等于[10]|
|我的文档数量|默认规则|默认规则|
|文档链接|默认规则|内容长度必须小于等于[100]|
|文档数|默认规则|默认规则|
|我的收藏数量|默认规则|默认规则|
|用户|默认规则|内容长度必须小于等于[65535]|
|最近添加数量|默认规则|默认规则|
|关键字|默认规则|内容长度必须小于等于[255]|
|是否收藏|默认规则|内容长度必须小于等于[200]|
|收藏者|默认规则|内容长度必须小于等于[65535]|
|由谁添加|默认规则|内容长度必须小于等于[30]|
|今日更新数量|默认规则|默认规则|
|附件|默认规则|内容长度必须小于等于[1000]|
|所属产品|默认规则|内容长度必须小于等于[90]|
|所属项目|默认规则|内容长度必须小于等于[90]|
|所属文档库|默认规则|内容长度必须小于等于[60]|
|模块分类|默认规则|内容长度必须小于等于[60]|
|所属文档库|默认规则|默认规则|
|所属项目|默认规则|默认规则|
|所属产品|默认规则|默认规则|
|所属分类|默认规则|默认规则|

## 状态控制

|文档查询类型||是否收藏|行为控制模式| 控制行为 | 操作标识控制模式 | 控制操作 |
| --------   || --------   | ------------|------------|------------|------------|
|Value||Value2| 允许|  | 不允许 | MODULE<br>DOCLIB<br>SRFUR__DOC_NFAVOUR_BUT<br> |
|Value||Value2| 允许|  | 不允许 | MODULE<br>SRFUR__DOC_FAVOUR_BUT<br>DOCLIB<br> |
|Value||Value2| 允许|  | 不允许 | DOC<br>MODULE<br>SRFUR__DOC_NFAVOUR_BUT<br> |
|Value||Value2| 允许|  | 不允许 | SRFUR__DOC_FAVOUR_BUT<br>DOC<br>MODULE<br> |
|Value||Value2| 允许|  | 不允许 | DOCLIB<br>DOC<br>SRFUR__DOC_NFAVOUR_BUT<br> |
|Value||Value2| 允许|  | 不允许 | DOCLIB<br>DOC<br>SRFUR__DOC_FAVOUR_BUT<br> |

文档查询类型

是否收藏


## 行为
| 行为    | 类型    |  说明  |
| --------   |------------| ----- | 
|Create|内置方法|&nbsp;|
|Update|内置方法|&nbsp;|
|Remove|内置方法|&nbsp;|
|Get|内置方法|&nbsp;|
|GetDraft|内置方法|&nbsp;|
|根据版本更新正文信息|用户自定义|&nbsp;|
|CheckKey|内置方法|&nbsp;|
|收藏|用户自定义|&nbsp;|
|行为|用户自定义|&nbsp;|
|仅收藏文档|用户自定义|&nbsp;|
|仅取消收藏文档|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|
|取消收藏|用户自定义|&nbsp;|

## 处理逻辑
* 根据版本更新正文信息 (ByVersionUpdateContext)
  
   

{% plantuml %}
hide footbox

文档 -> 文档: 获取正文信息
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|获取正文信息 |
<center>根据版本更新正文信息</center>
* 当前是否收藏文档 (CurUserIsF)
  
   

{% plantuml %}
hide footbox

文档 -> 文档: 查询是否收藏
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|查询是否收藏 |
<center>当前是否收藏文档</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|ChildDocLibDoc|文档库文档（子库）([MYSQL5](../../appendix/query_MYSQL5.md#Doc_ChildDocLibDoc))|否|&nbsp;|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Doc_Default))|是|&nbsp;|
|DocLibAndDoc|文档库文档([MYSQL5](../../appendix/query_MYSQL5.md#Doc_DocLibAndDoc))|否|&nbsp;|
|DocLibDoc|文档库文档([MYSQL5](../../appendix/query_MYSQL5.md#Doc_DocLibDoc))|否|&nbsp;|
|DocModuleDoc|文档库分类文档([MYSQL5](../../appendix/query_MYSQL5.md#Doc_DocModuleDoc))|否|&nbsp;|
|DocStatus|文档统计([MYSQL5](../../appendix/query_MYSQL5.md#Doc_DocStatus))|否|&nbsp;|
|ModuleDocChild|文件夹文档（子目录）([MYSQL5](../../appendix/query_MYSQL5.md#Doc_ModuleDocChild))|否|&nbsp;|
|MYFAVOURITE|我的收藏([MYSQL5](../../appendix/query_MYSQL5.md#Doc_MyFavourite))|否|&nbsp;|
|MyFavouritesOnlyDoc|我的收藏([MYSQL5](../../appendix/query_MYSQL5.md#Doc_MyFavouritesOnlyDoc))|否|&nbsp;只查询我收藏的文档|
|NotRootDoc|子目录文档([MYSQL5](../../appendix/query_MYSQL5.md#Doc_NotRootDoc))|否|&nbsp;|
|RootDoc|根目录文档([MYSQL5](../../appendix/query_MYSQL5.md#Doc_RootDoc))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Doc_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|ChildDocLibDoc|文档库文档（子库）|ChildDocLibDoc|否|&nbsp;|
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|DocLibAndDoc|文档库文档|DocLibAndDoc|否|&nbsp;|
|DocLibDoc|文档库文档|DocLibDoc|否|&nbsp;|
|DocModuleDoc|文档库分类文档|DocModuleDoc|否|&nbsp;|
|DocStatus|文档统计|DocStatus|否|&nbsp;|
|ModuleDocChild|文件夹文档（子目录）|ModuleDocChild|否|&nbsp;|
|MYFAVOURITE|我的收藏|MYFAVOURITE|否|&nbsp;|
|MyFavouritesOnlyDoc|我的收藏|MyFavouritesOnlyDoc|否|&nbsp;|
|NotRootDoc|子目录文档|NotRootDoc|否|&nbsp;|
|RootDoc|根目录文档|RootDoc|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|文档标题(TITLE)|LIKE|
|文档类型(TYPE)|EQ|
|权限(ACL)|EQ|
|收藏者(COLLECTOR)|LIKE|
|由谁添加(ADDEDBY)|EQ|
|所属产品(PRODUCTNAME)|EQ|
|所属产品(PRODUCTNAME)|LIKE|
|所属项目(PROJECTNAME)|EQ|
|所属项目(PROJECTNAME)|LIKE|
|所属文档库(LIBNAME)|EQ|
|所属文档库(LIBNAME)|LIKE|
|模块分类(MODULENAME)|EQ|
|模块分类(MODULENAME)|LIKE|
|所属文档库(LIB)|EQ|
|所属项目(PROJECT)|EQ|
|所属产品(PRODUCT)|EQ|
|所属分类(MODULE)|EQ|

## 导入模式
无


## 导出模式
无
