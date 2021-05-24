import { IBZProProductLineAuthServiceBase } from './ibzpro-product-line-auth-service-base';


/**
 * 产品线权限服务对象
 *
 * @export
 * @class IBZProProductLineAuthService
 * @extends {IBZProProductLineAuthServiceBase}
 */
export default class IBZProProductLineAuthService extends IBZProProductLineAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZProProductLineAuthService}
     * @memberof IBZProProductLineAuthService
     */
    private static basicUIServiceInstance: IBZProProductLineAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZProProductLineAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZProProductLineAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProProductLineAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZProProductLineAuthService
     */
     public static getInstance(context: any): IBZProProductLineAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZProProductLineAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZProProductLineAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZProProductLineAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZProProductLineAuthService({context:context}));
            }
            return IBZProProductLineAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}