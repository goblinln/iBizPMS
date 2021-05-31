import { ServiceRegisterBase } from 'ibiz-core';
/**
 * 实体权限服务注册中心
 *
 * @export
 * @class AuthServiceRegister
 */
export class AuthServiceRegister extends ServiceRegisterBase {

    /**
     * Creates an instance of AuthServiceRegister.
     * @memberof AuthServiceRegister
     */
    constructor() {
        super();
    }

    /**
     * 初始化
     *
     * @protected
     * @memberof AuthServiceRegister
     */
    protected init(): void {
                this.allService.set('authservice-projecttaskestimate', () => import('../authservice/project-task-estimate/project-task-estimate-auth-service'));
        this.allService.set('authservice-testcasestep', () => import('../authservice/test-case-step/test-case-step-auth-service'));
        this.allService.set('authservice-project', () => import('../authservice/project/project-auth-service'));
        this.allService.set('authservice-projectteam', () => import('../authservice/project-team/project-team-auth-service'));
        this.allService.set('authservice-productbranch', () => import('../authservice/product-branch/product-branch-auth-service'));
        this.allService.set('authservice-productrelease', () => import('../authservice/product-release/product-release-auth-service'));
        this.allService.set('authservice-testcase', () => import('../authservice/test-case/test-case-auth-service'));
        this.allService.set('authservice-projectmodule', () => import('../authservice/project-module/project-module-auth-service'));
        this.allService.set('authservice-projectbuild', () => import('../authservice/project-build/project-build-auth-service'));
        this.allService.set('authservice-bug', () => import('../authservice/bug/bug-auth-service'));
        this.allService.set('authservice-product', () => import('../authservice/product/product-auth-service'));
        this.allService.set('authservice-productplan', () => import('../authservice/product-plan/product-plan-auth-service'));
        this.allService.set('authservice-productmodule', () => import('../authservice/product-module/product-module-auth-service'));
        this.allService.set('authservice-test', () => import('../authservice/test/test-auth-service'));
        this.allService.set('authservice-projecttask', () => import('../authservice/project-task/project-task-auth-service'));
        this.allService.set('authservice-projecttesttask', () => import('../authservice/project-test-task/project-test-task-auth-service'));
        this.allService.set('authservice-story', () => import('../authservice/story/story-auth-service'));
        this.allService.set('authservice-productline', () => import('../authservice/product-line/product-line-auth-service'));
        this.allService.set('authservice-ibzmonthly', () => import('../authservice/ibz-monthly/ibz-monthly-auth-service'));
        this.allService.set('authservice-sysemployee', () => import('../authservice/sys-employee/sys-employee-auth-service'));
        this.allService.set('authservice-taskteam', () => import('../authservice/task-team/task-team-auth-service'));
        this.allService.set('authservice-ibzmyterritory', () => import('../authservice/ibz-my-territory/ibz-my-territory-auth-service'));
        this.allService.set('authservice-action', () => import('../authservice/action/action-auth-service'));
        this.allService.set('authservice-systeam', () => import('../authservice/sys-team/sys-team-auth-service'));
        this.allService.set('authservice-ibzfavorites', () => import('../authservice/ibz-favorites/ibz-favorites-auth-service'));
        this.allService.set('authservice-syspost', () => import('../authservice/sys-post/sys-post-auth-service'));
        this.allService.set('authservice-sysdepartment', () => import('../authservice/sys-department/sys-department-auth-service'));
        this.allService.set('authservice-ibzreportly', () => import('../authservice/ibz-reportly/ibz-reportly-auth-service'));
        this.allService.set('authservice-doccontent', () => import('../authservice/doc-content/doc-content-auth-service'));
        this.allService.set('authservice-testmodule', () => import('../authservice/test-module/test-module-auth-service'));
        this.allService.set('authservice-projectstats', () => import('../authservice/project-stats/project-stats-auth-service'));
        this.allService.set('authservice-ibztaskteam', () => import('../authservice/ibztaskteam/ibztaskteam-auth-service'));
        this.allService.set('authservice-ibztaskestimate', () => import('../authservice/ibz-taskestimate/ibz-taskestimate-auth-service'));
        this.allService.set('authservice-systeammember', () => import('../authservice/sys-team-member/sys-team-member-auth-service'));
        this.allService.set('authservice-testsuite', () => import('../authservice/test-suite/test-suite-auth-service'));
        this.allService.set('authservice-file', () => import('../authservice/file/file-auth-service'));
        this.allService.set('authservice-ibzdaily', () => import('../authservice/ibz-daily/ibz-daily-auth-service'));
        this.allService.set('authservice-doclib', () => import('../authservice/doc-lib/doc-lib-auth-service'));
        this.allService.set('authservice-module', () => import('../authservice/module/module-auth-service'));
        this.allService.set('authservice-doc', () => import('../authservice/doc/doc-auth-service'));
        this.allService.set('authservice-sysupdatelog', () => import('../authservice/sys-update-log/sys-update-log-auth-service'));
        this.allService.set('authservice-user', () => import('../authservice/user/user-auth-service'));
        this.allService.set('authservice-dynadashboard', () => import('../authservice/dyna-dashboard/dyna-dashboard-auth-service'));
        this.allService.set('authservice-ibzreport', () => import('../authservice/ibz-report/ibz-report-auth-service'));
        this.allService.set('authservice-productstats', () => import('../authservice/product-stats/product-stats-auth-service'));
        this.allService.set('authservice-ibzdoc', () => import('../authservice/ibz-doc/ibz-doc-auth-service'));
        this.allService.set('authservice-usercontact', () => import('../authservice/user-contact/user-contact-auth-service'));
        this.allService.set('authservice-sysupdatefeatures', () => import('../authservice/sys-update-features/sys-update-features-auth-service'));
        this.allService.set('authservice-todo', () => import('../authservice/todo/todo-auth-service'));
        this.allService.set('authservice-sysorganization', () => import('../authservice/sys-organization/sys-organization-auth-service'));
        this.allService.set('authservice-doclibmodule', () => import('../authservice/doc-lib-module/doc-lib-module-auth-service'));
        this.allService.set('authservice-ibzweekly', () => import('../authservice/ibz-weekly/ibz-weekly-auth-service'));
        this.allService.set('authservice-ibzprojectteam', () => import('../authservice/ibzprojectteam/ibzprojectteam-auth-service'));
        this.allService.set('authservice-dynafilter', () => import('../authservice/dyna-filter/dyna-filter-auth-service'));
    }


}
export const authServiceRegister: AuthServiceRegister = new AuthServiceRegister();