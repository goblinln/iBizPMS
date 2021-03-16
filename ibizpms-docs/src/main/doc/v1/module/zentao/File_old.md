# 附件(ZT_FILE)

  

## 关系
{% plantuml %}
附件 *-- 系统模板 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|路径|PATHNAME|TEXT|&nbsp;|
|对象ID|OBJECTID|BIGINT|&nbsp;|
|显示大小|STRSIZE|TEXT|&nbsp;|
|文档类型|DOCLIBTYPE|TEXT|&nbsp;|
|已删除|DELETED|TEXT|&nbsp;|
|文件类型|EXTENSION|TEXT|&nbsp;|
|对象类型|OBJECTTYPE|SSCODELIST|&nbsp;|
|由谁添加|ADDEDBY|TEXT|&nbsp;|
|标题|TITLE|TEXT|&nbsp;|
|添加时间|ADDEDDATE|DATETIME|&nbsp;|
|下载次数|DOWNLOADS|INT|&nbsp;|
|大小|SIZE|INT|&nbsp;|
|id|ID|ACID|&nbsp;|
|备注|EXTRA|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|路径|默认规则|内容长度必须小于等于[100]|
|对象ID|默认规则|默认规则|
|显示大小|默认规则|内容长度必须小于等于[200]|
|文档类型|默认规则|内容长度必须小于等于[100]|
|已删除|默认规则|内容长度必须小于等于[1]|
|文件类型|默认规则|内容长度必须小于等于[30]|
|对象类型|默认规则|内容长度必须小于等于[30]|
|由谁添加|默认规则|内容长度必须小于等于[30]|
|标题|默认规则|内容长度必须小于等于[255]|
|添加时间|默认规则|默认规则|
|下载次数|默认规则|默认规则|
|大小|默认规则|默认规则|
|id|默认规则|默认规则|
|备注|默认规则|内容长度必须小于等于[255]|

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
|下载|用户自定义|&nbsp;|
|预览|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|
|保存附件|用户自定义|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#File_Default))|是|&nbsp;|
|DocLibFile|文件库查询([MYSQL5](../../appendix/query_MYSQL5.md#File_DocLibFile))|否|&nbsp;|
|ProductDocLibFile|文件库查询([MYSQL5](../../appendix/query_MYSQL5.md#File_ProductDocLibFile))|否|&nbsp;|
|Type|动态(根据类型过滤)([MYSQL5](../../appendix/query_MYSQL5.md#File_Type))|否|&nbsp;过滤编辑器中上传的图片|
|TypeNotBySrfparentkey|查询附件([MYSQL5](../../appendix/query_MYSQL5.md#File_TypeNotBySrfparentkey))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#File_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|DocLibFile|文件库查询|DocLibFile|否|&nbsp;|
|ProductDocLibFile|文件库查询|ProductDocLibFile|否|&nbsp;|
|Type|动态(根据类型过滤)|Type|否|&nbsp;|
|TypeNotBySrfparentkey|查询附件|TypeNotBySrfparentkey|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|对象ID(OBJECTID)|EQ|
|对象类型(OBJECTTYPE)|EQ|
|标题(TITLE)|LIKE|
|备注(EXTRA)|EQ|
|备注(EXTRA)|NOTEQ|

## 导入模式
无


## 导出模式
无
