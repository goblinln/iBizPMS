# 代理(IBZ_AGENT)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|更新人|UPDATEMAN|TEXT|&nbsp;|
|创建人姓名|CREATEMANNAME|TEXT|&nbsp;|
|代理结束日期|AGENTEND|DATE|&nbsp;|
|代理标识|IBZ_AGENTID|ACID|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|代理用户|AGENTUSER|TEXT|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|代理开始日期|AGENTBEGIN|DATE|&nbsp;|
|代理名称|IBZ_AGENTNAME|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|更新人|默认规则|内容长度必须小于等于[60]|
|创建人姓名|默认规则|内容长度必须小于等于[100]|
|代理结束日期|默认规则|默认规则|
|代理标识|默认规则|默认规则|
|建立人|默认规则|内容长度必须小于等于[60]|
|更新时间|默认规则|默认规则|
|代理用户|默认规则|内容长度必须小于等于[100]|
|建立时间|默认规则|默认规则|
|代理开始日期|默认规则|默认规则|
|代理名称|默认规则|内容长度必须小于等于[200]|

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
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#IbzAgent_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IbzAgent_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|创建人姓名(CREATEMANNAME)|EQ|
|代理名称(IBZ_AGENTNAME)|LIKE|

## 导入模式
无


## 导出模式
无
