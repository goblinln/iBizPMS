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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TodoService')) {
            return ___ibz___.sc.get('TodoService');
        }
        ___ibz___.sc.set('TodoService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TodoService}
     * @memberof TodoService
     */
    static getInstance(): TodoService {
        if (!___ibz___.sc.has('TodoService')) {
            new TodoService();
        }
        return ___ibz___.sc.get('TodoService');
    }
}
export default TodoService;