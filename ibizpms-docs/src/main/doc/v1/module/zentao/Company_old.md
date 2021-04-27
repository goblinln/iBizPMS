# 公司(ZT_COMPANY)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|admins|ADMINS|TEXT|&nbsp;|
|传真|FAX|TEXT|&nbsp;|
|逻辑删除标志|DELETED|TEXT|&nbsp;|
|官网|WEBSITE|TEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|邮政编码|ZIPCODE|TEXT|&nbsp;|
|通讯地址|ADDRESS|TEXT|&nbsp;|
|内网|BACKYARD|TEXT|&nbsp;|
|公司名称|NAME|TEXT|&nbsp;|
|匿名登陆|GUEST|SSCODELIST|&nbsp;|
|联系电话|PHONE|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|admins|默认规则|内容长度必须小于等于[255]|
|传真|默认规则|内容长度必须小于等于[20]|
|逻辑删除标志|默认规则|内容长度必须小于等于[1]|
|官网|默认规则|内容长度必须小于等于[120]|
|id|默认规则|默认规则|
|邮政编码|默认规则|内容长度必须小于等于[10]|
|通讯地址|默认规则|内容长度必须小于等于[120]|
|内网|默认规则|内容长度必须小于等于[120]|
|公司名称|默认规则|内容长度必须小于等于[120]|
|匿名登陆|默认规则|内容长度必须小于等于[1]|
|联系电话|默认规则|内容长度必须小于等于[20]|

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
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Company_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Company_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|id(ID)|EQ|
|公司名称(NAME)|LIKE|
|匿名登陆(GUEST)|EQ|

## 导入模式
无


## 导出模式
无
