import { TaskTeamNestedBaseService } from './task-team-nested-base.service';

/**
 * 任务团队服务
 *
 * @export
 * @class TaskTeamNestedService
 * @extends {TaskTeamNestedBaseService}
 */
export class TaskTeamNestedService extends TaskTeamNestedBaseService {
    /**
     * Creates an instance of TaskTeamNestedService.
     * @memberof TaskTeamNestedService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TaskTeamNestedService')) {
            return ___ibz___.sc.get('TaskTeamNestedService');
        }
        ___ibz___.sc.set('TaskTeamNestedService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TaskTeamNestedService}
     * @memberof TaskTeamNestedService
     */
    static getInstance(): TaskTeamNestedService {
        if (!___ibz___.sc.has('TaskTeamNestedService')) {
            new TaskTeamNestedService();
        }
        return ___ibz___.sc.get('TaskTeamNestedService');
    }
}
export default TaskTeamNestedService;
