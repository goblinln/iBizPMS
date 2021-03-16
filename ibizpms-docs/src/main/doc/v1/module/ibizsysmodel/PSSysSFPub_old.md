# 后台服务架构(PSSYSSFPUB)

  

## 关系
{% plantuml %}
后台服务架构 *-- 后台服务架构 
后台服务架构 *-- 后台服务架构 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|用户标记|USERTAG|TEXT|&nbsp;|
|系统|PSSYSTEMID|TEXT|&nbsp;|
|服务目录|PUBFOLDER|TEXT|&nbsp;|
|服务框架扩展|PSSFSTYLEVERID|TEXT|&nbsp;|
|用户标记4|USERTAG4|TEXT|&nbsp;|
|发布标记4|PUBTAG4|TEXT|&nbsp;|
|服务框架|PSSFSTYLEID|TEXT|&nbsp;|
|删除模式|REMOVEFLAG|NSCODELIST|&nbsp;|
|引用系统组件|SUBSYSPKGFLAG|YESNO|&nbsp;|
|默认后台服务|DEFAULTPUB|YESNO|&nbsp;|
|文档模板样式|DOCPSSFSTYLENAME|TEXT|&nbsp;|
|用户标记3|USERTAG3|TEXT|&nbsp;|
|服务框架参数|PSSFSTYLEPARAMNAME|TEXT|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|代码名称|CODENAME|TEXT|&nbsp;|
|用户标记2|USERTAG2|TEXT|&nbsp;|
|后台服务架构名称|PSSYSSFPUBNAME|TEXT|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|发布标记|PUBTAG|TEXT|&nbsp;|
|基类代码包名|BASECLSPKGCODENAME|TEXT|&nbsp;|
|版本号|VERSTR|TEXT|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|发布标记3|PUBTAG3|TEXT|&nbsp;|
|服务框架参数|PSSFSTYLEPARAMID|TEXT|&nbsp;|
|文档模板样式|DOCPSSFSTYLEID|TEXT|&nbsp;|
|备注|MEMO|LONGTEXT_1000|&nbsp;|
|代码包名|PKGCODENAME|TEXT|&nbsp;|
|后台服务架构标识|PSSYSSFPUBID|GUID|&nbsp;|
|系统|PSSYSTEMNAME|TEXT|&nbsp;|
|发布内容类型|CONTENTTYPE|SSCODELIST|&nbsp;|
|用户分类|USERCAT|SSCODELIST|&nbsp;|
|发布标记2|PUBTAG2|TEXT|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|服务框架参数|STYLEPARAMS|LONGTEXT_1000|&nbsp;|
|父后台服务体系|PPSSYSSFPUBNAME|PICKUPTEXT|&nbsp;|
|父后台服务体系|PPSSYSSFPUBID|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|用户标记|默认规则|内容长度必须小于等于[200]|
|系统|默认规则|内容长度必须小于等于[100]|
|服务目录|默认规则|内容长度必须小于等于[500]|
|服务框架扩展|默认规则|内容长度必须小于等于[100]|
|用户标记4|默认规则|内容长度必须小于等于[50]|
|发布标记4|默认规则|内容长度必须小于等于[60]|
|服务框架|默认规则|内容长度必须小于等于[100]|
|删除模式|默认规则|默认规则|
|引用系统组件|默认规则|默认规则|
|默认后台服务|默认规则|默认规则|
|文档模板样式|默认规则|内容长度必须小于等于[200]|
|用户标记3|默认规则|内容长度必须小于等于[50]|
|服务框架参数|默认规则|内容长度必须小于等于[200]|
|建立人|默认规则|内容长度必须小于等于[60]|
|代码名称|默认规则|内容长度必须小于等于[100]|
|用户标记2|默认规则|内容长度必须小于等于[200]|
|后台服务架构名称|默认规则|内容长度必须小于等于[200]|
|更新人|默认规则|内容长度必须小于等于[60]|
|发布标记|默认规则|内容长度必须小于等于[60]|
|基类代码包名|默认规则|内容长度必须小于等于[200]|
|版本号|默认规则|内容长度必须小于等于[50]|
|建立时间|默认规则|默认规则|
|发布标记3|默认规则|内容长度必须小于等于[60]|
|服务框架参数|默认规则|内容长度必须小于等于[100]|
|文档模板样式|默认规则|内容长度必须小于等于[100]|
|备注|默认规则|内容长度必须小于等于[2000]|
|代码包名|默认规则|内容长度必须小于等于[200]|
|后台服务架构标识|默认规则|内容长度必须小于等于[100]|
|系统|默认规则|内容长度必须小于等于[100]|
|发布内容类型|默认规则|内容长度必须小于等于[20]|
|用户分类|默认规则|内容长度必须小于等于[10]|
|发布标记2|默认规则|内容长度必须小于等于[60]|
|更新时间|默认规则|默认规则|
|服务框架参数|默认规则|内容长度必须小于等于[4000]|
|父后台服务体系|默认规则|内容长度必须小于等于[200]|
|父后台服务体系|默认规则|内容长度必须小于等于[100]|

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
|Build|版本([MYSQL5](../../appendix/query_MYSQL5.md#PSSysSFPub_Build))|否|&nbsp;|
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#PSSysSFPub_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#PSSysSFPub_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|Build|版本|Build|否|&nbsp;|
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|删除模式(REMOVEFLAG)|EQ|
|后台服务架构名称(PSSYSSFPUBNAME)|LIKE|
|发布内容类型(CONTENTTYPE)|EQ|
|用户分类(USERCAT)|EQ|
|父后台服务体系(PPSSYSSFPUBNAME)|EQ|
|父后台服务体系(PPSSYSSFPUBNAME)|LIKE|
|父后台服务体系(PPSSYSSFPUBID)|EQ|

## 导入模式
无


## 导出模式
无
