import { IBzDocAuthServiceBase } from './ibz-doc-auth-service-base';


/**
 * 文档权限服务对象
 *
 * @export
 * @class IBzDocAuthService
 * @extends {IBzDocAuthServiceBase}
 */
export default class IBzDocAuthService extends IBzDocAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBzDocAuthService}
     * @memberof IBzDocAuthService
     */
    private static basicUIServiceInstance: IBzDocAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBzDocAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBzDocAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBzDocAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBzDocAuthService
     */
     public static getInstance(context: any): IBzDocAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBzDocAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBzDocAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBzDocAuthService.AuthServiceMap.set(context.srfdynainstid, new IBzDocAuthService({context:context}));
            }
            return IBzDocAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}