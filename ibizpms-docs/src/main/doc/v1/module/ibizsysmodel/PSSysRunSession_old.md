# 系统运行会话(PSSYSRUNSESSION)

  

## 关系
{% plantuml %}
系统应用 *-- 系统运行会话 
系统应用 *-- 系统运行会话 
系统服务接口 *-- 系统运行会话 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|运行参数4|RUNPARAM4|TEXT|&nbsp;|
|系统运行会话名称|PSSYSRUNSESSIONNAME|TEXT|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|系统运行会话标识|PSSYSRUNSESSIONID|GUID|&nbsp;|
|服务体系|PSSYSSFPUBID|TEXT|&nbsp;|
|打包模式|PACKMODE|SSCODELIST|&nbsp;|
|运行数据库|PSSYSTEMDBCFGNAME|TEXT|&nbsp;|
|系统|PSSYSTEMID|TEXT|&nbsp;|
|运行参数5|RUNPARAM5|INT|&nbsp;|
|开始时间|STARTTIME|DATETIME|&nbsp;|
|运行数据库|PSSYSTEMDBCFGID|TEXT|&nbsp;|
|运行模式|RUNMODE|SSCODELIST|&nbsp;|
|模板出错时终止|STOPWHENTEMPLERROR|YESNO|&nbsp;|
|运行参数|RUNPARAM|TEXT|&nbsp;|
|运行状态|RUNSTATE|NSCODELIST|&nbsp;|
|输出调试信息|DEBUGMODE|YESNO|&nbsp;|
|结束时间|ENDTIME|DATETIME|&nbsp;|
|运行参数6|RUNPARAM6|INT|&nbsp;|
|服务体系|PSSYSSFPUBNAME|TEXT|&nbsp;|
|备注|MEMO|LONGTEXT_1000|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|修复数据结构|FIXDBMODEL|YESNO|&nbsp;|
|重新构建|REBUILDMODE|NSCODELIST|&nbsp;|
|系统|PSSYSTEMNAME|TEXT|&nbsp;|
|运行参数3|RUNPARAM3|TEXT|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|控制台标识|PSDSCONSOLEID|TEXT|&nbsp;|
|签入版本库|ENABLEVC|YESNO|&nbsp;|
|运行参数2|RUNPARAM2|TEXT|&nbsp;|
|系统应用2|PSSYSAPPNAME2|PICKUPTEXT|&nbsp;|
|系统服务接口|PSSYSSERVICEAPINAME|PICKUPTEXT|&nbsp;|
|系统应用|PSSYSAPPNAME|PICKUPTEXT|&nbsp;|
|系统应用2|PSSYSAPPID2|PICKUP|&nbsp;|
|系统服务接口|PSSYSSERVICEAPIID|PICKUP|&nbsp;|
|系统应用|PSSYSAPPID|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|运行参数4|默认规则|内容长度必须小于等于[100]|
|系统运行会话名称|默认规则|内容长度必须小于等于[200]|
|建立时间|默认规则|默认规则|
|更新时间|默认规则|默认规则|
|系统运行会话标识|默认规则|内容长度必须小于等于[100]|
|服务体系|默认规则|内容长度必须小于等于[100]|
|打包模式|默认规则|内容长度必须小于等于[20]|
|运行数据库|默认规则|内容长度必须小于等于[100]|
|系统|默认规则|内容长度必须小于等于[100]|
|运行参数5|默认规则|默认规则|
|开始时间|默认规则|默认规则|
|运行数据库|默认规则|内容长度必须小于等于[100]|
|运行模式|默认规则|内容长度必须小于等于[100]|
|模板出错时终止|默认规则|默认规则|
|运行参数|默认规则|内容长度必须小于等于[100]|
|运行状态|默认规则|默认规则|
|输出调试信息|默认规则|默认规则|
|结束时间|默认规则|默认规则|
|运行参数6|默认规则|默认规则|
|服务体系|默认规则|内容长度必须小于等于[100]|
|备注|默认规则|内容长度必须小于等于[2000]|
|更新人|默认规则|内容长度必须小于等于[60]|
|修复数据结构|默认规则|默认规则|
|重新构建|默认规则|默认规则|
|系统|默认规则|内容长度必须小于等于[100]|
|运行参数3|默认规则|内容长度必须小于等于[100]|
|建立人|默认规则|内容长度必须小于等于[60]|
|控制台标识|默认规则|内容长度必须小于等于[100]|
|签入版本库|默认规则|默认规则|
|运行参数2|默认规则|内容长度必须小于等于[100]|
|系统应用2|默认规则|内容长度必须小于等于[200]|
|系统服务接口|默认规则|内容长度必须小于等于[200]|
|系统应用|默认规则|内容长度必须小于等于[200]|
|系统应用2|默认规则|内容长度必须小于等于[100]|
|系统服务接口|默认规则|内容长度必须小于等于[100]|
|系统应用|默认规则|内容长度必须小于等于[100]|

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
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#PSSysRunSession_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#PSSysRunSession_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|系统运行会话名称(PSSYSRUNSESSIONNAME)|LIKE|
|打包模式(PACKMODE)|EQ|
|运行模式(RUNMODE)|EQ|
|运行状态(RUNSTATE)|EQ|
|重新构建(REBUILDMODE)|EQ|
|系统应用2(PSSYSAPPNAME2)|EQ|
|系统应用2(PSSYSAPPNAME2)|LIKE|
|系统服务接口(PSSYSSERVICEAPINAME)|EQ|
|系统服务接口(PSSYSSERVICEAPINAME)|LIKE|
|系统应用(PSSYSAPPNAME)|EQ|
|系统应用(PSSYSAPPNAME)|LIKE|
|系统应用2(PSSYSAPPID2)|EQ|
|系统服务接口(PSSYSSERVICEAPIID)|EQ|
|系统应用(PSSYSAPPID)|EQ|

## 导入模式
无


## 导出模式
无
