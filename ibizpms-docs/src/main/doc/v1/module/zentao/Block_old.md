# block(ZT_BLOCK)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|高度|HEIGHT|INT|&nbsp;|
|params|PARAMS|LONGTEXT|&nbsp;|
|区块名称|TITLE|TEXT|&nbsp;|
|排序|ORDER|INT|&nbsp;|
|来源区块|BLOCK|SSCODELIST|&nbsp;|
|所属用户|ACCOUNT|TEXT|&nbsp;|
|位置|GRID|INT|&nbsp;|
|所属模块|MODULE|SSCODELIST|&nbsp;|
|隐藏|HIDDEN|NSCODELIST|&nbsp;|
|来源模块|SOURCE|SSCODELIST|&nbsp;|
|id|ID|ACID|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|高度|默认规则|默认规则|
|params|默认规则|内容长度必须小于等于[65535]|
|区块名称|默认规则|内容长度必须小于等于[100]|
|排序|默认规则|默认规则|
|来源区块|默认规则|内容长度必须小于等于[20]|
|所属用户|默认规则|内容长度必须小于等于[30]|
|位置|默认规则|默认规则|
|所属模块|默认规则|内容长度必须小于等于[20]|
|隐藏|默认规则|默认规则|
|来源模块|默认规则|内容长度必须小于等于[20]|
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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Block_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Block_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|区块名称(TITLE)|LIKE|
|id(ID)|EQ|

## 导入模式
无


## 导出模式
无
