# 系统需求模块(PSSYSREQMODULE)

  

## 关系
{% plantuml %}
系统需求模块 *-- 系统需求项 
系统需求模块 *-- 系统需求模块 
系统模块 *-- 系统需求模块 
系统需求模块 *-- 系统需求模块 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|模块标记2|MODULETAG2|TEXT|&nbsp;|
|系统需求模块名称|PSSYSREQMODULENAME|TEXT|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|用户标记2|USERTAG2|TEXT|&nbsp;|
|模块标记4|MODULETAG4|TEXT|&nbsp;|
|备注|MEMO|LONGTEXT_1000|&nbsp;|
|模块标记3|MODULETAG3|TEXT|&nbsp;|
|用户分类|USERCAT|SSCODELIST|&nbsp;|
|模块标记|MODULETAG|TEXT|&nbsp;|
|模块编号|MODULESN|TEXT|&nbsp;|
|系统|PSSYSTEMID|TEXT|&nbsp;|
|用户标记3|USERTAG3|TEXT|&nbsp;|
|用户标记|USERTAG|TEXT|&nbsp;|
|排序值|ORDERVALUE|INT|&nbsp;|
|系统|PSSYSTEMNAME|TEXT|&nbsp;|
|用户标记4|USERTAG4|TEXT|&nbsp;|
|系统需求模块标识|PSSYSREQMODULEID|GUID|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|内容|CONTENT|LONGTEXT|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|代码标识|CODENAME|TEXT|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|系统模块|PSMODULENAME|PICKUPTEXT|&nbsp;|
|父需求模块|PPSSYSREQMODULENAME|PICKUPTEXT|&nbsp;|
|父需求模块|PPSSYSREQMODULEID|PICKUP|&nbsp;|
|系统模块|PSMODULEID|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|模块标记2|默认规则|内容长度必须小于等于[200]|
|系统需求模块名称|默认规则|内容长度必须小于等于[200]|
|建立时间|默认规则|默认规则|
|用户标记2|默认规则|内容长度必须小于等于[200]|
|模块标记4|默认规则|内容长度必须小于等于[200]|
|备注|默认规则|内容长度必须小于等于[4000]|
|模块标记3|默认规则|内容长度必须小于等于[200]|
|用户分类|默认规则|内容长度必须小于等于[10]|
|模块标记|默认规则|内容长度必须小于等于[200]|
|模块编号|默认规则|内容长度必须小于等于[100]|
|系统|默认规则|内容长度必须小于等于[100]|
|用户标记3|默认规则|内容长度必须小于等于[50]|
|用户标记|默认规则|内容长度必须小于等于[200]|
|排序值|默认规则|默认规则|
|系统|默认规则|内容长度必须小于等于[100]|
|用户标记4|默认规则|内容长度必须小于等于[50]|
|系统需求模块标识|默认规则|内容长度必须小于等于[100]|
|更新人|默认规则|内容长度必须小于等于[60]|
|内容|默认规则|内容长度必须小于等于[4000]|
|更新时间|默认规则|默认规则|
|代码标识|默认规则|内容长度必须小于等于[50]|
|建立人|默认规则|内容长度必须小于等于[60]|
|系统模块|默认规则|内容长度必须小于等于[200]|
|父需求模块|默认规则|内容长度必须小于等于[200]|
|父需求模块|默认规则|内容长度必须小于等于[100]|
|系统模块|默认规则|内容长度必须小于等于[100]|

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
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#PSSysReqModule_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#PSSysReqModule_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|系统需求模块名称(PSSYSREQMODULENAME)|LIKE|
|用户分类(USERCAT)|EQ|
|系统需求模块标识(PSSYSREQMODULEID)|EQ|
|系统模块(PSMODULENAME)|EQ|
|系统模块(PSMODULENAME)|LIKE|
|父需求模块(PPSSYSREQMODULENAME)|EQ|
|父需求模块(PPSSYSREQMODULENAME)|LIKE|
|父需求模块(PPSSYSREQMODULEID)|EQ|
|系统模块(PSMODULEID)|EQ|

## 导入模式
无


## 导出模式
无
