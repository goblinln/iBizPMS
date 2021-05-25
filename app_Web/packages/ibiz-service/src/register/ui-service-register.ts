
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
        UIServiceRegister.allUIServiceMap.set('case', () => import('../uiservice/case/case-ui-service'));
        UIServiceRegister.allUIServiceMap.set('sysuser', () => import('../uiservice/sys-user/sys-user-ui-service'));
        UIServiceRegister.allUIServiceMap.set('product', () => import('../uiservice/product/product-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzcasestep', () => import('../uiservice/ibzcase-step/ibzcase-step-ui-service'));
        UIServiceRegister.allUIServiceMap.set('taskteam', () => import('../uiservice/task-team/task-team-ui-service'));
        UIServiceRegister.allUIServiceMap.set('file', () => import('../uiservice/file/file-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzagent', () => import('../uiservice/ibz-agent/ibz-agent-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzprostorymodule', () => import('../uiservice/ibzpro-story-module/ibzpro-story-module-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productsum', () => import('../uiservice/product-sum/product-sum-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzlibcasesteps', () => import('../uiservice/ibz-lib-casesteps/ibz-lib-casesteps-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzlib', () => import('../uiservice/ibz-lib/ibz-lib-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzdaily', () => import('../uiservice/ibz-daily/ibz-daily-ui-service'));
        UIServiceRegister.allUIServiceMap.set('suitecase', () => import('../uiservice/suite-case/suite-case-ui-service'));
        UIServiceRegister.allUIServiceMap.set('burn', () => import('../uiservice/burn/burn-ui-service'));
        UIServiceRegister.allUIServiceMap.set('employeeload', () => import('../uiservice/emp-loyeeload/emp-loyeeload-ui-service'));
        UIServiceRegister.allUIServiceMap.set('doccontent', () => import('../uiservice/doc-content/doc-content-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzreport', () => import('../uiservice/ibz-report/ibz-report-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibztaskestimate', () => import('../uiservice/ibztask-estimate/ibztask-estimate-ui-service'));
        UIServiceRegister.allUIServiceMap.set('syspost', () => import('../uiservice/sys-post/sys-post-ui-service'));
        UIServiceRegister.allUIServiceMap.set('usertpl', () => import('../uiservice/user-tpl/user-tpl-ui-service'));
        UIServiceRegister.allUIServiceMap.set('taskstats', () => import('../uiservice/task-stats/task-stats-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibizproprojectweekly', () => import('../uiservice/ibizpro-project-weekly/ibizpro-project-weekly-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzfavorites', () => import('../uiservice/ibz-favorites/ibz-favorites-ui-service'));
        UIServiceRegister.allUIServiceMap.set('sysdepartment', () => import('../uiservice/sys-department/sys-department-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productstats', () => import('../uiservice/product-stats/product-stats-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzprojectmember', () => import('../uiservice/ibz-project-member/ibz-project-member-ui-service'));
        UIServiceRegister.allUIServiceMap.set('bugstats', () => import('../uiservice/bug-stats/bug-stats-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibizproindex', () => import('../uiservice/ibizpro-index/ibizpro-index-ui-service'));
        UIServiceRegister.allUIServiceMap.set('group', () => import('../uiservice/group/group-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzproproduct', () => import('../uiservice/ibzpro-product/ibzpro-product-ui-service'));
        UIServiceRegister.allUIServiceMap.set('casestep', () => import('../uiservice/case-step/case-step-ui-service'));
        UIServiceRegister.allUIServiceMap.set('dept', () => import('../uiservice/dept/dept-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibizprotag', () => import('../uiservice/ibizpro-tag/ibizpro-tag-ui-service'));
        UIServiceRegister.allUIServiceMap.set('company', () => import('../uiservice/company/company-ui-service'));
        UIServiceRegister.allUIServiceMap.set('systeam', () => import('../uiservice/sys-team/sys-team-ui-service'));
        UIServiceRegister.allUIServiceMap.set('taskestimate', () => import('../uiservice/task-estimate/task-estimate-ui-service'));
        UIServiceRegister.allUIServiceMap.set('story', () => import('../uiservice/story/story-ui-service'));
        UIServiceRegister.allUIServiceMap.set('taskestimatestats', () => import('../uiservice/taskestimatestats/taskestimatestats-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzlibcasesteptmp', () => import('../uiservice/ibz-lib-case-step-tmp/ibz-lib-case-step-tmp-ui-service'));
        UIServiceRegister.allUIServiceMap.set('todo', () => import('../uiservice/todo/todo-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzlibmodule', () => import('../uiservice/ibz-lib-module/ibz-lib-module-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibizproproductweekly', () => import('../uiservice/ibizpro-product-weekly/ibizpro-product-weekly-ui-service'));
        UIServiceRegister.allUIServiceMap.set('useryearworkstats', () => import('../uiservice/user-year-work-stats/user-year-work-stats-ui-service'));
        UIServiceRegister.allUIServiceMap.set('sysorganization', () => import('../uiservice/sys-organization/sys-organization-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibizproplugin', () => import('../uiservice/ibizpro-plugin/ibizpro-plugin-ui-service'));
        UIServiceRegister.allUIServiceMap.set('subproductplan', () => import('../uiservice/sub-product-plan/sub-product-plan-ui-service'));
        UIServiceRegister.allUIServiceMap.set('project', () => import('../uiservice/project/project-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzreportly', () => import('../uiservice/ibz-reportly/ibz-reportly-ui-service'));
        UIServiceRegister.allUIServiceMap.set('subtask', () => import('../uiservice/sub-task/sub-task-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibizproprojectmonthly', () => import('../uiservice/ibizpro-project-monthly/ibizpro-project-monthly-ui-service'));
        UIServiceRegister.allUIServiceMap.set('user', () => import('../uiservice/user/user-ui-service'));
        UIServiceRegister.allUIServiceMap.set('doclib', () => import('../uiservice/doc-lib/doc-lib-ui-service'));
        UIServiceRegister.allUIServiceMap.set('companystats', () => import('../uiservice/company-stats/company-stats-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productmodule', () => import('../uiservice/product-module/product-module-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productteam', () => import('../uiservice/productteam/productteam-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testmodule', () => import('../uiservice/test-module/test-module-ui-service'));
        UIServiceRegister.allUIServiceMap.set('substory', () => import('../uiservice/sub-story/sub-story-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibizproprojectdaily', () => import('../uiservice/ibizpro-project-daily/ibizpro-project-daily-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzplantemplet', () => import('../uiservice/ibz-plan-templet/ibz-plan-templet-ui-service'));
        UIServiceRegister.allUIServiceMap.set('sysuserrole', () => import('../uiservice/sys-user-role/sys-user-role-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibizprokeyword', () => import('../uiservice/ibizpro-keyword/ibizpro-keyword-ui-service'));
        UIServiceRegister.allUIServiceMap.set('doclibmodule', () => import('../uiservice/doc-lib-module/doc-lib-module-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productlife', () => import('../uiservice/product-life/product-life-ui-service'));
        UIServiceRegister.allUIServiceMap.set('usercontact', () => import('../uiservice/user-contact/user-contact-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibizproproductdaily', () => import('../uiservice/ibizpro-product-daily/ibizpro-product-daily-ui-service'));
        UIServiceRegister.allUIServiceMap.set('sysrole', () => import('../uiservice/sys-role/sys-role-ui-service'));
        UIServiceRegister.allUIServiceMap.set('plantempletdetail', () => import('../uiservice/plan-templet-detail/plan-templet-detail-ui-service'));
        UIServiceRegister.allUIServiceMap.set('task', () => import('../uiservice/task/task-ui-service'));
        UIServiceRegister.allUIServiceMap.set('build', () => import('../uiservice/build/build-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibizproproductmonthly', () => import('../uiservice/ibizpro-product-monthly/ibizpro-product-monthly-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testresult', () => import('../uiservice/test-result/test-result-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testsuite', () => import('../uiservice/test-suite/test-suite-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzplantempletdetail', () => import('../uiservice/ibz-plan-templet-detail/ibz-plan-templet-detail-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzproconfig', () => import('../uiservice/ibzpro-config/ibzpro-config-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzprostory', () => import('../uiservice/ibzpro-story/ibzpro-story-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibztaskteam', () => import('../uiservice/ibztask-team/ibztask-team-ui-service'));
        UIServiceRegister.allUIServiceMap.set('projectteam', () => import('../uiservice/project-team/project-team-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testtask', () => import('../uiservice/test-task/test-task-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productline', () => import('../uiservice/product-line/product-line-ui-service'));
        UIServiceRegister.allUIServiceMap.set('pssyssfpub', () => import('../uiservice/pssys-sfpub/pssys-sfpub-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testreport', () => import('../uiservice/test-report/test-report-ui-service'));
        UIServiceRegister.allUIServiceMap.set('projectstats', () => import('../uiservice/project-stats/project-stats-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testrun', () => import('../uiservice/test-run/test-run-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzmonthly', () => import('../uiservice/ibz-monthly/ibz-monthly-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzmyterritory', () => import('../uiservice/ibz-my-territory/ibz-my-territory-ui-service'));
        UIServiceRegister.allUIServiceMap.set('sysupdatelog', () => import('../uiservice/sys-update-log/sys-update-log-ui-service'));
        UIServiceRegister.allUIServiceMap.set('doc', () => import('../uiservice/doc/doc-ui-service'));
        UIServiceRegister.allUIServiceMap.set('bug', () => import('../uiservice/bug/bug-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzweekly', () => import('../uiservice/ibzweekly/ibzweekly-ui-service'));
        UIServiceRegister.allUIServiceMap.set('projectmodule', () => import('../uiservice/project-module/project-module-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzdoc', () => import('../uiservice/ibz-doc/ibz-doc-ui-service'));
        UIServiceRegister.allUIServiceMap.set('pssysapp', () => import('../uiservice/pssys-app/pssys-app-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzproprojectusertask', () => import('../uiservice/ibzpro-project-user-task/ibzpro-project-user-task-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzcase', () => import('../uiservice/ibz-case/ibz-case-ui-service'));
        UIServiceRegister.allUIServiceMap.set('systeammember', () => import('../uiservice/sys-team-member/sys-team-member-ui-service'));
        UIServiceRegister.allUIServiceMap.set('pssystemdbcfg', () => import('../uiservice/pssystem-dbcfg/pssystem-dbcfg-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzproproductusertask', () => import('../uiservice/ibzpro-product-user-task/ibzpro-product-user-task-ui-service'));
        UIServiceRegister.allUIServiceMap.set('sysupdatefeatures', () => import('../uiservice/sys-update-features/sys-update-features-ui-service'));
        UIServiceRegister.allUIServiceMap.set('release', () => import('../uiservice/release/release-ui-service'));
        UIServiceRegister.allUIServiceMap.set('casestats', () => import('../uiservice/case-stats/case-stats-ui-service'));
        UIServiceRegister.allUIServiceMap.set('dynadashboard', () => import('../uiservice/dyna-dashboard/dyna-dashboard-ui-service'));
        UIServiceRegister.allUIServiceMap.set('sysemployee', () => import('../uiservice/sys-employee/sys-employee-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzproproductline', () => import('../uiservice/ibzpro-product-line/ibzpro-product-line-ui-service'));
        UIServiceRegister.allUIServiceMap.set('projectproduct', () => import('../uiservice/project-product/project-product-ui-service'));
        UIServiceRegister.allUIServiceMap.set('accounttaskestimate', () => import('../uiservice/account-taskestimate/account-taskestimate-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzproproductaction', () => import('../uiservice/ibzpro-product-action/ibzpro-product-action-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productplanaction', () => import('../uiservice/product-plan-action/product-plan-action-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzprobuildaction', () => import('../uiservice/ibz-pro-build-action/ibz-pro-build-action-ui-service'));
        UIServiceRegister.allUIServiceMap.set('branch', () => import('../uiservice/branch/branch-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzproprojectaction', () => import('../uiservice/ibzpro-project-action/ibzpro-project-action-ui-service'));
        UIServiceRegister.allUIServiceMap.set('action', () => import('../uiservice/action/action-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzprobugaction', () => import('../uiservice/ibz-pro-bug-action/ibz-pro-bug-action-ui-service'));
        UIServiceRegister.allUIServiceMap.set('dynafilter', () => import('../uiservice/dyna-filter/dyna-filter-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzproweeklyaction', () => import('../uiservice/ibzpro-weekly-action/ibzpro-weekly-action-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzcaseaction', () => import('../uiservice/ibzcase-action/ibzcase-action-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzproreleaseaction', () => import('../uiservice/ibzpro-release-action/ibzpro-release-action-ui-service'));
        UIServiceRegister.allUIServiceMap.set('history', () => import('../uiservice/history/history-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzproproducthistory', () => import('../uiservice/ibzpro-product-history/ibzpro-product-history-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzpromonthlyaction', () => import('../uiservice/ibz-pro-monthly-action/ibz-pro-monthly-action-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzstoryaction', () => import('../uiservice/ibzstory-action/ibzstory-action-ui-service'));
        UIServiceRegister.allUIServiceMap.set('module', () => import('../uiservice/module/module-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzprotesttaskaction', () => import('../uiservice/ibz-pro-test-task-action/ibz-pro-test-task-action-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibztestsuiteaction', () => import('../uiservice/ibztest-suite-action/ibztest-suite-action-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibztestreportaction', () => import('../uiservice/ibztest-report-action/ibztest-report-action-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzproreportlyaction', () => import('../uiservice/ibz-pro-reportly-action/ibz-pro-reportly-action-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzdailyaction', () => import('../uiservice/ibzdaily-action/ibzdaily-action-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzprotodoaction', () => import('../uiservice/ibzpro-to-do-action/ibzpro-to-do-action-ui-service'));
        UIServiceRegister.allUIServiceMap.set('projecttaskestimate', () => import('../uiservice/project-taskestimate/project-taskestimate-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibztaskaction', () => import('../uiservice/ibztask-action/ibztask-action-ui-service'));
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