import { IBZCaseStepBaseService } from './ibzcase-step-base.service';

/**
 * 用例步骤服务
 *
 * @export
 * @class IBZCaseStepService
 * @extends {IBZCaseStepBaseService}
 */
export class IBZCaseStepService extends IBZCaseStepBaseService {
    /**
     * Creates an instance of IBZCaseStepService.
     * @memberof IBZCaseStepService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZCaseStepService')) {
            return ___ibz___.sc.get('IBZCaseStepService');
        }
        ___ibz___.sc.set('IBZCaseStepService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZCaseStepService}
     * @memberof IBZCaseStepService
     */
    static getInstance(): IBZCaseStepService {
        if (!___ibz___.sc.has('IBZCaseStepService')) {
            new IBZCaseStepService();
        }
        return ___ibz___.sc.get('IBZCaseStepService');
    }
}
export default IBZCaseStepService;