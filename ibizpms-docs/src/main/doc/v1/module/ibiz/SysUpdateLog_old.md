# 更新日志(SYS_UPDATE_LOG)

  

## 关系
{% plantuml %}
更新日志 *-- 系统更新功能 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|系统更新日志标识|SYS_UPDATE_LOGID|GUID|&nbsp;|
|更新平台|UPDATEBRANCH|SSCODELIST|&nbsp;|
|更新功能列表详情|UPDATEFEATURES|ONE2MANYDATA|&nbsp;|
|更新说明|UPDESC|HTMLTEXT|&nbsp;|
|更新名称|SYS_UPDATE_LOGNAME|TEXT|&nbsp;|
|最新更新|LATESTUPDATE|YESNO|&nbsp;|
|更新日期|UPDATE|DATE|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|更新时间|默认规则|默认规则|
|更新人|默认规则|内容长度必须小于等于[60]|
|建立人|默认规则|内容长度必须小于等于[60]|
|建立时间|默认规则|默认规则|
|系统更新日志标识|默认规则|内容长度必须小于等于[100]|
|更新平台|默认规则|内容长度必须小于等于[60]|
|更新功能列表详情|默认规则|内容长度必须小于等于[1048576]|
|更新说明|默认规则|内容长度必须小于等于[1048576]|
|更新名称|默认规则|内容长度必须小于等于[200]|
|最新更新|默认规则|默认规则|
|更新日期|默认规则|默认规则|

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
|获取最新更新信息|用户自定义|&nbsp;|
|Save|内置方法|&nbsp;|

## 处理逻辑
* 更新最新更新标识 (UpdateLasted)
  
   

{% plantuml %}
hide footbox

更新日志 -> 更新日志: 更新最新更新标识
{% endplantuml %}

| 步骤       | 操作        |
| --------   | --------   |
|0|开始 | 
|1|更新最新更新标识 |
<center>更新最新更新标识</center>

## 查询集合

* **查询**

| 查询编号 | 查询名称       | 默认查询 |   备注|
| --------  | --------   | --------   | ----- |
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#SysUpdateLog_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#SysUpdateLog_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|系统更新日志标识(SYS_UPDATE_LOGID)|EQ|
|更新平台(UPDATEBRANCH)|EQ|
|更新名称(SYS_UPDATE_LOGNAME)|LIKE|
|最新更新(LATESTUPDATE)|EQ|

## 导入模式
无


## 导出模式
无
