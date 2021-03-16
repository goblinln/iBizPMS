# 产品(ZT_PRODUCT)

  

## 关系
{% plantuml %}
产品 *-- 产品日报 
产品 *-- 产品月报 
产品 *-- 产品周报 
产品 *-- Bug统计 
产品 *-- 测试用例统计 
产品 *-- 需求模块 
产品 *-- 产品团队 
产品 *-- 测试模块 
产品 *-- 产品的分支和平台信息 
产品 *-- Bug 
产品 *-- 版本 
产品 *-- 测试用例 
产品 *-- 文档库 
产品 *-- 文档 
产品 *-- 产品计划 
产品 *-- 项目产品 
产品 *-- 项目中需要做的需求 
产品 *-- 发布 
产品 *-- 需求 
产品 *-- 套件用例 
产品 *-- 测试报告 
产品 *-- 测试套件 
产品 *-- 测试版本 
模块 *-- 产品 
索引检索 <|-- 产品 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|组织标识|ORGID|TEXT|&nbsp;|
|是否置顶|ISTOP|INT|&nbsp;|
|备注|COMMENT|HTMLTEXT|&nbsp;|
|测试负责人|QD|TEXT|&nbsp;|
|产品分类|PRODUCTCLASS|SSCODELIST|&nbsp;|
|未确认Bug数|UNCONFIRMBUGCNT|INT|&nbsp;|
|访问控制|ACL|SSCODELIST|&nbsp;|
|产品名称|NAME|TEXT|&nbsp;|
|移动端图片|MOBIMAGE|TEXT|&nbsp;|
|测试单数|TESTTASKCNT|INT|&nbsp;|
|套件数|TESTSUITECNT|INT|&nbsp;|
|计划总数|PRODUCTPLANCNT|INT|&nbsp;|
|编号|ID|ACID|&nbsp;|
|已删除|DELETED|TEXT|&nbsp;|
|已关闭需求|CLOSEDSTORYCNT|INT|&nbsp;|
|相关Bug数|RELATEDBUGCNT|INT|&nbsp;|
|分组白名单|WHITELIST|LONGTEXT|&nbsp;|
|部门标识|MDEPTID|TEXT|&nbsp;|
|发布总数|RELEASECNT|INT|&nbsp;|
|发布负责人|RD|TEXT|&nbsp;|
|产品负责人（选择）|POPK|TEXT|&nbsp;|
|未关闭Bug数|NOTCLOSEDBUGCNT|INT|&nbsp;|
|支持产品汇报|SUPPROREPORT|SSCODELIST|&nbsp;|
|排序|ORDER|INT|&nbsp;|
|产品类型|TYPE|SSCODELIST|&nbsp;|
|产品负责人|PO|TEXT|&nbsp;|
|测试负责人（选择）|QDPK|TEXT|&nbsp;|
|产品描述	|DESC|LONGTEXT|&nbsp;|
|状态|STATUS|SSCODELIST|&nbsp;|
|已变更需求|CHANGEDSTORYCNT|INT|&nbsp;|
|未解决Bug数|ACTIVEBUGCNT|INT|&nbsp;|
|由谁创建|CREATEDBY|TEXT|&nbsp;|
|发布负责人（选择）|RDPK|TEXT|&nbsp;|
|当前系统版本|CREATEDVERSION|TEXT|&nbsp;来自系统配置信息|
|草稿需求|DRAFTSTORYCNT|INT|&nbsp;|
|文档数|DOCCNT|INT|&nbsp;|
|用例数|CASECNT|INT|&nbsp;|
|关联项目数|RELATEDPROJECTS|INT|&nbsp;|
|IBIZ标识|IBIZ_ID|TEXT|&nbsp;|
|子状态|SUBSTATUS|TEXT|&nbsp;|
|产品代号|CODE|TEXT|&nbsp;|
|属性|SRFCOUNT|INT|&nbsp;|
|排序|ORDER1|INT|&nbsp;|
|BUILD数|BUILDCNT|INT|&nbsp;|
|创建日期|CREATEDDATE|DATETIME|&nbsp;|
|消息通知用户|NOTICEUSERS|TEXT|&nbsp;|
|激活需求数|ACTIVESTORYCNT|INT|&nbsp;|
|产品线|LINENAME|PICKUPTEXT|&nbsp;|
|产品线|LINE|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|组织标识|默认规则|内容长度必须小于等于[100]|
|是否置顶|默认规则|默认规则|
|备注|默认规则|内容长度必须小于等于[1048576]|
|测试负责人|默认规则|内容长度必须小于等于[30]|
|产品分类|默认规则|内容长度必须小于等于[10]|
|未确认Bug数|默认规则|默认规则|
|访问控制|默认规则|内容长度必须小于等于[7]|
|产品名称|产品名称重复值校验|『产品名称』已经有这条记录了。如果您确定该记录已删除，请到后台-数据-回收站还原。|
|产品名称|默认规则|内容长度必须小于等于[90]|
|移动端图片|默认规则|内容长度必须小于等于[4000]|
|测试单数|默认规则|默认规则|
|套件数|默认规则|默认规则|
|计划总数|默认规则|默认规则|
|编号|默认规则|默认规则|
|已删除|默认规则|内容长度必须小于等于[1]|
|已关闭需求|默认规则|默认规则|
|相关Bug数|默认规则|默认规则|
|分组白名单|默认规则|内容长度必须小于等于[65535]|
|部门标识|默认规则|内容长度必须小于等于[100]|
|发布总数|默认规则|默认规则|
|发布负责人|默认规则|内容长度必须小于等于[30]|
|产品负责人（选择）|默认规则|内容长度必须小于等于[200]|
|未关闭Bug数|默认规则|默认规则|
|支持产品汇报|默认规则|内容长度必须小于等于[200]|
|排序|默认规则|默认规则|
|产品类型|默认规则|内容长度必须小于等于[30]|
|产品负责人|默认规则|内容长度必须小于等于[30]|
|测试负责人（选择）|默认规则|内容长度必须小于等于[200]|
|产品描述	|默认规则|内容长度必须小于等于[65535]|
|状态|默认规则|内容长度必须小于等于[30]|
|已变更需求|默认规则|默认规则|
|未解决Bug数|默认规则|默认规则|
|由谁创建|默认规则|内容长度必须小于等于[30]|
|发布负责人（选择）|默认规则|内容长度必须小于等于[200]|
|当前系统版本|默认规则|内容长度必须小于等于[20]|
|草稿需求|默认规则|默认规则|
|文档数|默认规则|默认规则|
|用例数|默认规则|默认规则|
|关联项目数|默认规则|默认规则|
|IBIZ标识|默认规则|内容长度必须小于等于[100]|
|子状态|默认规则|内容长度必须小于等于[30]|
|产品代号|产品代号重复值校验|『产品代号』已经有这条记录了。如果您确定该记录已删除，请到后台-数据-回收站还原。|
|产品代号|默认规则|内容长度必须小于等于[45]|
|属性|默认规则|默认规则|
|排序|默认规则|默认规则|
|BUILD数|默认规则|默认规则|
|创建日期|默认规则|默认规则|
|消息通知用户|默认规则|内容长度必须小于等于[100]|
|激活需求数|默认规则|默认规则|
|产品线|默认规则|内容长度必须小于等于[60]|
|产品线|默认规则|默认规则|

