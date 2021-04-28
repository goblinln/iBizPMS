import { TaskBaseService } from './task-base.service';

/**
 * 任务服务
 *
 * @export
 * @class TaskService
 * @extends {TaskBaseService}
 */
export class TaskService extends TaskBaseService {
    /**
     * Creates an instance of TaskService.
     * @memberof TaskService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TaskService')) {
            return ___ibz___.sc.get('TaskService');
        }
        ___ibz___.sc.set('TaskService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TaskService}
     * @memberof TaskService
     */
    static getInstance(): TaskService {
        if (!___ibz___.sc.has('TaskService')) {
            new TaskService();
        }
        return ___ibz___.sc.get('TaskService');
    }
}
export default TaskService;
