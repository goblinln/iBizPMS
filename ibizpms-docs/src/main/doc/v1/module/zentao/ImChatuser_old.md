# ImChatuser(ZT_IM_CHATUSER)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|id|ID|ACID|&nbsp;|
|order|ORDER|INT|&nbsp;|
|freeze|FREEZE|SSCODELIST|&nbsp;|
|star|STAR|SSCODELIST|&nbsp;|
|hide|HIDE|SSCODELIST|&nbsp;|
|join|JOIN|DATETIME|&nbsp;|
|user|USER|INT|&nbsp;|
|mute|MUTE|SSCODELIST|&nbsp;|
|cgid|CGID|TEXT|&nbsp;|
|quit|QUIT|DATETIME|&nbsp;|
|category|CATEGORY|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|id|默认规则|默认规则|
|order|默认规则|默认规则|
|freeze|默认规则|内容长度必须小于等于[1]|
|star|默认规则|内容长度必须小于等于[1]|
|hide|默认规则|内容长度必须小于等于[1]|
|join|默认规则|默认规则|
|user|默认规则|默认规则|
|mute|默认规则|内容长度必须小于等于[1]|
|cgid|默认规则|内容长度必须小于等于[40]|
|quit|默认规则|默认规则|
|category|默认规则|内容长度必须小于等于[40]|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#ImChatuser_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#ImChatuser_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|id(ID)|EQ|
|freeze(FREEZE)|EQ|
|star(STAR)|EQ|
|hide(HIDE)|EQ|
|mute(MUTE)|EQ|

## 导入模式
无


## 导出模式
无
