# 消息(IBIZPRO_MESSAGE)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|发送代理标识|SENDPROXYID|TEXT|&nbsp;|
|消息名称|IBIZPRO_MESSAGENAME|TEXT|&nbsp;|
|是否是链接消息|ISLINK|NSCODELIST|&nbsp;|
|标题|SUBJECT|TEXT|&nbsp;|
|重发间隔时间|RETRYINTERVALTIME|INT|&nbsp;|
|密件抄送方|BCC|LONGTEXT|&nbsp;|
|是否完成|ISDONE|YESNO|&nbsp;|
|消息参数|PARAM|LONGTEXT|&nbsp;|
|消息标识|IBIZPRO_MESSAGEID|TEXT|&nbsp;|
|重发次数|RETRYTIMES|INT|&nbsp;|
|是否同步|ISSYNC|NSCODELIST|&nbsp;|
|发送方|FROM|LONGTEXT|&nbsp;|
|抄送方|CC|LONGTEXT|&nbsp;|
|发送时间|SENDTIME|DATETIME|&nbsp;|
|内容|CONTENT|LONGTEXT|&nbsp;|
|链接地址|LINKURL|TEXT|&nbsp;|
|是否已读|ISREAD|YESNO|&nbsp;|
|是否重发|ISRETRY|NSCODELIST|&nbsp;|
|消息类型|TYPE|SSCODELIST|&nbsp;|
|接收方|TO|LONGTEXT|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|发送代理标识|默认规则|内容长度必须小于等于[100]|
|消息名称|默认规则|内容长度必须小于等于[100]|
|是否是链接消息|默认规则|默认规则|
|标题|默认规则|内容长度必须小于等于[100]|
|重发间隔时间|默认规则|默认规则|
|密件抄送方|默认规则|内容长度必须小于等于[1048576]|
|是否完成|默认规则|默认规则|
|消息参数|默认规则|内容长度必须小于等于[1048576]|
|消息标识|默认规则|内容长度必须小于等于[100]|
|重发次数|默认规则|默认规则|
|是否同步|默认规则|默认规则|
|发送方|默认规则|内容长度必须小于等于[1048576]|
|抄送方|默认规则|内容长度必须小于等于[1048576]|
|发送时间|默认规则|默认规则|
|内容|默认规则|内容长度必须小于等于[1048576]|
|链接地址|默认规则|内容长度必须小于等于[100]|
|是否已读|默认规则|默认规则|
|是否重发|默认规则|默认规则|
|消息类型|默认规则|内容长度必须小于等于[60]|
|接收方|默认规则|内容长度必须小于等于[1048576]|

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
|标记已完成|用户自定义|&nbsp;|
|标记已读|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|
|发送消息|用户自定义|&nbsp;|

## 处理逻辑
无

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#IBIZProMessage_Default))|是|&nbsp;|
|UserAllMessages|用户全部消息([MYSQL5](../../appendix/query_MYSQL5.md#IBIZProMessage_UserAllMessages))|否|&nbsp;|
|UserUnreadMessages|用户未读信息([MYSQL5](../../appendix/query_MYSQL5.md#IBIZProMessage_UserUnreadMessages))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#IBIZProMessage_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|
|UserAllMessages|用户全部消息|UserAllMessages|否|&nbsp;|
|UserUnreadMessages|用户未读信息|UserUnreadMessages|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|发送代理标识(SENDPROXYID)|EQ|
|消息名称(IBIZPRO_MESSAGENAME)|LIKE|
|是否是链接消息(ISLINK)|EQ|
|消息标识(IBIZPRO_MESSAGEID)|EQ|
|是否同步(ISSYNC)|EQ|
|是否重发(ISRETRY)|EQ|
|消息类型(TYPE)|EQ|

## 导入模式
无


## 导出模式
无
