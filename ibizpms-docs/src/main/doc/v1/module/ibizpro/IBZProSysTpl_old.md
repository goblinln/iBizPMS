# 系统模板(IBZPRO_SYSTPL)

  

## 关系
{% plantuml %}
附件 *-- 系统模板 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|模板内容|CONTENT|LONGTEXT|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|系统模板标识|IBZPRO_SYSTPLID|GUID|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|是否公开|PUBLIC|SSCODELIST|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|系统模板名称|IBZPRO_SYSTPLNAME|TEXT|&nbsp;|
|IBIZ模板类型|TPLTYPE|SSCODELIST|&nbsp;|
|来源对象|IBIZ_SOURCEOBJECT|SSCODELIST|&nbsp;|
|id|FILE|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|模板内容|默认规则|内容长度必须小于等于[65535]|
|建立人|默认规则|内容长度必须小于等于[60]|
|系统模板标识|默认规则|内容长度必须小于等于[100]|
|建立时间|默认规则|默认规则|
|是否公开|默认规则|内容长度必须小于等于[1]|
|更新人|默认规则|内容长度必须小于等于[60]|
|更新时间|默认规则|默认规则|
|系统模板名称|默认规则|内容长度必须小于等于[200]|
|IBIZ模板类型|默认规则|内容长度必须小于等于[60]|
|来源对象|默认规则|内容长度必须小于等于[100]|
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
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#IBZProSysTpl_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IBZProSysTpl_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|是否公开(PUBLIC)|EQ|
|系统模板名称(IBZPRO_SYSTPLNAME)|LIKE|
|IBIZ模板类型(TPLTYPE)|EQ|
|来源对象(IBIZ_SOURCEOBJECT)|EQ|
|id(FILE)|EQ|

## 导入模式
无


## 导出模式
无