## 状态控制

|状态||是否置顶|行为控制模式| 控制行为 | 操作标识控制模式 | 控制操作 |
| --------   || --------   | ------------|------------|------------|------------|
|Value||Value2| 允许| Update<br>Remove<br>Save<br>Get<br> | 不允许 | TOP<br>SRFUR__PROD_CLOSED_BUT<br> |
|Value||Value2| 允许|  | 不允许 | SRFUR__PROD_CLOSED_BUT<br>NOTOP<br> |
|Value||Value2| 允许| 关闭<br>Get<br>Update<br>Remove<br>Save<br> | 不允许 | TOP<br> |
|Value||Value2| 允许|  | 不允许 | NOTOP<br> |

状态

| 状态        |    状态值   |
| --------   |------------|
|正常|normal|
|结束|closed|
是否置顶


## 行为
| 行为    | 类型    |  说明  |
| --------   |------------| ----- | 
|Create|内置方法|&nbsp;|
|Update|内置方法|&nbsp;|
|Remove|内置方法|&nbsp;|
|Get|内置方法|&nbsp;|
|GetDraft|内置方法|&nbsp;|
|取消置顶|实体处理逻辑|&nbsp;|
|CheckKey|内置方法|&nbsp;|
|关闭|用户自定义|&nbsp;|
|产品移动端计数器方法|实体处理逻辑|&nbsp;|
|移动端测试计数器|实体处理逻辑|&nbsp;|
|置顶|实体处理逻辑|&nbsp;|
|退出|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|
|向导使用|用户自定义|&nbsp;|

## 处理逻辑
* 取消置顶 (CancelProductTop)
  
   

{% plantuml %}
hide footbox

产品 -> 产品: 删除置顶
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|删除置顶 |
<center>取消置顶</center>
* 移动端产品计数器 (MobProductCounter)
  
   

{% plantuml %}
hide footbox

产品 -> 产品: 获取相关BUG数
产品 -> 产品: 获取需求数
产品 -> 产品: 发布数
产品 -> 产品: 计划数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|获取相关BUG数 |
|2|获取需求数 |
|3|发布数 |
|4|计划数 |
<center>移动端产品计数器</center>
* 移动端测试计数器 (MobProductTestCounter)
  
   

