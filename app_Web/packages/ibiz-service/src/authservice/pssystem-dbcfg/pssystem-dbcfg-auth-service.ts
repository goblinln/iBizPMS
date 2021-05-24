import { PSSystemDBCfgAuthServiceBase } from './pssystem-dbcfg-auth-service-base';


/**
 * 系统数据库权限服务对象
 *
 * @export
 * @class PSSystemDBCfgAuthService
 * @extends {PSSystemDBCfgAuthServiceBase}
 */
export default class PSSystemDBCfgAuthService extends PSSystemDBCfgAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {PSSystemDBCfgAuthService}
     * @memberof PSSystemDBCfgAuthService
     */
    private static basicUIServiceInstance: PSSystemDBCfgAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof PSSystemDBCfgAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  PSSystemDBCfgAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  PSSystemDBCfgAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof PSSystemDBCfgAuthService
     */
     public static getInstance(context: any): PSSystemDBCfgAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new PSSystemDBCfgAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!PSSystemDBCfgAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                PSSystemDBCfgAuthService.AuthServiceMap.set(context.srfdynainstid, new PSSystemDBCfgAuthService({context:context}));
            }
            return PSSystemDBCfgAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}