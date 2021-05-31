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
            this.allService.set('UserRealName_valueofid', () => import('../codelist/user-real-name-valueofid'));
        this.allService.set('UserRealNameProject', () => import('../codelist/user-real-name-project'));
        this.allService.set('UserRealNameUnAssignTo_Gird', () => import('../codelist/user-real-name-un-assign-to-gird'));
        this.allService.set('ProjectProductPlan', () => import('../codelist/project-product-plan'));
        this.allService.set('Role', () => import('../codelist/role'));
        this.allService.set('CodeList', () => import('../codelist/code-list'));
        this.allService.set('MyCompleteTask', () => import('../codelist/my-complete-task'));
        this.allService.set('ProductBranch_Cache', () => import('../codelist/product-branch-cache'));
        this.allService.set('CurProductBuild', () => import('../codelist/cur-product-build'));
        this.allService.set('UserRealName_Gird', () => import('../codelist/user-real-name-gird'));
        this.allService.set('SysOperator', () => import('../codelist/sys-operator'));
        this.allService.set('RelatedStory', () => import('../codelist/related-story'));
        this.allService.set('UserRealNameProductTeam', () => import('../codelist/user-real-name-product-team'));
        this.allService.set('MyPlanTask', () => import('../codelist/my-plan-task'));
        this.allService.set('UserRealNameTaskTeam', () => import('../codelist/user-real-name-task-team'));
        this.allService.set('ProductBranch', () => import('../codelist/product-branch'));
        this.allService.set('CurStory', () => import('../codelist/cur-story'));
        this.allService.set('ProductPlan', () => import('../codelist/product-plan'));
        this.allService.set('CurCaseVersion', () => import('../codelist/cur-case-version'));
        this.allService.set('BugModule', () => import('../codelist/bug-module'));
        this.allService.set('Product', () => import('../codelist/product'));
        this.allService.set('UserRealName', () => import('../codelist/user-real-name'));
        this.allService.set('MonthlyCompleteTaskChoice', () => import('../codelist/monthly-complete-task-choice'));
    }


}
export const codeListRegister: CodeListRegister = new CodeListRegister();