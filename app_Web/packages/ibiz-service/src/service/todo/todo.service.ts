import { TodoBaseService } from './todo-base.service';

/**
 * 待办服务
 *
 * @export
 * @class TodoService
 * @extends {TodoBaseService}
 */
export class TodoService extends TodoBaseService {
    /**
     * Creates an instance of TodoService.
     * @memberof TodoService
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
     * @return {*}  {TodoService}
     * @memberof TodoService
     */
    static getInstance(context?: any): TodoService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TodoService` : `TodoService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TodoService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default TodoService;
