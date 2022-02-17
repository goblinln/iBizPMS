# 文档库分类(IBZ_DOCLIBMODULE)

  

## 关系
{% plantuml %}
文档库分类 *-- 文档库分类 
文档库分类 *-- 文档库分类 
文档库 *-- 文档库分类 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|是否已收藏|ISFAVOURITES|TEXT|&nbsp;|
|grade|GRADE|INT|&nbsp;|
|path|PATH|TEXT|&nbsp;|
|排序值|ORDER|INT|&nbsp;|
|名称|NAME|TEXT|&nbsp;|
|查询类型|DOCQTYPE|TEXT|&nbsp;|
|owner|OWNER|TEXT|&nbsp;|
|branch|BRANCH|INT|&nbsp;|
|叶子模块|ISLEAF|TEXT|&nbsp;|
|类型|TYPE|TEXT|&nbsp;|
|简称|SHORT|TEXT|&nbsp;|
|文档数|DOCCNT|INT|&nbsp;|
|collector|COLLECTOR|LONGTEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|逻辑删除标志|DELETED|TEXT|&nbsp;|
|上级模块|MODULENAME|PICKUPTEXT|&nbsp;|
|所属文档库|DOCLIBNAME|PICKUPTEXT|&nbsp;|
|id|PARENT|PICKUP|&nbsp;|
|编号|ROOT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|是否已收藏|默认规则|内容长度必须小于等于[200]|
|grade|默认规则|默认规则|
|path|默认规则|内容长度必须小于等于[255]|
|排序值|默认规则|默认规则|
|名称|默认规则|内容长度必须小于等于[60]|
|查询类型|默认规则|内容长度必须小于等于[100]|
|owner|默认规则|内容长度必须小于等于[30]|
|branch|默认规则|默认规则|
|叶子模块|默认规则|内容长度必须小于等于[200]|
|类型|默认规则|内容长度必须小于等于[30]|
|简称|默认规则|内容长度必须小于等于[30]|
|文档数|默认规则|默认规则|
|collector|默认规则|内容长度必须小于等于[65535]|
|id|默认规则|默认规则|
|逻辑删除标志|默认规则|内容长度必须小于等于[1]|
|上级模块|默认规则|内容长度必须小于等于[60]|
|所属文档库|默认规则|内容长度必须小于等于[60]|
|id|默认规则|默认规则|
|编号|默认规则|默认规则|

## 状态控制

|查询类型||是否已收藏|行为控制模式| 控制行为 | 操作标识控制模式 | 控制操作 |
| --------   || --------   | ------------|------------|------------|------------|
|Value||Value2| 允许|  | 不允许 | SRFUR__DOCLIBMODULE_NFAVOUR_BUT<br> |
|Value||Value2| 允许|  | 不允许 | SRFUR__DOCLIBMODULE_FAVOUR_BUT<br> |

查询类型

是否已收藏


## 行为
| 行为    | 类型    |  说明  |
| --------   |------------| ----- | 
|Create|内置方法|&nbsp;|
|Update|内置方法|&nbsp;|
|Remove|内置方法|&nbsp;|
|Get|内置方法|&nbsp;|
|GetDraft|内置方法|&nbsp;|
|CheckKey|内置方法|&nbsp;|
|收藏|用户自定义|&nbsp;|
|取消收藏|实体处理逻辑|&nbsp;|
|收藏|实体处理逻辑|&nbsp;|
|重建模块路径|实体处理逻辑|&nbsp;|
|Save|内置方法|&nbsp;|
|取消收藏|用户自定义|&nbsp;|

## 处理逻辑
* 当前是否收藏文档库分类 (CurUserIsF)
  
   

{% plantuml %}
hide footbox

文档库分类 -> 文档库分类: 查询是否收藏
文档库分类 -> 文档库分类: 设置查询类型
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|查询是否收藏 |
|2|设置查询类型 |
<center>当前是否收藏文档库分类</center>
* 文档库收藏 (DocLibModuleFavorite)
  
   

