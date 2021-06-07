/**
 * 实体权限服务注册中心
 *
 * @export
 * @class AuthServiceRegister
 */
export class AuthServiceRegister{

    /**
     * AuthServiceRegister 单例对象
     *
     * @private
     * @static
     * @memberof AuthServiceRegister
     */
    private static AuthServiceRegister: AuthServiceRegister;

    /**
     * 所有AuthService Map对象
     *
     * @private
     * @static
     * @memberof AuthServiceRegister
     */
    private static allAuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of AuthServiceRegister.
     * @memberof AuthServiceRegister
     */
    constructor() {
        this.init();
    }

    /**
     * 获取allAuthServiceMap 单例对象
     *
     * @public
     * @static
     * @memberof AuthServiceRegister
     */
    public static getInstance() {
        if (!this.AuthServiceRegister) {
            this.AuthServiceRegister = new AuthServiceRegister();
        }
        return this.AuthServiceRegister;
    }

    /**
     * 初始化
     *
     * @protected
     * @memberof AuthServiceRegister
     */
    protected init(): void {
                AuthServiceRegister.allAuthServiceMap.set('productplan', () => import('../authservice/product-plan/product-plan-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzreportroleconfig', () => import('../authservice/ibz-report-role-config/ibz-report-role-config-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('accounttesttask', () => import('../authservice/account-test-task/account-test-task-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('testcase', () => import('../authservice/test-case/test-case-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('sysaccount', () => import('../authservice/sys-account/sys-account-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('product', () => import('../authservice/product/product-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzcasestep', () => import('../authservice/ibzcase-step/ibzcase-step-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('taskteam', () => import('../authservice/task-team/task-team-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('file', () => import('../authservice/file/file-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzagent', () => import('../authservice/ibz-agent/ibz-agent-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzprostorymodule', () => import('../authservice/ibzpro-story-module/ibzpro-story-module-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('productsum', () => import('../authservice/product-sum/product-sum-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzlibcasesteps', () => import('../authservice/ibz-lib-casesteps/ibz-lib-casesteps-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('testcaselib', () => import('../authservice/test-case-lib/test-case-lib-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('daily', () => import('../authservice/daily/daily-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('accounttestcase', () => import('../authservice/account-test-case/account-test-case-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('suitecase', () => import('../authservice/suite-case/suite-case-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('projectburn', () => import('../authservice/project-burn/project-burn-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('test', () => import('../authservice/test/test-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('employeeload', () => import('../authservice/emp-loyeeload/emp-loyeeload-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('reportsummary', () => import('../authservice/report-summary/report-summary-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibztaskestimate', () => import('../authservice/ibztask-estimate/ibztask-estimate-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('storyspec', () => import('../authservice/story-spec/story-spec-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('syspost', () => import('../authservice/sys-post/sys-post-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('usertpl', () => import('../authservice/user-tpl/user-tpl-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('taskstats', () => import('../authservice/task-stats/task-stats-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('projectweekly', () => import('../authservice/project-weekly/project-weekly-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzfavorites', () => import('../authservice/ibz-favorites/ibz-favorites-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('productbranch', () => import('../authservice/product-branch/product-branch-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('sysdepartment', () => import('../authservice/sys-department/sys-department-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('productstats', () => import('../authservice/product-stats/product-stats-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzprojectmember', () => import('../authservice/ibz-project-member/ibz-project-member-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('action', () => import('../authservice/action/action-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('bugstats', () => import('../authservice/bug-stats/bug-stats-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibizproindex', () => import('../authservice/ibizpro-index/ibizpro-index-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('group', () => import('../authservice/group/group-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzproproduct', () => import('../authservice/ibzpro-product/ibzpro-product-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('testcasestep', () => import('../authservice/test-case-step/test-case-step-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('dept', () => import('../authservice/dept/dept-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibizprotag', () => import('../authservice/ibizpro-tag/ibizpro-tag-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('company', () => import('../authservice/company/company-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('systeam', () => import('../authservice/sys-team/sys-team-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('taskestimate', () => import('../authservice/task-estimate/task-estimate-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('story', () => import('../authservice/story/story-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('taskestimatestats', () => import('../authservice/taskestimatestats/taskestimatestats-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzlibcasesteptmp', () => import('../authservice/ibz-lib-case-step-tmp/ibz-lib-case-step-tmp-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('todo', () => import('../authservice/todo/todo-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('dynafilter', () => import('../authservice/dyna-filter/dyna-filter-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('testcaselibmodule', () => import('../authservice/test-case-lib-module/test-case-lib-module-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('productweekly', () => import('../authservice/product-weekly/product-weekly-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('useryearworkstats', () => import('../authservice/user-year-work-stats/user-year-work-stats-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('sysorganization', () => import('../authservice/sys-organization/sys-organization-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibizproplugin', () => import('../authservice/ibizpro-plugin/ibizpro-plugin-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('subproductplan', () => import('../authservice/sub-product-plan/sub-product-plan-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('project', () => import('../authservice/project/project-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('reportly', () => import('../authservice/reportly/reportly-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('subtask', () => import('../authservice/sub-task/sub-task-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('history', () => import('../authservice/history/history-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('projectmonthly', () => import('../authservice/project-monthly/project-monthly-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('user', () => import('../authservice/user/user-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('doclib', () => import('../authservice/doc-lib/doc-lib-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('companystats', () => import('../authservice/company-stats/company-stats-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('productmodule', () => import('../authservice/product-module/product-module-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('productteam', () => import('../authservice/product-team/product-team-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('testmodule', () => import('../authservice/test-module/test-module-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('substory', () => import('../authservice/sub-story/sub-story-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('projectdaily', () => import('../authservice/project-daily/project-daily-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzplantemplet', () => import('../authservice/ibz-plan-templet/ibz-plan-templet-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('sysuserrole', () => import('../authservice/sys-user-role/sys-user-role-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibizprokeyword', () => import('../authservice/ibizpro-keyword/ibizpro-keyword-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('doclibmodule', () => import('../authservice/doc-lib-module/doc-lib-module-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('productlife', () => import('../authservice/product-life/product-life-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('usercontact', () => import('../authservice/user-contact/user-contact-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('accountbug', () => import('../authservice/account-bug/account-bug-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('productdaily', () => import('../authservice/product-daily/product-daily-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('sysrole', () => import('../authservice/sys-role/sys-role-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('plantempletdetail', () => import('../authservice/plan-templet-detail/plan-templet-detail-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('accountproject', () => import('../authservice/account-project/account-project-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('task', () => import('../authservice/task/task-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('build', () => import('../authservice/build/build-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('productmonthly', () => import('../authservice/product-monthly/product-monthly-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('testreult', () => import('../authservice/test-reult/test-reult-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('testsuite', () => import('../authservice/test-suite/test-suite-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzplantempletdetail', () => import('../authservice/ibz-plan-templet-detail/ibz-plan-templet-detail-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzproconfig', () => import('../authservice/ibzpro-config/ibzpro-config-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzprostory', () => import('../authservice/ibzpro-story/ibzpro-story-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('taskteamnested', () => import('../authservice/task-team-nested/task-team-nested-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('accountproduct', () => import('../authservice/account-product/account-product-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('projectteam', () => import('../authservice/project-team/project-team-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('testtask', () => import('../authservice/test-task/test-task-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('pssyssfpub', () => import('../authservice/pssys-sfpub/pssys-sfpub-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('testreport', () => import('../authservice/test-report/test-report-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('projectstats', () => import('../authservice/project-stats/project-stats-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('testrun', () => import('../authservice/test-run/test-run-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('monthly', () => import('../authservice/monthly/monthly-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzmyterritory', () => import('../authservice/ibz-my-territory/ibz-my-territory-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('sysupdatelog', () => import('../authservice/sys-update-log/sys-update-log-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('doc', () => import('../authservice/doc/doc-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('accountstory', () => import('../authservice/account-story/account-story-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('bug', () => import('../authservice/bug/bug-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzweekly', () => import('../authservice/ibzweekly/ibzweekly-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('projectmodule', () => import('../authservice/project-module/project-module-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('pssysapp', () => import('../authservice/pssys-app/pssys-app-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzproprojectusertask', () => import('../authservice/ibzpro-project-user-task/ibzpro-project-user-task-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzcase', () => import('../authservice/ibz-case/ibz-case-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('systeammember', () => import('../authservice/sys-team-member/sys-team-member-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('pssystemdbcfg', () => import('../authservice/pssystem-dbcfg/pssystem-dbcfg-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzproproductusertask', () => import('../authservice/ibzpro-product-user-task/ibzpro-product-user-task-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('accounttask', () => import('../authservice/account-task/account-task-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('sysupdatefeatures', () => import('../authservice/sys-update-features/sys-update-features-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('productrelease', () => import('../authservice/product-release/product-release-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('casestats', () => import('../authservice/case-stats/case-stats-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('dynadashboard', () => import('../authservice/dyna-dashboard/dyna-dashboard-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('employee', () => import('../authservice/employee/employee-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('productline', () => import('../authservice/product-line/product-line-auth-service'));
    }

    /**
     * 获取指定AuthService
     *
     * @public
     * @memberof UIServiceRegister
     */
    public async getService(context: any, entityKey: string) {
        const importService = AuthServiceRegister.allAuthServiceMap.get(entityKey);
        if (importService) {
            const importModule = await importService();
            return importModule.default.getInstance(context);
        }
    }

}