import { PSSysSFPubBaseService } from './pssys-sfpub-base.service';

/**
 * 后台服务架构服务
 *
 * @export
 * @class PSSysSFPubService
 * @extends {PSSysSFPubBaseService}
 */
export class PSSysSFPubService extends PSSysSFPubBaseService {
    /**
     * Creates an instance of PSSysSFPubService.
     * @memberof PSSysSFPubService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('PSSysSFPubService')) {
            return ___ibz___.sc.get('PSSysSFPubService');
        }
        ___ibz___.sc.set('PSSysSFPubService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {PSSysSFPubService}
     * @memberof PSSysSFPubService
     */
    static getInstance(): PSSysSFPubService {
        if (!___ibz___.sc.has('PSSysSFPubService')) {
            new PSSysSFPubService();
        }
        return ___ibz___.sc.get('PSSysSFPubService');
    }
}
export default PSSysSFPubService;
