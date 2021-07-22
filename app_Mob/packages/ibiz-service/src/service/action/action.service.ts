import { ActionBaseService } from './action-base.service';

/**
 * 系统日志服务
 *
 * @export
 * @class ActionService
 * @extends {ActionBaseService}
 */
export class ActionService extends ActionBaseService {
    /**
     * Creates an instance of ActionService.
     * @memberof ActionService
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
     * @return {*}  {ActionService}
     * @memberof ActionService
     */
    static getInstance(context?: any): ActionService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ActionService` : `ActionService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ActionService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ActionService;
