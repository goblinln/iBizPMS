# job(ZT_JOB)

  

## 关系
{% plantuml %}
job *-- 测试结果 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|逻辑删除标志|DELETED|TEXT|&nbsp;|
|editedDate|EDITEDDATE|DATETIME|&nbsp;|
|product|PRODUCT|INT|&nbsp;|
|lastStatus|LASTSTATUS|TEXT|&nbsp;|
|createdDate|CREATEDDATE|DATETIME|&nbsp;|
|createdBy|CREATEDBY|TEXT|&nbsp;|
|triggerType|TRIGGERTYPE|TEXT|&nbsp;|
|comment|COMMENT|TEXT|&nbsp;|
|lastExec|LASTEXEC|DATETIME|&nbsp;|
|jkHost|JKHOST|INT|&nbsp;|
|frame|FRAME|TEXT|&nbsp;|
|atDay|ATDAY|TEXT|&nbsp;|
|jkJob|JKJOB|TEXT|&nbsp;|
|svnDir|SVNDIR|TEXT|&nbsp;|
|repo|REPO|INT|&nbsp;|
|name|NAME|TEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|lastTag|LASTTAG|TEXT|&nbsp;|
|atTime|ATTIME|TEXT|&nbsp;|
|editedBy|EDITEDBY|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|逻辑删除标志|默认规则|内容长度必须小于等于[1]|
|editedDate|默认规则|默认规则|
|product|默认规则|默认规则|
|lastStatus|默认规则|内容长度必须小于等于[255]|
|createdDate|默认规则|默认规则|
|createdBy|默认规则|内容长度必须小于等于[30]|
|triggerType|默认规则|内容长度必须小于等于[255]|
|comment|默认规则|内容长度必须小于等于[255]|
|lastExec|默认规则|默认规则|
|jkHost|默认规则|默认规则|
|frame|默认规则|内容长度必须小于等于[20]|
|atDay|默认规则|内容长度必须小于等于[255]|
|jkJob|默认规则|内容长度必须小于等于[500]|
|svnDir|默认规则|内容长度必须小于等于[255]|
|repo|默认规则|默认规则|
|name|默认规则|内容长度必须小于等于[50]|
|id|默认规则|默认规则|
|lastTag|默认规则|内容长度必须小于等于[255]|
|atTime|默认规则|内容长度必须小于等于[10]|
|editedBy|默认规则|内容长度必须小于等于[30]|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Job_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Job_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|name(NAME)|LIKE|

## 导入模式
无


## 导出模式
无
