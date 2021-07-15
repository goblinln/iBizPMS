import { IbzproProductUserTaskBaseService } from './ibzpro-product-user-task-base.service';

/**
 * 产品汇报用户任务服务
 *
 * @export
 * @class IbzproProductUserTaskService
 * @extends {IbzproProductUserTaskBaseService}
 */
export class IbzproProductUserTaskService extends IbzproProductUserTaskBaseService {
    /**
     * Creates an instance of IbzproProductUserTaskService.
     * @memberof IbzproProductUserTaskService
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
     * @return {*}  {IbzproProductUserTaskService}
     * @memberof IbzproProductUserTaskService
     */
    static getInstance(context?: any): IbzproProductUserTaskService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IbzproProductUserTaskService` : `IbzproProductUserTaskService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IbzproProductUserTaskService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IbzproProductUserTaskService;
