# 文档(IBZ_DOC)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|文档标识|IBZ_DOCID|GUID|&nbsp;|
|由谁添加|ADDEDBY|TEXT|&nbsp;|
|由谁更新|EDITEDBY|TEXT|&nbsp;|
|添加时间|ADDEDDATE|TEXT|&nbsp;|
|大小|SIZE|TEXT|&nbsp;文档中所有附件大小之和（数据库存入值单位为Byte）|
|所属文档库|LIB|TEXT|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|文档名称|IBZ_DOCNAME|TEXT|&nbsp;|
|排序|ORDER|TEXT|&nbsp;|
|是否已收藏|ISCOLLECT|YESNO|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|更新时间|EDITEDDATE|TEXT|&nbsp;|
|对象类型|OBJECTTYPE|TEXT|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|文档标识|默认规则|内容长度必须小于等于[100]|
|由谁添加|默认规则|内容长度必须小于等于[100]|
|由谁更新|默认规则|内容长度必须小于等于[100]|
|添加时间|默认规则|内容长度必须小于等于[100]|
|大小|默认规则|内容长度必须小于等于[100]|
|所属文档库|默认规则|内容长度必须小于等于[100]|
|建立时间|默认规则|默认规则|
|文档名称|默认规则|内容长度必须小于等于[200]|
|排序|默认规则|内容长度必须小于等于[100]|
|是否已收藏|默认规则|默认规则|
|建立人|默认规则|内容长度必须小于等于[60]|
|更新时间|默认规则|默认规则|
|更新时间|默认规则|内容长度必须小于等于[100]|
|对象类型|默认规则|内容长度必须小于等于[100]|
|更新人|默认规则|内容长度必须小于等于[60]|

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
|AllDoc|产品下所有文档（用户）([MYSQL5](../../appendix/query_MYSQL5.md#IBzDoc_AllDoc))|否|&nbsp;用户所能查看的指定产品下所有的文档|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#IBzDoc_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IBzDoc_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|文档名称(IBZ_DOCNAME)|LIKE|

## 导入模式
无


## 导出模式
无
