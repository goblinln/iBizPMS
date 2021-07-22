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
                AuthServiceRegister.allAuthServiceMap.set('monthly', () => import('../authservice/monthly/monthly-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('sysemployee', () => import('../authservice/sys-employee/sys-employee-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('taskteam', () => import('../authservice/task-team/task-team-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzmyterritory', () => import('../authservice/ibz-my-territory/ibz-my-territory-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('action', () => import('../authservice/action/action-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('systeam', () => import('../authservice/sys-team/sys-team-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('taskestimate', () => import('../authservice/task-estimate/task-estimate-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzfavorites', () => import('../authservice/ibz-favorites/ibz-favorites-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('syspost', () => import('../authservice/sys-post/sys-post-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('sysdepartment', () => import('../authservice/sys-department/sys-department-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('testcasestep', () => import('../authservice/test-case-step/test-case-step-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('reportly', () => import('../authservice/reportly/reportly-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('doccontent', () => import('../authservice/doc-content/doc-content-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('testmodule', () => import('../authservice/test-module/test-module-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('projectstats', () => import('../authservice/project-stats/project-stats-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibztaskteam', () => import('../authservice/ibztaskteam/ibztaskteam-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibztaskestimate', () => import('../authservice/ibz-taskestimate/ibz-taskestimate-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('project', () => import('../authservice/project/project-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('systeammember', () => import('../authservice/sys-team-member/sys-team-member-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('testsuite', () => import('../authservice/test-suite/test-suite-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('projectteam', () => import('../authservice/project-team/project-team-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('file', () => import('../authservice/file/file-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('productbranch', () => import('../authservice/product-branch/product-branch-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('productrelease', () => import('../authservice/product-release/product-release-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('testcase', () => import('../authservice/test-case/test-case-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('projectmodule', () => import('../authservice/project-module/project-module-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('daily', () => import('../authservice/daily/daily-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('doclib', () => import('../authservice/doc-lib/doc-lib-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('dynafilter', () => import('../authservice/dyna-filter/dyna-filter-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('module', () => import('../authservice/module/module-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('build', () => import('../authservice/build/build-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('bug', () => import('../authservice/bug/bug-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('product', () => import('../authservice/product/product-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('productplan', () => import('../authservice/product-plan/product-plan-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('doc', () => import('../authservice/doc/doc-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('sysupdatelog', () => import('../authservice/sys-update-log/sys-update-log-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('sysaccount', () => import('../authservice/sys-account/sys-account-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('user', () => import('../authservice/user/user-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('dynadashboard', () => import('../authservice/dyna-dashboard/dyna-dashboard-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('productmodule', () => import('../authservice/product-module/product-module-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('reportsummary', () => import('../authservice/report-summary/report-summary-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('productstats', () => import('../authservice/product-stats/product-stats-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('usercontact', () => import('../authservice/user-contact/user-contact-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('sysupdatefeatures', () => import('../authservice/sys-update-features/sys-update-features-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('task', () => import('../authservice/task/task-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('todo', () => import('../authservice/todo/todo-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('sysorganization', () => import('../authservice/sys-organization/sys-organization-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('doclibmodule', () => import('../authservice/doc-lib-module/doc-lib-module-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('testtask', () => import('../authservice/test-task/test-task-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('test', () => import('../authservice/test/test-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('weekly', () => import('../authservice/weekly/weekly-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('ibzprojectteam', () => import('../authservice/ibzprojectteam/ibzprojectteam-auth-service'));
        AuthServiceRegister.allAuthServiceMap.set('story', () => import('../authservice/story/story-auth-service'));
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