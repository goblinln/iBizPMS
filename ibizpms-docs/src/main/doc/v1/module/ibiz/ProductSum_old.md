# 产品汇总表(IBZ_PRODUCTSUM)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|设计缺陷|DESIGNDEFECT|INT|&nbsp;|
|已变更|CHANGEDSTORYCNT|INT|&nbsp;|
|研发中阶段需求工时|DEVELOPINGSTAGESTORYHOURS|INT|&nbsp;|
|总计|STORYCNT|INT|&nbsp;|
|其他|OTHERS|INT|&nbsp;|
|配置相关|CONFIG|INT|&nbsp;|
|代码错误|CODEERROR|INT|&nbsp;|
|bug总计|BUGSUM|INT|&nbsp;|
|性能问题|PERFORMANCE|INT|&nbsp;|
|结束日期|END|TEXT|&nbsp;|
|已关闭|CLOSEDSTORYCNT|INT|&nbsp;|
|已关闭阶段需求工时|CLOSEDSTAGESTORYHOURS|INT|&nbsp;|
|测试完毕阶段需求工时|TESTEDSTAGESTORYHOURS|INT|&nbsp;|
|未开始阶段需求工时|WAITSTAGESTORYHOURS|INT|&nbsp;|
|产品负责人|PO|SSCODELIST|&nbsp;|
|测试中阶段需求工时|TESTINGSTAGESTORYHOURS|INT|&nbsp;|
|已立项阶段需求工时|PROJECTEDSTAGESTORYHOURS|INT|&nbsp;|
|已立项阶段需求数量|PROJECTEDSTAGESTORYCNT|INT|&nbsp;|
|已验收阶段需求数量|VERIFIEDSTAGESTORYCNT|INT|&nbsp;|
|主键标识|ID|ACID|&nbsp;|
|总工时|TOTALHOURS|INT|&nbsp;|
|已计划阶段需求数量|PLANEDSTAGESTORYCNT|INT|&nbsp;|
|研发中阶段需求数量|DEVELOPINGSTAGESTORYCNT|INT|&nbsp;|
|激活|ACTIVESTORYCNT|INT|&nbsp;|
|Bug数|BUGCNT|INT|&nbsp;|
|未开始阶段需求数量|WAITSTAGESTORYCNT|INT|&nbsp;|
|已验收阶段需求工时|VERIFIEDSTAGESTORYHOURS|INT|&nbsp;|
|草稿|WAITSTORYCNT|INT|&nbsp;|
|测试中阶段需求数量|TESTINGSTAGESTORYCNT|INT|&nbsp;|
|开始日期|BEGIN|TEXT|&nbsp;|
|计划|PLAN|BIGINT|&nbsp;|
|已发布阶段需求工时|RELEASEDSTAGESTORYHOURS|INT|&nbsp;|
|研发完毕阶段需求工时|DEVELOPEDSTAGESTORYHOURS|INT|&nbsp;|
|产品名称|NAME|TEXT|&nbsp;|
|研发完毕阶段需求数量|DEVELOPEDSTAGESTORYCNT|INT|&nbsp;|
|测试脚本|AUTOMATION|INT|&nbsp;|
|已计划阶段需求工时|PLANEDSTAGESTORYHOURS|INT|&nbsp;|
|安装部署|INSTALL|INT|&nbsp;|
|安全相关|SECURITY|INT|&nbsp;|
|已发布阶段需求数量|RELEASEDSTAGESTORYCNT|INT|&nbsp;|
|测试完毕阶段需求数量|TESTEDSTAGESTORYCNT|INT|&nbsp;|
|标准规范|STANDARD|INT|&nbsp;|
|已关闭阶段需求数量|CLOSEDSTAGESTORYCNT|INT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|设计缺陷|默认规则|默认规则|
|已变更|默认规则|默认规则|
|研发中阶段需求工时|默认规则|默认规则|
|总计|默认规则|默认规则|
|其他|默认规则|默认规则|
|配置相关|默认规则|默认规则|
|代码错误|默认规则|默认规则|
|bug总计|默认规则|默认规则|
|性能问题|默认规则|默认规则|
|结束日期|默认规则|内容长度必须小于等于[100]|
|已关闭|默认规则|默认规则|
|已关闭阶段需求工时|默认规则|默认规则|
|测试完毕阶段需求工时|默认规则|默认规则|
|未开始阶段需求工时|默认规则|默认规则|
|产品负责人|默认规则|内容长度必须小于等于[60]|
|测试中阶段需求工时|默认规则|默认规则|
|已立项阶段需求工时|默认规则|默认规则|
|已立项阶段需求数量|默认规则|默认规则|
|已验收阶段需求数量|默认规则|默认规则|
|主键标识|默认规则|默认规则|
|总工时|默认规则|默认规则|
|已计划阶段需求数量|默认规则|默认规则|
|研发中阶段需求数量|默认规则|默认规则|
|激活|默认规则|默认规则|
|Bug数|默认规则|默认规则|
|未开始阶段需求数量|默认规则|默认规则|
|已验收阶段需求工时|默认规则|默认规则|
|草稿|默认规则|默认规则|
|测试中阶段需求数量|默认规则|默认规则|
|开始日期|默认规则|内容长度必须小于等于[100]|
|计划|默认规则|默认规则|
|已发布阶段需求工时|默认规则|默认规则|
|研发完毕阶段需求工时|默认规则|默认规则|
|产品名称|默认规则|内容长度必须小于等于[100]|
|研发完毕阶段需求数量|默认规则|默认规则|
|测试脚本|默认规则|默认规则|
|已计划阶段需求工时|默认规则|默认规则|
|安装部署|默认规则|默认规则|
|安全相关|默认规则|默认规则|
|已发布阶段需求数量|默认规则|默认规则|
|测试完毕阶段需求数量|默认规则|默认规则|
|标准规范|默认规则|默认规则|
|已关闭阶段需求数量|默认规则|默认规则|

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
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#ProductSum_Default))|是|&nbsp;|
|ProductBugcnt_QA|产品创建bug数_测试人员([MYSQL5](../../appendix/query_MYSQL5.md#ProductSum_ProductBugcnt_QA))|否|&nbsp;|
|ProductCreateStory|产品创建需求占比([MYSQL5](../../appendix/query_MYSQL5.md#ProductSum_ProductCreateStory))|否|&nbsp;|
|ProductPlancntAndStorycnt_PO|产品计划数和需求数_产品经理([MYSQL5](../../appendix/query_MYSQL5.md#ProductSum_ProductPlancntAndStorycnt_PO))|否|&nbsp;|
|ProductStoryHoursSum|产品需求工时汇总([MYSQL5](../../appendix/query_MYSQL5.md#ProductSum_ProductStoryHoursSum))|否|&nbsp;|
|ProductStorySum|产品需求汇总查询([MYSQL5](../../appendix/query_MYSQL5.md#ProductSum_ProductStorySum))|否|&nbsp;|
|ProductSumBugType|产品Bug类型统计([MYSQL5](../../appendix/query_MYSQL5.md#ProductSum_ProductSumBugType))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#ProductSum_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|
|ProductBugcnt_QA|产品创建bug数及占比|ProductBugcnt_QA|否|&nbsp;|
|ProductCreateStory|产品创建需求占比|ProductCreateStory|否|&nbsp;|
|ProductStoryHoursSum|产品需求工时汇总|ProductStoryHoursSum|否|&nbsp;|
|ProductStorySum|产品需求汇总查询|ProductStorySum|否|&nbsp;|
|ProductStorycntAndPlancnt|产品计划数和需求数|ProductPlancntAndStorycnt_PO|否|&nbsp;|
|ProductSumBugType|产品Bug类型统计|ProductSumBugType|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|产品负责人(PO)|EQ|
|主键标识(ID)|EQ|
|计划(PLAN)|EQ|

## 导入模式
无


## 导出模式
无
