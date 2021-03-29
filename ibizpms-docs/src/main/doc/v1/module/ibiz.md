# iBiz增强模块(ibiz)

  

## 实体关系
{% plantuml %}

package iBiz增强模块 {

class Bug统计 {
}

class 测试用例统计 {
}

class 公司动态汇总 {
}

class 文档库分类 {
}

class 动态数据看板 {
}

class 员工负载表 {
}

class 文档 {
}

class 实体 {
}

class 代理 {
}

class 测试用例 {
}

class 收藏 {
}

class 用例库 {
}

class 用例库用例步骤 {
}

class 用例库用例步骤 {
}

class 用例库模块 {
}

class 我的地盘 {
}

class 项目相关成员 {
}

class 置顶 {
}

class 产品团队 {
}

class 产品生命周期 {
}

class 产品线 {
}

class 需求模块 {
}

class 产品统计 {
}

class 产品汇总表 {
}

class 任务模块 {
}

class 项目统计 {
}

class 项目团队 {
}

class 系统更新功能 {
}

class 更新日志 {
}

class 待办消息记录 {
}

class 任务统计 {
}

class 任务团队 {
}

class 测试模块 {
}

class 用户年度工作内容统计 {
}


用例库模块 *-- 测试用例 


用例库 *-- 测试用例 


文档库分类 *-- 文档库分类 


测试用例 *-- 用例库用例步骤 


用例库用例步骤 *-- 用例库用例步骤 


用例库模块 *-- 用例库模块 


用例库 *-- 用例库模块 


需求模块 *-- 需求模块 


任务模块 *-- 任务模块 


测试模块 *-- 测试模块 


更新日志 *-- 系统更新功能 



}

hide members

{% endplantuml %}


## 实体

| 实体编号    |    实体名称    |  实体类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|IBZ_BUGSTATS|[Bug统计](ibiz/BugStats.md)|主实体|&nbsp;|
|IBZ_CASESTATS|[测试用例统计](ibiz/CaseStats.md)|主实体|&nbsp;|
|IBZ_COMPANYSTATS|[公司动态汇总](ibiz/CompanyStats.md)|主实体|&nbsp;|
|IBZ_DOCLIBMODULE|[文档库分类](ibiz/DocLibModule.md)|主实体|&nbsp;|
|DYNADASHBOARD|[动态数据看板](ibiz/DynaDashboard.md)|主实体|&nbsp;|
|IBZ_EMPLOYEELOAD|[员工负载表](ibiz/EmpLoyeeload.md)|主实体|&nbsp;|
|IBZ_DOC|[文档](ibiz/IBzDoc.md)|主实体|&nbsp;|
|IBZ_LOGIN|[实体](ibiz/IbiLogin.md)|主实体|&nbsp;|
|IBZ_AGENT|[代理](ibiz/IbzAgent.md)|主实体|&nbsp;|
|IBZ_CASE|[测试用例](ibiz/IbzCase.md)|主实体|&nbsp;|
|IBZ_FAVORITES|[收藏](ibiz/IbzFavorites.md)|主实体|&nbsp;|
|IBZ_LIB|[用例库](ibiz/IbzLib.md)|主实体|&nbsp;|
|IBZ_CASESTEP|[用例库用例步骤](ibiz/IbzLibCaseStep.md)|主实体|&nbsp;|
|IBZ_LIBCASESTEPS|[用例库用例步骤](ibiz/IbzLibCaseSteps.md)|主实体|&nbsp;|
|IBZ_LIBMODULE|[用例库模块](ibiz/IbzLibModule.md)|主实体|&nbsp;|
|IBZ_MYTERRITORY|[我的地盘](ibiz/IbzMyTerritory.md)|主实体|&nbsp;|
|IBZ_PROJECTMEMBER|[项目相关成员](ibiz/IbzProjectMember.md)|主实体|&nbsp;|
|IBZ_TOP|[置顶](ibiz/IbzTop.md)|主实体|&nbsp;|
|IBZ_PRODUCTTEAM|[产品团队](ibiz/PRODUCTTEAM.md)|主实体|&nbsp;|
|IBZ_PRODUCTLIFE|[产品生命周期](ibiz/ProductLife.md)|主实体|&nbsp;|
|IBZ_PRODUCTLINE|[产品线](ibiz/ProductLine.md)|主实体|&nbsp;|
|IBZ_PRODUCTMODULE|[需求模块](ibiz/ProductModule.md)|主实体|&nbsp;产品需求模块|
|IBZ_PRODUCTSTATS|[产品统计](ibiz/ProductStats.md)|主实体|&nbsp;|
|IBZ_PRODUCTSUM|[产品汇总表](ibiz/ProductSum.md)|主实体|&nbsp;|
|IBZ_PROJECTMODULE|[任务模块](ibiz/ProjectModule.md)|主实体|&nbsp;项目任务模块|
|IBZ_PROJECTSTATS|[项目统计](ibiz/ProjectStats.md)|主实体|&nbsp;|
|IBZ_PROJECTTEAM|[项目团队](ibiz/ProjectTeam.md)|主实体|&nbsp;|
|SYS_UPDATE_FEATURES|[系统更新功能](ibiz/SysUpdateFeatures.md)|主实体|&nbsp;|
|SYS_UPDATE_LOG|[更新日志](ibiz/SysUpdateLog.md)|主实体|&nbsp;|
|TASKMSGRECORD|[待办消息记录](ibiz/TaskMsgRecord.md)|主实体|&nbsp;用于保存待办消息发送成功后，返回的ID|
|IBZ_TASKSTATS|[任务统计](ibiz/TaskStats.md)|主实体|&nbsp;|
|IBZ_TASKTEAM|[任务团队](ibiz/TaskTeam.md)|主实体|&nbsp;任务团队|
|IBZ_TESTMODULE|[测试模块](ibiz/TestModule.md)|主实体|&nbsp;|
|IBZ_USERYEARWORKSTATS|[用户年度工作内容统计](ibiz/UserYearWorkStats.md)|主实体|&nbsp;|
