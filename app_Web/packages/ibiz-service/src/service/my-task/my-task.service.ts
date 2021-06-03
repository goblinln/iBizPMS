import { MyTaskBaseService } from './my-task-base.service';

/**
 * 任务服务
 *
 * @export
 * @class MyTaskService
 * @extends {MyTaskBaseService}
 */
export class MyTaskService extends MyTaskBaseService {
    /**
     * Creates an instance of MyTaskService.
     * @memberof MyTaskService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('MyTaskService')) {
            return ___ibz___.sc.get('MyTaskService');
        }
        ___ibz___.sc.set('MyTaskService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {MyTaskService}
     * @memberof MyTaskService
     */
    static getInstance(): MyTaskService {
        if (!___ibz___.sc.has('MyTaskService')) {
            new MyTaskService();
        }
        return ___ibz___.sc.get('MyTaskService');
    }
}
export default MyTaskService;
