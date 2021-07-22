import { IBZPROJECTTEAMBaseService } from './ibzprojectteam-base.service';

/**
 * 项目团队服务
 *
 * @export
 * @class IBZPROJECTTEAMService
 * @extends {IBZPROJECTTEAMBaseService}
 */
export class IBZPROJECTTEAMService extends IBZPROJECTTEAMBaseService {
    /**
     * Creates an instance of IBZPROJECTTEAMService.
     * @memberof IBZPROJECTTEAMService
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
     * @return {*}  {IBZPROJECTTEAMService}
     * @memberof IBZPROJECTTEAMService
     */
    static getInstance(context?: any): IBZPROJECTTEAMService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IBZPROJECTTEAMService` : `IBZPROJECTTEAMService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IBZPROJECTTEAMService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IBZPROJECTTEAMService;
