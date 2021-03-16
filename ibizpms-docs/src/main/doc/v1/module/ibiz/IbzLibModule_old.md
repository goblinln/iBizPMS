# 用例库模块(IBZ_LIBMODULE)

  

## 关系
{% plantuml %}
用例库模块 *-- 测试用例 
用例库模块 *-- 用例库模块 
用例库模块 *-- 用例库模块 
用例库 *-- 用例库模块 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|逻辑删除标志|DELETED|TEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|branch|BRANCH|INT|&nbsp;|
|叶子模块|ISLEAF|TEXT|&nbsp;|
|类型（story）|TYPE|TEXT|&nbsp;|
|简称|SHORT|TEXT|&nbsp;|
|grade|GRADE|INT|&nbsp;|
|collector|COLLECTOR|LONGTEXT|&nbsp;|
|path|PATH|TEXT|&nbsp;|
|名称|NAME|TEXT|&nbsp;|
|owner|OWNER|TEXT|&nbsp;|
|排序值|ORDER|INT|&nbsp;|
|上级模块|PARENTNAME|PICKUPTEXT|&nbsp;|
|编号|ROOT|PICKUP|&nbsp;|
|id|PARENT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|逻辑删除标志|默认规则|内容长度必须小于等于[1]|
|id|默认规则|默认规则|
|branch|默认规则|默认规则|
|叶子模块|默认规则|内容长度必须小于等于[200]|
|类型（story）|默认规则|内容长度必须小于等于[30]|
|简称|默认规则|内容长度必须小于等于[30]|
|grade|默认规则|默认规则|
|collector|默认规则|内容长度必须小于等于[65535]|
|path|默认规则|内容长度必须小于等于[255]|
|名称|默认规则|内容长度必须小于等于[60]|
|owner|默认规则|内容长度必须小于等于[30]|
|排序值|默认规则|默认规则|
|上级模块|默认规则|内容长度必须小于等于[60]|
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

## 处理逻辑
* 重建模块路径 (FixPath)
  
   

{% plantuml %}
hide footbox

用例库模块 -> 用例库模块: 准备参数
用例库模块 -> 模块: 执行重建模块路径行为
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|准备参数 |
|2|执行重建模块路径行为 |
<center>重建模块路径</center>
* 删除模块 (RemoveModule)
  
   

{% plantuml %}
hide footbox

用例库模块 -> 用例库模块: 设置模块参数
用例库模块 -> 模块: 模块删除
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|设置模块参数 |
|2|模块删除 |
<center>删除模块</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#IbzLibModule_Default))|是|&nbsp;|
|Root_NoBranch|无枝叶([MYSQL5](../../appendix/query_MYSQL5.md#IbzLibModule_Root_NoBranch))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IbzLibModule_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|Root_NoBranch|无枝叶|Root_NoBranch|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|名称(NAME)|LIKE|
|编号(ROOT)|EQ|
|id(PARENT)|EQ|

## 导入模式
无


## 导出模式
无
