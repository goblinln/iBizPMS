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
                this.allService.set('authservice-productplan', () => import('../authservice/product-plan/product-plan-auth-service'));
        this.allService.set('authservice-ibzreportroleconfig', () => import('../authservice/ibz-report-role-config/ibz-report-role-config-auth-service'));
        this.allService.set('authservice-case', () => import('../authservice/case/case-auth-service'));
        this.allService.set('authservice-sysuser', () => import('../authservice/sys-user/sys-user-auth-service'));
        this.allService.set('authservice-product', () => import('../authservice/product/product-auth-service'));
        this.allService.set('authservice-ibzcasestep', () => import('../authservice/ibzcase-step/ibzcase-step-auth-service'));
        this.allService.set('authservice-taskteam', () => import('../authservice/task-team/task-team-auth-service'));
        this.allService.set('authservice-file', () => import('../authservice/file/file-auth-service'));
        this.allService.set('authservice-ibzagent', () => import('../authservice/ibz-agent/ibz-agent-auth-service'));
        this.allService.set('authservice-ibzprostorymodule', () => import('../authservice/ibzpro-story-module/ibzpro-story-module-auth-service'));
        this.allService.set('authservice-productsum', () => import('../authservice/product-sum/product-sum-auth-service'));
        this.allService.set('authservice-ibzlibcasesteps', () => import('../authservice/ibz-lib-casesteps/ibz-lib-casesteps-auth-service'));
        this.allService.set('authservice-ibzlib', () => import('../authservice/ibz-lib/ibz-lib-auth-service'));
        this.allService.set('authservice-ibzdaily', () => import('../authservice/ibz-daily/ibz-daily-auth-service'));
        this.allService.set('authservice-suitecase', () => import('../authservice/suite-case/suite-case-auth-service'));
        this.allService.set('authservice-burn', () => import('../authservice/burn/burn-auth-service'));
        this.allService.set('authservice-employeeload', () => import('../authservice/emp-loyeeload/emp-loyeeload-auth-service'));
        this.allService.set('authservice-doccontent', () => import('../authservice/doc-content/doc-content-auth-service'));
        this.allService.set('authservice-ibzreport', () => import('../authservice/ibz-report/ibz-report-auth-service'));
        this.allService.set('authservice-ibztaskestimate', () => import('../authservice/ibztask-estimate/ibztask-estimate-auth-service'));
        this.allService.set('authservice-syspost', () => import('../authservice/sys-post/sys-post-auth-service'));
        this.allService.set('authservice-usertpl', () => import('../authservice/user-tpl/user-tpl-auth-service'));
        this.allService.set('authservice-taskstats', () => import('../authservice/task-stats/task-stats-auth-service'));
        this.allService.set('authservice-ibizproprojectweekly', () => import('../authservice/ibizpro-project-weekly/ibizpro-project-weekly-auth-service'));
        this.allService.set('authservice-ibzfavorites', () => import('../authservice/ibz-favorites/ibz-favorites-auth-service'));
        this.allService.set('authservice-sysdepartment', () => import('../authservice/sys-department/sys-department-auth-service'));
        this.allService.set('authservice-productstats', () => import('../authservice/product-stats/product-stats-auth-service'));
        this.allService.set('authservice-ibzprojectmember', () => import('../authservice/ibz-project-member/ibz-project-member-auth-service'));
        this.allService.set('authservice-bugstats', () => import('../authservice/bug-stats/bug-stats-auth-service'));
        this.allService.set('authservice-ibizproindex', () => import('../authservice/ibizpro-index/ibizpro-index-auth-service'));
        this.allService.set('authservice-group', () => import('../authservice/group/group-auth-service'));
        this.allService.set('authservice-ibzproproduct', () => import('../authservice/ibzpro-product/ibzpro-product-auth-service'));
        this.allService.set('authservice-casestep', () => import('../authservice/case-step/case-step-auth-service'));
        this.allService.set('authservice-dept', () => import('../authservice/dept/dept-auth-service'));
        this.allService.set('authservice-ibizprotag', () => import('../authservice/ibizpro-tag/ibizpro-tag-auth-service'));
        this.allService.set('authservice-company', () => import('../authservice/company/company-auth-service'));
        this.allService.set('authservice-systeam', () => import('../authservice/sys-team/sys-team-auth-service'));
        this.allService.set('authservice-taskestimate', () => import('../authservice/task-estimate/task-estimate-auth-service'));
        this.allService.set('authservice-story', () => import('../authservice/story/story-auth-service'));
        this.allService.set('authservice-taskestimatestats', () => import('../authservice/taskestimatestats/taskestimatestats-auth-service'));
        this.allService.set('authservice-ibzlibcasesteptmp', () => import('../authservice/ibz-lib-case-step-tmp/ibz-lib-case-step-tmp-auth-service'));
        this.allService.set('authservice-todo', () => import('../authservice/todo/todo-auth-service'));
        this.allService.set('authservice-ibzlibmodule', () => import('../authservice/ibz-lib-module/ibz-lib-module-auth-service'));
        this.allService.set('authservice-ibizproproductweekly', () => import('../authservice/ibizpro-product-weekly/ibizpro-product-weekly-auth-service'));
        this.allService.set('authservice-useryearworkstats', () => import('../authservice/user-year-work-stats/user-year-work-stats-auth-service'));
        this.allService.set('authservice-sysorganization', () => import('../authservice/sys-organization/sys-organization-auth-service'));
        this.allService.set('authservice-ibizproplugin', () => import('../authservice/ibizpro-plugin/ibizpro-plugin-auth-service'));
        this.allService.set('authservice-subproductplan', () => import('../authservice/sub-product-plan/sub-product-plan-auth-service'));
        this.allService.set('authservice-project', () => import('../authservice/project/project-auth-service'));
        this.allService.set('authservice-ibzreportly', () => import('../authservice/ibz-reportly/ibz-reportly-auth-service'));
        this.allService.set('authservice-subtask', () => import('../authservice/sub-task/sub-task-auth-service'));
        this.allService.set('authservice-ibizproprojectmonthly', () => import('../authservice/ibizpro-project-monthly/ibizpro-project-monthly-auth-service'));
        this.allService.set('authservice-user', () => import('../authservice/user/user-auth-service'));
        this.allService.set('authservice-doclib', () => import('../authservice/doc-lib/doc-lib-auth-service'));
        this.allService.set('authservice-companystats', () => import('../authservice/company-stats/company-stats-auth-service'));
        this.allService.set('authservice-productmodule', () => import('../authservice/product-module/product-module-auth-service'));
        this.allService.set('authservice-productteam', () => import('../authservice/productteam/productteam-auth-service'));
        this.allService.set('authservice-testmodule', () => import('../authservice/test-module/test-module-auth-service'));
        this.allService.set('authservice-substory', () => import('../authservice/sub-story/sub-story-auth-service'));
        this.allService.set('authservice-ibizproprojectdaily', () => import('../authservice/ibizpro-project-daily/ibizpro-project-daily-auth-service'));
        this.allService.set('authservice-ibzplantemplet', () => import('../authservice/ibz-plan-templet/ibz-plan-templet-auth-service'));
        this.allService.set('authservice-sysuserrole', () => import('../authservice/sys-user-role/sys-user-role-auth-service'));
        this.allService.set('authservice-ibizprokeyword', () => import('../authservice/ibizpro-keyword/ibizpro-keyword-auth-service'));
        this.allService.set('authservice-doclibmodule', () => import('../authservice/doc-lib-module/doc-lib-module-auth-service'));
        this.allService.set('authservice-productlife', () => import('../authservice/product-life/product-life-auth-service'));
        this.allService.set('authservice-usercontact', () => import('../authservice/user-contact/user-contact-auth-service'));
        this.allService.set('authservice-ibizproproductdaily', () => import('../authservice/ibizpro-product-daily/ibizpro-product-daily-auth-service'));
        this.allService.set('authservice-sysrole', () => import('../authservice/sys-role/sys-role-auth-service'));
        this.allService.set('authservice-plantempletdetail', () => import('../authservice/plan-templet-detail/plan-templet-detail-auth-service'));
        this.allService.set('authservice-task', () => import('../authservice/task/task-auth-service'));
        this.allService.set('authservice-build', () => import('../authservice/build/build-auth-service'));
        this.allService.set('authservice-ibizproproductmonthly', () => import('../authservice/ibizpro-product-monthly/ibizpro-product-monthly-auth-service'));
        this.allService.set('authservice-testresult', () => import('../authservice/test-result/test-result-auth-service'));
        this.allService.set('authservice-testsuite', () => import('../authservice/test-suite/test-suite-auth-service'));
        this.allService.set('authservice-ibzplantempletdetail', () => import('../authservice/ibz-plan-templet-detail/ibz-plan-templet-detail-auth-service'));
        this.allService.set('authservice-ibzproconfig', () => import('../authservice/ibzpro-config/ibzpro-config-auth-service'));
        this.allService.set('authservice-ibzprostory', () => import('../authservice/ibzpro-story/ibzpro-story-auth-service'));
        this.allService.set('authservice-ibztaskteam', () => import('../authservice/ibztask-team/ibztask-team-auth-service'));
        this.allService.set('authservice-projectteam', () => import('../authservice/project-team/project-team-auth-service'));
        this.allService.set('authservice-testtask', () => import('../authservice/test-task/test-task-auth-service'));
        this.allService.set('authservice-productline', () => import('../authservice/product-line/product-line-auth-service'));
        this.allService.set('authservice-pssyssfpub', () => import('../authservice/pssys-sfpub/pssys-sfpub-auth-service'));
        this.allService.set('authservice-testreport', () => import('../authservice/test-report/test-report-auth-service'));
        this.allService.set('authservice-projectstats', () => import('../authservice/project-stats/project-stats-auth-service'));
        this.allService.set('authservice-testrun', () => import('../authservice/test-run/test-run-auth-service'));
        this.allService.set('authservice-ibzmonthly', () => import('../authservice/ibz-monthly/ibz-monthly-auth-service'));
        this.allService.set('authservice-ibzmyterritory', () => import('../authservice/ibz-my-territory/ibz-my-territory-auth-service'));
        this.allService.set('authservice-sysupdatelog', () => import('../authservice/sys-update-log/sys-update-log-auth-service'));
        this.allService.set('authservice-doc', () => import('../authservice/doc/doc-auth-service'));
        this.allService.set('authservice-bug', () => import('../authservice/bug/bug-auth-service'));
        this.allService.set('authservice-ibzweekly', () => import('../authservice/ibzweekly/ibzweekly-auth-service'));
        this.allService.set('authservice-projectmodule', () => import('../authservice/project-module/project-module-auth-service'));
        this.allService.set('authservice-ibzdoc', () => import('../authservice/ibz-doc/ibz-doc-auth-service'));
        this.allService.set('authservice-pssysapp', () => import('../authservice/pssys-app/pssys-app-auth-service'));
        this.allService.set('authservice-ibzproprojectusertask', () => import('../authservice/ibzpro-project-user-task/ibzpro-project-user-task-auth-service'));
        this.allService.set('authservice-ibzcase', () => import('../authservice/ibz-case/ibz-case-auth-service'));
        this.allService.set('authservice-systeammember', () => import('../authservice/sys-team-member/sys-team-member-auth-service'));
        this.allService.set('authservice-pssystemdbcfg', () => import('../authservice/pssystem-dbcfg/pssystem-dbcfg-auth-service'));
        this.allService.set('authservice-ibzproproductusertask', () => import('../authservice/ibzpro-product-user-task/ibzpro-product-user-task-auth-service'));
        this.allService.set('authservice-sysupdatefeatures', () => import('../authservice/sys-update-features/sys-update-features-auth-service'));
        this.allService.set('authservice-release', () => import('../authservice/release/release-auth-service'));
        this.allService.set('authservice-casestats', () => import('../authservice/case-stats/case-stats-auth-service'));
        this.allService.set('authservice-dynadashboard', () => import('../authservice/dyna-dashboard/dyna-dashboard-auth-service'));
        this.allService.set('authservice-sysemployee', () => import('../authservice/sys-employee/sys-employee-auth-service'));
        this.allService.set('authservice-ibzproproductline', () => import('../authservice/ibzpro-product-line/ibzpro-product-line-auth-service'));
        this.allService.set('authservice-projectproduct', () => import('../authservice/project-product/project-product-auth-service'));
        this.allService.set('authservice-accounttaskestimate', () => import('../authservice/account-taskestimate/account-taskestimate-auth-service'));
        this.allService.set('authservice-ibzproproductaction', () => import('../authservice/ibzpro-product-action/ibzpro-product-action-auth-service'));
        this.allService.set('authservice-branch', () => import('../authservice/branch/branch-auth-service'));
        this.allService.set('authservice-action', () => import('../authservice/action/action-auth-service'));
        this.allService.set('authservice-dynafilter', () => import('../authservice/dyna-filter/dyna-filter-auth-service'));
        this.allService.set('authservice-history', () => import('../authservice/history/history-auth-service'));
        this.allService.set('authservice-ibzproproducthistory', () => import('../authservice/ibzpro-product-history/ibzpro-product-history-auth-service'));
        this.allService.set('authservice-ibzstoryaction', () => import('../authservice/ibzstory-action/ibzstory-action-auth-service'));
        this.allService.set('authservice-module', () => import('../authservice/module/module-auth-service'));
        this.allService.set('authservice-projecttaskestimate', () => import('../authservice/project-taskestimate/project-taskestimate-auth-service'));
    }


}
export const authServiceRegister: AuthServiceRegister = new AuthServiceRegister();