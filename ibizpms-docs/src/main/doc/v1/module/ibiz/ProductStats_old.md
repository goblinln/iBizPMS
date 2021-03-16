# 产品统计(IBZ_PRODUCTSTATS)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|产品代号|CODE|TEXT|&nbsp;|
|产品编号|ID|ACID|&nbsp;|
|已完成的需求数|FINISHEDSTORYCNT|INT|&nbsp;|
|已发布需求数|RELEASEDSTORYCNT|INT|&nbsp;|
|严重bug比|IMPORTANTBUGPERCENT|TEXT|&nbsp;|
|未完成关联项目数|UNDONERESPROJECTCNT|INT|&nbsp;|
|指派给我的Bug数|ASSIGNTOMEBUGCNT|INT|&nbsp;|
|已删除|DELETED|TEXT|&nbsp;|
|是否置顶|ISTOP|INT|&nbsp;|
|关联项目数|RESPROJECTCNT|INT|&nbsp;|
|所有Bug数|BUGCNT|INT|&nbsp;|
|已消耗工时|HAVECONSUMED|FLOAT|&nbsp;|
|产品类型|TYPE|SSCODELIST|&nbsp;|
|未开始需求数|WAITSTORYCNT|INT|&nbsp;|
|需求所提bug数|BUGSTORY|INT|&nbsp;|
|需求总数|STORYCNT|INT|&nbsp;|
|产品名称|NAME|TEXT|&nbsp;|
|重要的Bug数|IMPORTANTBUGCNT|INT|&nbsp;|
|昨天关闭Bug数|YESTERDAYCLOSEDBUGCNT|INT|&nbsp;|
|状态|STATUS|SSCODELIST|&nbsp;|
|昨天解决Bug数|YESTERDAYRESOLVEDBUGCNT|INT|&nbsp;|
|开发中需求数|DEVELOPINGSTORYCNT|INT|&nbsp;|
|激活需求数|ACTIVESTORYCNT|INT|&nbsp;|
|未关闭Bug数|NOTCLOSEDBUGCNT|INT|&nbsp;|
|维护中发布数|NORMALRELEASECNT|INT|&nbsp;|
|产品排序|ORDER1|INT|&nbsp;|
|当前项目|CURRPROJECT|TEXT|&nbsp;|
|测试中需求数|TESTINGSTORYCNT|INT|&nbsp;|
|计划总数|PRODUCTPLANCNT|INT|&nbsp;|
|发布总数|RELEASECNT|INT|&nbsp;|
|昨天确认Bug数|YESTERDAYCONFIRMBUGCNT|INT|&nbsp;|
|已延期|POSTPONEDPROJECTCNT|INT|&nbsp;|
|未过期计划数|UNENDPRODUCTPLANCNT|INT|&nbsp;|
|解决Bug数|RESOLVEDBUGCNT|INT|&nbsp;|
|已计划需求数|PLANNEDSTORYCNT|INT|&nbsp;|
|未确认Bug数|UNCONFIRMBUGCNT|INT|&nbsp;|
|未解决Bug数|ACTIVEBUGCNT|INT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|产品代号|默认规则|内容长度必须小于等于[45]|
|产品编号|默认规则|默认规则|
|已完成的需求数|默认规则|默认规则|
|已发布需求数|默认规则|默认规则|
|严重bug比|默认规则|内容长度必须小于等于[100]|
|未完成关联项目数|默认规则|默认规则|
|指派给我的Bug数|默认规则|默认规则|
|已删除|默认规则|内容长度必须小于等于[1]|
|是否置顶|默认规则|默认规则|
|关联项目数|默认规则|默认规则|
|所有Bug数|默认规则|默认规则|
|已消耗工时|默认规则|默认规则|
|产品类型|默认规则|内容长度必须小于等于[60]|
|未开始需求数|默认规则|默认规则|
|需求所提bug数|默认规则|默认规则|
|需求总数|默认规则|默认规则|
|产品名称|默认规则|内容长度必须小于等于[90]|
|重要的Bug数|默认规则|默认规则|
|昨天关闭Bug数|默认规则|默认规则|
|状态|默认规则|内容长度必须小于等于[60]|
|昨天解决Bug数|默认规则|默认规则|
|开发中需求数|默认规则|默认规则|
|激活需求数|默认规则|默认规则|
|未关闭Bug数|默认规则|默认规则|
|维护中发布数|默认规则|默认规则|
|产品排序|默认规则|默认规则|
|当前项目|默认规则|内容长度必须小于等于[200]|
|测试中需求数|默认规则|默认规则|
|计划总数|默认规则|默认规则|
|发布总数|默认规则|默认规则|
|昨天确认Bug数|默认规则|默认规则|
|已延期|默认规则|默认规则|
|未过期计划数|默认规则|默认规则|
|解决Bug数|默认规则|默认规则|
|已计划需求数|默认规则|默认规则|
|未确认Bug数|默认规则|默认规则|
|未解决Bug数|默认规则|默认规则|

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
|获取测试统计详情|内置方法|&nbsp;|
|Save|内置方法|&nbsp;|

## 处理逻辑
* 获取当前用户的Bug数 (GetCurUserBugCnt)
  
   

{% plantuml %}
hide footbox

产品统计 -> 产品统计: 获取bug数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|获取bug数 |
<center>获取当前用户的Bug数</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#ProductStats_Default))|是|&nbsp;|
|NoOpenProduct|未关闭产品([MYSQL5](../../appendix/query_MYSQL5.md#ProductStats_NoOpenProduct))|否|&nbsp;|
|ProdctQuantiGird|产品质量表([MYSQL5](../../appendix/query_MYSQL5.md#ProductStats_ProdctQuantiGird))|否|&nbsp;|
|ProductInputTable|产品投入表([MYSQL5](../../appendix/query_MYSQL5.md#ProductStats_ProductInputTable))|否|&nbsp;|
|Productcompletionstatistics|产品完成统计表([MYSQL5](../../appendix/query_MYSQL5.md#ProductStats_Productcompletionstatistics))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#ProductStats_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|NoOpenProduct|未关闭产品|NoOpenProduct|否|&nbsp;|
|ProdctQuantiGird|产品质量表|ProdctQuantiGird|否|&nbsp;|
|ProductInputTable|产品投入表|ProductInputTable|否|&nbsp;|
|Productcompletionstatistics|产品完成统计表|Productcompletionstatistics|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|产品类型(TYPE)|EQ|
|产品名称(NAME)|LIKE|
|状态(STATUS)|EQ|

## 导入模式
无


## 导出模式
* 产品投入数据导出

|产品名称|关联项目数|已消耗工时|
| :------: | :------: | :------: |
| - | - | - |
* 产品质量数据导出

|产品名称|需求总数|已完成的需求数|所有Bug数|解决Bug数|需求所提bug数|重要的Bug数|严重bug比|
| :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: |
| - | - | - | - | - | - | - | - |
