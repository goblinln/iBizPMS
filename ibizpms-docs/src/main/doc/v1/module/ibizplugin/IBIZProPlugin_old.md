# 系统插件(IBIZPRO_PLUGIN)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|版本|VERSION|INT|&nbsp;|
|类型|TYPE|SSCODELIST|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|总下载量|DOWNLOADCOUNT|INT|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|最新版本下载地址|DOWNLOADURL|TEXT|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|标签|TAG|SMCODELIST|&nbsp;|
|总评分|SCORE|INT|&nbsp;|
|系统插件名称|IBIZPRO_PLUGINNAME|TEXT|&nbsp;|
|系统插件标识|IBIZPRO_PLUGINID|GUID|&nbsp;|
|关键字|KEYWORD|SMCODELIST|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|总评论数|COMMENTCOUNT|INT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|版本|默认规则|默认规则|
|类型|默认规则|内容长度必须小于等于[10]|
|建立人|默认规则|内容长度必须小于等于[60]|
|总下载量|默认规则|默认规则|
|建立时间|默认规则|默认规则|
|最新版本下载地址|默认规则|内容长度必须小于等于[100]|
|更新时间|默认规则|默认规则|
|标签|默认规则|内容长度必须小于等于[2000]|
|总评分|默认规则|默认规则|
|系统插件名称|默认规则|内容长度必须小于等于[200]|
|系统插件标识|默认规则|内容长度必须小于等于[100]|
|关键字|默认规则|内容长度必须小于等于[2000]|
|更新人|默认规则|内容长度必须小于等于[60]|
|总评论数|默认规则|默认规则|

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
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#IBIZProPlugin_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IBIZProPlugin_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|类型(TYPE)|EQ|
|系统插件名称(IBIZPRO_PLUGINNAME)|LIKE|
|系统插件标识(IBIZPRO_PLUGINID)|EQ|

## 导入模式
无


## 导出模式
无
