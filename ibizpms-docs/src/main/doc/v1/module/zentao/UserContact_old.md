# 用户联系方式(ZT_USERCONTACT)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|userList|USERLIST|LONGTEXT|&nbsp;|
|标题|LISTNAME|TEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|account|ACCOUNT|TEXT|&nbsp;|
|由谁更新|UPDATEBY|TEXT|&nbsp;|
|归属组织|ORG|TEXT|&nbsp;|
|归属部门|DEPT|TEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|userList|默认规则|内容长度必须小于等于[65535]|
|标题|默认规则|内容长度必须小于等于[60]|
|id|默认规则|默认规则|
|account|默认规则|内容长度必须小于等于[30]|
|由谁更新|默认规则|内容长度必须小于等于[100]|
|归属组织|默认规则|内容长度必须小于等于[100]|
|归属部门|默认规则|内容长度必须小于等于[100]|

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
|CurUSERCONTACT|抄送联系人([MYSQL5](../../appendix/query_MYSQL5.md#UserContact_CurUSERCONTACT))|否|&nbsp;|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#UserContact_Default))|是|&nbsp;|
|MyUSERCONTACT|我的联系人([MYSQL5](../../appendix/query_MYSQL5.md#UserContact_MyUSERCONTACT))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#UserContact_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|CurUSERCONTACT|抄送联系人|CurUSERCONTACT|否|&nbsp;|
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|MyUSERCONTACT|我的联系人|MyUSERCONTACT|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|

## 导入模式
无


## 导出模式
无
