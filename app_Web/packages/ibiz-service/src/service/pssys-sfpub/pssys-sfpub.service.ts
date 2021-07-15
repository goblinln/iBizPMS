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
    constructor(opts?: any) {
        const { context: context, tag: cacheKey } = opts;
        super(context);
        if (___ibz___.sc.has(cacheKey)) {
            return ___ibz___.sc.get(cacheKey);
        }
        ___ibz___.sc.set(cacheKey, this);
    }

    /**
     * 获取实例
     *
     * @static
     * @param 应用上下文
     * @return {*}  {PSSysSFPubService}
     * @memberof PSSysSFPubService
     */
    static getInstance(context?: any): PSSysSFPubService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}PSSysSFPubService` : `PSSysSFPubService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new PSSysSFPubService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default PSSysSFPubService;
