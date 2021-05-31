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
        if(!name){
          return undefined;
        }
        name = name.replace(name[0],name[0].toUpperCase());
        return (this as any)[`get${name}Service`]();
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
     * 用例步骤服务
     *
     * @return {TestCaseStep}
     * @memberof GlobalService
     */
    async getTestCaseStepService() {
        return (await import('./test-case-step/test-case-step.service')).default.getInstance();
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
     * 项目团队服务
     *
     * @return {ProjectTeam}
     * @memberof GlobalService
     */
    async getProjectTeamService() {
        return (await import('./project-team/project-team.service')).default.getInstance();
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
     * 发布服务
     *
     * @return {ProductRelease}
     * @memberof GlobalService
     */
    async getProductReleaseService() {
        return (await import('./product-release/product-release.service')).default.getInstance();
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
     * 任务模块服务
     *
     * @return {ProjectModule}
     * @memberof GlobalService
     */
    async getProjectModuleService() {
        return (await import('./project-module/project-module.service')).default.getInstance();
    }
    /**
     * 版本服务
     *
     * @return {Build}
     * @memberof GlobalService
     */
    async getBuildService() {
        return (await import('./build/build.service')).default.getInstance();
    }
    /**
     * Bug服务
     *
     * @return {Bug}
     * @memberof GlobalService
     */
    async getBugService() {
        return (await import('./bug/bug.service')).default.getInstance();
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
     * 产品计划服务
     *
     * @return {ProductPlan}
     * @memberof GlobalService
     */
    async getProductPlanService() {
        return (await import('./product-plan/product-plan.service')).default.getInstance();
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
     * 测试服务
     *
     * @return {Test}
     * @memberof GlobalService
     */
    async getTestService() {
        return (await import('./test/test.service')).default.getInstance();
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
     * 测试版本服务
     *
     * @return {TestTask}
     * @memberof GlobalService
     */
    async getTestTaskService() {
        return (await import('./test-task/test-task.service')).default.getInstance();
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
     * 产品线服务
     *
     * @return {ProductLine}
     * @memberof GlobalService
     */
    async getProductLineService() {
        return (await import('./product-line/product-line.service')).default.getInstance();
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
     * 人员服务
     *
     * @return {SysEmployee}
     * @memberof GlobalService
     */
    async getSysEmployeeService() {
        return (await import('./sys-employee/sys-employee.service')).default.getInstance();
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
     * 我的地盘服务
     *
     * @return {IbzMyTerritory}
     * @memberof GlobalService
     */
    async getIbzMyTerritoryService() {
        return (await import('./ibz-my-territory/ibz-my-territory.service')).default.getInstance();
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
     * 组服务
     *
     * @return {SysTeam}
     * @memberof GlobalService
     */
    async getSysTeamService() {
        return (await import('./sys-team/sys-team.service')).default.getInstance();
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
     * 岗位服务
     *
     * @return {SysPost}
     * @memberof GlobalService
     */
    async getSysPostService() {
        return (await import('./sys-post/sys-post.service')).default.getInstance();
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
     * 汇报服务
     *
     * @return {IbzReportly}
     * @memberof GlobalService
     */
    async getIbzReportlyService() {
        return (await import('./ibz-reportly/ibz-reportly.service')).default.getInstance();
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
     * 测试模块服务
     *
     * @return {TestModule}
     * @memberof GlobalService
     */
    async getTestModuleService() {
        return (await import('./test-module/test-module.service')).default.getInstance();
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
     * 任务团队服务
     *
     * @return {Ibztaskteam}
     * @memberof GlobalService
     */
    async getIbztaskteamService() {
        return (await import('./ibztaskteam/ibztaskteam.service')).default.getInstance();
    }
    /**
     * 任务预计服务
     *
     * @return {IbzTaskestimate}
     * @memberof GlobalService
     */
    async getIbzTaskestimateService() {
        return (await import('./ibz-taskestimate/ibz-taskestimate.service')).default.getInstance();
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
     * 测试套件服务
     *
     * @return {TestSuite}
     * @memberof GlobalService
     */
    async getTestSuiteService() {
        return (await import('./test-suite/test-suite.service')).default.getInstance();
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
     * 日报服务
     *
     * @return {IbzDaily}
     * @memberof GlobalService
     */
    async getIbzDailyService() {
        return (await import('./ibz-daily/ibz-daily.service')).default.getInstance();
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
     * 模块服务
     *
     * @return {Module}
     * @memberof GlobalService
     */
    async getModuleService() {
        return (await import('./module/module.service')).default.getInstance();
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
     * 系统更新日志服务
     *
     * @return {SysUpdateLog}
     * @memberof GlobalService
     */
    async getSysUpdateLogService() {
        return (await import('./sys-update-log/sys-update-log.service')).default.getInstance();
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
     * 动态数据看板服务
     *
     * @return {DynaDashboard}
     * @memberof GlobalService
     */
    async getDynaDashboardService() {
        return (await import('./dyna-dashboard/dyna-dashboard.service')).default.getInstance();
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
     * 产品统计服务
     *
     * @return {ProductStats}
     * @memberof GlobalService
     */
    async getProductStatsService() {
        return (await import('./product-stats/product-stats.service')).default.getInstance();
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
     * 用户联系方式服务
     *
     * @return {UserContact}
     * @memberof GlobalService
     */
    async getUserContactService() {
        return (await import('./user-contact/user-contact.service')).default.getInstance();
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
     * 待办事宜表服务
     *
     * @return {Todo}
     * @memberof GlobalService
     */
    async getTodoService() {
        return (await import('./todo/todo.service')).default.getInstance();
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
     * 文档库分类服务
     *
     * @return {DocLibModule}
     * @memberof GlobalService
     */
    async getDocLibModuleService() {
        return (await import('./doc-lib-module/doc-lib-module.service')).default.getInstance();
    }
    /**
     * 周报服务
     *
     * @return {IbzWeekly}
     * @memberof GlobalService
     */
    async getIbzWeeklyService() {
        return (await import('./ibz-weekly/ibz-weekly.service')).default.getInstance();
    }
    /**
     * 项目团队服务
     *
     * @return {IBZPROJECTTEAM}
     * @memberof GlobalService
     */
    async getIBZPROJECTTEAMService() {
        return (await import('./ibzprojectteam/ibzprojectteam.service')).default.getInstance();
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
}
