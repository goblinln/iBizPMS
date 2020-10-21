/**
 * UI服务注册中心
 *
 * @export
 * @class UIServiceRegister
 */
export class UIServiceRegister {

    /**
     * 所有UI实体服务Map
     *
     * @protected
     * @type {*}
     * @memberof UIServiceRegister
     */
    protected allUIService: Map<string, () => Promise<any>> = new Map();

    /**
     * 已加载UI实体服务Map缓存
     *
     * @protected
     * @type {Map<string, any>}
     * @memberof UIServiceRegister
     */
    protected serviceCache: Map<string, any> = new Map();

    /**
     * Creates an instance of UIServiceRegister.
     * @memberof UIServiceRegister
     */
    constructor() {
        this.init();
    }

    /**
     * 初始化
     *
     * @protected
     * @memberof UIServiceRegister
     */
    protected init(): void {
                this.allUIService.set('productplan', () => import('@/uiservice/product-plan/product-plan-ui-service'));
        this.allUIService.set('projectproduct', () => import('@/uiservice/project-product/project-product-ui-service'));
        this.allUIService.set('case', () => import('@/uiservice/case/case-ui-service'));
        this.allUIService.set('ibztaskteam', () => import('@/uiservice/ibztask-team/ibztask-team-ui-service'));
        this.allUIService.set('product', () => import('@/uiservice/product/product-ui-service'));
        this.allUIService.set('taskteam', () => import('@/uiservice/task-team/task-team-ui-service'));
        this.allUIService.set('file', () => import('@/uiservice/file/file-ui-service'));
        this.allUIService.set('ibzprostorymodule', () => import('@/uiservice/ibzpro-story-module/ibzpro-story-module-ui-service'));
        this.allUIService.set('productsum', () => import('@/uiservice/product-sum/product-sum-ui-service'));
        this.allUIService.set('ibzlibcasesteps', () => import('@/uiservice/ibz-lib-casesteps/ibz-lib-casesteps-ui-service'));
        this.allUIService.set('ibzlib', () => import('@/uiservice/ibz-lib/ibz-lib-ui-service'));
        this.allUIService.set('suitecase', () => import('@/uiservice/suite-case/suite-case-ui-service'));
        this.allUIService.set('burn', () => import('@/uiservice/burn/burn-ui-service'));
        this.allUIService.set('substory', () => import('@/uiservice/sub-story/sub-story-ui-service'));
        this.allUIService.set('subproductplan', () => import('@/uiservice/sub-product-plan/sub-product-plan-ui-service'));
        this.allUIService.set('employeeload', () => import('@/uiservice/employ-eeload/employ-eeload-ui-service'));
        this.allUIService.set('storyspec', () => import('@/uiservice/story-spec/story-spec-ui-service'));
        this.allUIService.set('usertpl', () => import('@/uiservice/user-tpl/user-tpl-ui-service'));
        this.allUIService.set('ibzfavorites', () => import('@/uiservice/ibz-favorites/ibz-favorites-ui-service'));
        this.allUIService.set('branch', () => import('@/uiservice/branch/branch-ui-service'));
        this.allUIService.set('sysdepartment', () => import('@/uiservice/sys-department/sys-department-ui-service'));
        this.allUIService.set('productstats', () => import('@/uiservice/product-stats/product-stats-ui-service'));
        this.allUIService.set('ibzprojectmember', () => import('@/uiservice/ibz-project-member/ibz-project-member-ui-service'));
        this.allUIService.set('action', () => import('@/uiservice/action/action-ui-service'));
        this.allUIService.set('bugstats', () => import('@/uiservice/bug-stats/bug-stats-ui-service'));
        this.allUIService.set('group', () => import('@/uiservice/group/group-ui-service'));
        this.allUIService.set('ibzproproduct', () => import('@/uiservice/ibzpro-product/ibzpro-product-ui-service'));
        this.allUIService.set('casestep', () => import('@/uiservice/case-step/case-step-ui-service'));
        this.allUIService.set('dept', () => import('@/uiservice/dept/dept-ui-service'));
        this.allUIService.set('company', () => import('@/uiservice/company/company-ui-service'));
        this.allUIService.set('ibzcasestep', () => import('@/uiservice/ibzcase-step/ibzcase-step-ui-service'));
        this.allUIService.set('taskestimate', () => import('@/uiservice/task-estimate/task-estimate-ui-service'));
        this.allUIService.set('story', () => import('@/uiservice/story/story-ui-service'));
        this.allUIService.set('todo', () => import('@/uiservice/todo/todo-ui-service'));
        this.allUIService.set('ibzlibmodule', () => import('@/uiservice/ibz-lib-module/ibz-lib-module-ui-service'));
        this.allUIService.set('subtask', () => import('@/uiservice/sub-task/sub-task-ui-service'));
        this.allUIService.set('useryearworkstats', () => import('@/uiservice/user-year-work-stats/user-year-work-stats-ui-service'));
        this.allUIService.set('ibzlibcasesteptmp', () => import('@/uiservice/ibz-lib-case-step-tmp/ibz-lib-case-step-tmp-ui-service'));
        this.allUIService.set('project', () => import('@/uiservice/project/project-ui-service'));
        this.allUIService.set('history', () => import('@/uiservice/history/history-ui-service'));
        this.allUIService.set('user', () => import('@/uiservice/user/user-ui-service'));
        this.allUIService.set('doclib', () => import('@/uiservice/doc-lib/doc-lib-ui-service'));
        this.allUIService.set('productmodule', () => import('@/uiservice/product-module/product-module-ui-service'));
        this.allUIService.set('module', () => import('@/uiservice/module/module-ui-service'));
        this.allUIService.set('testmodule', () => import('@/uiservice/test-module/test-module-ui-service'));
        this.allUIService.set('productlife', () => import('@/uiservice/product-life/product-life-ui-service'));
        this.allUIService.set('task', () => import('@/uiservice/task/task-ui-service'));
        this.allUIService.set('build', () => import('@/uiservice/build/build-ui-service'));
        this.allUIService.set('testresult', () => import('@/uiservice/test-result/test-result-ui-service'));
        this.allUIService.set('testsuite', () => import('@/uiservice/test-suite/test-suite-ui-service'));
        this.allUIService.set('ibzprostory', () => import('@/uiservice/ibzpro-story/ibzpro-story-ui-service'));
        this.allUIService.set('projectteam', () => import('@/uiservice/project-team/project-team-ui-service'));
        this.allUIService.set('testtask', () => import('@/uiservice/test-task/test-task-ui-service'));
        this.allUIService.set('ibztaskestimate', () => import('@/uiservice/ibztask-estimate/ibztask-estimate-ui-service'));
        this.allUIService.set('productline', () => import('@/uiservice/product-line/product-line-ui-service'));
        this.allUIService.set('pssyssfpub', () => import('@/uiservice/pssys-sfpub/pssys-sfpub-ui-service'));
        this.allUIService.set('testreport', () => import('@/uiservice/test-report/test-report-ui-service'));
        this.allUIService.set('projectstats', () => import('@/uiservice/project-stats/project-stats-ui-service'));
        this.allUIService.set('testrun', () => import('@/uiservice/test-run/test-run-ui-service'));
        this.allUIService.set('ibzmyterritory', () => import('@/uiservice/ibz-my-territory/ibz-my-territory-ui-service'));
        this.allUIService.set('bug', () => import('@/uiservice/bug/bug-ui-service'));
        this.allUIService.set('projectmodule', () => import('@/uiservice/project-module/project-module-ui-service'));
        this.allUIService.set('ibzdoc', () => import('@/uiservice/ibz-doc/ibz-doc-ui-service'));
        this.allUIService.set('pssysapp', () => import('@/uiservice/pssys-app/pssys-app-ui-service'));
        this.allUIService.set('ibzcase', () => import('@/uiservice/ibz-case/ibz-case-ui-service'));
        this.allUIService.set('pssystemdbcfg', () => import('@/uiservice/pssystem-dbcfg/pssystem-dbcfg-ui-service'));
        this.allUIService.set('release', () => import('@/uiservice/release/release-ui-service'));
        this.allUIService.set('dynadashboard', () => import('@/uiservice/dyna-dashboard/dyna-dashboard-ui-service'));
        this.allUIService.set('sysemployee', () => import('@/uiservice/sys-employee/sys-employee-ui-service'));
    }

    /**
     * 加载服务实体
     *
     * @protected
     * @param {string} serviceName
     * @returns {Promise<any>}
     * @memberof UIServiceRegister
     */
    protected async loadService(serviceName: string): Promise<any> {
        const service = this.allUIService.get(serviceName);
        if (service) {
            return service();
        }
    }

    /**
     * 获取应用实体服务
     *
     * @param {string} name
     * @returns {Promise<any>}
     * @memberof UIServiceRegister
     */
    public async getService(name: string): Promise<any> {
        if (this.serviceCache.has(name)) {
            return this.serviceCache.get(name);
        }
        const entityService: any = await this.loadService(name);
        if (entityService && entityService.default) {
            const instance: any = new entityService.default();
            this.serviceCache.set(name, instance);
            return instance;
        }
    }

}
export const uiServiceRegister: UIServiceRegister = new UIServiceRegister();