import { UserContactUIServiceBase } from './user-contact-ui-service-base';

/**
 * 用户联系方式UI服务对象
 *
 * @export
 * @class UserContactUIService
 */
export default class UserContactUIService extends UserContactUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof UserContactUIService
     */
    private static basicUIServiceInstance: UserContactUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof UserContactUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  UserContactUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  UserContactUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof UserContactUIService
     */
    public static getInstance(context: any): UserContactUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new UserContactUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!UserContactUIService.UIServiceMap.get(context.srfdynainstid)) {
                UserContactUIService.UIServiceMap.set(context.srfdynainstid, new UserContactUIService({context:context}));
            }
            return UserContactUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}