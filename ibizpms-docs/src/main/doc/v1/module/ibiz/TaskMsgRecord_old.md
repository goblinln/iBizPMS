# 待办消息记录(TASKMSGRECORD)

  用于保存待办消息发送成功后，返回的ID

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|第三方应用待办标识|APPTASKID|TEXT|&nbsp;发送代办成功后的，返回的taskid|
|数据标识|DATAID|TEXT|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|标题|TITLE|TEXT|&nbsp;|
|备注|MEMO|TEXT|&nbsp;待办信息正文|
|建立人|CREATEMAN|TEXT|&nbsp;|
|待办人标识|TASKUSERID|TEXT|&nbsp;指定的待办人标识|
|待办类型|TASKTYPE|TEXT|&nbsp;实体名。|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|待办消息记录名称|TASKMSGRECORDNAME|TEXT|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|待办消息记录标识|TASKMSGRECORDID|GUID|&nbsp;|
|逻辑有效标志|ENABLE|YESNO|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|第三方应用待办标识|默认规则|内容长度必须小于等于[100]|
|数据标识|默认规则|内容长度必须小于等于[100]|
|更新时间|默认规则|默认规则|
|标题|默认规则|内容长度必须小于等于[100]|
|备注|默认规则|内容长度必须小于等于[100]|
|建立人|默认规则|内容长度必须小于等于[60]|
|待办人标识|默认规则|内容长度必须小于等于[100]|
|待办类型|默认规则|内容长度必须小于等于[100]|
|更新人|默认规则|内容长度必须小于等于[60]|
|待办消息记录名称|默认规则|内容长度必须小于等于[200]|
|建立时间|默认规则|默认规则|
|待办消息记录标识|默认规则|内容长度必须小于等于[100]|
|逻辑有效标志|默认规则|默认规则|

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
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#TaskMsgRecord_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#TaskMsgRecord_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|待办消息记录名称(TASKMSGRECORDNAME)|LIKE|

## 导入模式
无


## 导出模式
无