{% plantuml %}
hide footbox

产品 -> 产品: 获取测试用例数
产品 -> 产品: 测试单数
产品 -> 产品: 套件数
产品 -> 产品: 获取相关BUG数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|获取测试用例数 |
|2|测试单数 |
|3|套件数 |
|3|开始 | 
|4|获取相关BUG数 |
<center>移动端测试计数器</center>
* 置顶 (ProductTop)
  
   

{% plantuml %}
hide footbox

产品 -> 置顶: save置顶
产品 -> 产品: 获取置顶最大排序值
产品 -> 产品: 设置置顶数据
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|save置顶 |
|1|开始 | 
|2|获取置顶最大排序值 |
|3|设置置顶数据 |
<center>置顶</center>
* 行为[Update]主状态拒绝逻辑 (Update__MSDeny)
  
   

{% plantuml %}
hide footbox

产品 -> 产品: 准备临时参数
产品 -> 产品: 获取数据
产品 -> : 状态[关闭_已置顶]拒绝[Update]操作
产品 -> : 状态[正常_已置顶]拒绝[Update]操作
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|准备临时参数 |
|2|获取数据 |
|3|状态[关闭_已置顶]拒绝[Update]操作 |
|4|状态[正常_已置顶]拒绝[Update]操作 |
<center>行为[Update]主状态拒绝逻辑</center>
* 行为[Remove]主状态拒绝逻辑 (Remove__MSDeny)
  
   

{% plantuml %}
hide footbox

产品 -> 产品: 准备临时参数
产品 -> 产品: 获取数据
产品 -> : 状态[关闭_已置顶]拒绝[Remove]操作
产品 -> : 状态[正常_已置顶]拒绝[Remove]操作
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|准备临时参数 |
|2|获取数据 |
|3|状态[关闭_已置顶]拒绝[Remove]操作 |
|4|状态[正常_已置顶]拒绝[Remove]操作 |
<center>行为[Remove]主状态拒绝逻辑</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|AllList|全部产品([MYSQL5](../../appendix/query_MYSQL5.md#Product_AllList))|否|&nbsp;用户（非管理员）相关的全部产品|
|AllProduct|所有产品([MYSQL5](../../appendix/query_MYSQL5.md#Product_AllProduct))|否|&nbsp;|
|CheckNameOrCode|校验产品名称或产品代号是否已经存在([MYSQL5](../../appendix/query_MYSQL5.md#Product_CheckNameOrCode))|否|&nbsp;|
|CURPROJECT|当前项目([MYSQL5](../../appendix/query_MYSQL5.md#Product_CurProject))|否|&nbsp;|
|CurUer|当前用户([MYSQL5](../../appendix/query_MYSQL5.md#Product_CurUer))|否|&nbsp;|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Product_Default))|是|&nbsp;|
|ESBulk|ES批量的导入([MYSQL5](../../appendix/query_MYSQL5.md#Product_ESBulk))|否|&nbsp;|
|ProductPM|产品总览([MYSQL5](../../appendix/query_MYSQL5.md#Product_ProductPM))|否|&nbsp;|
|ProductTeam|产品团队([MYSQL5](../../appendix/query_MYSQL5.md#Product_ProductTeam))|否|&nbsp;|
|StoryCURPROJECT|当前项目([MYSQL5](../../appendix/query_MYSQL5.md#Product_StoryCurProject))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Product_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|AllList|全部产品|AllList|否|&nbsp;|
|AllProduct|所有产品|AllProduct|否|&nbsp;|
|CheckNameOrCode|校验产品名称或产品代号是否已经存在|CheckNameOrCode|否|&nbsp;|
|CURPROJECT|当前项目|CURPROJECT|否|&nbsp;|
|CurUer|当前用户|CurUer|否|&nbsp;|
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|ESBulk|ES批量的导入|ESBulk|否|&nbsp;|
|ProductPM|产品总览|ProductPM|否|&nbsp;|
|ProductTeam|产品团队|ProductTeam|否|&nbsp;|
|StoryCURPROJECT|当前项目|StoryCURPROJECT|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|产品分类(PRODUCTCLASS)|EQ|
|访问控制(ACL)|EQ|
|产品名称(NAME)|EQ|
|产品名称(NAME)|LIKE|
|编号(ID)|EQ|
|编号(ID)|IN|
|产品类型(TYPE)|EQ|
|状态(STATUS)|EQ|
|状态(STATUS)|ISNOTNULL|
|产品线(LINENAME)|EQ|
|产品线(LINENAME)|LIKE|
|产品线(LINE)|EQ|

## 导入模式
无


## 导出模式
无
