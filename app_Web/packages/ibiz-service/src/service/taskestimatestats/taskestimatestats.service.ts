import { TaskestimatestatsBaseService } from './taskestimatestats-base.service';

/**
 * 任务工时统计服务
 *
 * @export
 * @class TaskestimatestatsService
 * @extends {TaskestimatestatsBaseService}
 */
export class TaskestimatestatsService extends TaskestimatestatsBaseService {
    /**
     * Creates an instance of TaskestimatestatsService.
     * @memberof TaskestimatestatsService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TaskestimatestatsService')) {
            return ___ibz___.sc.get('TaskestimatestatsService');
        }
        ___ibz___.sc.set('TaskestimatestatsService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TaskestimatestatsService}
     * @memberof TaskestimatestatsService
     */
    static getInstance(): TaskestimatestatsService {
        if (!___ibz___.sc.has('TaskestimatestatsService')) {
            new TaskestimatestatsService();
        }
        return ___ibz___.sc.get('TaskestimatestatsService');
    }
}
export default TaskestimatestatsService;