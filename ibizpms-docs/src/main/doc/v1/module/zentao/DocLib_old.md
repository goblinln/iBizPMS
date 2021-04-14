# 文档库(ZT_DOCLIB)

  

## 关系
{% plantuml %}
文档库 *-- 文档库分类 
文档库 *-- 文档 
产品 *-- 文档库 
项目 *-- 文档库 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|文档类型|TYPE|SSCODELIST|&nbsp;业务逻辑处理上还存在collector进行判断是否为all展示|
|文件库类型|DOCLIBTYPE|SSCODELIST|&nbsp;|
|是否收藏|ISFAVOURITES|TEXT|&nbsp;|
|收藏者|COLLECTOR|LONGTEXT|&nbsp;|
|部门标识|MDEPTID|TEXT|&nbsp;|
|组织标识|ORGID|TEXT|&nbsp;|
|权限|ACL|SSCODELIST|&nbsp;|
|Root|ROOT|TEXT|&nbsp;|
|已删除|DELETED|TEXT|&nbsp;|
|分组|GROUPS|TEXT|&nbsp;选择权限为自定义生效|
|文档数量|DOCCNT|INT|&nbsp;|
|编号|ID|ACID|&nbsp;|
|用户|USERS|LONGTEXT|&nbsp;选择权限为自定义生效|
|是否是主库|MAIN|TEXT|&nbsp;|
|文档库名称|NAME|TEXT|&nbsp;|
|文件夹数|MODULECNT|INT|&nbsp;|
|创建时间|OPENEDDATE|DATETIME|&nbsp;|
|排序|ORDER|INT|&nbsp;|
|所属产品|PRODUCTNAME|PICKUPTEXT|&nbsp;|
|所属项目|PROJECTNAME|PICKUPTEXT|&nbsp;|
|项目库|PROJECT|PICKUP|&nbsp;|
|产品库|PRODUCT|PICKUP|&nbsp;|
|由谁创建|CREATEBY|TEXT|&nbsp;|
|由谁更新|UPDATEBY|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|文档类型|默认规则|内容长度必须小于等于[30]|
|文件库类型|默认规则|内容长度必须小于等于[200]|
|是否收藏|默认规则|内容长度必须小于等于[200]|
|收藏者|默认规则|内容长度必须小于等于[65535]|
|部门标识|默认规则|内容长度必须小于等于[100]|
|组织标识|默认规则|内容长度必须小于等于[100]|
|权限|默认规则|内容长度必须小于等于[10]|
|Root|默认规则|内容长度必须小于等于[100]|
|已删除|默认规则|内容长度必须小于等于[1]|
|分组|默认规则|内容长度必须小于等于[255]|
|文档数量|默认规则|默认规则|
|编号|默认规则|默认规则|
|用户|默认规则|内容长度必须小于等于[65535]|
|是否是主库|默认规则|内容长度必须小于等于[1]|
|文档库名称|默认规则|内容长度必须小于等于[60]|
|文件夹数|默认规则|默认规则|
|创建时间|默认规则|默认规则|
|排序|默认规则|默认规则|
|所属产品|默认规则|内容长度必须小于等于[90]|
|所属项目|默认规则|内容长度必须小于等于[90]|
|项目库|默认规则|默认规则|
|产品库|默认规则|默认规则|
|由谁创建|默认规则|内容长度必须小于等于[100]|
|由谁更新|默认规则|内容长度必须小于等于[100]|

## 状态控制

|文件库类型||是否收藏|行为控制模式| 控制行为 | 操作标识控制模式 | 控制操作 |
| --------   || --------   | ------------|------------|------------|------------|
|Value||Value2| 允许|  | 不允许 | SRFUR__DOCLIB_NFAVOUR_BUT<br>FILE<br> |
|Value||Value2| 允许|  | 不允许 | SRFUR__DOCLIB_FAVOUR_BUT<br>FILE<br> |
|Value||Value2| 允许|  | 不允许 | SRFUR__DOCLIB_FAVOUR_BUT<br>DOC<br>SRFUR__DOCLIB_NFAVOUR_BUT<br> |
|Value||Value2| 允许|  | 不允许 | DOC<br>SRFUR__DOCLIB_NFAVOUR_BUT<br>SRFUR__DOCLIB_FAVOUR_BUT<br> |

文件库类型

| 状态        |    状态值   |
| --------   |------------|
|文档|doc|
|附件|file|
是否收藏


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
|Save|内置方法|&nbsp;|
|取消收藏|用户自定义|&nbsp;|

## 处理逻辑
* 当前是否收藏文档库 (CurUserIsF)
  
   

{% plantuml %}
hide footbox

文档库 -> 文档库: 设置是否收藏
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|设置是否收藏 |
<center>当前是否收藏文档库</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|ByCustom|自定义文档库([MYSQL5](../../appendix/query_MYSQL5.md#DocLib_ByCustom))|否|&nbsp;|
|ByProduct|产品文档库([MYSQL5](../../appendix/query_MYSQL5.md#DocLib_ByProduct))|否|&nbsp;|
|ByProductNotFiles|产品文档库([MYSQL5](../../appendix/query_MYSQL5.md#DocLib_ByProductNotFiles))|否|&nbsp;|
|ByProject|项目文件库([MYSQL5](../../appendix/query_MYSQL5.md#DocLib_ByProject))|否|&nbsp;|
|ByProjectNotFiles|项目文件库([MYSQL5](../../appendix/query_MYSQL5.md#DocLib_ByProjectNotFiles))|否|&nbsp;|
|CurDocLib|所属文档库([MYSQL5](../../appendix/query_MYSQL5.md#DocLib_CurDocLib))|否|&nbsp;|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#DocLib_Default))|是|&nbsp;|
|MyFavourites|我的收藏([MYSQL5](../../appendix/query_MYSQL5.md#DocLib_MyFavourites))|否|&nbsp;|
|RootModuleMuLu|根目录([MYSQL5](../../appendix/query_MYSQL5.md#DocLib_RootModuleMuLu))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#DocLib_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|ByCustom|自定义文档库|ByCustom|否|&nbsp;|
|ByProduct|产品文档库|ByProduct|否|&nbsp;|
|ByProductNotFiles|产品文档库|ByProductNotFiles|否|&nbsp;|
|ByProject|项目文件库|ByProject|否|&nbsp;|
|ByProjectNotFiles|项目文件库|ByProjectNotFiles|否|&nbsp;|
|CurDocLib|所属文档库|CurDocLib|否|&nbsp;|
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|MyFavourites|我的收藏|MyFavourites|否|&nbsp;|
|RootModuleMuLu|根目录|RootModuleMuLu|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|文档类型(TYPE)|EQ|
|文件库类型(DOCLIBTYPE)|EQ|
|权限(ACL)|EQ|
|是否是主库(MAIN)|EQ|
|文档库名称(NAME)|LIKE|
|所属产品(PRODUCTNAME)|EQ|
|所属产品(PRODUCTNAME)|LIKE|
|所属项目(PROJECTNAME)|EQ|
|所属项目(PROJECTNAME)|LIKE|
|项目库(PROJECT)|EQ|
|产品库(PRODUCT)|EQ|

## 导入模式
无


## 导出模式
无
