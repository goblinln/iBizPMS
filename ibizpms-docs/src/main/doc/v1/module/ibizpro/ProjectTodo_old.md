# 项目其他活动(PROJECTTODO)

  

## 关系
{% plantuml %}
项目 *-- 项目其他活动 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|编号|ID|ACID|&nbsp;|
|所有者|ACCOUNT|TEXT|&nbsp;|
|私人事务|PRIVATE|SMCODELIST|&nbsp;|
|状态|STATUS|SSCODELIST|&nbsp;|
|类型|TYPE|TEXT|&nbsp;|
|完成时间|FINISHEDDATE|DATETIME|&nbsp;|
|优先级|PRI|INT|&nbsp;|
|config|CONFIG|TEXT|&nbsp;|
|由谁指派|ASSIGNEDBY|TEXT|&nbsp;|
|指派日期|ASSIGNEDDATE|DATE|&nbsp;|
|关闭时间|CLOSEDDATE|DATETIME|&nbsp;|
|指派给|ASSIGNEDTO|TEXT|&nbsp;|
|日期|DATE|DATE|&nbsp;|
|由谁完成|FINISHEDBY|TEXT|&nbsp;|
|待办名称|NAME|TEXT|&nbsp;|
|费用|COST|INT|&nbsp;|
|由谁关闭|CLOSEDBY|TEXT|&nbsp;|
|描述|DESC|LONGTEXT|&nbsp;|
|项目编号|IDVALUE|PICKUP|&nbsp;|
|工时|CONSUMED|FLOAT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|编号|默认规则|默认规则|
|所有者|默认规则|内容长度必须小于等于[30]|
|私人事务|默认规则|内容长度必须小于等于[2000]|
|状态|默认规则|内容长度必须小于等于[6]|
|类型|默认规则|内容长度必须小于等于[10]|
|完成时间|默认规则|默认规则|
|优先级|默认规则|默认规则|
|config|默认规则|内容长度必须小于等于[255]|
|由谁指派|默认规则|内容长度必须小于等于[30]|
|指派日期|默认规则|默认规则|
|关闭时间|默认规则|默认规则|
|指派给|默认规则|内容长度必须小于等于[30]|
|日期|默认规则|默认规则|
|由谁完成|默认规则|内容长度必须小于等于[30]|
|待办名称|默认规则|内容长度必须小于等于[150]|
|费用|默认规则|默认规则|
|由谁关闭|默认规则|内容长度必须小于等于[30]|
|描述|默认规则|内容长度必须小于等于[65535]|
|项目编号|默认规则|默认规则|
|工时|默认规则|默认规则|

## 状态控制

|状态|行为控制模式| 控制行为 | 操作标识控制模式 | 控制操作 |
| --------   | ------------|------------|------------|------------|
|Value| 允许|  | 允许 | DELETE<br>UPDATE<br>ACTIVATE<br> |
|Value| 允许|  | 允许 | DELETE<br>TOTASK<br>FINISH<br>TOBUG<br>UPDATE<br>ASSIGNTO<br> |
|Value| 允许|  | 允许 | UPDATE<br>DELETE<br>CLOSE<br>ACTIVATE<br> |
|Value| 允许|  | 允许 | UPDATE<br>TOTASK<br>TOBUG<br>FINISH<br>DELETE<br>ASSIGNTO<br> |

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
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#ProjectTodo_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#ProjectTodo_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|状态(STATUS)|EQ|
|待办名称(NAME)|LIKE|
|项目编号(IDVALUE)|EQ|

## 导入模式
无


## 导出模式
无
