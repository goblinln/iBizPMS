# 测试版本(ZT_TESTTASK)

  

## 关系
{% plantuml %}
测试版本 *-- Bug 
测试版本 *-- 测试运行 
版本 *-- 测试版本 
产品 *-- 测试版本 
项目 *-- 测试版本 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|结束日期|END|DATE|&nbsp;|
|开始日期|BEGIN|DATE|&nbsp;|
|负责人（选择）|OWNERPK|TEXT|&nbsp;|
|抄送给|MAILTO|SMCODELIST|&nbsp;|
|用例数|CASECNT|INT|&nbsp;|
|抄送给|MAILTOPK|SMCODELIST|&nbsp;|
|优先级|PRI|NSCODELIST|&nbsp;|
|备注|COMMENT|HTMLTEXT|&nbsp;|
|子状态|SUBSTATUS|TEXT|&nbsp;|
|report|REPORT|LONGTEXT|&nbsp;|
|描述|DESC|LONGTEXT|&nbsp;|
|编号|ID|ACID|&nbsp;|
|当前状态|STATUS|SSCODELIST|&nbsp;|
|联系人|MAILTOCONACT|TEXT|&nbsp;|
|负责人|OWNER|TEXT|&nbsp;|
|已删除|DELETED|TEXT|&nbsp;|
|auto|AUTO|TEXT|&nbsp;|
|名称|NAME|TEXT|&nbsp;|
|版本|BUILDNAME|PICKUPTEXT|&nbsp;|
|产品|PRODUCTNAME|PICKUPTEXT|&nbsp;|
|项目|PROJECTTNAME|PICKUPTEXT|&nbsp;|
|所属产品|PRODUCT|PICKUP|&nbsp;|
|版本|BUILD|PICKUP|&nbsp;|
|所属项目|PROJECT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|结束日期|结束日期必须大于等于开始日期|结束日期必须大于等于开始日期!|
|结束日期|默认规则|默认规则|
|开始日期|默认规则|默认规则|
|负责人（选择）|默认规则|内容长度必须小于等于[200]|
|抄送给|默认规则|内容长度必须小于等于[65535]|
|用例数|默认规则|默认规则|
|抄送给|默认规则|内容长度必须小于等于[65535]|
|优先级|默认规则|默认规则|
|备注|默认规则|内容长度必须小于等于[1048576]|
|子状态|默认规则|内容长度必须小于等于[30]|
|report|默认规则|内容长度必须小于等于[65535]|
|描述|默认规则|内容长度必须小于等于[65535]|
|编号|默认规则|默认规则|
|当前状态|默认规则|内容长度必须小于等于[7]|
|联系人|默认规则|内容长度必须小于等于[100]|
|负责人|默认规则|内容长度必须小于等于[30]|
|已删除|默认规则|内容长度必须小于等于[1]|
|auto|默认规则|内容长度必须小于等于[10]|
|名称|默认规则|内容长度必须小于等于[90]|
|版本|默认规则|内容长度必须小于等于[150]|
|产品|默认规则|内容长度必须小于等于[90]|
|项目|默认规则|内容长度必须小于等于[90]|
|所属产品|默认规则|默认规则|
|版本|默认规则|默认规则|
|所属项目|默认规则|默认规则|

## 状态控制

|当前状态|行为控制模式| 控制行为 | 操作标识控制模式 | 控制操作 |
| --------   | ------------|------------|------------|------------|
|Value| 允许|  | 不允许 | SRFUR__TESTT_START_BUT<br>SRFUR__TESTT_BLOCK_BUT<br> |
|Value| 允许|  | 不允许 | SRFUR__TESTT_ACTIVITE_BUT<br>SRFUR__TESTT_START_BUT<br> |
|Value| 允许|  | 不允许 | SRFUR__TESTT_CLOSED_BUT<br>SRFUR__TESTT_START_BUT<br>SRFUR__TESTT_BLOCK_BUT<br> |
|Value| 允许|  | 不允许 | SRFUR__TESTT_ACTIVITE_BUT<br> |

当前状态

| 状态        |    状态值   |
| --------   |------------|
|未开始|wait|
|进行中|doing|
|已完成|done|
|被阻塞|blocked|

## 行为
| 行为    | 类型    |  说明  |
| --------   |------------| ----- | 
|Create|内置方法|&nbsp;|
|Update|内置方法|&nbsp;|
|Remove|内置方法|&nbsp;|
|Get|内置方法|&nbsp;|
|GetDraft|内置方法|&nbsp;|
|激活|用户自定义|&nbsp;|
|阻塞|用户自定义|&nbsp;|
|CheckKey|内置方法|&nbsp;|
|关闭|用户自定义|&nbsp;|
|获取联系人|实体处理逻辑|&nbsp;|
|关联测试用例|用户自定义|&nbsp;|
|移动端测试版本计数器|实体处理逻辑|&nbsp;|
|Save|内置方法|&nbsp;|
|开始|用户自定义|&nbsp;|
|关联测试用例|用户自定义|&nbsp;|

## 处理逻辑
* 获取联系人 (GetCurUserConcat)
  
   

{% plantuml %}
hide footbox

测试版本 -> 测试版本: 获取参数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|获取参数 |
<center>获取联系人</center>
* 获取产品及版本 (GetProductBuild)
  
   

{% plantuml %}
hide footbox

测试版本 -> 测试版本: 获取版本
测试版本 -> 测试版本: 获取产品
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|获取版本 |
|1|开始 | 
|2|获取产品 |
<center>获取产品及版本</center>
* 移动端测试版本计数器 (MobTestTaskCounter)
  
   

{% plantuml %}
hide footbox

测试版本 -> 测试版本: 获取测试用例数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|获取测试用例数 |
<center>移动端测试版本计数器</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#TestTask_Default))|是|&nbsp;|
|MyTestTaskPc|我的测试单([MYSQL5](../../appendix/query_MYSQL5.md#TestTask_MyTestTaskPc))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#TestTask_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|MyTestTaskPc|我的测试单|MyTestTaskPc|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|优先级(PRI)|EQ|
|当前状态(STATUS)|EQ|
|当前状态(STATUS)|NOTEQ|
|负责人(OWNER)|EQ|
|名称(NAME)|LIKE|
|版本(BUILDNAME)|EQ|
|版本(BUILDNAME)|LIKE|
|产品(PRODUCTNAME)|EQ|
|产品(PRODUCTNAME)|LIKE|
|项目(PROJECTTNAME)|EQ|
|项目(PROJECTTNAME)|LIKE|
|所属产品(PRODUCT)|EQ|
|版本(BUILD)|EQ|
|所属项目(PROJECT)|EQ|

## 导入模式
* 数据导入

|report|负责人|产品|已删除|开始日期|auto|名称|当前状态|子状态|优先级|所属项目|版本|项目|结束日期|备注|描述|版本|编号|抄送给|所属产品|
| :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: |
| - | - | - | - | - | - | - | - | - | - | - | - | - | - | - | - | - | - | - | - |


## 导出模式
* 数据导出

|ID|名称|产品|项目|所属产品|所属项目|版本|版本|负责人|开始日期|结束日期|当前状态|
| :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: |
| - | - | - | - | - | - | - | - | - | - | - | - |
