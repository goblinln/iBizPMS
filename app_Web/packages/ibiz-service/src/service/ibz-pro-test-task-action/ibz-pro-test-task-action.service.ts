import { IbzProTestTaskActionBaseService } from './ibz-pro-test-task-action-base.service';

/**
 * 测试单日志服务
 *
 * @export
 * @class IbzProTestTaskActionService
 * @extends {IbzProTestTaskActionBaseService}
 */
export class IbzProTestTaskActionService extends IbzProTestTaskActionBaseService {
    /**
     * Creates an instance of IbzProTestTaskActionService.
     * @memberof IbzProTestTaskActionService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzProTestTaskActionService')) {
            return ___ibz___.sc.get('IbzProTestTaskActionService');
        }
        ___ibz___.sc.set('IbzProTestTaskActionService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzProTestTaskActionService}
     * @memberof IbzProTestTaskActionService
     */
    static getInstance(): IbzProTestTaskActionService {
        if (!___ibz___.sc.has('IbzProTestTaskActionService')) {
            new IbzProTestTaskActionService();
        }
        return ___ibz___.sc.get('IbzProTestTaskActionService');
    }
}
export default IbzProTestTaskActionService;
