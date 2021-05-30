import { UserTplUIServiceBase } from './user-tpl-ui-service-base';

/**
 * 用户模板UI服务对象
 *
 * @export
 * @class UserTplUIService
 */
export default class UserTplUIService extends UserTplUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof UserTplUIService
     */
    private static basicUIServiceInstance: UserTplUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof UserTplUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  UserTplUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  UserTplUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof UserTplUIService
     */
    public static getInstance(context: any): UserTplUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new UserTplUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!UserTplUIService.UIServiceMap.get(context.srfdynainstid)) {
                UserTplUIService.UIServiceMap.set(context.srfdynainstid, new UserTplUIService({context:context}));
            }
            return UserTplUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}