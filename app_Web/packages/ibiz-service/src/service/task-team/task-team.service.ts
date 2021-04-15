import { TaskTeamBaseService } from './task-team-base.service';

/**
 * 任务团队服务
 *
 * @export
 * @class TaskTeamService
 * @extends {TaskTeamBaseService}
 */
export class TaskTeamService extends TaskTeamBaseService {
    /**
     * Creates an instance of TaskTeamService.
     * @memberof TaskTeamService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TaskTeamService')) {
            return ___ibz___.sc.get('TaskTeamService');
        }
        ___ibz___.sc.set('TaskTeamService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TaskTeamService}
     * @memberof TaskTeamService
     */
    static getInstance(): TaskTeamService {
        if (!___ibz___.sc.has('TaskTeamService')) {
            new TaskTeamService();
        }
        return ___ibz___.sc.get('TaskTeamService');
    }
}
export default TaskTeamService;