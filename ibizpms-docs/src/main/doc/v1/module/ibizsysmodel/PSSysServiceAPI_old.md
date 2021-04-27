# 系统服务接口(PSSYSSERVICEAPI)

  

## 关系
{% plantuml %}
系统服务接口 *-- 外部服务接口 
系统服务接口 *-- 系统应用 
系统服务接口 *-- 系统运行会话 
系统模块 *-- 系统服务接口 
hide members
{% endplantuml %}

## 属性

| 属性名称        |    中文名称    | 类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|认证客户端标识|AUTHCLIENTID|TEXT|&nbsp;|
|接口模式|APIMODE|NSCODELIST|&nbsp;|
|更新时间|UPDATEDATE|DATETIME|&nbsp;|
|平台预定义类型|PREDEFINEDTYPE|SSCODELIST|&nbsp;|
|系统服务接口标识|PSSYSSERVICEAPIID|GUID|&nbsp;|
|服务参数4|SERVICEPARAM4|TEXT|&nbsp;|
|配置标记|CFGTAG|TEXT|&nbsp;|
|服务参数|SERVICEPARAM|TEXT|&nbsp;|
|建立人|CREATEMAN|TEXT|&nbsp;|
|服务代码名称|SERVICECODENAME|TEXT|&nbsp;|
|建立时间|CREATEDATE|DATETIME|&nbsp;|
|模型锁标志|LOCKFLAG|NSCODELIST|&nbsp;|
|默认查询请求方式|DEFSELECTREQMETHOD|SSCODELIST|&nbsp;|
|服务参数2|SERVICEPARAM2|TEXT|&nbsp;|
|代码名称|CODENAME|TEXT|&nbsp;|
|认证参数4|AUTHPARAM4|TEXT|&nbsp;|
|用户分类|USERCAT|SSCODELIST|&nbsp;|
|认证模式|AUTHMODE|SSCODELIST|&nbsp;|
|用户标记|USERTAG|TEXT|&nbsp;|
|接口类型|APITYPE|SSCODELIST|&nbsp;|
|系统|PSSYSTEMNAME|TEXT|&nbsp;|
|认证参数2|AUTHPARAM2|TEXT|&nbsp;|
|配置模型存储标识|CFGPSMODELSTORAGEID|TEXT|&nbsp;|
|接口标记|APITAG|TEXT|&nbsp;|
|用户标记4|USERTAG4|TEXT|&nbsp;|
|服务参数3|SERVICEPARAM3|TEXT|&nbsp;|
|默认结果集请求方式|DEFDEDATASETREQMETHOD|SSCODELIST|&nbsp;|
|唯一标识|UNIQUETAG|TEXT|&nbsp;|
|服务类型|SERVICETYPE|SSCODELIST|&nbsp;|
|系统服务接口名称|PSSYSSERVICEAPINAME|TEXT|&nbsp;|
|系统|PSSYSTEMID|TEXT|&nbsp;|
|用户标记3|USERTAG3|TEXT|&nbsp;|
|是否启用|VALIDFLAG|YESNO|&nbsp;|
|接口标记2|APITAG2|TEXT|&nbsp;|
|认证token路径|AUTHCHECKTOKENURI|TEXT|&nbsp;|
|默认实体行为请求方式|DEFDEACTIONREQMETHOD|SSCODELIST|&nbsp;|
|认证参数|AUTHPARAM|TEXT|&nbsp;|
|更新人|UPDATEMAN|TEXT|&nbsp;|
|认证参数3|AUTHPARAM3|TEXT|&nbsp;|
|用户标记2|USERTAG2|TEXT|&nbsp;|
|认证客户端密钥|AUTHCLIENTSECRET|TEXT|&nbsp;|
|版本|VER|INT|&nbsp;|
|备注|MEMO|LONGTEXT_1000|&nbsp;|
|系统模块|PSMODULENAME|PICKUPTEXT|&nbsp;|
|系统模块|PSMODULEID|PICKUP|&nbsp;|