{% plantuml %}
hide footbox

文档库分类 -> 文档库分类: 准备参数
文档库分类 -> 收藏: 收藏
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|准备参数 |
|2|收藏 |
<center>文档库收藏</center>
* 文档库取消收藏 (DoclibModuleNFavorite)
  
   

{% plantuml %}
hide footbox

文档库分类 -> 文档库分类: 取消收藏
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|取消收藏 |
<center>文档库取消收藏</center>
* 重建模块路径 (FixPath)
  
   

{% plantuml %}
hide footbox

文档库分类 -> 文档库分类: 准备参数
文档库分类 -> 模块: 执行重建模型路径
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|准备参数 |
|2|执行重建模型路径 |
<center>重建模块路径</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|AllDoclibModule_Custom|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#DocLibModule_AllDoclibModule_Custom))|否|&nbsp;|
|ChildModuleByParent|子模块目录([MYSQL5](../../appendix/query_MYSQL5.md#DocLibModule_ChildModuleByParent))|否|&nbsp;|
|ChildModuleByRealParent|文档库分类子模块([MYSQL5](../../appendix/query_MYSQL5.md#DocLibModule_ChildModuleByRealParent))|否|&nbsp;文档库分类下维护分类专属|
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#DocLibModule_Default))|是|&nbsp;|
|AllDoclibModule|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#DocLibModule_DefaultDoclib))|否|&nbsp;|
|MyFavourites|我的收藏([MYSQL5](../../appendix/query_MYSQL5.md#DocLibModule_MyFavourites))|否|&nbsp;|
|ParentModule|父模块([MYSQL5](../../appendix/query_MYSQL5.md#DocLibModule_ParentModule))|否|&nbsp;|
|RootModuleMuLu|所有根模块目录([MYSQL5](../../appendix/query_MYSQL5.md#DocLibModule_RootModuleMuLu))|否|&nbsp;|
|RootModuleMuLuByRoot|根模块目录动态([MYSQL5](../../appendix/query_MYSQL5.md#DocLibModule_RootModuleMuLuByRoot))|否|&nbsp;|
|RootModuleMuLuBysrfparentkey|根模块目录动态([MYSQL5](../../appendix/query_MYSQL5.md#DocLibModule_RootModuleMuLuBysrfparentkey))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#DocLibModule_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|AllDocLibModule_Custom|自定义文档库的模块|AllDoclibModule_Custom|否|&nbsp;|
|AllDoclibModule|所有文档库模块|AllDoclibModule|否|&nbsp;|
|ChildModuleByParent|子模块目录|ChildModuleByParent|否|&nbsp;|
|ChildModuleByRealParent|文档库分类子模块|ChildModuleByRealParent|否|&nbsp;|
|DEFAULT|数据集|DEFAULT|是|&nbsp;|
|MyFavourites|我的收藏|MyFavourites|否|&nbsp;|
|ParentModule|父集合|ParentModule|否|&nbsp;|
|RootModuleMuLu|所有根模块目录|RootModuleMuLu|否|&nbsp;|
|RootModuleMuLuByRoot|根模块目录|RootModuleMuLuByRoot|否|&nbsp;|
|RootModuleMuLuBysrfparentkey|根模块目录动态|RootModuleMuLuBysrfparentkey|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|是否已收藏(ISFAVOURITES)|EQ|
|名称(NAME)|LIKE|
|查询类型(DOCQTYPE)|EQ|
|id(ID)|EQ|
|上级模块(MODULENAME)|EQ|
|上级模块(MODULENAME)|LIKE|
|所属文档库(DOCLIBNAME)|EQ|
|所属文档库(DOCLIBNAME)|LIKE|
|id(PARENT)|EQ|
|编号(ROOT)|EQ|

## 导入模式
无


## 导出模式
无
