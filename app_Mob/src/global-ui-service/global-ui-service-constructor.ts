import { ServiceConstructorBase } from '@/ibiz-core/service/service-constructor-base';

/**
 * 应用实体服务
 *
 * @export
 * @class GlobalUiServiceConstructor
 * @extends {ServiceConstructorBase}
 */
export default class GlobalUiServiceConstructor extends ServiceConstructorBase {

    /**
     * 初始化
     *
     * @protected
     * @memberof GlobalUiServiceConstructor
     */
    protected init(): void {
        this.allService.set('projectteam_ui_action', () => import('@/ui-service/project-team/project-team-ui-action'));
        this.allService.set('build_ui_action', () => import('@/ui-service/build/build-ui-action'));
        this.allService.set('task_ui_action', () => import('@/ui-service/task/task-ui-action'));
        this.allService.set('taskestimate_ui_action', () => import('@/ui-service/task-estimate/task-estimate-ui-action'));
        this.allService.set('product_ui_action', () => import('@/ui-service/product/product-ui-action'));
        this.allService.set('release_ui_action', () => import('@/ui-service/release/release-ui-action'));
        this.allService.set('project_ui_action', () => import('@/ui-service/project/project-ui-action'));
        this.allService.set('todo_ui_action', () => import('@/ui-service/todo/todo-ui-action'));
        this.allService.set('story_ui_action', () => import('@/ui-service/story/story-ui-action'));
        this.allService.set('case_ui_action', () => import('@/ui-service/case/case-ui-action'));
        this.allService.set('productplan_ui_action', () => import('@/ui-service/product-plan/product-plan-ui-action'));
        this.allService.set('bug_ui_action', () => import('@/ui-service/bug/bug-ui-action'));
        this.allService.set('testtask_ui_action', () => import('@/ui-service/test-task/test-task-ui-action'));
        this.allService.set('testsuite_ui_action', () => import('@/ui-service/test-suite/test-suite-ui-action'));
    }

}