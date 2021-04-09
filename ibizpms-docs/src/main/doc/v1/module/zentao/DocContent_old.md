# 文档内容(ZT_DOCCONTENT)

  

## 关系
{% plantuml %}
文档 *-- 文档内容 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|附件|FILES|LONGTEXT|&nbsp;|
|编号|ID|ACID|&nbsp;|
|文档正文|CONTENT|LONGTEXT|&nbsp;|
|文档类型|TYPE|SSCODELIST|&nbsp;|
|文档标题|TITLE|TEXT|&nbsp;|
|版本号|VERSION|INT|&nbsp;|
|文档摘要|DIGEST|TEXT|&nbsp;|
|文档|DOC|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|附件|默认规则|内容长度必须小于等于[65535]|
|编号|默认规则|默认规则|
|文档正文|默认规则|内容长度必须小于等于[65535]|
|文档类型|默认规则|内容长度必须小于等于[10]|
|文档标题|默认规则|内容长度必须小于等于[255]|
|版本号|默认规则|默认规则|
|文档摘要|默认规则|内容长度必须小于等于[255]|
|文档|默认规则|默认规则|

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
|CurVersion|当前版本([MYSQL5](../../appendix/query_MYSQL5.md#DocContent_CurVersion))|否|&nbsp;|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#DocContent_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#DocContent_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|CurVersion|当前版本|CurVersion|否|&nbsp;|
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|文档标题(TITLE)|LIKE|
|文档(DOC)|EQ|

## 导入模式
无


## 导出模式
无
