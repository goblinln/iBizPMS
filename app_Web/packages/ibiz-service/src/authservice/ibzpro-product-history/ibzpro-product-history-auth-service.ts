import { IBZProProductHistoryAuthServiceBase } from './ibzpro-product-history-auth-service-base';


/**
 * 产品操作历史权限服务对象
 *
 * @export
 * @class IBZProProductHistoryAuthService
 * @extends {IBZProProductHistoryAuthServiceBase}
 */
export default class IBZProProductHistoryAuthService extends IBZProProductHistoryAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZProProductHistoryAuthService}
     * @memberof IBZProProductHistoryAuthService
     */
    private static basicUIServiceInstance: IBZProProductHistoryAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZProProductHistoryAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZProProductHistoryAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProProductHistoryAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZProProductHistoryAuthService
     */
     public static getInstance(context: any): IBZProProductHistoryAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZProProductHistoryAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZProProductHistoryAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZProProductHistoryAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZProProductHistoryAuthService({context:context}));
            }
            return IBZProProductHistoryAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}