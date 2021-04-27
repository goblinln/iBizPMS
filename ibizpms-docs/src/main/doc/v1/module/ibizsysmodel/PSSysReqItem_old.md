# 系统需求项(PSSYSREQITEM)

  

## 关系
{% plantuml %}
系统需求项 *-- 实体 
系统需求项 *-- 系统需求项 
系统需求项 *-- 系统需求项 
系统需求模块 *-- 系统需求项 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|项编号|ITEMSN|TEXT|&nbsp;|
|项类型|ITEMTYPE|SSCODELIST|&nbsp;|
|系统用例|PSSYSUSERCASEID|TEXT|&nbsp;|
|用户标记2|USERTAG2|TEXT|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|次序|ORDERVALUE|INT|&nbsp;|
|版本|VER|INT|&nbsp;|
|用户标记3|USERTAG3|TEXT|&nbsp;|
|系统|PSSYSTEMNAME|TEXT|&nbsp;|
|项标记|ITEMTAG|TEXT|&nbsp;|
|系统需求项标识|PSSYSREQITEMID|GUID|&nbsp;|
|项标记2|ITEMTAG2|TEXT|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|内容|REQCONTENT|HTMLTEXT|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|用户标记|USERTAG|TEXT|&nbsp;|
|是否有效|VALIDFLAG|YESNO|&nbsp;|
|系统用例|PSSYSUSERCASENAME|TEXT|&nbsp;|
|用户分类|USERCAT|SSCODELIST|&nbsp;|
|代码标识|CODENAME|TEXT|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|备注|MEMO|LONGTEXT_1000|&nbsp;|
|系统需求项名称|PSSYSREQITEMNAME|TEXT|&nbsp;|
|用户标记4|USERTAG4|TEXT|&nbsp;|
|系统|PSSYSTEMID|TEXT|&nbsp;|
|需求模块|PSSYSREQMODULENAME|PICKUPTEXT|&nbsp;|
|父需求项|PPSSYSREQITEMNAME|PICKUPTEXT|&nbsp;|
|需求模块|PSSYSREQMODULEID|PICKUP|&nbsp;|
|父需求项|PPSSYSREQITEMID|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|项编号|默认规则|内容长度必须小于等于[100]|
|项类型|默认规则|内容长度必须小于等于[50]|
|系统用例|默认规则|内容长度必须小于等于[100]|
|用户标记2|默认规则|内容长度必须小于等于[200]|
|建立时间|默认规则|默认规则|
|次序|默认规则|默认规则|
|版本|默认规则|默认规则|
|用户标记3|默认规则|内容长度必须小于等于[50]|
|系统|默认规则|内容长度必须小于等于[100]|
|项标记|默认规则|内容长度必须小于等于[200]|
|系统需求项标识|默认规则|内容长度必须小于等于[100]|
|项标记2|默认规则|内容长度必须小于等于[200]|
|更新时间|默认规则|默认规则|
|内容|默认规则|内容长度必须小于等于[1048576]|
|建立人|默认规则|内容长度必须小于等于[60]|
|用户标记|默认规则|内容长度必须小于等于[200]|
|是否有效|默认规则|默认规则|
|系统用例|默认规则|内容长度必须小于等于[100]|
|用户分类|默认规则|内容长度必须小于等于[10]|
|代码标识|默认规则|内容长度必须小于等于[50]|
|更新人|默认规则|内容长度必须小于等于[60]|
|备注|默认规则|内容长度必须小于等于[4000]|
|系统需求项名称|默认规则|内容长度必须小于等于[200]|
|用户标记4|默认规则|内容长度必须小于等于[50]|
|系统|默认规则|内容长度必须小于等于[100]|
|需求模块|默认规则|内容长度必须小于等于[200]|
|父需求项|默认规则|内容长度必须小于等于[200]|
|需求模块|默认规则|内容长度必须小于等于[100]|
|父需求项|默认规则|内容长度必须小于等于[100]|

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
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#PSSysReqItem_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#PSSysReqItem_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|项类型(ITEMTYPE)|EQ|
|系统需求项标识(PSSYSREQITEMID)|EQ|
|用户分类(USERCAT)|EQ|
|系统需求项名称(PSSYSREQITEMNAME)|LIKE|
|需求模块(PSSYSREQMODULENAME)|EQ|
|需求模块(PSSYSREQMODULENAME)|LIKE|
|父需求项(PPSSYSREQITEMNAME)|EQ|
|父需求项(PPSSYSREQITEMNAME)|LIKE|
|需求模块(PSSYSREQMODULEID)|EQ|
|父需求项(PPSSYSREQITEMID)|EQ|

## 导入模式
无


## 导出模式
无
