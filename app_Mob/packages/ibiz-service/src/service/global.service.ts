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
     * 人员服务
     *
     * @param context 应用上下文
     * @return {SysEmployee}
     * @memberof GlobalService
     */
    async getSysEmployeeService(context?: any) {
        return (await import('./sys-employee/sys-employee.service')).default.getInstance(context);
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
     * 任务团队（嵌套）服务
     *
     * @param context 应用上下文
     * @return {Ibztaskteam}
     * @memberof GlobalService
     */
    async getIbztaskteamService(context?: any) {
        return (await import('./ibztaskteam/ibztaskteam.service')).default.getInstance(context);
    }
    /**
     * 任务预计服务
     *
     * @param context 应用上下文
     * @return {IbzTaskestimate}
     * @memberof GlobalService
     */
    async getIbzTaskestimateService(context?: any) {
        return (await import('./ibz-taskestimate/ibz-taskestimate.service')).default.getInstance(context);
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
     * 模块服务
     *
     * @param context 应用上下文
     * @return {Module}
     * @memberof GlobalService
     */
    async getModuleService(context?: any) {
        return (await import('./module/module.service')).default.getInstance(context);
    }
    /**
     * 版本服务
     *
     * @param context 应用上下文
     * @return {Build}
     * @memberof GlobalService
     */
    async getBuildService(context?: any) {
        return (await import('./build/build.service')).default.getInstance(context);
    }
    /**
     * Bug服务
     *
     * @param context 应用上下文
     * @return {Bug}
     * @memberof GlobalService
     */
    async getBugService(context?: any) {
        return (await import('./bug/bug.service')).default.getInstance(context);
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
     * 项目团队服务
     *
     * @param context 应用上下文
     * @return {IBZPROJECTTEAM}
     * @memberof GlobalService
     */
    async getIBZPROJECTTEAMService(context?: any) {
        return (await import('./ibzprojectteam/ibzprojectteam.service')).default.getInstance(context);
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
