# 系统模块(PSMODULE)

  

## 关系
{% plantuml %}
系统模块 *-- 实体 
系统模块 *-- 外部服务接口 
系统模块 *-- 系统需求模块 
系统模块 *-- 系统服务接口 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|代码包名|PKGCODENAME|TEXT|&nbsp;|
|子系统模块|SUBSYSMODULE|YESNO|&nbsp;|
|模块标记4|MODTAG4|TEXT|&nbsp;|
|模块标记3|MODTAG3|TEXT|&nbsp;|
|模型锁模式|LOCKFLAG|NSCODELIST|&nbsp;|
|显示颜色|COLOR|TEXT|&nbsp;|
|模块编号|MODULESN|TEXT|&nbsp;|
|系统模块名称|PSMODULENAME|TEXT|&nbsp;|
|系统|PSSYSTEMNAME|TEXT|&nbsp;|
|备注|MEMO|LONGTEXT_1000|&nbsp;|
|用户标记2|USERTAG2|TEXT|&nbsp;|
|默认模块|DEFAULTFLAG|YESNO|&nbsp;|
|用户分类|USERCAT|SSCODELIST|&nbsp;|
|类包参数|CLSPKGPARAMS|LONGTEXT_1000|&nbsp;|
|用户标记|USERTAG|TEXT|&nbsp;|
|代码名称|CODENAME|TEXT|&nbsp;|
|自定义参数|USERPARAMS|TEXT|&nbsp;|
|模块标记2|MODTAG2|TEXT|&nbsp;|
|系统模块标识|PSMODULEID|GUID|&nbsp;|
|模块标记|MODTAG|TEXT|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|服务API模式|SERVICEAPIFLAG|NSCODELIST|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|排序值|ORDERVALUE|INT|&nbsp;|
|用户标记3|USERTAG3|TEXT|&nbsp;|
|无视图模式|NOVIEWMODE|YESNO|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|系统|PSSYSTEMID|TEXT|&nbsp;|
|用户标记4|USERTAG4|TEXT|&nbsp;|
|子系统类型|SYSREFTYPE|SSCODELIST|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|代码包名|默认规则|内容长度必须小于等于[200]|
|子系统模块|默认规则|默认规则|
|模块标记4|默认规则|内容长度必须小于等于[60]|
|模块标记3|默认规则|内容长度必须小于等于[60]|
|模型锁模式|默认规则|默认规则|
|显示颜色|默认规则|内容长度必须小于等于[30]|
|模块编号|默认规则|内容长度必须小于等于[100]|
|系统模块名称|默认规则|内容长度必须小于等于[200]|
|系统|默认规则|内容长度必须小于等于[100]|
|备注|默认规则|内容长度必须小于等于[2000]|
|用户标记2|默认规则|内容长度必须小于等于[200]|
|默认模块|默认规则|默认规则|
|用户分类|默认规则|内容长度必须小于等于[10]|
|类包参数|默认规则|内容长度必须小于等于[4000]|
|用户标记|默认规则|内容长度必须小于等于[200]|
|代码名称|默认规则|内容长度必须小于等于[60]|
|自定义参数|默认规则|内容长度必须小于等于[2000]|
|模块标记2|默认规则|内容长度必须小于等于[60]|
|系统模块标识|默认规则|内容长度必须小于等于[100]|
|模块标记|默认规则|内容长度必须小于等于[60]|
|更新时间|默认规则|默认规则|
|服务API模式|默认规则|默认规则|
|更新人|默认规则|内容长度必须小于等于[60]|
|排序值|默认规则|默认规则|
|用户标记3|默认规则|内容长度必须小于等于[50]|
|无视图模式|默认规则|默认规则|
|建立人|默认规则|内容长度必须小于等于[60]|
|系统|默认规则|内容长度必须小于等于[100]|
|用户标记4|默认规则|内容长度必须小于等于[50]|
|子系统类型|默认规则|内容长度必须小于等于[20]|
|建立时间|默认规则|默认规则|

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
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#PSModule_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#PSModule_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|模型锁模式(LOCKFLAG)|EQ|
|系统模块名称(PSMODULENAME)|LIKE|
|用户分类(USERCAT)|EQ|
|服务API模式(SERVICEAPIFLAG)|EQ|
|子系统类型(SYSREFTYPE)|EQ|

## 导入模式
无


## 导出模式
无
