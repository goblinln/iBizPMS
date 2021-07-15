import { SubTaskBaseService } from './sub-task-base.service';

/**
 * 任务服务
 *
 * @export
 * @class SubTaskService
 * @extends {SubTaskBaseService}
 */
export class SubTaskService extends SubTaskBaseService {
    /**
     * Creates an instance of SubTaskService.
     * @memberof SubTaskService
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
     * @return {*}  {SubTaskService}
     * @memberof SubTaskService
     */
    static getInstance(context?: any): SubTaskService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}SubTaskService` : `SubTaskService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new SubTaskService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default SubTaskService;
