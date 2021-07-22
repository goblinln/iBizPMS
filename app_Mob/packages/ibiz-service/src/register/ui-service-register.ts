
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
                UIServiceRegister.allUIServiceMap.set('monthly', () => import('../uiservice/monthly/monthly-ui-service'));
        UIServiceRegister.allUIServiceMap.set('employee', () => import('../uiservice/employee/employee-ui-service'));
        UIServiceRegister.allUIServiceMap.set('taskteam', () => import('../uiservice/task-team/task-team-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzmyterritory', () => import('../uiservice/ibz-my-territory/ibz-my-territory-ui-service'));
        UIServiceRegister.allUIServiceMap.set('action', () => import('../uiservice/action/action-ui-service'));
        UIServiceRegister.allUIServiceMap.set('systeam', () => import('../uiservice/sys-team/sys-team-ui-service'));
        UIServiceRegister.allUIServiceMap.set('taskestimate', () => import('../uiservice/task-estimate/task-estimate-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzfavorites', () => import('../uiservice/ibz-favorites/ibz-favorites-ui-service'));
        UIServiceRegister.allUIServiceMap.set('syspost', () => import('../uiservice/sys-post/sys-post-ui-service'));
        UIServiceRegister.allUIServiceMap.set('sysdepartment', () => import('../uiservice/sys-department/sys-department-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testcasestep', () => import('../uiservice/test-case-step/test-case-step-ui-service'));
        UIServiceRegister.allUIServiceMap.set('reportly', () => import('../uiservice/reportly/reportly-ui-service'));
        UIServiceRegister.allUIServiceMap.set('doccontent', () => import('../uiservice/doc-content/doc-content-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testmodule', () => import('../uiservice/test-module/test-module-ui-service'));
        UIServiceRegister.allUIServiceMap.set('projectstats', () => import('../uiservice/project-stats/project-stats-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibztaskteam', () => import('../uiservice/ibztaskteam/ibztaskteam-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibztaskestimate', () => import('../uiservice/ibz-taskestimate/ibz-taskestimate-ui-service'));
        UIServiceRegister.allUIServiceMap.set('project', () => import('../uiservice/project/project-ui-service'));
        UIServiceRegister.allUIServiceMap.set('systeammember', () => import('../uiservice/sys-team-member/sys-team-member-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testsuite', () => import('../uiservice/test-suite/test-suite-ui-service'));
        UIServiceRegister.allUIServiceMap.set('projectteam', () => import('../uiservice/project-team/project-team-ui-service'));
        UIServiceRegister.allUIServiceMap.set('file', () => import('../uiservice/file/file-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productbranch', () => import('../uiservice/product-branch/product-branch-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productrelease', () => import('../uiservice/product-release/product-release-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testcase', () => import('../uiservice/test-case/test-case-ui-service'));
        UIServiceRegister.allUIServiceMap.set('projectmodule', () => import('../uiservice/project-module/project-module-ui-service'));
        UIServiceRegister.allUIServiceMap.set('daily', () => import('../uiservice/daily/daily-ui-service'));
        UIServiceRegister.allUIServiceMap.set('doclib', () => import('../uiservice/doc-lib/doc-lib-ui-service'));
        UIServiceRegister.allUIServiceMap.set('dynafilter', () => import('../uiservice/dyna-filter/dyna-filter-ui-service'));
        UIServiceRegister.allUIServiceMap.set('module', () => import('../uiservice/module/module-ui-service'));
        UIServiceRegister.allUIServiceMap.set('build', () => import('../uiservice/build/build-ui-service'));
        UIServiceRegister.allUIServiceMap.set('bug', () => import('../uiservice/bug/bug-ui-service'));
        UIServiceRegister.allUIServiceMap.set('product', () => import('../uiservice/product/product-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productplan', () => import('../uiservice/product-plan/product-plan-ui-service'));
        UIServiceRegister.allUIServiceMap.set('doc', () => import('../uiservice/doc/doc-ui-service'));
        UIServiceRegister.allUIServiceMap.set('sysupdatelog', () => import('../uiservice/sys-update-log/sys-update-log-ui-service'));
        UIServiceRegister.allUIServiceMap.set('sysaccount', () => import('../uiservice/sys-account/sys-account-ui-service'));
        UIServiceRegister.allUIServiceMap.set('user', () => import('../uiservice/user/user-ui-service'));
        UIServiceRegister.allUIServiceMap.set('dynadashboard', () => import('../uiservice/dyna-dashboard/dyna-dashboard-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productmodule', () => import('../uiservice/product-module/product-module-ui-service'));
        UIServiceRegister.allUIServiceMap.set('reportsummary', () => import('../uiservice/report-summary/report-summary-ui-service'));
        UIServiceRegister.allUIServiceMap.set('productstats', () => import('../uiservice/product-stats/product-stats-ui-service'));
        UIServiceRegister.allUIServiceMap.set('usercontact', () => import('../uiservice/user-contact/user-contact-ui-service'));
        UIServiceRegister.allUIServiceMap.set('sysupdatefeatures', () => import('../uiservice/sys-update-features/sys-update-features-ui-service'));
        UIServiceRegister.allUIServiceMap.set('task', () => import('../uiservice/task/task-ui-service'));
        UIServiceRegister.allUIServiceMap.set('todo', () => import('../uiservice/todo/todo-ui-service'));
        UIServiceRegister.allUIServiceMap.set('sysorganization', () => import('../uiservice/sys-organization/sys-organization-ui-service'));
        UIServiceRegister.allUIServiceMap.set('doclibmodule', () => import('../uiservice/doc-lib-module/doc-lib-module-ui-service'));
        UIServiceRegister.allUIServiceMap.set('testtask', () => import('../uiservice/test-task/test-task-ui-service'));
        UIServiceRegister.allUIServiceMap.set('test', () => import('../uiservice/test/test-ui-service'));
        UIServiceRegister.allUIServiceMap.set('weekly', () => import('../uiservice/weekly/weekly-ui-service'));
        UIServiceRegister.allUIServiceMap.set('ibzprojectteam', () => import('../uiservice/ibzprojectteam/ibzprojectteam-ui-service'));
        UIServiceRegister.allUIServiceMap.set('story', () => import('../uiservice/story/story-ui-service'));
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