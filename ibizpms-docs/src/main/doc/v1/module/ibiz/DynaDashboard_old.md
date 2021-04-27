# 动态数据看板(DYNADASHBOARD)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|用户标识|USERID|TEXT|&nbsp;|
|动态数据看板标识|DYNADASHBOARDID|GUID|&nbsp;|
|动态数据看板名称|DYNADASHBOARDNAME|TEXT|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|模型标识|MODELID|TEXT|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|应用标识|APPID|TEXT|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|模型|MODEL|LONGTEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|用户标识|默认规则|内容长度必须小于等于[100]|
|动态数据看板标识|默认规则|内容长度必须小于等于[100]|
|动态数据看板名称|默认规则|内容长度必须小于等于[200]|
|建立人|默认规则|内容长度必须小于等于[60]|
|更新人|默认规则|内容长度必须小于等于[60]|
|模型标识|默认规则|内容长度必须小于等于[100]|
|更新时间|默认规则|默认规则|
|应用标识|默认规则|内容长度必须小于等于[100]|
|建立时间|默认规则|默认规则|
|模型|默认规则|内容长度必须小于等于[1048576]|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#DynaDashboard_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#DynaDashboard_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|动态数据看板标识(DYNADASHBOARDID)|EQ|
|动态数据看板名称(DYNADASHBOARDNAME)|LIKE|

## 导入模式
无


## 导出模式
无
