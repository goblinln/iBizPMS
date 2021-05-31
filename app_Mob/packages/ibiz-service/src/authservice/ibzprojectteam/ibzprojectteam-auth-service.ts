import { IBZPROJECTTEAMAuthServiceBase } from './ibzprojectteam-auth-service-base';


/**
 * 项目团队权限服务对象
 *
 * @export
 * @class IBZPROJECTTEAMAuthService
 * @extends {IBZPROJECTTEAMAuthServiceBase}
 */
export default class IBZPROJECTTEAMAuthService extends IBZPROJECTTEAMAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZPROJECTTEAMAuthService}
     * @memberof IBZPROJECTTEAMAuthService
     */
    private static basicUIServiceInstance: IBZPROJECTTEAMAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZPROJECTTEAMAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZPROJECTTEAMAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZPROJECTTEAMAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZPROJECTTEAMAuthService
     */
     public static getInstance(context: any): IBZPROJECTTEAMAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZPROJECTTEAMAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZPROJECTTEAMAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZPROJECTTEAMAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZPROJECTTEAMAuthService({context:context}));
            }
            return IBZPROJECTTEAMAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}