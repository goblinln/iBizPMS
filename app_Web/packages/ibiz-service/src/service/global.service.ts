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
     * @param context 应用上下文
     * memberof GlobalService
     */
    public async getService(name:string, context?: any){
        const targetService:any = await (this as any)[`get${name}Service`](context);
        await targetService.loaded(context);
        return targetService;
    }

    /**
     * 产品计划服务
     *
     * @param context 应用上下文
     * @return {ProductPlan}
     * @memberof GlobalService
     */
    async getProductPlanService(context?: any) {
        return (await import('./product-plan/product-plan.service')).default.getInstance(context);
    }
    /**
     * 汇报角色配置服务
     *
     * @param context 应用上下文
     * @return {IbzReportRoleConfig}
     * @memberof GlobalService
     */
    async getIbzReportRoleConfigService(context?: any) {
        return (await import('./ibz-report-role-config/ibz-report-role-config.service')).default.getInstance(context);
    }
    /**
     * 测试服务
     *
     * @param context 应用上下文
     * @return {Test}
     * @memberof GlobalService
     */
    async getTestService(context?: any) {
        return (await import('./test/test.service')).default.getInstance(context);
    }
    /**
     * 测试用例服务
     *
     * @param context 应用上下文
     * @return {TestCase}
     * @memberof GlobalService
     */
    async getTestCaseService(context?: any) {
        return (await import('./test-case/test-case.service')).default.getInstance(context);
    }
    /**
     * 系统用户服务
     *
     * @param context 应用上下文
     * @return {SysAccount}
     * @memberof GlobalService
     */
    async getSysAccountService(context?: any) {
        return (await import('./sys-account/sys-account.service')).default.getInstance(context);
    }
    /**
     * 产品服务
     *
     * @param context 应用上下文
     * @return {Product}
     * @memberof GlobalService
     */
    async getProductService(context?: any) {
        return (await import('./product/product.service')).default.getInstance(context);
    }
    /**
     * 测试用例步骤服务
     *
     * @param context 应用上下文
     * @return {TestCaseStepNested}
     * @memberof GlobalService
     */
    async getTestCaseStepNestedService(context?: any) {
        return (await import('./test-case-step-nested/test-case-step-nested.service')).default.getInstance(context);
    }
    /**
     * 任务团队服务
     *
     * @param context 应用上下文
     * @return {TaskTeam}
     * @memberof GlobalService
     */
    async getTaskTeamService(context?: any) {
        return (await import('./task-team/task-team.service')).default.getInstance(context);
    }
    /**
     * 附件服务
     *
     * @param context 应用上下文
     * @return {File}
     * @memberof GlobalService
     */
    async getFileService(context?: any) {
        return (await import('./file/file.service')).default.getInstance(context);
    }
    /**
     * 代理服务
     *
     * @param context 应用上下文
     * @return {IbzAgent}
     * @memberof GlobalService
     */
    async getIbzAgentService(context?: any) {
        return (await import('./ibz-agent/ibz-agent.service')).default.getInstance(context);
    }
    /**
     * 需求模块服务
     *
     * @param context 应用上下文
     * @return {IBZProStoryModule}
     * @memberof GlobalService
     */
    async getIBZProStoryModuleService(context?: any) {
        return (await import('./ibzpro-story-module/ibzpro-story-module.service')).default.getInstance(context);
    }
    /**
     * 产品汇总表服务
     *
     * @param context 应用上下文
     * @return {ProductSum}
     * @memberof GlobalService
     */
    async getProductSumService(context?: any) {
        return (await import('./product-sum/product-sum.service')).default.getInstance(context);
    }
    /**
     * 用户需求服务
     *
     * @param context 应用上下文
     * @return {AccountStory}
     * @memberof GlobalService
     */
    async getAccountStoryService(context?: any) {
        return (await import('./account-story/account-story.service')).default.getInstance(context);
    }
    /**
     * 用例库用例步骤服务
     *
     * @param context 应用上下文
     * @return {IbzLibCasesteps}
     * @memberof GlobalService
     */
    async getIbzLibCasestepsService(context?: any) {
        return (await import('./ibz-lib-casesteps/ibz-lib-casesteps.service')).default.getInstance(context);
    }
    /**
     * 用户测试单服务
     *
     * @param context 应用上下文
     * @return {AccountTestTask}
     * @memberof GlobalService
     */
    async getAccountTestTaskService(context?: any) {
        return (await import('./account-test-task/account-test-task.service')).default.getInstance(context);
    }
    /**
     * 用例库服务
     *
     * @param context 应用上下文
     * @return {TestCaseLib}
     * @memberof GlobalService
     */
    async getTestCaseLibService(context?: any) {
        return (await import('./test-case-lib/test-case-lib.service')).default.getInstance(context);
    }
    /**
     * 日报服务
     *
     * @param context 应用上下文
     * @return {Daily}
     * @memberof GlobalService
     */
    async getDailyService(context?: any) {
        return (await import('./daily/daily.service')).default.getInstance(context);
    }
    /**
     * 套件用例服务
     *
     * @param context 应用上下文
     * @return {SuiteCase}
     * @memberof GlobalService
     */
    async getSuiteCaseService(context?: any) {
        return (await import('./suite-case/suite-case.service')).default.getInstance(context);
    }
    /**
     * burn服务
     *
     * @param context 应用上下文
     * @return {ProjectBurn}
     * @memberof GlobalService
     */
    async getProjectBurnService(context?: any) {
        return (await import('./project-burn/project-burn.service')).default.getInstance(context);
    }
    /**
     * 员工负载表服务
     *
     * @param context 应用上下文
     * @return {EmpLoyeeload}
     * @memberof GlobalService
     */
    async getEmpLoyeeloadService(context?: any) {
        return (await import('./emp-loyeeload/emp-loyeeload.service')).default.getInstance(context);
    }
    /**
     * 文档内容服务
     *
     * @param context 应用上下文
     * @return {DocContent}
     * @memberof GlobalService
     */
    async getDocContentService(context?: any) {
        return (await import('./doc-content/doc-content.service')).default.getInstance(context);
    }
    /**
     * 汇报服务
     *
     * @param context 应用上下文
     * @return {ReportSummary}
     * @memberof GlobalService
     */
    async getReportSummaryService(context?: any) {
        return (await import('./report-summary/report-summary.service')).default.getInstance(context);
    }
    /**
     * 任务预计服务
     *
     * @param context 应用上下文
     * @return {IBZTaskEstimate}
     * @memberof GlobalService
     */
    async getIBZTaskEstimateService(context?: any) {
        return (await import('./ibztask-estimate/ibztask-estimate.service')).default.getInstance(context);
    }
    /**
     * 需求描述服务
     *
     * @param context 应用上下文
     * @return {StorySpec}
     * @memberof GlobalService
     */
    async getStorySpecService(context?: any) {
        return (await import('./story-spec/story-spec.service')).default.getInstance(context);
    }
    /**
     * 岗位服务
     *
     * @param context 应用上下文
     * @return {SysPost}
     * @memberof GlobalService
     */
    async getSysPostService(context?: any) {
        return (await import('./sys-post/sys-post.service')).default.getInstance(context);
    }
    /**
     * 用户测试用例服务
     *
     * @param context 应用上下文
     * @return {AccountTestCase}
     * @memberof GlobalService
     */
    async getAccountTestCaseService(context?: any) {
        return (await import('./account-test-case/account-test-case.service')).default.getInstance(context);
    }
    /**
     * 用户模板服务
     *
     * @param context 应用上下文
     * @return {UserTpl}
     * @memberof GlobalService
     */
    async getUserTplService(context?: any) {
        return (await import('./user-tpl/user-tpl.service')).default.getInstance(context);
    }
    /**
     * 任务统计服务
     *
     * @param context 应用上下文
     * @return {TaskStats}
     * @memberof GlobalService
     */
    async getTaskStatsService(context?: any) {
        return (await import('./task-stats/task-stats.service')).default.getInstance(context);
    }
    /**
     * 项目周报服务
     *
     * @param context 应用上下文
     * @return {ProjectWeekly}
     * @memberof GlobalService
     */
    async getProjectWeeklyService(context?: any) {
        return (await import('./project-weekly/project-weekly.service')).default.getInstance(context);
    }
    /**
     * 收藏服务
     *
     * @param context 应用上下文
     * @return {IbzFavorites}
     * @memberof GlobalService
     */
    async getIbzFavoritesService(context?: any) {
        return (await import('./ibz-favorites/ibz-favorites.service')).default.getInstance(context);
    }
    /**
     * 产品的分支和平台信息服务
     *
     * @param context 应用上下文
     * @return {ProductBranch}
     * @memberof GlobalService
     */
    async getProductBranchService(context?: any) {
        return (await import('./product-branch/product-branch.service')).default.getInstance(context);
    }
    /**
     * 部门服务
     *
     * @param context 应用上下文
     * @return {SysDepartment}
     * @memberof GlobalService
     */
    async getSysDepartmentService(context?: any) {
        return (await import('./sys-department/sys-department.service')).default.getInstance(context);
    }
    /**
     * 产品统计服务
     *
     * @param context 应用上下文
     * @return {ProductStats}
     * @memberof GlobalService
     */
    async getProductStatsService(context?: any) {
        return (await import('./product-stats/product-stats.service')).default.getInstance(context);
    }
    /**
     * 项目相关成员服务
     *
     * @param context 应用上下文
     * @return {IbzProjectMember}
     * @memberof GlobalService
     */
    async getIbzProjectMemberService(context?: any) {
        return (await import('./ibz-project-member/ibz-project-member.service')).default.getInstance(context);
    }
    /**
     * 系统日志服务
     *
     * @param context 应用上下文
     * @return {Action}
     * @memberof GlobalService
     */
    async getActionService(context?: any) {
        return (await import('./action/action.service')).default.getInstance(context);
    }
    /**
     * Bug统计服务
     *
     * @param context 应用上下文
     * @return {BugStats}
     * @memberof GlobalService
     */
    async getBugStatsService(context?: any) {
        return (await import('./bug-stats/bug-stats.service')).default.getInstance(context);
    }
    /**
     * 用户任务服务
     *
     * @param context 应用上下文
     * @return {AccountTask}
     * @memberof GlobalService
     */
    async getAccountTaskService(context?: any) {
        return (await import('./account-task/account-task.service')).default.getInstance(context);
    }
    /**
     * 索引检索服务
     *
     * @param context 应用上下文
     * @return {IbizproIndex}
     * @memberof GlobalService
     */
    async getIbizproIndexService(context?: any) {
        return (await import('./ibizpro-index/ibizpro-index.service')).default.getInstance(context);
    }
    /**
     * 群组服务
     *
     * @param context 应用上下文
     * @return {Group}
     * @memberof GlobalService
     */
    async getGroupService(context?: any) {
        return (await import('./group/group.service')).default.getInstance(context);
    }
    /**
     * 平台产品服务
     *
     * @param context 应用上下文
     * @return {IBZProProduct}
     * @memberof GlobalService
     */
    async getIBZProProductService(context?: any) {
        return (await import('./ibzpro-product/ibzpro-product.service')).default.getInstance(context);
    }
    /**
     * 用例步骤服务
     *
     * @param context 应用上下文
     * @return {TestCaseStep}
     * @memberof GlobalService
     */
    async getTestCaseStepService(context?: any) {
        return (await import('./test-case-step/test-case-step.service')).default.getInstance(context);
    }
    /**
     * 需求阶段服务
     *
     * @param context 应用上下文
     * @return {StoryStage}
     * @memberof GlobalService
     */
    async getStoryStageService(context?: any) {
        return (await import('./story-stage/story-stage.service')).default.getInstance(context);
    }
    /**
     * 部门服务
     *
     * @param context 应用上下文
     * @return {Dept}
     * @memberof GlobalService
     */
    async getDeptService(context?: any) {
        return (await import('./dept/dept.service')).default.getInstance(context);
    }
    /**
     * 标签服务
     *
     * @param context 应用上下文
     * @return {IBIZProTag}
     * @memberof GlobalService
     */
    async getIBIZProTagService(context?: any) {
        return (await import('./ibizpro-tag/ibizpro-tag.service')).default.getInstance(context);
    }
    /**
     * 公司服务
     *
     * @param context 应用上下文
     * @return {Company}
     * @memberof GlobalService
     */
    async getCompanyService(context?: any) {
        return (await import('./company/company.service')).default.getInstance(context);
    }
    /**
     * 组服务
     *
     * @param context 应用上下文
     * @return {SysTeam}
     * @memberof GlobalService
     */
    async getSysTeamService(context?: any) {
        return (await import('./sys-team/sys-team.service')).default.getInstance(context);
    }
    /**
     * 任务预计服务
     *
     * @param context 应用上下文
     * @return {TaskEstimate}
     * @memberof GlobalService
     */
    async getTaskEstimateService(context?: any) {
        return (await import('./task-estimate/task-estimate.service')).default.getInstance(context);
    }
    /**
     * 需求服务
     *
     * @param context 应用上下文
     * @return {Story}
     * @memberof GlobalService
     */
    async getStoryService(context?: any) {
        return (await import('./story/story.service')).default.getInstance(context);
    }
    /**
     * 任务工时统计服务
     *
     * @param context 应用上下文
     * @return {Taskestimatestats}
     * @memberof GlobalService
     */
    async getTaskestimatestatsService(context?: any) {
        return (await import('./taskestimatestats/taskestimatestats.service')).default.getInstance(context);
    }
    /**
     * 测试用例库测试用例步骤（嵌套）服务
     *
     * @param context 应用上下文
     * @return {TestCaseLibCaseStepNested}
     * @memberof GlobalService
     */
    async getTestCaseLibCaseStepNestedService(context?: any) {
        return (await import('./test-case-lib-case-step-nested/test-case-lib-case-step-nested.service')).default.getInstance(context);
    }
    /**
     * 待办事宜表服务
     *
     * @param context 应用上下文
     * @return {Todo}
     * @memberof GlobalService
     */
    async getTodoService(context?: any) {
        return (await import('./todo/todo.service')).default.getInstance(context);
    }
    /**
     * 动态搜索栏服务
     *
     * @param context 应用上下文
     * @return {DynaFilter}
     * @memberof GlobalService
     */
    async getDynaFilterService(context?: any) {
        return (await import('./dyna-filter/dyna-filter.service')).default.getInstance(context);
    }
    /**
     * 用例库模块服务
     *
     * @param context 应用上下文
     * @return {TestCaseLibModule}
     * @memberof GlobalService
     */
    async getTestCaseLibModuleService(context?: any) {
        return (await import('./test-case-lib-module/test-case-lib-module.service')).default.getInstance(context);
    }
    /**
     * 产品周报服务
     *
     * @param context 应用上下文
     * @return {ProductWeekly}
     * @memberof GlobalService
     */
    async getProductWeeklyService(context?: any) {
        return (await import('./product-weekly/product-weekly.service')).default.getInstance(context);
    }
    /**
     * 用户年度工作内容统计服务
     *
     * @param context 应用上下文
     * @return {UserYearWorkStats}
     * @memberof GlobalService
     */
    async getUserYearWorkStatsService(context?: any) {
        return (await import('./user-year-work-stats/user-year-work-stats.service')).default.getInstance(context);
    }
    /**
     * 单位服务
     *
     * @param context 应用上下文
     * @return {SysOrganization}
     * @memberof GlobalService
     */
    async getSysOrganizationService(context?: any) {
        return (await import('./sys-organization/sys-organization.service')).default.getInstance(context);
    }
    /**
     * 系统插件服务
     *
     * @param context 应用上下文
     * @return {IBIZProPlugin}
     * @memberof GlobalService
     */
    async getIBIZProPluginService(context?: any) {
        return (await import('./ibizpro-plugin/ibizpro-plugin.service')).default.getInstance(context);
    }
    /**
     * 产品计划服务
     *
     * @param context 应用上下文
     * @return {SubProductPlan}
     * @memberof GlobalService
     */
    async getSubProductPlanService(context?: any) {
        return (await import('./sub-product-plan/sub-product-plan.service')).default.getInstance(context);
    }
    /**
     * 项目服务
     *
     * @param context 应用上下文
     * @return {Project}
     * @memberof GlobalService
     */
    async getProjectService(context?: any) {
        return (await import('./project/project.service')).default.getInstance(context);
    }
    /**
     * 汇报服务
     *
     * @param context 应用上下文
     * @return {Reportly}
     * @memberof GlobalService
     */
    async getReportlyService(context?: any) {
        return (await import('./reportly/reportly.service')).default.getInstance(context);
    }
    /**
     * 子任务服务
     *
     * @param context 应用上下文
     * @return {SubTask}
     * @memberof GlobalService
     */
    async getSubTaskService(context?: any) {
        return (await import('./sub-task/sub-task.service')).default.getInstance(context);
    }
    /**
     * 操作历史服务
     *
     * @param context 应用上下文
     * @return {History}
     * @memberof GlobalService
     */
    async getHistoryService(context?: any) {
        return (await import('./history/history.service')).default.getInstance(context);
    }
    /**
     * 项目月报服务
     *
     * @param context 应用上下文
     * @return {ProjectMonthly}
     * @memberof GlobalService
     */
    async getProjectMonthlyService(context?: any) {
        return (await import('./project-monthly/project-monthly.service')).default.getInstance(context);
    }
    /**
     * 用户服务
     *
     * @param context 应用上下文
     * @return {User}
     * @memberof GlobalService
     */
    async getUserService(context?: any) {
        return (await import('./user/user.service')).default.getInstance(context);
    }
    /**
     * 文档库服务
     *
     * @param context 应用上下文
     * @return {DocLib}
     * @memberof GlobalService
     */
    async getDocLibService(context?: any) {
        return (await import('./doc-lib/doc-lib.service')).default.getInstance(context);
    }
    /**
     * 公司动态汇总服务
     *
     * @param context 应用上下文
     * @return {CompanyStats}
     * @memberof GlobalService
     */
    async getCompanyStatsService(context?: any) {
        return (await import('./company-stats/company-stats.service')).default.getInstance(context);
    }
    /**
     * 需求模块服务
     *
     * @param context 应用上下文
     * @return {ProductModule}
     * @memberof GlobalService
     */
    async getProductModuleService(context?: any) {
        return (await import('./product-module/product-module.service')).default.getInstance(context);
    }
    /**
     * 产品团队服务
     *
     * @param context 应用上下文
     * @return {ProductTeam}
     * @memberof GlobalService
     */
    async getProductTeamService(context?: any) {
        return (await import('./product-team/product-team.service')).default.getInstance(context);
    }
    /**
     * 测试模块服务
     *
     * @param context 应用上下文
     * @return {TestModule}
     * @memberof GlobalService
     */
    async getTestModuleService(context?: any) {
        return (await import('./test-module/test-module.service')).default.getInstance(context);
    }
    /**
     * 子需求服务
     *
     * @param context 应用上下文
     * @return {SubStory}
     * @memberof GlobalService
     */
    async getSubStoryService(context?: any) {
        return (await import('./sub-story/sub-story.service')).default.getInstance(context);
    }
    /**
     * 项目日报服务
     *
     * @param context 应用上下文
     * @return {ProjectDaily}
     * @memberof GlobalService
     */
    async getProjectDailyService(context?: any) {
        return (await import('./project-daily/project-daily.service')).default.getInstance(context);
    }
    /**
     * 产品计划模板服务
     *
     * @param context 应用上下文
     * @return {IbzPlanTemplet}
     * @memberof GlobalService
     */
    async getIbzPlanTempletService(context?: any) {
        return (await import('./ibz-plan-templet/ibz-plan-templet.service')).default.getInstance(context);
    }
    /**
     * 用户角色关系服务
     *
     * @param context 应用上下文
     * @return {SysUserRole}
     * @memberof GlobalService
     */
    async getSysUserRoleService(context?: any) {
        return (await import('./sys-user-role/sys-user-role.service')).default.getInstance(context);
    }
    /**
     * 关键字服务
     *
     * @param context 应用上下文
     * @return {IBIZProKeyword}
     * @memberof GlobalService
     */
    async getIBIZProKeywordService(context?: any) {
        return (await import('./ibizpro-keyword/ibizpro-keyword.service')).default.getInstance(context);
    }
    /**
     * 文档库分类服务
     *
     * @param context 应用上下文
     * @return {DocLibModule}
     * @memberof GlobalService
     */
    async getDocLibModuleService(context?: any) {
        return (await import('./doc-lib-module/doc-lib-module.service')).default.getInstance(context);
    }
    /**
     * 产品生命周期服务
     *
     * @param context 应用上下文
     * @return {ProductLife}
     * @memberof GlobalService
     */
    async getProductLifeService(context?: any) {
        return (await import('./product-life/product-life.service')).default.getInstance(context);
    }
    /**
     * 用户联系方式服务
     *
     * @param context 应用上下文
     * @return {UserContact}
     * @memberof GlobalService
     */
    async getUserContactService(context?: any) {
        return (await import('./user-contact/user-contact.service')).default.getInstance(context);
    }
    /**
     * 产品日报服务
     *
     * @param context 应用上下文
     * @return {ProductDaily}
     * @memberof GlobalService
     */
    async getProductDailyService(context?: any) {
        return (await import('./product-daily/product-daily.service')).default.getInstance(context);
    }
    /**
     * 系统角色服务
     *
     * @param context 应用上下文
     * @return {SysRole}
     * @memberof GlobalService
     */
    async getSysRoleService(context?: any) {
        return (await import('./sys-role/sys-role.service')).default.getInstance(context);
    }
    /**
     * 用户项目服务
     *
     * @param context 应用上下文
     * @return {AccountProject}
     * @memberof GlobalService
     */
    async getAccountProjectService(context?: any) {
        return (await import('./account-project/account-project.service')).default.getInstance(context);
    }
    /**
     * 计划模板详情嵌套服务
     *
     * @param context 应用上下文
     * @return {PlanTempletDetail}
     * @memberof GlobalService
     */
    async getPlanTempletDetailService(context?: any) {
        return (await import('./plan-templet-detail/plan-templet-detail.service')).default.getInstance(context);
    }
    /**
     * 任务服务
     *
     * @param context 应用上下文
     * @return {Task}
     * @memberof GlobalService
     */
    async getTaskService(context?: any) {
        return (await import('./task/task.service')).default.getInstance(context);
    }
    /**
     * build服务
     *
     * @param context 应用上下文
     * @return {Build}
     * @memberof GlobalService
     */
    async getBuildService(context?: any) {
        return (await import('./build/build.service')).default.getInstance(context);
    }
    /**
     * 产品月报服务
     *
     * @param context 应用上下文
     * @return {ProductMonthly}
     * @memberof GlobalService
     */
    async getProductMonthlyService(context?: any) {
        return (await import('./product-monthly/product-monthly.service')).default.getInstance(context);
    }
    /**
     * 测试结果服务
     *
     * @param context 应用上下文
     * @return {TestReult}
     * @memberof GlobalService
     */
    async getTestReultService(context?: any) {
        return (await import('./test-reult/test-reult.service')).default.getInstance(context);
    }
    /**
     * 测试套件服务
     *
     * @param context 应用上下文
     * @return {TestSuite}
     * @memberof GlobalService
     */
    async getTestSuiteService(context?: any) {
        return (await import('./test-suite/test-suite.service')).default.getInstance(context);
    }
    /**
     * 计划模板详情服务
     *
     * @param context 应用上下文
     * @return {IbzPlanTempletDetail}
     * @memberof GlobalService
     */
    async getIbzPlanTempletDetailService(context?: any) {
        return (await import('./ibz-plan-templet-detail/ibz-plan-templet-detail.service')).default.getInstance(context);
    }
    /**
     * 系统配置表服务
     *
     * @param context 应用上下文
     * @return {IbzproConfig}
     * @memberof GlobalService
     */
    async getIbzproConfigService(context?: any) {
        return (await import('./ibzpro-config/ibzpro-config.service')).default.getInstance(context);
    }
    /**
     * 需求服务
     *
     * @param context 应用上下文
     * @return {IBZProStory}
     * @memberof GlobalService
     */
    async getIBZProStoryService(context?: any) {
        return (await import('./ibzpro-story/ibzpro-story.service')).default.getInstance(context);
    }
    /**
     * 任务团队（嵌套）服务
     *
     * @param context 应用上下文
     * @return {TaskTeamNested}
     * @memberof GlobalService
     */
    async getTaskTeamNestedService(context?: any) {
        return (await import('./task-team-nested/task-team-nested.service')).default.getInstance(context);
    }
    /**
     * 项目团队服务
     *
     * @param context 应用上下文
     * @return {ProjectTeam}
     * @memberof GlobalService
     */
    async getProjectTeamService(context?: any) {
        return (await import('./project-team/project-team.service')).default.getInstance(context);
    }
    /**
     * 测试版本服务
     *
     * @param context 应用上下文
     * @return {TestTask}
     * @memberof GlobalService
     */
    async getTestTaskService(context?: any) {
        return (await import('./test-task/test-task.service')).default.getInstance(context);
    }
    /**
     * 后台服务架构服务
     *
     * @param context 应用上下文
     * @return {PSSysSFPub}
     * @memberof GlobalService
     */
    async getPSSysSFPubService(context?: any) {
        return (await import('./pssys-sfpub/pssys-sfpub.service')).default.getInstance(context);
    }
    /**
     * 测试报告服务
     *
     * @param context 应用上下文
     * @return {TestReport}
     * @memberof GlobalService
     */
    async getTestReportService(context?: any) {
        return (await import('./test-report/test-report.service')).default.getInstance(context);
    }
    /**
     * 项目统计服务
     *
     * @param context 应用上下文
     * @return {ProjectStats}
     * @memberof GlobalService
     */
    async getProjectStatsService(context?: any) {
        return (await import('./project-stats/project-stats.service')).default.getInstance(context);
    }
    /**
     * 测试运行服务
     *
     * @param context 应用上下文
     * @return {TestRun}
     * @memberof GlobalService
     */
    async getTestRunService(context?: any) {
        return (await import('./test-run/test-run.service')).default.getInstance(context);
    }
    /**
     * 月报服务
     *
     * @param context 应用上下文
     * @return {Monthly}
     * @memberof GlobalService
     */
    async getMonthlyService(context?: any) {
        return (await import('./monthly/monthly.service')).default.getInstance(context);
    }
    /**
     * 我的地盘服务
     *
     * @param context 应用上下文
     * @return {IbzMyTerritory}
     * @memberof GlobalService
     */
    async getIbzMyTerritoryService(context?: any) {
        return (await import('./ibz-my-territory/ibz-my-territory.service')).default.getInstance(context);
    }
    /**
     * 用户BUG服务
     *
     * @param context 应用上下文
     * @return {AccountBug}
     * @memberof GlobalService
     */
    async getAccountBugService(context?: any) {
        return (await import('./account-bug/account-bug.service')).default.getInstance(context);
    }
    /**
     * 系统更新日志服务
     *
     * @param context 应用上下文
     * @return {SysUpdateLog}
     * @memberof GlobalService
     */
    async getSysUpdateLogService(context?: any) {
        return (await import('./sys-update-log/sys-update-log.service')).default.getInstance(context);
    }
    /**
     * 文档服务
     *
     * @param context 应用上下文
     * @return {Doc}
     * @memberof GlobalService
     */
    async getDocService(context?: any) {
        return (await import('./doc/doc.service')).default.getInstance(context);
    }
    /**
     * BUG服务
     *
     * @param context 应用上下文
     * @return {Bug}
     * @memberof GlobalService
     */
    async getBugService(context?: any) {
        return (await import('./bug/bug.service')).default.getInstance(context);
    }
    /**
     * 个人周报服务
     *
     * @param context 应用上下文
     * @return {Weekly}
     * @memberof GlobalService
     */
    async getWeeklyService(context?: any) {
        return (await import('./weekly/weekly.service')).default.getInstance(context);
    }
    /**
     * 任务模块服务
     *
     * @param context 应用上下文
     * @return {ProjectModule}
     * @memberof GlobalService
     */
    async getProjectModuleService(context?: any) {
        return (await import('./project-module/project-module.service')).default.getInstance(context);
    }
    /**
     * 系统应用服务
     *
     * @param context 应用上下文
     * @return {PSSysApp}
     * @memberof GlobalService
     */
    async getPSSysAppService(context?: any) {
        return (await import('./pssys-app/pssys-app.service')).default.getInstance(context);
    }
    /**
     * 项目汇报用户任务服务
     *
     * @param context 应用上下文
     * @return {IbzproProjectUserTask}
     * @memberof GlobalService
     */
    async getIbzproProjectUserTaskService(context?: any) {
        return (await import('./ibzpro-project-user-task/ibzpro-project-user-task.service')).default.getInstance(context);
    }
    /**
     * 用例库用例服务
     *
     * @param context 应用上下文
     * @return {IbzCase}
     * @memberof GlobalService
     */
    async getIbzCaseService(context?: any) {
        return (await import('./ibz-case/ibz-case.service')).default.getInstance(context);
    }
    /**
     * 组成员服务
     *
     * @param context 应用上下文
     * @return {SysTeamMember}
     * @memberof GlobalService
     */
    async getSysTeamMemberService(context?: any) {
        return (await import('./sys-team-member/sys-team-member.service')).default.getInstance(context);
    }
    /**
     * 系统数据库服务
     *
     * @param context 应用上下文
     * @return {PSSystemDBCfg}
     * @memberof GlobalService
     */
    async getPSSystemDBCfgService(context?: any) {
        return (await import('./pssystem-dbcfg/pssystem-dbcfg.service')).default.getInstance(context);
    }
    /**
     * 产品汇报用户任务服务
     *
     * @param context 应用上下文
     * @return {IbzproProductUserTask}
     * @memberof GlobalService
     */
    async getIbzproProductUserTaskService(context?: any) {
        return (await import('./ibzpro-product-user-task/ibzpro-product-user-task.service')).default.getInstance(context);
    }
    /**
     * 系统更新功能服务
     *
     * @param context 应用上下文
     * @return {SysUpdateFeatures}
     * @memberof GlobalService
     */
    async getSysUpdateFeaturesService(context?: any) {
        return (await import('./sys-update-features/sys-update-features.service')).default.getInstance(context);
    }
    /**
     * 用户产品服务
     *
     * @param context 应用上下文
     * @return {AccountProduct}
     * @memberof GlobalService
     */
    async getAccountProductService(context?: any) {
        return (await import('./account-product/account-product.service')).default.getInstance(context);
    }
    /**
     * 发布服务
     *
     * @param context 应用上下文
     * @return {ProductRelease}
     * @memberof GlobalService
     */
    async getProductReleaseService(context?: any) {
        return (await import('./product-release/product-release.service')).default.getInstance(context);
    }
    /**
     * 测试用例统计服务
     *
     * @param context 应用上下文
     * @return {CaseStats}
     * @memberof GlobalService
     */
    async getCaseStatsService(context?: any) {
        return (await import('./case-stats/case-stats.service')).default.getInstance(context);
    }
    /**
     * 动态数据看板服务
     *
     * @param context 应用上下文
     * @return {DynaDashboard}
     * @memberof GlobalService
     */
    async getDynaDashboardService(context?: any) {
        return (await import('./dyna-dashboard/dyna-dashboard.service')).default.getInstance(context);
    }
    /**
     * 人员服务
     *
     * @param context 应用上下文
     * @return {Employee}
     * @memberof GlobalService
     */
    async getEmployeeService(context?: any) {
        return (await import('./employee/employee.service')).default.getInstance(context);
    }
    /**
     * 产品线服务
     *
     * @param context 应用上下文
     * @return {ProductLine}
     * @memberof GlobalService
     */
    async getProductLineService(context?: any) {
        return (await import('./product-line/product-line.service')).default.getInstance(context);
    }
}
