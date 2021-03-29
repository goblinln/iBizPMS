import { ActionBaseService } from './action-base.service';

/**
 * 系统日志服务
 *
 * @export
 * @class ActionService
 * @extends {ActionBaseService}
 */
export class ActionService extends ActionBaseService {
    /**
     * Creates an instance of ActionService.
     * @memberof ActionService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ActionService')) {
            return ___ibz___.sc.get('ActionService');
        }
        ___ibz___.sc.set('ActionService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ActionService}
     * @memberof ActionService
     */
    static getInstance(): ActionService {
        if (!___ibz___.sc.has('ActionService')) {
            new ActionService();
        }
        return ___ibz___.sc.get('ActionService');
    }
}
export default ActionService;