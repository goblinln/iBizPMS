import { IbzAgentBaseService } from './ibz-agent-base.service';

/**
 * 代理服务
 *
 * @export
 * @class IbzAgentService
 * @extends {IbzAgentBaseService}
 */
export class IbzAgentService extends IbzAgentBaseService {
    /**
     * Creates an instance of IbzAgentService.
     * @memberof IbzAgentService
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
     * @return {*}  {IbzAgentService}
     * @memberof IbzAgentService
     */
    static getInstance(context?: any): IbzAgentService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IbzAgentService` : `IbzAgentService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IbzAgentService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IbzAgentService;
