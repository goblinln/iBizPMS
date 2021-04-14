import { TaskEstimateBaseService } from './task-estimate-base.service';

/**
 * 任务预计服务
 *
 * @export
 * @class TaskEstimateService
 * @extends {TaskEstimateBaseService}
 */
export class TaskEstimateService extends TaskEstimateBaseService {
    /**
     * Creates an instance of TaskEstimateService.
     * @memberof TaskEstimateService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TaskEstimateService')) {
            return ___ibz___.sc.get('TaskEstimateService');
        }
        ___ibz___.sc.set('TaskEstimateService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TaskEstimateService}
     * @memberof TaskEstimateService
     */
    static getInstance(): TaskEstimateService {
        if (!___ibz___.sc.has('TaskEstimateService')) {
            new TaskEstimateService();
        }
        return ___ibz___.sc.get('TaskEstimateService');
    }
}
export default TaskEstimateService;