## 值规则
| 属性名称    | 规则    |  说明  |
| --------   |------------| ----- | 
|认证客户端标识|默认规则|内容长度必须小于等于[100]|
|接口模式|默认规则|默认规则|
|更新时间|默认规则|默认规则|
|平台预定义类型|默认规则|内容长度必须小于等于[50]|
|系统服务接口标识|默认规则|内容长度必须小于等于[100]|
|服务参数4|默认规则|内容长度必须小于等于[100]|
|配置标记|默认规则|内容长度必须小于等于[100]|
|服务参数|默认规则|内容长度必须小于等于[200]|
|建立人|默认规则|内容长度必须小于等于[60]|
|服务代码名称|默认规则|内容长度必须小于等于[60]|
|建立时间|默认规则|默认规则|
|模型锁标志|默认规则|默认规则|
|默认查询请求方式|默认规则|内容长度必须小于等于[20]|
|服务参数2|默认规则|内容长度必须小于等于[200]|
|代码名称|默认规则|内容长度必须小于等于[40]|
|认证参数4|默认规则|内容长度必须小于等于[100]|
|用户分类|默认规则|内容长度必须小于等于[10]|
|认证模式|默认规则|内容长度必须小于等于[50]|
|用户标记|默认规则|内容长度必须小于等于[200]|
|接口类型|默认规则|内容长度必须小于等于[40]|
|系统|默认规则|内容长度必须小于等于[100]|
|认证参数2|默认规则|内容长度必须小于等于[200]|
|配置模型存储标识|默认规则|内容长度必须小于等于[100]|
|接口标记|默认规则|内容长度必须小于等于[100]|
|用户标记4|默认规则|内容长度必须小于等于[50]|
|服务参数3|默认规则|内容长度必须小于等于[100]|
|默认结果集请求方式|默认规则|内容长度必须小于等于[20]|
|唯一标识|默认规则|内容长度必须小于等于[100]|
|服务类型|默认规则|内容长度必须小于等于[30]|
|系统服务接口名称|默认规则|内容长度必须小于等于[200]|
|系统|默认规则|内容长度必须小于等于[100]|
|用户标记3|默认规则|内容长度必须小于等于[50]|
|是否启用|默认规则|默认规则|
|接口标记2|默认规则|内容长度必须小于等于[100]|
|认证token路径|默认规则|内容长度必须小于等于[500]|
|默认实体行为请求方式|默认规则|内容长度必须小于等于[20]|
|认证参数|默认规则|内容长度必须小于等于[200]|
|更新人|默认规则|内容长度必须小于等于[60]|
|认证参数3|默认规则|内容长度必须小于等于[100]|
|用户标记2|默认规则|内容长度必须小于等于[200]|
|认证客户端密钥|默认规则|内容长度必须小于等于[1000]|
|版本|默认规则|默认规则|
|备注|默认规则|内容长度必须小于等于[2000]|
|系统模块|默认规则|内容长度必须小于等于[200]|
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
|DEFAULT|数据查询([MYSQL5](../../appendix/query_MYSQL5.md#PSSysServiceAPI_Default))|是|&nbsp;|
|VIEW|默认（全部数据）([MYSQL5](../../appendix/query_MYSQL5.md#PSSysServiceAPI_View))|否|&nbsp;|

* **数据集合**

| 集合编号 | 集合名称   |  包含查询  | 默认集合 |   备注|
| --------  | --------   | -------- | --------   | ----- |
|DEFAULT|数据集|DEFAULT|是|&nbsp;|

## 查询模式
| 属性      |    搜索模式     |
| --------   |------------|
|接口模式(APIMODE)|EQ|
|平台预定义类型(PREDEFINEDTYPE)|EQ|
|系统服务接口标识(PSSYSSERVICEAPIID)|EQ|
|模型锁标志(LOCKFLAG)|EQ|
|默认查询请求方式(DEFSELECTREQMETHOD)|EQ|
|用户分类(USERCAT)|EQ|
|认证模式(AUTHMODE)|EQ|
|接口类型(APITYPE)|EQ|
|默认结果集请求方式(DEFDEDATASETREQMETHOD)|EQ|
|服务类型(SERVICETYPE)|EQ|
|系统服务接口名称(PSSYSSERVICEAPINAME)|LIKE|
|默认实体行为请求方式(DEFDEACTIONREQMETHOD)|EQ|
|系统模块(PSMODULENAME)|EQ|
|系统模块(PSMODULENAME)|LIKE|
|系统模块(PSMODULEID)|EQ|

## 导入模式
无


## 导出模式
无
