import { IbzproProjectUserTaskBaseService } from './ibzpro-project-user-task-base.service';

/**
 * 项目汇报用户任务服务
 *
 * @export
 * @class IbzproProjectUserTaskService
 * @extends {IbzproProjectUserTaskBaseService}
 */
export class IbzproProjectUserTaskService extends IbzproProjectUserTaskBaseService {
    /**
     * Creates an instance of IbzproProjectUserTaskService.
     * @memberof IbzproProjectUserTaskService
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
     * @return {*}  {IbzproProjectUserTaskService}
     * @memberof IbzproProjectUserTaskService
     */
    static getInstance(context?: any): IbzproProjectUserTaskService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IbzproProjectUserTaskService` : `IbzproProjectUserTaskService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IbzproProjectUserTaskService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IbzproProjectUserTaskService;
