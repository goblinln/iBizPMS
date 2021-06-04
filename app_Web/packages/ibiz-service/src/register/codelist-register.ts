import { ServiceRegisterBase } from 'ibiz-core';

/**
 * 代码表服务注册中心
 *
 * @export
 * @class CodeListRegister
 */
export class CodeListRegister extends ServiceRegisterBase  {

    /**
     * Creates an instance of CodeListRegister.
     * @memberof CodeListRegister
     */
    constructor() {
        super();
    }

    /**
     * 初始化
     *
     * @protected
     * @memberof CodeListRegister
     */
    protected init(): void {
            this.allService.set('SQLBuild', () => import('../codelist/sqlbuild'));
        this.allService.set('UserRealNameProject', () => import('../codelist/user-real-name-project'));
        this.allService.set('AllBug', () => import('../codelist/all-bug'));
        this.allService.set('TestTask', () => import('../codelist/test-task'));
        this.allService.set('ProjectProductPlan', () => import('../codelist/project-product-plan'));
        this.allService.set('CodeList25', () => import('../codelist/code-list25'));
        this.allService.set('CodeList', () => import('../codelist/code-list'));
        this.allService.set('MyCompleteTask', () => import('../codelist/my-complete-task'));
        this.allService.set('ProductBranch_Cache', () => import('../codelist/product-branch-cache'));
        this.allService.set('SysOperator', () => import('../codelist/sys-operator'));
        this.allService.set('SystemAPP', () => import('../codelist/system-app'));
        this.allService.set('BugUserRealName', () => import('../codelist/bug-user-real-name'));
        this.allService.set('ProductBranch', () => import('../codelist/product-branch'));
        this.allService.set('AllCase', () => import('../codelist/all-case'));
        this.allService.set('APPBuild', () => import('../codelist/appbuild'));
        this.allService.set('AllRole', () => import('../codelist/all-role'));
        this.allService.set('Product', () => import('../codelist/product'));
        this.allService.set('UserRealName', () => import('../codelist/user-real-name'));
        this.allService.set('MonthlyCompleteTaskChoice', () => import('../codelist/monthly-complete-task-choice'));
        this.allService.set('UserRealNameW', () => import('../codelist/user-real-name-w'));
        this.allService.set('UserRealNameProductTeamNotAssign', () => import('../codelist/user-real-name-product-team-not-assign'));
        this.allService.set('UserRealNameTaskMTeam', () => import('../codelist/user-real-name-task-mteam'));
        this.allService.set('UserRealNameUnAssignTo_Gird', () => import('../codelist/user-real-name-un-assign-to-gird'));
        this.allService.set('Role', () => import('../codelist/role'));
        this.allService.set('CurProductBuild', () => import('../codelist/cur-product-build'));
        this.allService.set('UserRealName_Gird', () => import('../codelist/user-real-name-gird'));
        this.allService.set('RelatedStory', () => import('../codelist/related-story'));
        this.allService.set('AllEntry', () => import('../codelist/all-entry'));
        this.allService.set('PlanCodeList', () => import('../codelist/plan-code-list'));
        this.allService.set('CurProductProject', () => import('../codelist/cur-product-project'));
        this.allService.set('AllRepo', () => import('../codelist/all-repo'));
        this.allService.set('CurProductPlan', () => import('../codelist/cur-product-plan'));
        this.allService.set('UserRealNameProductTeam', () => import('../codelist/user-real-name-product-team'));
        this.allService.set('Backendservicesystem', () => import('../codelist/backendservicesystem'));
        this.allService.set('MyPlanTask', () => import('../codelist/my-plan-task'));
        this.allService.set('ProjectCodeList', () => import('../codelist/project-code-list'));
        this.allService.set('UserRealNameTaskTeam', () => import('../codelist/user-real-name-task-team'));
        this.allService.set('CurDocVersion', () => import('../codelist/cur-doc-version'));
        this.allService.set('PlanTemplet', () => import('../codelist/plan-templet'));
        this.allService.set('ProductTeam', () => import('../codelist/product-team'));
        this.allService.set('CurStory', () => import('../codelist/cur-story'));
        this.allService.set('ProductPlan', () => import('../codelist/product-plan'));
        this.allService.set('ProjectTeam', () => import('../codelist/project-team'));
        this.allService.set('AllStory', () => import('../codelist/all-story'));
        this.allService.set('CurCaseVersion', () => import('../codelist/cur-case-version'));
        this.allService.set('BugModule', () => import('../codelist/bug-module'));
        this.allService.set('RealDept', () => import('../codelist/real-dept'));
        this.allService.set('RunSQL', () => import('../codelist/run-sql'));
        this.allService.set('TaskTeamUserTemp', () => import('../codelist/task-team-user-temp'));
        this.allService.set('BackendBuild', () => import('../codelist/backend-build'));
        this.allService.set('AllTask', () => import('../codelist/all-task'));
        this.allService.set('AllTestTask', () => import('../codelist/all-test-task'));
    }


}
export const codeListRegister: CodeListRegister = new CodeListRegister();