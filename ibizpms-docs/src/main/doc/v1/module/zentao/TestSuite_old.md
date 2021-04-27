# 测试套件(ZT_TESTSUITE)

  

## 关系
{% plantuml %}
测试套件 *-- 测试用例 
测试套件 *-- 套件用例 
产品 *-- 测试套件 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|名称|NAME|TEXT|&nbsp;|
|创建时间|ADDEDDATE|DATETIME|&nbsp;|
|编号|ID|ACID|&nbsp;|
|已删除|DELETED|TEXT|&nbsp;|
|最后编辑人|LASTEDITEDBY|TEXT|&nbsp;|
|类型|TYPE|SSCODELIST|&nbsp;|
|最后编辑时间|LASTEDITEDDATE|DATETIME|&nbsp;|
|由谁创建|ADDEDBY|TEXT|&nbsp;|
|用例数|CASECNT|INT|&nbsp;|
|描述|DESC|LONGTEXT|&nbsp;|
|所属产品|PRODUCT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|名称|默认规则|内容长度必须小于等于[255]|
|创建时间|默认规则|默认规则|
|编号|默认规则|默认规则|
|已删除|默认规则|内容长度必须小于等于[1]|
|最后编辑人|默认规则|内容长度必须小于等于[30]|
|类型|默认规则|内容长度必须小于等于[20]|
|最后编辑时间|默认规则|默认规则|
|由谁创建|默认规则|内容长度必须小于等于[30]|
|用例数|默认规则|默认规则|
|描述|默认规则|内容长度必须小于等于[65535]|
|所属产品|默认规则|默认规则|

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
|移动端测试套件计数器|实体处理逻辑|&nbsp;|
|Save|内置方法|&nbsp;|

## 处理逻辑
* 移动端测试套件计数器 (MobTestSuiteCount)
  
   

{% plantuml %}
hide footbox

测试套件 -> 测试套件: 查询测试用例数
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|查询测试用例数 |
<center>移动端测试套件计数器</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#TestSuite_Default))|是|&nbsp;|
|PublicTestSuite|公开套件([MYSQL5](../../appendix/query_MYSQL5.md#TestSuite_PublicTestSuite))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#TestSuite_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|PublicTestSuite|公开套件|PublicTestSuite|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|名称(NAME)|LIKE|
|编号(ID)|EQ|
|类型(TYPE)|EQ|
|所属产品(PRODUCT)|EQ|

## 导入模式
无


## 导出模式
* 数据导出

|编号|名称|描述|由谁创建|创建时间|
| :------: | :------: | :------: | :------: | :------: |
| - | - | - | - | - |
