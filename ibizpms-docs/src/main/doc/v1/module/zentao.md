# 基础管理模块(zentao)

  

## 实体关系
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


## 实体

| 实体编号    |    实体名称    |  实体类型     |  备注  |
| --------   |------------| -----   |  -------- | 
|ZT_ACTION|[系统日志](zentao/Action.md)|主实体|&nbsp;|
|ZT_BLOCK|[block](zentao/Block.md)|主实体|&nbsp;|
|ZT_BRANCH|[产品的分支和平台信息](zentao/Branch.md)|主实体|&nbsp;记录产品的分支和平台信息|
|ZT_BUG|[Bug](zentao/Bug.md)|主实体|&nbsp;|
|ZT_BUILD|[版本](zentao/Build.md)|主实体|&nbsp;|
|ZT_BURN|[burn](zentao/Burn.md)|主实体|&nbsp;|
|ZT_CASE|[测试用例](zentao/Case.md)|主实体|&nbsp;|
|ZT_CASESTEP|[用例步骤](zentao/CaseStep.md)|主实体|&nbsp;|
|ZT_COMPANY|[公司](zentao/Company.md)|主实体|&nbsp;|
|ZT_COMPILE|[compile](zentao/Compile.md)|主实体|&nbsp;|
|ZT_CONFIG|[配置](zentao/Config.md)|主实体|&nbsp;|
|ZT_CRON|[cron](zentao/Cron.md)|主实体|&nbsp;|
|ZT_DEPT|[部门](zentao/Dept.md)|主实体|&nbsp;|
|ZT_DOC|[文档](zentao/Doc.md)|主实体|&nbsp;|
|ZT_DOCCONTENT|[文档内容](zentao/DocContent.md)|主实体|&nbsp;|
|ZT_DOCLIB|[文档库](zentao/DocLib.md)|主实体|&nbsp;|
|ZT_EFFORT|[effort](zentao/Effort.md)|主实体|&nbsp;|
|ZT_ENTRY|[entry](zentao/Entry.md)|主实体|&nbsp;|
|ZT_EXTENSION|[extension](zentao/Extension.md)|主实体|&nbsp;|
|ZT_FILE|[附件](zentao/File.md)|主实体|&nbsp;|
|ZT_GROUP|[群组](zentao/Group.md)|主实体|&nbsp;|
|ZT_GROUPPRIV|[群组权限](zentao/GroupPriv.md)|主实体|&nbsp;|
|ZT_HISTORY|[操作历史](zentao/History.md)|主实体|&nbsp;|
|ZT_IM_CHAT|[ImChat](zentao/ImChat.md)|主实体|&nbsp;|
|ZT_IM_CHATUSER|[ImChatuser](zentao/ImChatuser.md)|主实体|&nbsp;|
|ZT_IM_CLIENT|[ImClient](zentao/ImClient.md)|主实体|&nbsp;|
|ZT_IM_CONFERENCE|[ImConference](zentao/ImConference.md)|主实体|&nbsp;|
|ZT_IM_CONFERENCEACTION|[ImConferenceaction](zentao/ImConferenceaction.md)|主实体|&nbsp;|
|ZT_IM_MESSAGE|[ImMessage](zentao/ImMessage.md)|主实体|&nbsp;|
|ZT_IM_MESSAGESTATUS|[ImMessagestatus](zentao/ImMessagestatus.md)|主实体|&nbsp;|
|ZT_IM_QUEUE|[ImQueue](zentao/ImQueue.md)|主实体|&nbsp;|
|ZT_JENKINS|[jenkins](zentao/Jenkins.md)|主实体|&nbsp;|
|ZT_JOB|[job](zentao/Job.md)|主实体|&nbsp;|
|ZT_LANG|[lang](zentao/Lang.md)|主实体|&nbsp;|
|ZT_LOG|[log](zentao/Log.md)|主实体|&nbsp;|
|ZT_MODULE|[模块](zentao/Module.md)|主实体|&nbsp;|
|ZT_NOTIFY|[通知](zentao/Notify.md)|主实体|&nbsp;|
|ZT_PRODUCT|[产品](zentao/Product.md)|主实体|&nbsp;|
|ZT_PRODUCTPLAN|[产品计划](zentao/ProductPlan.md)|主实体|&nbsp;|
|ZT_PROJECT|[项目](zentao/Project.md)|主实体|&nbsp;|
|ZT_PROJECTPRODUCT|[项目产品](zentao/ProjectProduct.md)|主实体|&nbsp;|
|ZT_PROJECTSTORY|[项目中需要做的需求](zentao/ProjectStory.md)|主实体|&nbsp;|
|ZT_RELATION|[relation](zentao/Relation.md)|主实体|&nbsp;|
|ZT_RELEASE|[发布](zentao/Release.md)|主实体|&nbsp;|
|ZT_REPO|[repo](zentao/Repo.md)|主实体|&nbsp;|
|ZT_REPOBRANCH|[repobranch](zentao/RepoBranch.md)|主实体|&nbsp;|
|ZT_REPOFILES|[repofiles](zentao/RepoFiles.md)|主实体|&nbsp;|
|ZT_REPOHISTORY|[repohistory](zentao/RepoHistory.md)|主实体|&nbsp;|
|ZT_SCORE|[score](zentao/Score.md)|主实体|&nbsp;|
|ZT_STORY|[需求](zentao/Story.md)|主实体|&nbsp;|
|ZT_STORYSPEC|[需求描述](zentao/StorySpec.md)|主实体|&nbsp;|
|ZT_STORYSTAGE|[需求阶段](zentao/StoryStage.md)|主实体|&nbsp;还不知道该表的插入时机|
|ZT_SUITECASE|[套件用例](zentao/SuiteCase.md)|主实体|&nbsp;|
|ZT_TASK|[任务](zentao/Task.md)|主实体|&nbsp;|
|ZT_TASKESTIMATE|[任务预计](zentao/TaskEstimate.md)|主实体|&nbsp;|
|TASKESTIMATESTATS|[任务工时统计](zentao/TaskEstimateStats.md)|主实体|&nbsp;|
|ZT_TEAM|[群组](zentao/Team.md)|主实体|&nbsp;|
|ZT_TESTREPORT|[测试报告](zentao/TestReport.md)|主实体|&nbsp;|
|ZT_TESTRESULT|[测试结果](zentao/TestResult.md)|主实体|&nbsp;|
|ZT_TESTRUN|[测试运行](zentao/TestRun.md)|主实体|&nbsp;|
|ZT_TESTSUITE|[测试套件](zentao/TestSuite.md)|主实体|&nbsp;|
|ZT_TESTTASK|[测试版本](zentao/TestTask.md)|主实体|&nbsp;|
|ZT_TODO|[待办](zentao/Todo.md)|主实体|&nbsp;|
|ZT_USER|[用户](zentao/User.md)|主实体|&nbsp;|
|ZT_USERCONTACT|[用户联系方式](zentao/UserContact.md)|主实体|&nbsp;|
|ZT_USERGROUP|[用户组](zentao/UserGroup.md)|主实体|&nbsp;|
|ZT_USERQUERY|[用户查询](zentao/UserQuery.md)|主实体|&nbsp;|
|ZT_USERTPL|[用户模板](zentao/UserTpl.md)|主实体|&nbsp;|
|ZT_USERVIEW|[用户视图](zentao/UserView.md)|主实体|&nbsp;|
|ZT_WEBHOOK|[网页钩子](zentao/WebHook.md)|主实体|&nbsp;|
