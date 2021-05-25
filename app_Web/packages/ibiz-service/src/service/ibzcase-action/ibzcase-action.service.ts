import { IBZCaseActionBaseService } from './ibzcase-action-base.service';

/**
 * 测试用例日志服务
 *
 * @export
 * @class IBZCaseActionService
 * @extends {IBZCaseActionBaseService}
 */
export class IBZCaseActionService extends IBZCaseActionBaseService {
    /**
     * Creates an instance of IBZCaseActionService.
     * @memberof IBZCaseActionService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZCaseActionService')) {
            return ___ibz___.sc.get('IBZCaseActionService');
        }
        ___ibz___.sc.set('IBZCaseActionService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZCaseActionService}
     * @memberof IBZCaseActionService
     */
    static getInstance(): IBZCaseActionService {
        if (!___ibz___.sc.has('IBZCaseActionService')) {
            new IBZCaseActionService();
        }
        return ___ibz___.sc.get('IBZCaseActionService');
    }
}
export default IBZCaseActionService;
