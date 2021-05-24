import { IBZProProductActionAuthServiceBase } from './ibzpro-product-action-auth-service-base';


/**
 * 产品日志权限服务对象
 *
 * @export
 * @class IBZProProductActionAuthService
 * @extends {IBZProProductActionAuthServiceBase}
 */
export default class IBZProProductActionAuthService extends IBZProProductActionAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZProProductActionAuthService}
     * @memberof IBZProProductActionAuthService
     */
    private static basicUIServiceInstance: IBZProProductActionAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZProProductActionAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZProProductActionAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProProductActionAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZProProductActionAuthService
     */
     public static getInstance(context: any): IBZProProductActionAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZProProductActionAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZProProductActionAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZProProductActionAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZProProductActionAuthService({context:context}));
            }
            return IBZProProductActionAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}