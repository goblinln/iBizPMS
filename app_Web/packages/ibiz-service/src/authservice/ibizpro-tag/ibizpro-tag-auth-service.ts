import { IBIZProTagAuthServiceBase } from './ibizpro-tag-auth-service-base';


/**
 * 标签权限服务对象
 *
 * @export
 * @class IBIZProTagAuthService
 * @extends {IBIZProTagAuthServiceBase}
 */
export default class IBIZProTagAuthService extends IBIZProTagAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBIZProTagAuthService}
     * @memberof IBIZProTagAuthService
     */
    private static basicUIServiceInstance: IBIZProTagAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBIZProTagAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBIZProTagAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBIZProTagAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBIZProTagAuthService
     */
     public static getInstance(context: any): IBIZProTagAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBIZProTagAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBIZProTagAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBIZProTagAuthService.AuthServiceMap.set(context.srfdynainstid, new IBIZProTagAuthService({context:context}));
            }
            return IBIZProTagAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}