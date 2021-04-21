import { SubTaskBaseService } from './sub-task-base.service';

/**
 * 任务服务
 *
 * @export
 * @class SubTaskService
 * @extends {SubTaskBaseService}
 */
export class SubTaskService extends SubTaskBaseService {
    /**
     * Creates an instance of SubTaskService.
     * @memberof SubTaskService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('SubTaskService')) {
            return ___ibz___.sc.get('SubTaskService');
        }
        ___ibz___.sc.set('SubTaskService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {SubTaskService}
     * @memberof SubTaskService
     */
    static getInstance(): SubTaskService {
        if (!___ibz___.sc.has('SubTaskService')) {
            new SubTaskService();
        }
        return ___ibz___.sc.get('SubTaskService');
    }
}
export default SubTaskService;
