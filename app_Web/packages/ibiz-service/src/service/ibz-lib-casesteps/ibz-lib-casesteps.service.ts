import { IbzLibCasestepsBaseService } from './ibz-lib-casesteps-base.service';

/**
 * 用例库用例步骤服务
 *
 * @export
 * @class IbzLibCasestepsService
 * @extends {IbzLibCasestepsBaseService}
 */
export class IbzLibCasestepsService extends IbzLibCasestepsBaseService {
    /**
     * Creates an instance of IbzLibCasestepsService.
     * @memberof IbzLibCasestepsService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzLibCasestepsService')) {
            return ___ibz___.sc.get('IbzLibCasestepsService');
        }
        ___ibz___.sc.set('IbzLibCasestepsService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzLibCasestepsService}
     * @memberof IbzLibCasestepsService
     */
    static getInstance(): IbzLibCasestepsService {
        if (!___ibz___.sc.has('IbzLibCasestepsService')) {
            new IbzLibCasestepsService();
        }
        return ___ibz___.sc.get('IbzLibCasestepsService');
    }
}
export default IbzLibCasestepsService;