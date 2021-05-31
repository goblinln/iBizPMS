import { IbizproIndexAuthServiceBase } from './ibizpro-index-auth-service-base';


/**
 * 索引检索权限服务对象
 *
 * @export
 * @class IbizproIndexAuthService
 * @extends {IbizproIndexAuthServiceBase}
 */
export default class IbizproIndexAuthService extends IbizproIndexAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbizproIndexAuthService}
     * @memberof IbizproIndexAuthService
     */
    private static basicUIServiceInstance: IbizproIndexAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbizproIndexAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbizproIndexAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproIndexAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbizproIndexAuthService
     */
     public static getInstance(context: any): IbizproIndexAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbizproIndexAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbizproIndexAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbizproIndexAuthService.AuthServiceMap.set(context.srfdynainstid, new IbizproIndexAuthService({context:context}));
            }
            return IbizproIndexAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}