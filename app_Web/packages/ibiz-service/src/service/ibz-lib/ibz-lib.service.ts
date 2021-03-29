import { IbzLibBaseService } from './ibz-lib-base.service';

/**
 * 用例库服务
 *
 * @export
 * @class IbzLibService
 * @extends {IbzLibBaseService}
 */
export class IbzLibService extends IbzLibBaseService {
    /**
     * Creates an instance of IbzLibService.
     * @memberof IbzLibService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzLibService')) {
            return ___ibz___.sc.get('IbzLibService');
        }
        ___ibz___.sc.set('IbzLibService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzLibService}
     * @memberof IbzLibService
     */
    static getInstance(): IbzLibService {
        if (!___ibz___.sc.has('IbzLibService')) {
            new IbzLibService();
        }
        return ___ibz___.sc.get('IbzLibService');
    }
}
export default IbzLibService;