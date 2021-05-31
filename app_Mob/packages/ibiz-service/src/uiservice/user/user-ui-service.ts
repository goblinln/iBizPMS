import { UserUIServiceBase } from './user-ui-service-base';

/**
 * 用户UI服务对象
 *
 * @export
 * @class UserUIService
 */
export default class UserUIService extends UserUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof UserUIService
     */
    private static basicUIServiceInstance: UserUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof UserUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  UserUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  UserUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof UserUIService
     */
    public static getInstance(context: any): UserUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new UserUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!UserUIService.UIServiceMap.get(context.srfdynainstid)) {
                UserUIService.UIServiceMap.set(context.srfdynainstid, new UserUIService({context:context}));
            }
            return UserUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}