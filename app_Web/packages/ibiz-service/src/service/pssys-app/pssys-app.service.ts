import { PSSysAppBaseService } from './pssys-app-base.service';

/**
 * 系统应用服务
 *
 * @export
 * @class PSSysAppService
 * @extends {PSSysAppBaseService}
 */
export class PSSysAppService extends PSSysAppBaseService {
    /**
     * Creates an instance of PSSysAppService.
     * @memberof PSSysAppService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('PSSysAppService')) {
            return ___ibz___.sc.get('PSSysAppService');
        }
        ___ibz___.sc.set('PSSysAppService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {PSSysAppService}
     * @memberof PSSysAppService
     */
    static getInstance(): PSSysAppService {
        if (!___ibz___.sc.has('PSSysAppService')) {
            new PSSysAppService();
        }
        return ___ibz___.sc.get('PSSysAppService');
    }
}
export default PSSysAppService;
