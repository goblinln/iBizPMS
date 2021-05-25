import { IBZTaskActionBaseService } from './ibztask-action-base.service';

/**
 * 任务日志服务
 *
 * @export
 * @class IBZTaskActionService
 * @extends {IBZTaskActionBaseService}
 */
export class IBZTaskActionService extends IBZTaskActionBaseService {
    /**
     * Creates an instance of IBZTaskActionService.
     * @memberof IBZTaskActionService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZTaskActionService')) {
            return ___ibz___.sc.get('IBZTaskActionService');
        }
        ___ibz___.sc.set('IBZTaskActionService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZTaskActionService}
     * @memberof IBZTaskActionService
     */
    static getInstance(): IBZTaskActionService {
        if (!___ibz___.sc.has('IBZTaskActionService')) {
            new IBZTaskActionService();
        }
        return ___ibz___.sc.get('IBZTaskActionService');
    }
}
export default IBZTaskActionService;
