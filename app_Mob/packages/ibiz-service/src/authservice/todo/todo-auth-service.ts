import { TodoAuthServiceBase } from './todo-auth-service-base';


/**
 * 待办权限服务对象
 *
 * @export
 * @class TodoAuthService
 * @extends {TodoAuthServiceBase}
 */
export default class TodoAuthService extends TodoAuthServiceBase {

    /**
     * Creates an instance of  TodoAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TodoAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}