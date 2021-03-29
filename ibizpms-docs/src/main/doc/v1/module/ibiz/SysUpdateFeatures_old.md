# 系统更新功能(SYS_UPDATE_FEATURES)

  

## 关系
{% plantuml %}
更新日志 *-- 系统更新功能 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|系统更新功能名称|SYS_UPDATE_FEATURESNAME|TEXT|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|更新类型|TYPE|SSCODELIST|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|更新功能|UPFEATURES|TEXT|&nbsp;|
|系统更新功能标识|SYS_UPDATE_FEATURESID|GUID|&nbsp;|
|展示顺序|DISPLAYORDER|INT|&nbsp;|
|功能描述|FEATURESDESC|HTMLTEXT|&nbsp;|
|所属更新|SYS_UPDATE_LOGNAME|PICKUPTEXT|&nbsp;|
|系统更新日志标识|SYS_UPDATE_LOGID|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|系统更新功能名称|默认规则|内容长度必须小于等于[200]|
|更新时间|默认规则|默认规则|
|建立人|默认规则|内容长度必须小于等于[60]|
|更新人|默认规则|内容长度必须小于等于[60]|
|更新类型|默认规则|内容长度必须小于等于[60]|
|建立时间|默认规则|默认规则|
|更新功能|默认规则|内容长度必须小于等于[100]|
|系统更新功能标识|默认规则|内容长度必须小于等于[100]|
|展示顺序|默认规则|默认规则|
|功能描述|默认规则|内容长度必须小于等于[1048576]|
|所属更新|默认规则|内容长度必须小于等于[200]|
|系统更新日志标识|默认规则|内容长度必须小于等于[100]|

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
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#SysUpdateFeatures_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#SysUpdateFeatures_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|系统更新功能名称(SYS_UPDATE_FEATURESNAME)|LIKE|
|更新类型(TYPE)|EQ|
|所属更新(SYS_UPDATE_LOGNAME)|EQ|
|所属更新(SYS_UPDATE_LOGNAME)|LIKE|
|系统更新日志标识(SYS_UPDATE_LOGID)|EQ|

## 导入模式
无


## 导出模式
无
