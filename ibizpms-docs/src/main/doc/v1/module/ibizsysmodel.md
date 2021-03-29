# iBiz系统模型(ibizsysmodel)

  

## 实体关系
{% plantuml %}

package iBiz系统模型 {

class 实体属性 {
}

class 实体 {
}

class 系统模块 {
}

class 外部接口实体 {
}

class 外部服务接口 {
}

class 系统应用 {
}

class 系统需求项 {
}

class 系统需求模块 {
}

class 系统运行会话 {
}

class 后台服务架构 {
}

class 系统服务接口 {
}

class 系统数据库 {
}


系统模块 *-- 实体 


外部接口实体 *-- 实体 


外部服务接口 *-- 实体 


系统需求项 *-- 实体 


实体 *-- 实体属性 


实体属性 *-- 实体属性 


实体属性 *-- 实体属性 


实体属性 *-- 实体属性 


实体属性 *-- 实体属性 


实体属性 *-- 实体属性 


外部服务接口 *-- 外部接口实体 


系统模块 *-- 外部服务接口 


系统服务接口 *-- 外部服务接口 


系统服务接口 *-- 系统应用 


系统需求项 *-- 系统需求项 


系统需求模块 *-- 系统需求项 


系统模块 *-- 系统需求模块 


系统需求模块 *-- 系统需求模块 


系统应用 *-- 系统运行会话 


系统应用 *-- 系统运行会话 


系统服务接口 *-- 系统运行会话 


系统模块 *-- 系统服务接口 


后台服务架构 *-- 后台服务架构 



}

hide members

{% endplantuml %}


## 实体

| 实体编号    |    实体名称    |  实体类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|PSDEFIELD|[实体属性](ibizsysmodel/PSDEField.md)|主实体|&nbsp;|
|PSDATAENTITY|[实体](ibizsysmodel/PSDataEntity.md)|主实体|&nbsp;|
|PSMODULE|[系统模块](ibizsysmodel/PSModule.md)|主实体|&nbsp;|
|PSSUBSYSSADE|[外部接口实体](ibizsysmodel/PSSubSysSADE.md)|主实体|&nbsp;|
|PSSUBSYSSERVICEAPI|[外部服务接口](ibizsysmodel/PSSubSysServiceAPI.md)|主实体|&nbsp;|
|PSSYSAPP|[系统应用](ibizsysmodel/PSSysApp.md)|主实体|&nbsp;|
|PSSYSREQITEM|[系统需求项](ibizsysmodel/PSSysReqItem.md)|主实体|&nbsp;|
|PSSYSREQMODULE|[系统需求模块](ibizsysmodel/PSSysReqModule.md)|主实体|&nbsp;|
|PSSYSRUNSESSION|[系统运行会话](ibizsysmodel/PSSysRunSession.md)|主实体|&nbsp;|
|PSSYSSFPUB|[后台服务架构](ibizsysmodel/PSSysSFPub.md)|主实体|&nbsp;|
|PSSYSSERVICEAPI|[系统服务接口](ibizsysmodel/PSSysServiceAPI.md)|主实体|&nbsp;|
|PSSYSTEMDBCFG|[系统数据库](ibizsysmodel/PSSystemDBCfg.md)|主实体|&nbsp;|
