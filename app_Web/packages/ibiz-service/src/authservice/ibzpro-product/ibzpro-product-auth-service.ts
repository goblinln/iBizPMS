import { IBZProProductAuthServiceBase } from './ibzpro-product-auth-service-base';


/**
 * 平台产品权限服务对象
 *
 * @export
 * @class IBZProProductAuthService
 * @extends {IBZProProductAuthServiceBase}
 */
export default class IBZProProductAuthService extends IBZProProductAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZProProductAuthService}
     * @memberof IBZProProductAuthService
     */
    private static basicUIServiceInstance: IBZProProductAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZProProductAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZProProductAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProProductAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZProProductAuthService
     */
     public static getInstance(context: any): IBZProProductAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZProProductAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZProProductAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZProProductAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZProProductAuthService({context:context}));
            }
            return IBZProProductAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}