# 待办(ZT_TODO)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|编号|ID|ACID|&nbsp;|
|所有者|ACCOUNT|TEXT|&nbsp;|
|间隔天数|CONFIG_DAY|INT|&nbsp;|
|指派给（选择）|ASSIGNEDTOPK|TEXT|&nbsp;|
|日期|DATE1|TEXT|&nbsp;|
|周期类型|CONFIG_TYPE|SSCODELIST|&nbsp;|
|关闭时间|CLOSEDDATE|DATETIME|&nbsp;|
|由谁关闭|CLOSEDBY|TEXT|&nbsp;|
|类型|TYPE|TEXT|&nbsp;|
|结束|END|NSCODELIST|&nbsp;|
|描述|DESC|LONGTEXT|&nbsp;|
|消息通知用户|NOTICEUSERS|TEXT|&nbsp;|
|由谁完成|FINISHEDBY|TEXT|&nbsp;|
|开始|BEGIN|NSCODELIST|&nbsp;|
|关联编号|IDVALUE|BIGINT|&nbsp;|
|由谁指派|ASSIGNEDBY|TEXT|&nbsp;|
|周期设置月|CONFIG_MONTH|SMCODELIST|&nbsp;|
|待办名称|TASK|TEXT|&nbsp;|
|待办名称|BUG|TEXT|&nbsp;|
|完成时间|FINISHEDDATE|DATETIME|&nbsp;|
|周期|CYCLE|INT|&nbsp;|
|待定|DATE_DISABLE|TEXT|&nbsp;|
|周期设置周几|CONFIG_WEEK|SMCODELIST|&nbsp;|
|指派给|ASSIGNEDTO|TEXT|&nbsp;|
|状态|STATUS|SSCODELIST|&nbsp;|
|提前|CONFIG_BEFOREDAYS|INT|&nbsp;|
|待办名称|NAME|TEXT|&nbsp;|
|指派日期|ASSIGNEDDATE|DATE|&nbsp;|
|过期时间|CONFIG_END|DATE|&nbsp;|
|费用|COST|INT|&nbsp;|
|优先级|PRI|INT|&nbsp;|
|日期|DATE|DATE|&nbsp;|
|待办名称|STORY|TEXT|&nbsp;|
|私人事务|PRIVATE|SMCODELIST|&nbsp;|
|config|CONFIG|TEXT|&nbsp;|
|工时|CONSUMED|FLOAT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|编号|默认规则|默认规则|
|所有者|默认规则|内容长度必须小于等于[30]|
|间隔天数|默认规则|默认规则|
|指派给（选择）|默认规则|内容长度必须小于等于[200]|
|日期|默认规则|内容长度必须小于等于[200]|
|周期类型|默认规则|内容长度必须小于等于[200]|
|关闭时间|默认规则|默认规则|
|由谁关闭|默认规则|内容长度必须小于等于[30]|
|类型|默认规则|内容长度必须小于等于[10]|
|结束|默认规则|默认规则|
|描述|默认规则|内容长度必须小于等于[65535]|
|消息通知用户|默认规则|内容长度必须小于等于[100]|
|由谁完成|默认规则|内容长度必须小于等于[30]|
|开始|默认规则|默认规则|
|关联编号|默认规则|默认规则|
|由谁指派|默认规则|内容长度必须小于等于[30]|
|周期设置月|默认规则|内容长度必须小于等于[4000]|
|待办名称|默认规则|内容长度必须小于等于[100]|
|待办名称|默认规则|内容长度必须小于等于[100]|
|完成时间|默认规则|默认规则|
|周期|默认规则|默认规则|
|待定|默认规则|内容长度必须小于等于[100]|
|周期设置周几|默认规则|内容长度必须小于等于[4000]|
|指派给|默认规则|内容长度必须小于等于[30]|
|状态|默认规则|内容长度必须小于等于[6]|
|提前|默认规则|默认规则|
|待办名称|默认规则|内容长度必须小于等于[150]|
|指派日期|默认规则|默认规则|
|过期时间|默认规则|默认规则|
|费用|默认规则|默认规则|
|优先级|默认规则|默认规则|
|日期|默认规则|默认规则|
|待办名称|默认规则|内容长度必须小于等于[100]|
|私人事务|默认规则|内容长度必须小于等于[4000]|
|config|默认规则|内容长度必须小于等于[255]|
|工时|默认规则|默认规则|

