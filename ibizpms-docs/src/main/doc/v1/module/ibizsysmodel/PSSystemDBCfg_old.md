# 系统数据库(PSSYSTEMDBCFG)

  

## 关系
无

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|表空间2名称|TABSPACE2|TEXT|&nbsp;|
|资源状态|RESSTATE|NSCODELIST|&nbsp;|
|空值排序|NULLVALORDER|SSCODELIST|&nbsp;|
|用户标记3|USERTAG3|TEXT|&nbsp;|
|默认表空间名称|TABSPACE|TEXT|&nbsp;|
|默认数据源|DEFAULTFLAG|YESNO|&nbsp;|
|备注|MEMO|LONGTEXT_1000|&nbsp;|
|无数据库模式|NODBINSTMODE|YESNO|&nbsp;|
|用户标记4|USERTAG4|TEXT|&nbsp;|
|系统数据库名称|PSSYSTEMDBCFGNAME|TEXT|&nbsp;|
|系统|PSSYSTEMNAME|TEXT|&nbsp;|
|资源信息|RESINFO|LONGTEXT_1000|&nbsp;|
|用户标记|USERTAG|TEXT|&nbsp;|
|自定义参数|USERPARAMS|TEXT|&nbsp;|
|资源就绪时间|RESREADYTIME|DATETIME|&nbsp;|
|发布外键|PUBFKEYFLAG|YESNO|&nbsp;|
|系统数据库标识|PSSYSTEMDBCFGID|GUID|&nbsp;|
|用户标记2|USERTAG2|TEXT|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|发布模型注释|PUBCOMMENTFLAG|YESNO|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|表空间3名称|TABSPACE3|TEXT|&nbsp;|
|支持Web管理|ENABLEWEBTOOL|YESNO|&nbsp;|
|发布数据库模型|PUBDBMODELFLAG|YESNO|&nbsp;|
|对象名称转换|OBJNAMECASE|SSCODELIST|&nbsp;|
|发布索引|PUBINDEXFLAG|YESNO|&nbsp;|
|附加模式名称|APPENDSCHEMA|YESNO|&nbsp;|
|系统|PSSYSTEMID|TEXT|&nbsp;|
|数据库模式名称|DBSCHEMANAME|TEXT|&nbsp;|
|表空间4名称|TABSPACE4|TEXT|&nbsp;|
|用户分类|USERCAT|SSCODELIST|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|发布视图|PUBVIEWFLAG|YESNO|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|表空间2名称|默认规则|内容长度必须小于等于[60]|
|资源状态|默认规则|默认规则|
|空值排序|默认规则|内容长度必须小于等于[10]|
|用户标记3|默认规则|内容长度必须小于等于[50]|
|默认表空间名称|默认规则|内容长度必须小于等于[60]|
|默认数据源|默认规则|默认规则|
|备注|默认规则|内容长度必须小于等于[2000]|
|无数据库模式|默认规则|默认规则|
|用户标记4|默认规则|内容长度必须小于等于[50]|
|系统数据库名称|默认规则|内容长度必须小于等于[200]|
|系统|默认规则|内容长度必须小于等于[100]|
|资源信息|默认规则|内容长度必须小于等于[2000]|
|用户标记|默认规则|内容长度必须小于等于[100]|
|自定义参数|默认规则|内容长度必须小于等于[2000]|
|资源就绪时间|默认规则|默认规则|
|发布外键|默认规则|默认规则|
|系统数据库标识|默认规则|内容长度必须小于等于[100]|
|用户标记2|默认规则|内容长度必须小于等于[100]|
|更新人|默认规则|内容长度必须小于等于[60]|
|发布模型注释|默认规则|默认规则|
|建立时间|默认规则|默认规则|
|表空间3名称|默认规则|内容长度必须小于等于[60]|
|支持Web管理|默认规则|默认规则|
|发布数据库模型|默认规则|默认规则|
|对象名称转换|默认规则|内容长度必须小于等于[20]|
|发布索引|默认规则|默认规则|
|附加模式名称|默认规则|默认规则|
|系统|默认规则|内容长度必须小于等于[100]|
|数据库模式名称|默认规则|内容长度必须小于等于[100]|
|表空间4名称|默认规则|内容长度必须小于等于[60]|
|用户分类|默认规则|内容长度必须小于等于[10]|
|建立人|默认规则|内容长度必须小于等于[60]|
|更新时间|默认规则|默认规则|
|发布视图|默认规则|默认规则|

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
|Build|版本([MYSQL5](../../appendix/query_MYSQL5.md#PSSystemDBCfg_Build))|否|&nbsp;|
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#PSSystemDBCfg_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#PSSystemDBCfg_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|Build|版本|Build|否|&nbsp;|
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|资源状态(RESSTATE)|EQ|
|空值排序(NULLVALORDER)|EQ|
|系统数据库名称(PSSYSTEMDBCFGNAME)|LIKE|
|对象名称转换(OBJNAMECASE)|EQ|
|用户分类(USERCAT)|EQ|

## 导入模式
无


## 导出模式
无
