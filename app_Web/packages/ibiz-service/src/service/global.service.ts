/**
 * 全局服务
 *
 * @export
 * @class GlobalService
 */
export class GlobalService {

    /**
     * 获取数据服务
     * 
     * @param name 实体名称
     * memberof GlobalService
     */
    public async getService(name:string){
        return (this as any)[`get${name}Service`]();
    }

    /**
     * 产品计划服务
     *
     * @return {ProductPlan}
     * @memberof GlobalService
     */
    async getProductPlanService() {
        return (await import('./product-plan/product-plan.service')).default.getInstance();
    }
    /**
     * 项目产品服务
     *
     * @return {ProductProject}
     * @memberof GlobalService
     */
    async getProductProjectService() {
        return (await import('./product-project/product-project.service')).default.getInstance();
    }
    /**
     * 测试用例服务
     *
     * @return {TestCase}
     * @memberof GlobalService
     */
    async getTestCaseService() {
        return (await import('./test-case/test-case.service')).default.getInstance();
    }
    /**
     * 产品服务
     *
     * @return {Product}
     * @memberof GlobalService
     */
    async getProductService() {
        return (await import('./product/product.service')).default.getInstance();
    }
    /**
     * burn服务
     *
     * @return {ProjectBurn}
     * @memberof GlobalService
     */
    async getProjectBurnService() {
        return (await import('./project-burn/project-burn.service')).default.getInstance();
    }
    /**
     * 测试服务
     *
     * @return {Test}
     * @memberof GlobalService
     */
    async getTestService() {
        return (await import('./test/test.service')).default.getInstance();
    }
    /**
     * 需求描述服务
     *
     * @return {StorySpec}
     * @memberof GlobalService
     */
    async getStorySpecService() {
        return (await import('./story-spec/story-spec.service')).default.getInstance();
    }
    /**
     * 产品的分支和平台信息服务
     *
     * @return {ProductBranch}
     * @memberof GlobalService
     */
    async getProductBranchService() {
        return (await import('./product-branch/product-branch.service')).default.getInstance();
    }
    /**
     * 系统日志服务
     *
     * @return {Action}
     * @memberof GlobalService
     */
    async getActionService() {
        return (await import('./action/action.service')).default.getInstance();
    }
    /**
     * 任务预计服务
     *
     * @return {TaskEstimate}
     * @memberof GlobalService
     */
    async getTaskEstimateService() {
        return (await import('./task-estimate/task-estimate.service')).default.getInstance();
    }
    /**
     * 需求服务
     *
     * @return {Story}
     * @memberof GlobalService
     */
    async getStoryService() {
        return (await import('./story/story.service')).default.getInstance();
    }
    /**
     * 动态搜索栏服务
     *
     * @return {DynaFilter}
     * @memberof GlobalService
     */
    async getDynaFilterService() {
        return (await import('./dyna-filter/dyna-filter.service')).default.getInstance();
    }
    /**
     * 项目服务
     *
     * @return {Project}
     * @memberof GlobalService
     */
    async getProjectService() {
        return (await import('./project/project.service')).default.getInstance();
    }
    /**
     * 操作历史服务
     *
     * @return {History}
     * @memberof GlobalService
     */
    async getHistoryService() {
        return (await import('./history/history.service')).default.getInstance();
    }
    /**
     * 需求模块服务
     *
     * @return {ProductModule}
     * @memberof GlobalService
     */
    async getProductModuleService() {
        return (await import('./product-module/product-module.service')).default.getInstance();
    }
    /**
     * 产品团队服务
     *
     * @return {ProductTeam}
     * @memberof GlobalService
     */
    async getProductTeamService() {
        return (await import('./product-team/product-team.service')).default.getInstance();
    }
    /**
     * 测试模块服务
     *
     * @return {TestModule}
     * @memberof GlobalService
     */
    async getTestModuleService() {
        return (await import('./test-module/test-module.service')).default.getInstance();
    }
    /**
     * 产品生命周期服务
     *
     * @return {ProductLife}
     * @memberof GlobalService
     */
    async getProductLifeService() {
        return (await import('./product-life/product-life.service')).default.getInstance();
    }
    /**
     * 任务服务
     *
     * @return {Task}
     * @memberof GlobalService
     */
    async getTaskService() {
        return (await import('./task/task.service')).default.getInstance();
    }
    /**
     * build服务
     *
     * @return {Build}
     * @memberof GlobalService
     */
    async getBuildService() {
        return (await import('./build/build.service')).default.getInstance();
    }
    /**
     * 测试结果服务
     *
     * @return {TestReult}
     * @memberof GlobalService
     */
    async getTestReultService() {
        return (await import('./test-reult/test-reult.service')).default.getInstance();
    }
    /**
     * 测试套件服务
     *
     * @return {TestSuite}
     * @memberof GlobalService
     */
    async getTestSuiteService() {
        return (await import('./test-suite/test-suite.service')).default.getInstance();
    }
    /**
     * 项目团队服务
     *
     * @return {ProjectTeam}
     * @memberof GlobalService
     */
    async getProjectTeamService() {
        return (await import('./project-team/project-team.service')).default.getInstance();
    }
    /**
     * 测试版本服务
     *
     * @return {TestTask}
     * @memberof GlobalService
     */
    async getTestTaskService() {
        return (await import('./test-task/test-task.service')).default.getInstance();
    }
    /**
     * 测试报告服务
     *
     * @return {TestReport}
     * @memberof GlobalService
     */
    async getTestReportService() {
        return (await import('./test-report/test-report.service')).default.getInstance();
    }
    /**
     * BUG服务
     *
     * @return {Bug}
     * @memberof GlobalService
     */
    async getBugService() {
        return (await import('./bug/bug.service')).default.getInstance();
    }
    /**
     * 任务模块服务
     *
     * @return {ProjectModule}
     * @memberof GlobalService
     */
    async getProjectModuleService() {
        return (await import('./project-module/project-module.service')).default.getInstance();
    }
    /**
     * 发布服务
     *
     * @return {ProductRelease}
     * @memberof GlobalService
     */
    async getProductReleaseService() {
        return (await import('./product-release/product-release.service')).default.getInstance();
    }
    /**
     * 动态数据看板服务
     *
     * @return {DynaDashboard}
     * @memberof GlobalService
     */
    async getDynaDashboardService() {
        return (await import('./dyna-dashboard/dyna-dashboard.service')).default.getInstance();
    }
    /**
     * 汇报角色配置服务
     *
     * @return {IbzReportRoleConfig}
     * @memberof GlobalService
     */
    async getIbzReportRoleConfigService() {
        return (await import('./ibz-report-role-config/ibz-report-role-config.service')).default.getInstance();
    }
    /**
     * 系统用户服务
     *
     * @return {SysUser}
     * @memberof GlobalService
     */
    async getSysUserService() {
        return (await import('./sys-user/sys-user.service')).default.getInstance();
    }
    /**
     * 用例步骤服务
     *
     * @return {IBZCaseStep}
     * @memberof GlobalService
     */
    async getIBZCaseStepService() {
        return (await import('./ibzcase-step/ibzcase-step.service')).default.getInstance();
    }
    /**
     * 任务团队服务
     *
     * @return {TaskTeam}
     * @memberof GlobalService
     */
    async getTaskTeamService() {
        return (await import('./task-team/task-team.service')).default.getInstance();
    }
    /**
     * 附件服务
     *
     * @return {File}
     * @memberof GlobalService
     */
    async getFileService() {
        return (await import('./file/file.service')).default.getInstance();
    }
    /**
     * 代理服务
     *
     * @return {IbzAgent}
     * @memberof GlobalService
     */
    async getIbzAgentService() {
        return (await import('./ibz-agent/ibz-agent.service')).default.getInstance();
    }
    /**
     * 需求模块服务
     *
     * @return {IBZProStoryModule}
     * @memberof GlobalService
     */
    async getIBZProStoryModuleService() {
        return (await import('./ibzpro-story-module/ibzpro-story-module.service')).default.getInstance();
    }
    /**
     * 产品汇总表服务
     *
     * @return {ProductSum}
     * @memberof GlobalService
     */
    async getProductSumService() {
        return (await import('./product-sum/product-sum.service')).default.getInstance();
    }
    /**
     * 用例库用例步骤服务
     *
     * @return {IbzLibCasesteps}
     * @memberof GlobalService
     */
    async getIbzLibCasestepsService() {
        return (await import('./ibz-lib-casesteps/ibz-lib-casesteps.service')).default.getInstance();
    }
    /**
     * 用例库服务
     *
     * @return {IbzLib}
     * @memberof GlobalService
     */
    async getIbzLibService() {
        return (await import('./ibz-lib/ibz-lib.service')).default.getInstance();
    }
    /**
     * 日报服务
     *
     * @return {IbzDaily}
     * @memberof GlobalService
     */
    async getIbzDailyService() {
        return (await import('./ibz-daily/ibz-daily.service')).default.getInstance();
    }
    /**
     * 套件用例服务
     *
     * @return {SuiteCase}
     * @memberof GlobalService
     */
    async getSuiteCaseService() {
        return (await import('./suite-case/suite-case.service')).default.getInstance();
    }
    /**
     * 员工负载表服务
     *
     * @return {EmpLoyeeload}
     * @memberof GlobalService
     */
    async getEmpLoyeeloadService() {
        return (await import('./emp-loyeeload/emp-loyeeload.service')).default.getInstance();
    }
    /**
     * 文档内容服务
     *
     * @return {DocContent}
     * @memberof GlobalService
     */
    async getDocContentService() {
        return (await import('./doc-content/doc-content.service')).default.getInstance();
    }
    /**
     * 汇报服务
     *
     * @return {IbzReport}
     * @memberof GlobalService
     */
    async getIbzReportService() {
        return (await import('./ibz-report/ibz-report.service')).default.getInstance();
    }
    /**
     * 任务预计服务
     *
     * @return {IBZTaskEstimate}
     * @memberof GlobalService
     */
    async getIBZTaskEstimateService() {
        return (await import('./ibztask-estimate/ibztask-estimate.service')).default.getInstance();
    }
    /**
     * 岗位服务
     *
     * @return {SysPost}
     * @memberof GlobalService
     */
    async getSysPostService() {
        return (await import('./sys-post/sys-post.service')).default.getInstance();
    }
    /**
     * 用户模板服务
     *
     * @return {UserTpl}
     * @memberof GlobalService
     */
    async getUserTplService() {
        return (await import('./user-tpl/user-tpl.service')).default.getInstance();
    }
    /**
     * 任务统计服务
     *
     * @return {TaskStats}
     * @memberof GlobalService
     */
    async getTaskStatsService() {
        return (await import('./task-stats/task-stats.service')).default.getInstance();
    }
    /**
     * 项目周报服务
     *
     * @return {IbizproProjectWeekly}
     * @memberof GlobalService
     */
    async getIbizproProjectWeeklyService() {
        return (await import('./ibizpro-project-weekly/ibizpro-project-weekly.service')).default.getInstance();
    }
    /**
     * 收藏服务
     *
     * @return {IbzFavorites}
     * @memberof GlobalService
     */
    async getIbzFavoritesService() {
        return (await import('./ibz-favorites/ibz-favorites.service')).default.getInstance();
    }
    /**
     * 部门服务
     *
     * @return {SysDepartment}
     * @memberof GlobalService
     */
    async getSysDepartmentService() {
        return (await import('./sys-department/sys-department.service')).default.getInstance();
    }
    /**
     * 产品统计服务
     *
     * @return {ProductStats}
     * @memberof GlobalService
     */
    async getProductStatsService() {
        return (await import('./product-stats/product-stats.service')).default.getInstance();
    }
    /**
     * 项目相关成员服务
     *
     * @return {IbzProjectMember}
     * @memberof GlobalService
     */
    async getIbzProjectMemberService() {
        return (await import('./ibz-project-member/ibz-project-member.service')).default.getInstance();
    }
    /**
     * Bug统计服务
     *
     * @return {BugStats}
     * @memberof GlobalService
     */
    async getBugStatsService() {
        return (await import('./bug-stats/bug-stats.service')).default.getInstance();
    }
    /**
     * 索引检索服务
     *
     * @return {IbizproIndex}
     * @memberof GlobalService
     */
    async getIbizproIndexService() {
        return (await import('./ibizpro-index/ibizpro-index.service')).default.getInstance();
    }
    /**
     * 群组服务
     *
     * @return {Group}
     * @memberof GlobalService
     */
    async getGroupService() {
        return (await import('./group/group.service')).default.getInstance();
    }
    /**
     * 平台产品服务
     *
     * @return {IBZProProduct}
     * @memberof GlobalService
     */
    async getIBZProProductService() {
        return (await import('./ibzpro-product/ibzpro-product.service')).default.getInstance();
    }
    /**
     * 用例步骤服务
     *
     * @return {CaseStep}
     * @memberof GlobalService
     */
    async getCaseStepService() {
        return (await import('./case-step/case-step.service')).default.getInstance();
    }
    /**
     * 部门服务
     *
     * @return {Dept}
     * @memberof GlobalService
     */
    async getDeptService() {
        return (await import('./dept/dept.service')).default.getInstance();
    }
    /**
     * 标签服务
     *
     * @return {IBIZProTag}
     * @memberof GlobalService
     */
    async getIBIZProTagService() {
        return (await import('./ibizpro-tag/ibizpro-tag.service')).default.getInstance();
    }
    /**
     * 公司服务
     *
     * @return {Company}
     * @memberof GlobalService
     */
    async getCompanyService() {
        return (await import('./company/company.service')).default.getInstance();
    }
    /**
     * 组服务
     *
     * @return {SysTeam}
     * @memberof GlobalService
     */
    async getSysTeamService() {
        return (await import('./sys-team/sys-team.service')).default.getInstance();
    }
    /**
     * 任务工时统计服务
     *
     * @return {Taskestimatestats}
     * @memberof GlobalService
     */
    async getTaskestimatestatsService() {
        return (await import('./taskestimatestats/taskestimatestats.service')).default.getInstance();
    }
    /**
     * 用例库用例步骤服务
     *
     * @return {IbzLibCaseStepTmp}
     * @memberof GlobalService
     */
    async getIbzLibCaseStepTmpService() {
        return (await import('./ibz-lib-case-step-tmp/ibz-lib-case-step-tmp.service')).default.getInstance();
    }
    /**
     * 待办事宜表服务
     *
     * @return {Todo}
     * @memberof GlobalService
     */
    async getTodoService() {
        return (await import('./todo/todo.service')).default.getInstance();
    }
    /**
     * 用例库模块服务
     *
     * @return {IbzLibModule}
     * @memberof GlobalService
     */
    async getIbzLibModuleService() {
        return (await import('./ibz-lib-module/ibz-lib-module.service')).default.getInstance();
    }
    /**
     * 产品周报服务
     *
     * @return {IbizproProductWeekly}
     * @memberof GlobalService
     */
    async getIbizproProductWeeklyService() {
        return (await import('./ibizpro-product-weekly/ibizpro-product-weekly.service')).default.getInstance();
    }
    /**
     * 用户年度工作内容统计服务
     *
     * @return {UserYearWorkStats}
     * @memberof GlobalService
     */
    async getUserYearWorkStatsService() {
        return (await import('./user-year-work-stats/user-year-work-stats.service')).default.getInstance();
    }
    /**
     * 单位服务
     *
     * @return {SysOrganization}
     * @memberof GlobalService
     */
    async getSysOrganizationService() {
        return (await import('./sys-organization/sys-organization.service')).default.getInstance();
    }
    /**
     * 系统插件服务
     *
     * @return {IBIZProPlugin}
     * @memberof GlobalService
     */
    async getIBIZProPluginService() {
        return (await import('./ibizpro-plugin/ibizpro-plugin.service')).default.getInstance();
    }
    /**
     * 产品计划服务
     *
     * @return {SubProductPlan}
     * @memberof GlobalService
     */
    async getSubProductPlanService() {
        return (await import('./sub-product-plan/sub-product-plan.service')).default.getInstance();
    }
    /**
     * 汇报服务
     *
     * @return {IbzReportly}
     * @memberof GlobalService
     */
    async getIbzReportlyService() {
        return (await import('./ibz-reportly/ibz-reportly.service')).default.getInstance();
    }
    /**
     * 子任务服务
     *
     * @return {SubTask}
     * @memberof GlobalService
     */
    async getSubTaskService() {
        return (await import('./sub-task/sub-task.service')).default.getInstance();
    }
    /**
     * 项目月报服务
     *
     * @return {IbizproProjectMonthly}
     * @memberof GlobalService
     */
    async getIbizproProjectMonthlyService() {
        return (await import('./ibizpro-project-monthly/ibizpro-project-monthly.service')).default.getInstance();
    }
    /**
     * 用户服务
     *
     * @return {User}
     * @memberof GlobalService
     */
    async getUserService() {
        return (await import('./user/user.service')).default.getInstance();
    }
    /**
     * 文档库服务
     *
     * @return {DocLib}
     * @memberof GlobalService
     */
    async getDocLibService() {
        return (await import('./doc-lib/doc-lib.service')).default.getInstance();
    }
    /**
     * 公司动态汇总服务
     *
     * @return {CompanyStats}
     * @memberof GlobalService
     */
    async getCompanyStatsService() {
        return (await import('./company-stats/company-stats.service')).default.getInstance();
    }
    /**
     * 子需求服务
     *
     * @return {SubStory}
     * @memberof GlobalService
     */
    async getSubStoryService() {
        return (await import('./sub-story/sub-story.service')).default.getInstance();
    }
    /**
     * 项目日报服务
     *
     * @return {IbizproProjectDaily}
     * @memberof GlobalService
     */
    async getIbizproProjectDailyService() {
        return (await import('./ibizpro-project-daily/ibizpro-project-daily.service')).default.getInstance();
    }
    /**
     * 产品计划模板服务
     *
     * @return {IbzPlanTemplet}
     * @memberof GlobalService
     */
    async getIbzPlanTempletService() {
        return (await import('./ibz-plan-templet/ibz-plan-templet.service')).default.getInstance();
    }
    /**
     * 用户角色关系服务
     *
     * @return {SysUserRole}
     * @memberof GlobalService
     */
    async getSysUserRoleService() {
        return (await import('./sys-user-role/sys-user-role.service')).default.getInstance();
    }
    /**
     * 关键字服务
     *
     * @return {IBIZProKeyword}
     * @memberof GlobalService
     */
    async getIBIZProKeywordService() {
        return (await import('./ibizpro-keyword/ibizpro-keyword.service')).default.getInstance();
    }
    /**
     * 文档库分类服务
     *
     * @return {DocLibModule}
     * @memberof GlobalService
     */
    async getDocLibModuleService() {
        return (await import('./doc-lib-module/doc-lib-module.service')).default.getInstance();
    }
    /**
     * 用户联系方式服务
     *
     * @return {UserContact}
     * @memberof GlobalService
     */
    async getUserContactService() {
        return (await import('./user-contact/user-contact.service')).default.getInstance();
    }
    /**
     * 产品日报服务
     *
     * @return {IbizproProductDaily}
     * @memberof GlobalService
     */
    async getIbizproProductDailyService() {
        return (await import('./ibizpro-product-daily/ibizpro-product-daily.service')).default.getInstance();
    }
    /**
     * 系统角色服务
     *
     * @return {SysRole}
     * @memberof GlobalService
     */
    async getSysRoleService() {
        return (await import('./sys-role/sys-role.service')).default.getInstance();
    }
    /**
     * 计划模板详情嵌套服务
     *
     * @return {PlanTempletDetail}
     * @memberof GlobalService
     */
    async getPlanTempletDetailService() {
        return (await import('./plan-templet-detail/plan-templet-detail.service')).default.getInstance();
    }
    /**
     * 产品月报服务
     *
     * @return {IbizproProductMonthly}
     * @memberof GlobalService
     */
    async getIbizproProductMonthlyService() {
        return (await import('./ibizpro-product-monthly/ibizpro-product-monthly.service')).default.getInstance();
    }
    /**
     * 计划模板详情服务
     *
     * @return {IbzPlanTempletDetail}
     * @memberof GlobalService
     */
    async getIbzPlanTempletDetailService() {
        return (await import('./ibz-plan-templet-detail/ibz-plan-templet-detail.service')).default.getInstance();
    }
    /**
     * 系统配置表服务
     *
     * @return {IbzproConfig}
     * @memberof GlobalService
     */
    async getIbzproConfigService() {
        return (await import('./ibzpro-config/ibzpro-config.service')).default.getInstance();
    }
    /**
     * 需求服务
     *
     * @return {IBZProStory}
     * @memberof GlobalService
     */
    async getIBZProStoryService() {
        return (await import('./ibzpro-story/ibzpro-story.service')).default.getInstance();
    }
    /**
     * 任务团队服务
     *
     * @return {IBZTaskTeam}
     * @memberof GlobalService
     */
    async getIBZTaskTeamService() {
        return (await import('./ibztask-team/ibztask-team.service')).default.getInstance();
    }
    /**
     * 产品线服务
     *
     * @return {ProductLine}
     * @memberof GlobalService
     */
    async getProductLineService() {
        return (await import('./product-line/product-line.service')).default.getInstance();
    }
    /**
     * 后台服务架构服务
     *
     * @return {PSSysSFPub}
     * @memberof GlobalService
     */
    async getPSSysSFPubService() {
        return (await import('./pssys-sfpub/pssys-sfpub.service')).default.getInstance();
    }
    /**
     * 项目统计服务
     *
     * @return {ProjectStats}
     * @memberof GlobalService
     */
    async getProjectStatsService() {
        return (await import('./project-stats/project-stats.service')).default.getInstance();
    }
    /**
     * 测试运行服务
     *
     * @return {TestRun}
     * @memberof GlobalService
     */
    async getTestRunService() {
        return (await import('./test-run/test-run.service')).default.getInstance();
    }
    /**
     * 月报服务
     *
     * @return {IbzMonthly}
     * @memberof GlobalService
     */
    async getIbzMonthlyService() {
        return (await import('./ibz-monthly/ibz-monthly.service')).default.getInstance();
    }
    /**
     * 我的地盘服务
     *
     * @return {IbzMyTerritory}
     * @memberof GlobalService
     */
    async getIbzMyTerritoryService() {
        return (await import('./ibz-my-territory/ibz-my-territory.service')).default.getInstance();
    }
    /**
     * 系统更新日志服务
     *
     * @return {SysUpdateLog}
     * @memberof GlobalService
     */
    async getSysUpdateLogService() {
        return (await import('./sys-update-log/sys-update-log.service')).default.getInstance();
    }
    /**
     * 文档服务
     *
     * @return {Doc}
     * @memberof GlobalService
     */
    async getDocService() {
        return (await import('./doc/doc.service')).default.getInstance();
    }
    /**
     * 周报服务
     *
     * @return {IBZWEEKLY}
     * @memberof GlobalService
     */
    async getIBZWEEKLYService() {
        return (await import('./ibzweekly/ibzweekly.service')).default.getInstance();
    }
    /**
     * 文档服务
     *
     * @return {IBzDoc}
     * @memberof GlobalService
     */
    async getIBzDocService() {
        return (await import('./ibz-doc/ibz-doc.service')).default.getInstance();
    }
    /**
     * 系统应用服务
     *
     * @return {PSSysApp}
     * @memberof GlobalService
     */
    async getPSSysAppService() {
        return (await import('./pssys-app/pssys-app.service')).default.getInstance();
    }
    /**
     * 项目汇报用户任务服务
     *
     * @return {IbzproProjectUserTask}
     * @memberof GlobalService
     */
    async getIbzproProjectUserTaskService() {
        return (await import('./ibzpro-project-user-task/ibzpro-project-user-task.service')).default.getInstance();
    }
    /**
     * 用例库用例服务
     *
     * @return {IbzCase}
     * @memberof GlobalService
     */
    async getIbzCaseService() {
        return (await import('./ibz-case/ibz-case.service')).default.getInstance();
    }
    /**
     * 组成员服务
     *
     * @return {SysTeamMember}
     * @memberof GlobalService
     */
    async getSysTeamMemberService() {
        return (await import('./sys-team-member/sys-team-member.service')).default.getInstance();
    }
    /**
     * 系统数据库服务
     *
     * @return {PSSystemDBCfg}
     * @memberof GlobalService
     */
    async getPSSystemDBCfgService() {
        return (await import('./pssystem-dbcfg/pssystem-dbcfg.service')).default.getInstance();
    }
    /**
     * 产品汇报用户任务服务
     *
     * @return {IbzproProductUserTask}
     * @memberof GlobalService
     */
    async getIbzproProductUserTaskService() {
        return (await import('./ibzpro-product-user-task/ibzpro-product-user-task.service')).default.getInstance();
    }
    /**
     * 系统更新功能服务
     *
     * @return {SysUpdateFeatures}
     * @memberof GlobalService
     */
    async getSysUpdateFeaturesService() {
        return (await import('./sys-update-features/sys-update-features.service')).default.getInstance();
    }
    /**
     * 测试用例统计服务
     *
     * @return {CaseStats}
     * @memberof GlobalService
     */
    async getCaseStatsService() {
        return (await import('./case-stats/case-stats.service')).default.getInstance();
    }
    /**
     * 人员服务
     *
     * @return {SysEmployee}
     * @memberof GlobalService
     */
    async getSysEmployeeService() {
        return (await import('./sys-employee/sys-employee.service')).default.getInstance();
    }
    /**
     * 产品线服务
     *
     * @return {IBZProProductLine}
     * @memberof GlobalService
     */
    async getIBZProProductLineService() {
        return (await import('./ibzpro-product-line/ibzpro-product-line.service')).default.getInstance();
    }
}
