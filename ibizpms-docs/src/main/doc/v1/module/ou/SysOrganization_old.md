# 单位(SYS_ORG)

  

## 关系
{% plantuml %}
单位 *-- 人员 
单位 *-- 部门 
单位 *-- 单位 
单位 *-- 单位 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|单位标识|ORGID|TEXT|&nbsp;|
|单位代码|ORGCODE|TEXT|&nbsp;|
|名称|ORGNAME|TEXT|&nbsp;|
|上级单位|PORGID|PICKUP|&nbsp;|
|单位简称|SHORTNAME|TEXT|&nbsp;|
|单位级别|ORGLEVEL|INT|&nbsp;|
|排序|SHOWORDER|INT|&nbsp;|
|上级单位|PORGNAME|PICKUPTEXT|&nbsp;|
|区属|DOMAINS|TEXT|&nbsp;|
|逻辑有效|ENABLE|YESNO|&nbsp;|
|创建时间|CREATEDATE|DATETIME|&nbsp;|
|最后修改时间|UPDATEDATE|DATETIME|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|单位标识|默认规则|内容长度必须小于等于[100]|
|单位代码|默认规则|内容长度必须小于等于[100]|
|名称|默认规则|内容长度必须小于等于[100]|
|上级单位|默认规则|内容长度必须小于等于[100]|
|单位简称|默认规则|内容长度必须小于等于[100]|
|单位级别|默认规则|默认规则|
|排序|默认规则|默认规则|
|上级单位|默认规则|内容长度必须小于等于[100]|
|区属|默认规则|内容长度必须小于等于[100]|
|逻辑有效|默认规则|默认规则|
|创建时间|默认规则|默认规则|
|最后修改时间|默认规则|默认规则|

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
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#SysOrganization_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#SysOrganization_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|名称(ORGNAME)|LIKE|
|上级单位(PORGID)|EQ|
|上级单位(PORGNAME)|EQ|
|上级单位(PORGNAME)|LIKE|

## 导入模式
无


## 导出模式
无
