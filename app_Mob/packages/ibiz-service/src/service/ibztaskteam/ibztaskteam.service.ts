import { IbztaskteamBaseService } from './ibztaskteam-base.service';

/**
 * 任务团队服务
 *
 * @export
 * @class IbztaskteamService
 * @extends {IbztaskteamBaseService}
 */
export class IbztaskteamService extends IbztaskteamBaseService {
    /**
     * Creates an instance of IbztaskteamService.
     * @memberof IbztaskteamService
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
     * @return {*}  {IbztaskteamService}
     * @memberof IbztaskteamService
     */
    static getInstance(context?: any): IbztaskteamService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IbztaskteamService` : `IbztaskteamService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IbztaskteamService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IbztaskteamService;
