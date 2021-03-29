# 部门(ZT_DEPT)

  

## 关系
{% plantuml %}
部门 *-- 部门 
部门 *-- 部门 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|负责人|MANAGER|SSCODELIST|&nbsp;|
|无子部门|ISLEAF|TEXT|&nbsp;|
|grade|GRADE|INT|&nbsp;|
|function|FUNCTION|TEXT|&nbsp;|
|order|ORDER|INT|&nbsp;|
|path|PATH|TEXT|&nbsp;|
|position|POSITION|TEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|部门名称|NAME|TEXT|&nbsp;|
|上级部门|PARENTNAME|PICKUPTEXT|&nbsp;|
|parent|PARENT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|负责人|默认规则|内容长度必须小于等于[30]|
|无子部门|默认规则|内容长度必须小于等于[200]|
|grade|默认规则|默认规则|
|function|默认规则|内容长度必须小于等于[255]|
|order|默认规则|默认规则|
|path|默认规则|内容长度必须小于等于[255]|
|position|默认规则|内容长度必须小于等于[30]|
|id|默认规则|默认规则|
|部门名称|默认规则|内容长度必须小于等于[60]|
|上级部门|默认规则|内容长度必须小于等于[60]|
|parent|默认规则|默认规则|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Dept_Default))|是|&nbsp;|
|ROOT|根部门([MYSQL5](../../appendix/query_MYSQL5.md#Dept_Root))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Dept_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|ROOT|根部门|ROOT|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|部门名称(NAME)|LIKE|
|上级部门(PARENTNAME)|EQ|
|上级部门(PARENTNAME)|LIKE|
|parent(PARENT)|EQ|

## 导入模式
无


## 导出模式
无
