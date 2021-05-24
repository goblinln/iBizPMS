import { GroupAuthServiceBase } from './group-auth-service-base';


/**
 * 群组权限服务对象
 *
 * @export
 * @class GroupAuthService
 * @extends {GroupAuthServiceBase}
 */
export default class GroupAuthService extends GroupAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {GroupAuthService}
     * @memberof GroupAuthService
     */
    private static basicUIServiceInstance: GroupAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof GroupAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  GroupAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  GroupAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof GroupAuthService
     */
     public static getInstance(context: any): GroupAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new GroupAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!GroupAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                GroupAuthService.AuthServiceMap.set(context.srfdynainstid, new GroupAuthService({context:context}));
            }
            return GroupAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}