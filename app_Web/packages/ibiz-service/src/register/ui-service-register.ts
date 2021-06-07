
/**
 * UI服务注册中心
 *
 * @export
 * @class UIServiceRegister
 */
export class UIServiceRegister {

    /**
     * UIServiceRegister 单例对象
     *
     * @private
     * @static
     * @memberof UIServiceRegister
     */
    private static UIServiceRegister:UIServiceRegister;

    /**
     * 所有UIService Map对象
     *
     * @private
     * @static
     * @memberof UIServiceRegister
     */    
    private static allUIServiceMap:Map<string,any> = new Map();

    /**
     * Creates an instance of UIServiceRegister.
     * @memberof UIServiceRegister
     */
    constructor() {
        this.init();
    }

    /**
     * 获取UIServiceRegister 单例对象
     *
     * @public
     * @static
     * @memberof UIServiceRegister
     */
    public static getInstance(){
        if(!this.UIServiceRegister){
            this.UIServiceRegister = new UIServiceRegister();
        }
        return this.UIServiceRegister;
    }

    /**
     * 初始化
     *
     * @protected
     * @memberof UIServiceRegister
     */
    protected init(): void {
                UIServiceRegister.allUIServiceMap.set('productplan', () => import('../uiservice/product-plan/product-plan-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzreportroleconfig', () => import('../uiservice/ibz-report-role-config/ibz-report-role-config-ui-service'));
        UIServiceRegister.allUIServiceMap.set('accounttesttask', () => import('../uiservice/account-test-task/account-test-task-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testcase', () => import('../uiservice/test-case/test-case-ui-service'));
        UIServiceRegister.allUIServiceMap.set('sysaccount', () => import('../uiservice/sys-account/sys-account-ui-service'));
        UIServiceRegister.allUIServiceMap.set('product', () => import('../uiservice/product/product-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzcasestep', () => import('../uiservice/ibzcase-step/ibzcase-step-ui-service'));
        UIServiceRegister.allUIServiceMap.set('taskteam', () => import('../uiservice/task-team/task-team-ui-service'));
        UIServiceRegister.allUIServiceMap.set('file', () => import('../uiservice/file/file-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzagent', () => import('../uiservice/ibz-agent/ibz-agent-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzprostorymodule', () => import('../uiservice/ibzpro-story-module/ibzpro-story-module-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productsum', () => import('../uiservice/product-sum/product-sum-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzlibcasesteps', () => import('../uiservice/ibz-lib-casesteps/ibz-lib-casesteps-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testcaselib', () => import('../uiservice/test-case-lib/test-case-lib-ui-service'));
        UIServiceRegister.allUIServiceMap.set('daily', () => import('../uiservice/daily/daily-ui-service'));
        UIServiceRegister.allUIServiceMap.set('accounttestcase', () => import('../uiservice/account-test-case/account-test-case-ui-service'));
        UIServiceRegister.allUIServiceMap.set('suitecase', () => import('../uiservice/suite-case/suite-case-ui-service'));
        UIServiceRegister.allUIServiceMap.set('projectburn', () => import('../uiservice/project-burn/project-burn-ui-service'));
        UIServiceRegister.allUIServiceMap.set('test', () => import('../uiservice/test/test-ui-service'));
        UIServiceRegister.allUIServiceMap.set('employeeload', () => import('../uiservice/emp-loyeeload/emp-loyeeload-ui-service'));
        UIServiceRegister.allUIServiceMap.set('reportsummary', () => import('../uiservice/report-summary/report-summary-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibztaskestimate', () => import('../uiservice/ibztask-estimate/ibztask-estimate-ui-service'));
        UIServiceRegister.allUIServiceMap.set('storyspec', () => import('../uiservice/story-spec/story-spec-ui-service'));
        UIServiceRegister.allUIServiceMap.set('syspost', () => import('../uiservice/sys-post/sys-post-ui-service'));
        UIServiceRegister.allUIServiceMap.set('usertpl', () => import('../uiservice/user-tpl/user-tpl-ui-service'));
        UIServiceRegister.allUIServiceMap.set('taskstats', () => import('../uiservice/task-stats/task-stats-ui-service'));
        UIServiceRegister.allUIServiceMap.set('projectweekly', () => import('../uiservice/project-weekly/project-weekly-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzfavorites', () => import('../uiservice/ibz-favorites/ibz-favorites-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productbranch', () => import('../uiservice/product-branch/product-branch-ui-service'));
        UIServiceRegister.allUIServiceMap.set('sysdepartment', () => import('../uiservice/sys-department/sys-department-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productstats', () => import('../uiservice/product-stats/product-stats-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzprojectmember', () => import('../uiservice/ibz-project-member/ibz-project-member-ui-service'));
        UIServiceRegister.allUIServiceMap.set('action', () => import('../uiservice/action/action-ui-service'));
        UIServiceRegister.allUIServiceMap.set('bugstats', () => import('../uiservice/bug-stats/bug-stats-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibizproindex', () => import('../uiservice/ibizpro-index/ibizpro-index-ui-service'));
        UIServiceRegister.allUIServiceMap.set('group', () => import('../uiservice/group/group-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzproproduct', () => import('../uiservice/ibzpro-product/ibzpro-product-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testcasestep', () => import('../uiservice/test-case-step/test-case-step-ui-service'));
        UIServiceRegister.allUIServiceMap.set('dept', () => import('../uiservice/dept/dept-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibizprotag', () => import('../uiservice/ibizpro-tag/ibizpro-tag-ui-service'));
        UIServiceRegister.allUIServiceMap.set('company', () => import('../uiservice/company/company-ui-service'));
        UIServiceRegister.allUIServiceMap.set('systeam', () => import('../uiservice/sys-team/sys-team-ui-service'));
        UIServiceRegister.allUIServiceMap.set('taskestimate', () => import('../uiservice/task-estimate/task-estimate-ui-service'));
        UIServiceRegister.allUIServiceMap.set('story', () => import('../uiservice/story/story-ui-service'));
        UIServiceRegister.allUIServiceMap.set('taskestimatestats', () => import('../uiservice/taskestimatestats/taskestimatestats-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzlibcasesteptmp', () => import('../uiservice/ibz-lib-case-step-tmp/ibz-lib-case-step-tmp-ui-service'));
        UIServiceRegister.allUIServiceMap.set('todo', () => import('../uiservice/todo/todo-ui-service'));
        UIServiceRegister.allUIServiceMap.set('dynafilter', () => import('../uiservice/dyna-filter/dyna-filter-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testcaselibmodule', () => import('../uiservice/test-case-lib-module/test-case-lib-module-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productweekly', () => import('../uiservice/product-weekly/product-weekly-ui-service'));
        UIServiceRegister.allUIServiceMap.set('useryearworkstats', () => import('../uiservice/user-year-work-stats/user-year-work-stats-ui-service'));
        UIServiceRegister.allUIServiceMap.set('sysorganization', () => import('../uiservice/sys-organization/sys-organization-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibizproplugin', () => import('../uiservice/ibizpro-plugin/ibizpro-plugin-ui-service'));
        UIServiceRegister.allUIServiceMap.set('subproductplan', () => import('../uiservice/sub-product-plan/sub-product-plan-ui-service'));
        UIServiceRegister.allUIServiceMap.set('project', () => import('../uiservice/project/project-ui-service'));
        UIServiceRegister.allUIServiceMap.set('reportly', () => import('../uiservice/reportly/reportly-ui-service'));
        UIServiceRegister.allUIServiceMap.set('subtask', () => import('../uiservice/sub-task/sub-task-ui-service'));
        UIServiceRegister.allUIServiceMap.set('history', () => import('../uiservice/history/history-ui-service'));
        UIServiceRegister.allUIServiceMap.set('projectmonthly', () => import('../uiservice/project-monthly/project-monthly-ui-service'));
        UIServiceRegister.allUIServiceMap.set('user', () => import('../uiservice/user/user-ui-service'));
        UIServiceRegister.allUIServiceMap.set('doclib', () => import('../uiservice/doc-lib/doc-lib-ui-service'));
        UIServiceRegister.allUIServiceMap.set('companystats', () => import('../uiservice/company-stats/company-stats-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productmodule', () => import('../uiservice/product-module/product-module-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productteam', () => import('../uiservice/product-team/product-team-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testmodule', () => import('../uiservice/test-module/test-module-ui-service'));
        UIServiceRegister.allUIServiceMap.set('substory', () => import('../uiservice/sub-story/sub-story-ui-service'));
        UIServiceRegister.allUIServiceMap.set('projectdaily', () => import('../uiservice/project-daily/project-daily-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzplantemplet', () => import('../uiservice/ibz-plan-templet/ibz-plan-templet-ui-service'));
        UIServiceRegister.allUIServiceMap.set('sysuserrole', () => import('../uiservice/sys-user-role/sys-user-role-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibizprokeyword', () => import('../uiservice/ibizpro-keyword/ibizpro-keyword-ui-service'));
        UIServiceRegister.allUIServiceMap.set('doclibmodule', () => import('../uiservice/doc-lib-module/doc-lib-module-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productlife', () => import('../uiservice/product-life/product-life-ui-service'));
        UIServiceRegister.allUIServiceMap.set('usercontact', () => import('../uiservice/user-contact/user-contact-ui-service'));
        UIServiceRegister.allUIServiceMap.set('accountbug', () => import('../uiservice/account-bug/account-bug-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productdaily', () => import('../uiservice/product-daily/product-daily-ui-service'));
        UIServiceRegister.allUIServiceMap.set('sysrole', () => import('../uiservice/sys-role/sys-role-ui-service'));
        UIServiceRegister.allUIServiceMap.set('plantempletdetail', () => import('../uiservice/plan-templet-detail/plan-templet-detail-ui-service'));
        UIServiceRegister.allUIServiceMap.set('accountproject', () => import('../uiservice/account-project/account-project-ui-service'));
        UIServiceRegister.allUIServiceMap.set('task', () => import('../uiservice/task/task-ui-service'));
        UIServiceRegister.allUIServiceMap.set('build', () => import('../uiservice/build/build-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productmonthly', () => import('../uiservice/product-monthly/product-monthly-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testreult', () => import('../uiservice/test-reult/test-reult-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testsuite', () => import('../uiservice/test-suite/test-suite-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzplantempletdetail', () => import('../uiservice/ibz-plan-templet-detail/ibz-plan-templet-detail-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzproconfig', () => import('../uiservice/ibzpro-config/ibzpro-config-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzprostory', () => import('../uiservice/ibzpro-story/ibzpro-story-ui-service'));
        UIServiceRegister.allUIServiceMap.set('taskteamnested', () => import('../uiservice/task-team-nested/task-team-nested-ui-service'));
        UIServiceRegister.allUIServiceMap.set('accountproduct', () => import('../uiservice/account-product/account-product-ui-service'));
        UIServiceRegister.allUIServiceMap.set('projectteam', () => import('../uiservice/project-team/project-team-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testtask', () => import('../uiservice/test-task/test-task-ui-service'));
        UIServiceRegister.allUIServiceMap.set('pssyssfpub', () => import('../uiservice/pssys-sfpub/pssys-sfpub-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testreport', () => import('../uiservice/test-report/test-report-ui-service'));
        UIServiceRegister.allUIServiceMap.set('projectstats', () => import('../uiservice/project-stats/project-stats-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testrun', () => import('../uiservice/test-run/test-run-ui-service'));
        UIServiceRegister.allUIServiceMap.set('monthly', () => import('../uiservice/monthly/monthly-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzmyterritory', () => import('../uiservice/ibz-my-territory/ibz-my-territory-ui-service'));
        UIServiceRegister.allUIServiceMap.set('sysupdatelog', () => import('../uiservice/sys-update-log/sys-update-log-ui-service'));
        UIServiceRegister.allUIServiceMap.set('doc', () => import('../uiservice/doc/doc-ui-service'));
        UIServiceRegister.allUIServiceMap.set('accountstory', () => import('../uiservice/account-story/account-story-ui-service'));
        UIServiceRegister.allUIServiceMap.set('bug', () => import('../uiservice/bug/bug-ui-service'));
        UIServiceRegister.allUIServiceMap.set('weekly', () => import('../uiservice/weekly/weekly-ui-service'));
        UIServiceRegister.allUIServiceMap.set('projectmodule', () => import('../uiservice/project-module/project-module-ui-service'));
        UIServiceRegister.allUIServiceMap.set('pssysapp', () => import('../uiservice/pssys-app/pssys-app-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzproprojectusertask', () => import('../uiservice/ibzpro-project-user-task/ibzpro-project-user-task-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzcase', () => import('../uiservice/ibz-case/ibz-case-ui-service'));
        UIServiceRegister.allUIServiceMap.set('systeammember', () => import('../uiservice/sys-team-member/sys-team-member-ui-service'));
        UIServiceRegister.allUIServiceMap.set('pssystemdbcfg', () => import('../uiservice/pssystem-dbcfg/pssystem-dbcfg-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzproproductusertask', () => import('../uiservice/ibzpro-product-user-task/ibzpro-product-user-task-ui-service'));
        UIServiceRegister.allUIServiceMap.set('accounttask', () => import('../uiservice/account-task/account-task-ui-service'));
        UIServiceRegister.allUIServiceMap.set('sysupdatefeatures', () => import('../uiservice/sys-update-features/sys-update-features-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productrelease', () => import('../uiservice/product-release/product-release-ui-service'));
        UIServiceRegister.allUIServiceMap.set('casestats', () => import('../uiservice/case-stats/case-stats-ui-service'));
        UIServiceRegister.allUIServiceMap.set('dynadashboard', () => import('../uiservice/dyna-dashboard/dyna-dashboard-ui-service'));
        UIServiceRegister.allUIServiceMap.set('employee', () => import('../uiservice/employee/employee-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productline', () => import('../uiservice/product-line/product-line-ui-service'));
    }

    /**
     * 获取指定UIService
     *
     * @public
     * @memberof UIServiceRegister
     */
    public async getService(context:any,entityKey:string){
        const importService = UIServiceRegister.allUIServiceMap.get(entityKey);
        if(importService){
            const importModule = await importService();
            return importModule.default.getInstance(context);
        }
    }

}