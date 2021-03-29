import { PRODUCTTEAMBaseService } from './productteam-base.service';

/**
 * 产品团队服务
 *
 * @export
 * @class PRODUCTTEAMService
 * @extends {PRODUCTTEAMBaseService}
 */
export class PRODUCTTEAMService extends PRODUCTTEAMBaseService {
    /**
     * Creates an instance of PRODUCTTEAMService.
     * @memberof PRODUCTTEAMService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('PRODUCTTEAMService')) {
            return ___ibz___.sc.get('PRODUCTTEAMService');
        }
        ___ibz___.sc.set('PRODUCTTEAMService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {PRODUCTTEAMService}
     * @memberof PRODUCTTEAMService
     */
    static getInstance(): PRODUCTTEAMService {
        if (!___ibz___.sc.has('PRODUCTTEAMService')) {
            new PRODUCTTEAMService();
        }
        return ___ibz___.sc.get('PRODUCTTEAMService');
    }
}
export default PRODUCTTEAMService;
