# 版本(ZT_BUILD)

  

## 关系
{% plantuml %}
版本 *-- 发布 
版本 *-- 测试版本 
产品的分支和平台信息 *-- 版本 
产品 *-- 版本 
项目 *-- 版本 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|Bug版本健值|IDS|TEXT|&nbsp;|
|名称编号|NAME|TEXT|&nbsp;|
|后台体系|BACKGROUNDID|SSCODELIST|&nbsp;|
|构建者|BUILDER|TEXT|&nbsp;|
|附件|FILES|TEXT|&nbsp;|
|运行模式|RELEASETYPE|SSCODELIST|&nbsp;|
|构建者（选择）|BUILDERPK|TEXT|&nbsp;|
|重新构建|REBUILD|NSCODELIST|&nbsp;|
|描述|DESC|LONGTEXT|&nbsp;|
|id|ID|ACID|&nbsp;|
|已删除|DELETED|TEXT|&nbsp;|
|运行数据库|SQLID|SSCODELIST|&nbsp;|
|源代码地址|SCMPATH|TEXT|&nbsp;|
|下载地址|FILEPATH|TEXT|&nbsp;|
|产生的bug|CREATEBUGCNT|LONGTEXT|&nbsp;|
|完成的需求|STORIES|LONGTEXT|&nbsp;|
|解决的Bug|BUGS|LONGTEXT|&nbsp;|
|系统应用|FRONTAPPLICATION|SSCODELIST|&nbsp;|
|消息通知用户|NOTICEUSERS|TEXT|&nbsp;|
|打包日期|DATE|DATE|&nbsp;|
|产品名称|PRODUCTNAME|PICKUPTEXT|&nbsp;|
|产品|PRODUCT|PICKUP|&nbsp;|
|平台/分支|BRANCH|PICKUP|&nbsp;|
|所属项目|PROJECT|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|Bug版本健值|默认规则|内容长度必须小于等于[100]|
|名称编号|默认规则|内容长度必须小于等于[150]|
|后台体系|默认规则|内容长度必须小于等于[200]|
|构建者|默认规则|内容长度必须小于等于[30]|
|附件|默认规则|内容长度必须小于等于[1000]|
|运行模式|默认规则|内容长度必须小于等于[200]|
|构建者（选择）|默认规则|内容长度必须小于等于[200]|
|重新构建|默认规则|默认规则|
|描述|默认规则|内容长度必须小于等于[65535]|
|id|默认规则|默认规则|
|已删除|默认规则|内容长度必须小于等于[1]|
|运行数据库|默认规则|内容长度必须小于等于[200]|
|源代码地址|默认规则|内容长度必须小于等于[255]|
|下载地址|默认规则|内容长度必须小于等于[255]|
|产生的bug|默认规则|内容长度必须小于等于[1048576]|
|完成的需求|默认规则|内容长度必须小于等于[65535]|
|解决的Bug|默认规则|内容长度必须小于等于[65535]|
|系统应用|默认规则|内容长度必须小于等于[200]|
|消息通知用户|默认规则|内容长度必须小于等于[100]|
|打包日期|默认规则|默认规则|
|产品名称|默认规则|内容长度必须小于等于[90]|
|产品|默认规则|默认规则|
|平台/分支|默认规则|默认规则|
|所属项目|默认规则|默认规则|

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
|关联需求|用户自定义|&nbsp;【版本】关联需求、使用多项数据选择视图，选择多个数据，再保存关联性。|
|提交测试|用户自定义|&nbsp;|
|移动端项目版本计数器|实体处理逻辑|&nbsp;|
|一键发布|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|

## 处理逻辑
* 移动端项目版本计数器 (MobProjectBuildCounter)
  
   

{% plantuml %}
hide footbox

版本 -> 版本: 获取完成的需求数
版本 -> 版本: 获取产生的bug
版本 -> 版本: 获取解决的bug
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|获取完成的需求数 |
|2|获取产生的bug |
|3|获取解决的bug |
<center>移动端项目版本计数器</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|BugProductBuild|Bug产品版本([MYSQL5](../../appendix/query_MYSQL5.md#Build_BugProductBuild))|否|&nbsp;|
|CurProduct|产品版本([MYSQL5](../../appendix/query_MYSQL5.md#Build_CurProduct))|否|&nbsp;|
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Build_Default))|是|&nbsp;|
|TestBuild|测试版本([MYSQL5](../../appendix/query_MYSQL5.md#Build_TestBuild))|否|&nbsp;|
|TestRounds|测试轮次([MYSQL5](../../appendix/query_MYSQL5.md#Build_TestRounds))|否|&nbsp;|
|UpdateLog|更新日志([MYSQL5](../../appendix/query_MYSQL5.md#Build_UpdateLog))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Build_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|BugProductBuild|Bug产品版本|BugProductBuild|否|&nbsp;|
|CurProduct|产品版本|CurProduct|否|&nbsp;|
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|TestBuild|测试版本|TestBuild|否|&nbsp;|
|TestRounds|测试轮次|TestRounds|否|&nbsp;|
|UpdateLog|更新日志|UpdateLog|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|名称编号(NAME)|LIKE|
|后台体系(BACKGROUNDID)|EQ|
|运行模式(RELEASETYPE)|EQ|
|重新构建(REBUILD)|EQ|
|运行数据库(SQLID)|EQ|
|系统应用(FRONTAPPLICATION)|EQ|
|打包日期(DATE)|LTANDEQ|
|产品名称(PRODUCTNAME)|EQ|
|产品名称(PRODUCTNAME)|LIKE|
|产品(PRODUCT)|EQ|
|平台/分支(BRANCH)|EQ|
|所属项目(PROJECT)|EQ|

## 导入模式
无


## 导出模式
* 数据导出

|ID|产品名称|名称编号|源代码地址|下载地址|打包日期|构建者|产品|所属项目|
| :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: | :------: |
| - | - | - | - | - | - | - | - | - |
