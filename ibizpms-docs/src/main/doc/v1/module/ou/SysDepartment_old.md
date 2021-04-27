# 部门(SYS_DEPT)

  

## 关系
{% plantuml %}
部门 *-- 人员 
部门 *-- 部门 
部门 *-- 部门 
单位 *-- 部门 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|部门标识|DEPTID|TEXT|&nbsp;|
|部门代码|DEPTCODE|TEXT|&nbsp;|
|部门名称|DEPTNAME|TEXT|&nbsp;|
|单位|ORGID|PICKUP|&nbsp;|
|上级部门|PDEPTID|PICKUP|&nbsp;|
|部门简称|SHORTNAME|TEXT|&nbsp;|
|部门级别|DEPTLEVEL|INT|&nbsp;|
|区属|DOMAINS|TEXT|&nbsp;|
|排序|SHOWORDER|INT|&nbsp;|
|业务编码|BCODE|TEXT|&nbsp;|
|分管领导标识|LEADERID|TEXT|&nbsp;|
|分管领导|LEADERNAME|TEXT|&nbsp;|
|单位|ORGNAME|PICKUPTEXT|&nbsp;|
|上级部门|PDEPTNAME|PICKUPTEXT|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|逻辑有效标志|ENABLE|YESNO|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|部门标识|默认规则|内容长度必须小于等于[100]|
|部门代码|默认规则|内容长度必须小于等于[100]|
|部门名称|默认规则|内容长度必须小于等于[100]|
|单位|默认规则|内容长度必须小于等于[100]|
|上级部门|默认规则|内容长度必须小于等于[100]|
|部门简称|默认规则|内容长度必须小于等于[100]|
|部门级别|默认规则|默认规则|
|区属|默认规则|内容长度必须小于等于[100]|
|排序|默认规则|默认规则|
|业务编码|默认规则|内容长度必须小于等于[100]|
|分管领导标识|默认规则|内容长度必须小于等于[100]|
|分管领导|默认规则|内容长度必须小于等于[100]|
|单位|默认规则|内容长度必须小于等于[100]|
|上级部门|默认规则|内容长度必须小于等于[100]|
|建立时间|默认规则|默认规则|
|逻辑有效标志|默认规则|默认规则|
|更新人|默认规则|内容长度必须小于等于[60]|
|更新时间|默认规则|默认规则|
|建立人|默认规则|内容长度必须小于等于[60]|

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
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#SysDepartment_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#SysDepartment_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|部门标识(DEPTID)|EQ|
|部门名称(DEPTNAME)|LIKE|
|单位(ORGID)|EQ|
|上级部门(PDEPTID)|EQ|
|单位(ORGNAME)|EQ|
|单位(ORGNAME)|LIKE|
|上级部门(PDEPTNAME)|EQ|
|上级部门(PDEPTNAME)|LIKE|

## 导入模式
无


## 导出模式
无
