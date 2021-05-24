import { IbzproConfigAuthServiceBase } from './ibzpro-config-auth-service-base';


/**
 * 系统配置表权限服务对象
 *
 * @export
 * @class IbzproConfigAuthService
 * @extends {IbzproConfigAuthServiceBase}
 */
export default class IbzproConfigAuthService extends IbzproConfigAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzproConfigAuthService}
     * @memberof IbzproConfigAuthService
     */
    private static basicUIServiceInstance: IbzproConfigAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzproConfigAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzproConfigAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzproConfigAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzproConfigAuthService
     */
     public static getInstance(context: any): IbzproConfigAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzproConfigAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzproConfigAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzproConfigAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzproConfigAuthService({context:context}));
            }
            return IbzproConfigAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}