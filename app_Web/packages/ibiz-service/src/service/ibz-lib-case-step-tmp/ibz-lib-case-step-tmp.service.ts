import { IbzLibCaseStepTmpBaseService } from './ibz-lib-case-step-tmp-base.service';

/**
 * 用例库用例步骤服务
 *
 * @export
 * @class IbzLibCaseStepTmpService
 * @extends {IbzLibCaseStepTmpBaseService}
 */
export class IbzLibCaseStepTmpService extends IbzLibCaseStepTmpBaseService {
    /**
     * Creates an instance of IbzLibCaseStepTmpService.
     * @memberof IbzLibCaseStepTmpService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzLibCaseStepTmpService')) {
            return ___ibz___.sc.get('IbzLibCaseStepTmpService');
        }
        ___ibz___.sc.set('IbzLibCaseStepTmpService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzLibCaseStepTmpService}
     * @memberof IbzLibCaseStepTmpService
     */
    static getInstance(): IbzLibCaseStepTmpService {
        if (!___ibz___.sc.has('IbzLibCaseStepTmpService')) {
            new IbzLibCaseStepTmpService();
        }
        return ___ibz___.sc.get('IbzLibCaseStepTmpService');
    }
}
export default IbzLibCaseStepTmpService;