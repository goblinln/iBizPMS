# 发布(ZT_RELEASE)

  

## 关系
{% plantuml %}
产品的分支和平台信息 *-- 发布 
版本 *-- 发布 
产品 *-- 发布 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|完成的需求|STORIES|LONGTEXT|&nbsp;|
|里程碑|MARKER|SSCODELIST|&nbsp;|
|ID|ID|ACID|&nbsp;|
|运行数据库|SQLID|SSCODELIST|&nbsp;|
|遗留的Bug|LEFTBUGS|LONGTEXT|&nbsp;|
|解决的Bug|BUGS|LONGTEXT|&nbsp;|
|已删除|DELETED|TEXT|&nbsp;|
|消息通知用户|NOTICEUSERS|TEXT|&nbsp;|
|重新构建|REBUILD|NSCODELIST|&nbsp;|
|发布名称|NAME|TEXT|&nbsp;|
|发布日期|DATE|DATE|&nbsp;|
|状态|STATUS|SSCODELIST|&nbsp;|
|运行模式|RELEASETYPE|SSCODELIST|&nbsp;|
|子状态|SUBSTATUS|TEXT|&nbsp;|
|后台体系|BACKGROUNDID|SSCODELIST|&nbsp;|
|描述|DESC|LONGTEXT|&nbsp;|
|附件|FILES|TEXT|&nbsp;|
|系统应用|FRONTAPPLICATION|SSCODELIST|&nbsp;|
|产品名称|PRODUCTNAME|PICKUPTEXT|&nbsp;|
|构建者|BUILDER|PICKUPDATA|&nbsp;|
|版本|BUILDNAME|PICKUPTEXT|&nbsp;|
|打包日期|BUILDDATE|PICKUPDATA|&nbsp;|
|产品|PRODUCT|PICKUP|&nbsp;|
|版本|BUILD|PICKUP|&nbsp;|
|平台/分支|BRANCH|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|完成的需求|默认规则|内容长度必须小于等于[65535]|
|里程碑|默认规则|内容长度必须小于等于[1]|
|ID|默认规则|默认规则|
|运行数据库|默认规则|内容长度必须小于等于[200]|
|遗留的Bug|默认规则|内容长度必须小于等于[65535]|
|解决的Bug|默认规则|内容长度必须小于等于[65535]|
|已删除|默认规则|内容长度必须小于等于[1]|
|消息通知用户|默认规则|内容长度必须小于等于[100]|
|重新构建|默认规则|默认规则|
|发布名称|默认规则|内容长度必须小于等于[255]|
|发布日期|发布日期不能大于当前日期|发布日期不能大于当前日期|
|发布日期|默认规则|默认规则|
|状态|默认规则|内容长度必须小于等于[20]|
|运行模式|默认规则|内容长度必须小于等于[200]|
|子状态|默认规则|内容长度必须小于等于[30]|
|后台体系|默认规则|内容长度必须小于等于[200]|
|描述|默认规则|内容长度必须小于等于[65535]|
|附件|默认规则|内容长度必须小于等于[1000]|
|系统应用|默认规则|内容长度必须小于等于[200]|
|产品名称|默认规则|内容长度必须小于等于[8]|
|构建者|默认规则|内容长度必须小于等于[30]|
|版本|默认规则|内容长度必须小于等于[150]|
|打包日期|默认规则|默认规则|
|产品|默认规则|默认规则|
|版本|默认规则|默认规则|
|平台/分支|默认规则|默认规则|

## 状态控制

|状态|行为控制模式| 控制行为 | 操作标识控制模式 | 控制操作 |
| --------   | ------------|------------|------------|------------|
|Value| 允许|  | 不允许 | SRFUR__RELEASE_ACTIVE<br> |
|Value| 允许|  | 不允许 | SRFUR__RELEASE_TERMINATE<br> |

状态

| 状态        |    状态值   |
| --------   |------------|
|正常|normal|
|停止维护|terminate|

## 行为
| 行为    | 类型    |  说明  |
| --------   |------------| ----- | 
|Create|内置方法|&nbsp;|
|Update|内置方法|&nbsp;|
|Remove|内置方法|&nbsp;|
|Get|内置方法|&nbsp;|
|GetDraft|内置方法|&nbsp;|
|状态变更（激活）|用户自定义|&nbsp;|
|批量解除关联Bug|用户自定义|&nbsp;|
|状态变更|用户自定义|&nbsp;|
|CheckKey|内置方法|&nbsp;|
|关联Bug|用户自定义|&nbsp;|
|关联Bug（解决Bug）|用户自定义|&nbsp;|
|关联Bug（遗留Bug）|用户自定义|&nbsp;|
|关联需求|用户自定义|&nbsp;|
|移动端发布计数器|实体处理逻辑|&nbsp;|
|一键发布|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|
|状态变更（停止维护）|用户自定义|&nbsp;|
|解除关联Bug|用户自定义|&nbsp;|

## 处理逻辑
* 移动端产品发布计数器 (MobReleaseCounter)
  
   

{% plantuml %}
hide footbox

发布 -> 发布: 获取解决的bug
发布 -> 发布: 获取完成需求数
发布 -> 发布: 获取遗留的bug
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|1|获取解决的bug |
|1|开始 | 
|2|获取完成需求数 |
|3|获取遗留的bug |
<center>移动端产品发布计数器</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|DEFAULT([MYSQL5](../../appendix/query_MYSQL5.md#Release_Default))|是|&nbsp;|
|GetList|获取产品发布([MYSQL5](../../appendix/query_MYSQL5.md#Release_GetList))|否|&nbsp;获取相关产品发布列表（根据产品指定）|
|ReportRelease|测试报告关联发布([MYSQL5](../../appendix/query_MYSQL5.md#Release_ReportRelease))|否|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#Release_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|DEFAULT|DEFAULT|是|&nbsp;|
|ReportRelease|测试报告关联发布|ReportRelease|否|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|里程碑(MARKER)|EQ|
|重新构建(REBUILD)|EQ|
|发布名称(NAME)|LIKE|
|发布日期(DATE)|LTANDEQ|
|状态(STATUS)|EQ|
|运行模式(RELEASETYPE)|EQ|
|产品名称(PRODUCTNAME)|EQ|
|产品名称(PRODUCTNAME)|LIKE|
|版本(BUILDNAME)|EQ|
|版本(BUILDNAME)|LIKE|
|产品(PRODUCT)|EQ|
|版本(BUILD)|EQ|
|平台/分支(BRANCH)|EQ|

## 导入模式
无


## 导出模式
* 数据导出

|ID|发布名称|版本|状态|发布日期|产品|
| :------: | :------: | :------: | :------: | :------: | :------: |
| - | - | - | - | - | - |
