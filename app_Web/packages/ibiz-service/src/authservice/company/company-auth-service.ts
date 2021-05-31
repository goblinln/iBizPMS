import { CompanyAuthServiceBase } from './company-auth-service-base';


/**
 * 公司权限服务对象
 *
 * @export
 * @class CompanyAuthService
 * @extends {CompanyAuthServiceBase}
 */
export default class CompanyAuthService extends CompanyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {CompanyAuthService}
     * @memberof CompanyAuthService
     */
    private static basicUIServiceInstance: CompanyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof CompanyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  CompanyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  CompanyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof CompanyAuthService
     */
     public static getInstance(context: any): CompanyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new CompanyAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!CompanyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                CompanyAuthService.AuthServiceMap.set(context.srfdynainstid, new CompanyAuthService({context:context}));
            }
            return CompanyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}