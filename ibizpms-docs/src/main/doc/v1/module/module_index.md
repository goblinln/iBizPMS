# 系统模块

| 模块编号   |   模块名称  |   备注 |
| --------  | ------------ | -----------| 
|ibiz|[iBiz增强模块](ibiz.md)||
|uaa|[uaa权限模块](uaa.md)||
|ibizplugin|[iBiz插件模块](ibizplugin.md)||
|zentao|[基础管理模块](zentao.md)||
|ibizsysmodel|[iBiz系统模型](ibizsysmodel.md)||
|report|[汇报模块](report.md)||
|ou|[ou组织用户管理模块](ou.md)||
|ibizpro|[iBizPro模块](ibizpro.md)||


## 关系图
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

{% plantuml %}

package uaa权限模块 {

class 系统角色 {
}

class 系统用户 {
}

class 用户角色关系 {
}


系统角色 *-- 系统角色 


系统角色 *-- 用户角色关系 


系统用户 *-- 用户角色关系 



}

hide members

{% endplantuml %}

{% plantuml %}

package iBiz插件模块 {

class 关键字 {
}

class 消息 {
}

class 系统插件 {
}

class 标签 {
}



}

hide members

{% endplantuml %}

{% plantuml %}

package 基础管理模块 {

class 系统日志 {
}

class block {
}

class 产品的分支和平台信息 {
}

class Bug {
}

class 版本 {
}

class burn {
}

class 测试用例 {
}

class 用例步骤 {
}

class 公司 {
}

class compile {
}

class 配置 {
}

class cron {
}

class 部门 {
}

class 文档 {
}

class 文档内容 {
}

class 文档库 {
}

class effort {
}

class entry {
}

class extension {
}

class 附件 {
}

class 群组 {
}

class 群组权限 {
}

class 操作历史 {
}

class ImChat {
}

class ImChatuser {
}

class ImClient {
}

class ImConference {
}

class ImConferenceaction {
}

class ImMessage {
}

class ImMessagestatus {
}

class ImQueue {
}

class jenkins {
}

class job {
}

class lang {
}

class log {
}

class 模块 {
}

class 通知 {
}

class 产品 {
}

class 产品计划 {
}

class 项目 {
}

class 项目产品 {
}

class 项目中需要做的需求 {
}

class relation {
}

class 发布 {
}

class repo {
}

class repobranch {
}

class repofiles {
}

class repohistory {
}

class score {
}

class 需求 {
}

class 需求描述 {
}

class 需求阶段 {
}

class 套件用例 {
}

class 任务 {
}

class 任务预计 {
}

class 任务工时统计 {
}

class 群组 {
}

class 测试报告 {
}

class 测试结果 {
}

class 测试运行 {
}

class 测试套件 {
}

class 测试版本 {
}

class 待办 {
}

class 用户 {
}

class 用户联系方式 {
}

class 用户组 {
}

class 用户查询 {
}

class 用户模板 {
}

class 用户视图 {
}

class 网页钩子 {
}


项目 *-- 系统日志 


产品 *-- 产品的分支和平台信息 


产品的分支和平台信息 *-- Bug 


Bug *-- Bug 


测试用例 *-- Bug 


entry *-- Bug 


模块 *-- Bug 


产品计划 *-- Bug 


产品 *-- Bug 


项目 *-- Bug 


repo *-- Bug 


需求 *-- Bug 


需求 *-- Bug 


任务 *-- Bug 


任务 *-- Bug 


测试版本 *-- Bug 


产品的分支和平台信息 *-- 版本 


产品 *-- 版本 


项目 *-- 版本 


项目 *-- burn 


任务 *-- burn 


测试用例 *-- 用例步骤 


产品的分支和平台信息 *-- 测试用例 


Bug *-- 测试用例 


测试用例 *-- 测试用例 


模块 *-- 测试用例 


产品 *-- 测试用例 


需求 *-- 测试用例 


测试套件 *-- 测试用例 


文档 *-- 文档内容 


产品 *-- 文档库 


项目 *-- 文档库 


文档库 *-- 文档 


模块 *-- 文档 


产品 *-- 文档 


项目 *-- 文档 


系统日志 *-- 操作历史 


产品的分支和平台信息 *-- 模块 


产品的分支和平台信息 *-- 产品计划 


产品 *-- 产品计划 


模块 *-- 产品 


产品的分支和平台信息 *-- 项目产品 


产品计划 *-- 项目产品 


产品 *-- 项目产品 


项目 *-- 项目产品 


产品 *-- 项目中需要做的需求 


项目 *-- 项目中需要做的需求 


需求 *-- 项目中需要做的需求 


产品的分支和平台信息 *-- 发布 


版本 *-- 发布 


产品 *-- 发布 


需求 *-- 需求描述 


产品的分支和平台信息 *-- 需求阶段 


需求 *-- 需求阶段 


产品的分支和平台信息 *-- 需求 


Bug *-- 需求 


Bug *-- 需求 


产品 *-- 需求 


需求 *-- 需求 


测试用例 *-- 套件用例 


产品 *-- 套件用例 


测试套件 *-- 套件用例 


任务 *-- 任务预计 


Bug *-- 任务 


产品计划 *-- 任务 


项目 *-- 任务 


需求 *-- 任务 


产品 *-- 测试报告 


项目 *-- 测试报告 


测试用例 *-- 测试结果 


compile *-- 测试结果 


job *-- 测试结果 


测试运行 *-- 测试结果 


测试用例 *-- 测试运行 


测试版本 *-- 测试运行 


产品 *-- 测试套件 


版本 *-- 测试版本 


产品 *-- 测试版本 


项目 *-- 测试版本 


用例步骤 *-- 用例步骤 


部门 *-- 部门 


模块 *-- 模块 


产品计划 *-- 产品计划 


项目 *-- 项目 


repofiles *-- repofiles 


需求 *-- 需求 


任务 *-- 任务 



}

hide members

{% endplantuml %}

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

{% plantuml %}

package 汇报模块 {

class 日报 {
}

class 月报 {
}

class 汇报汇总 {
}

class 汇报角色配置 {
}

class 汇报 {
}

class 周报 {
}



}

hide members

{% endplantuml %}

{% plantuml %}

package ou组织用户管理模块 {

class 部门 {
}

class 人员 {
}

class 单位 {
}

class 岗位 {
}

class 组 {
}

class 组成员 {
}


部门 *-- 人员 


单位 *-- 人员 


部门 *-- 部门 


单位 *-- 部门 


单位 *-- 单位 


人员 *-- 组成员 


岗位 *-- 组成员 


组 *-- 组成员 



}

hide members

{% endplantuml %}

{% plantuml %}

package iBizPro模块 {

class 用户工时统计 {
}

class 平台产品 {
}

class 需求 {
}

class 需求模块 {
}

class 系统模板 {
}

class 运行生产系统 {
}

class 索引检索 {
}

class 产品日报 {
}

class 产品月报 {
}

class 产品周报 {
}

class 项目日报 {
}

class 项目月报 {
}

class 项目周报 {
}

class 计划模板 {
}

class 计划模板详情 {
}

class 系统配置表 {
}

class 产品汇报用户任务 {
}

class 项目汇报用户任务 {
}

class 项目工时统计 {
}


平台产品 *-- 需求模块 


需求模块 *-- 需求模块 


平台产品 *-- 需求 


需求模块 *-- 需求 


计划模板 *-- 计划模板详情 



}

hide members

{% endplantuml %}

