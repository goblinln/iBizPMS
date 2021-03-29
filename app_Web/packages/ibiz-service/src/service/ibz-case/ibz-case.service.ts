import { IbzCaseBaseService } from './ibz-case-base.service';

/**
 * 测试用例服务
 *
 * @export
 * @class IbzCaseService
 * @extends {IbzCaseBaseService}
 */
export class IbzCaseService extends IbzCaseBaseService {
    /**
     * Creates an instance of IbzCaseService.
     * @memberof IbzCaseService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzCaseService')) {
            return ___ibz___.sc.get('IbzCaseService');
        }
        ___ibz___.sc.set('IbzCaseService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzCaseService}
     * @memberof IbzCaseService
     */
    static getInstance(): IbzCaseService {
        if (!___ibz___.sc.has('IbzCaseService')) {
            new IbzCaseService();
        }
        return ___ibz___.sc.get('IbzCaseService');
    }
}
export default IbzCaseService;
