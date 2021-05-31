import { IbzproProductUserTaskBaseService } from './ibzpro-product-user-task-base.service';

/**
 * 产品汇报用户任务服务
 *
 * @export
 * @class IbzproProductUserTaskService
 * @extends {IbzproProductUserTaskBaseService}
 */
export class IbzproProductUserTaskService extends IbzproProductUserTaskBaseService {
    /**
     * Creates an instance of IbzproProductUserTaskService.
     * @memberof IbzproProductUserTaskService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzproProductUserTaskService')) {
            return ___ibz___.sc.get('IbzproProductUserTaskService');
        }
        ___ibz___.sc.set('IbzproProductUserTaskService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzproProductUserTaskService}
     * @memberof IbzproProductUserTaskService
     */
    static getInstance(): IbzproProductUserTaskService {
        if (!___ibz___.sc.has('IbzproProductUserTaskService')) {
            new IbzproProductUserTaskService();
        }
        return ___ibz___.sc.get('IbzproProductUserTaskService');
    }
}
export default IbzproProductUserTaskService;