## 状态控制

|状态|行为控制模式| 控制行为 | 操作标识控制模式 | 控制操作 |
| --------   | ------------|------------|------------|------------|
|Value| 允许|  | 允许 | UPDATE<br>DELETE<br>ACTIVATE<br> |
|Value| 允许|  | 允许 | FINISH<br>ASSIGNTO<br>DELETE<br>TOTASK<br>TOBUG<br>UPDATE<br> |
|Value| 允许|  | 允许 | DELETE<br>CLOSE<br>UPDATE<br>ACTIVATE<br> |
|Value| 允许|  | 允许 | DELETE<br>FINISH<br>UPDATE<br>TOBUG<br>ASSIGNTO<br>TOTASK<br> |

状态

| 状态        |    状态值   |
| --------   |------------|
|未开始|wait|
|进行中|doing|
|已完成|done|
|已关闭|closed|

## 行为
| 行为    | 类型    |  说明  |
| --------   |------------| ----- | 
|Create|内置方法|&nbsp;|
|Update|内置方法|&nbsp;|
|Remove|内置方法|&nbsp;|
|Get|内置方法|&nbsp;|
|GetDraft|内置方法|&nbsp;|
|Activate|用户自定义|&nbsp;|
|AssignTo|用户自定义|&nbsp;|
|CheckKey|内置方法|&nbsp;|
|Close|用户自定义|&nbsp;|
|定时创建周期|用户自定义|&nbsp;|
|Finish|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|
|行为|用户自定义|&nbsp;|
|发送消息前置处理|用户自定义|&nbsp;|

## 处理逻辑
* 获取待办名称 (GetTODOTitle)
  
   

{% plantuml %}
hide footbox

待办 -> 待办: 获取待办的标题
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|获取待办的标题 |
<center>获取待办名称</center>
* 重置开始日期和结束日期 (ResetBeginEnd)
  
   

{% plantuml %}
hide footbox

待办 -> 待办: 获取开始日期
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|获取开始日期 |
<center>重置开始日期和结束日期</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Todo_Default))|是|&nbsp;|
|MyTodo|我的待办([MYSQL5](../../appendix/query_MYSQL5.md#Todo_MyTodo))|否|&nbsp;|
|MyTodoPc|我的待办([MYSQL5](../../appendix/query_MYSQL5.md#Todo_MyTodoPc))|否|&nbsp;|
|MyUpcoming|MyUpcoming([MYSQL5](../../appendix/query_MYSQL5.md#Todo_MyUpcoming))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Todo_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|MyTodo|我的待办|MyTodo|否|&nbsp;|
|MyTodoPc|我的待办|MyTodoPc|否|&nbsp;|
|MyUpcoming|MyUpcoming|MyUpcoming|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|所有者(ACCOUNT)|EQ|
|周期类型(CONFIG_TYPE)|EQ|
|类型(TYPE)|EQ|
|类型(TYPE)|NOTEQ|
|结束(END)|EQ|
|开始(BEGIN)|EQ|
|周期(CYCLE)|EQ|
|状态(STATUS)|EQ|
|状态(STATUS)|NOTEQ|
|待办名称(NAME)|LIKE|
|日期(DATE)|EQ|
|日期(DATE)|GTANDEQ|
|日期(DATE)|LTANDEQ|
|日期(DATE)|NOTEQ|
|私人事务(PRIVATE)|EQ|

## 导入模式
无


## 导出模式
* 数据导出

|编号|日期|类型|优先级|待办名称|开始|结束|状态|
| :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: |
| - | - | - | - | - | - | - | - |
