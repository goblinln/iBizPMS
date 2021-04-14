import { CaseStepBaseService } from './case-step-base.service';

/**
 * 用例步骤服务
 *
 * @export
 * @class CaseStepService
 * @extends {CaseStepBaseService}
 */
export class CaseStepService extends CaseStepBaseService {
    /**
     * Creates an instance of CaseStepService.
     * @memberof CaseStepService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('CaseStepService')) {
            return ___ibz___.sc.get('CaseStepService');
        }
        ___ibz___.sc.set('CaseStepService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {CaseStepService}
     * @memberof CaseStepService
     */
    static getInstance(): CaseStepService {
        if (!___ibz___.sc.has('CaseStepService')) {
            new CaseStepService();
        }
        return ___ibz___.sc.get('CaseStepService');
    }
}
export default CaseStepService;