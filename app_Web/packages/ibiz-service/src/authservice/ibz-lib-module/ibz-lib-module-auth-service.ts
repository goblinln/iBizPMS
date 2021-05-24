import { IbzLibModuleAuthServiceBase } from './ibz-lib-module-auth-service-base';


/**
 * 用例库模块权限服务对象
 *
 * @export
 * @class IbzLibModuleAuthService
 * @extends {IbzLibModuleAuthServiceBase}
 */
export default class IbzLibModuleAuthService extends IbzLibModuleAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzLibModuleAuthService}
     * @memberof IbzLibModuleAuthService
     */
    private static basicUIServiceInstance: IbzLibModuleAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzLibModuleAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzLibModuleAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzLibModuleAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzLibModuleAuthService
     */
     public static getInstance(context: any): IbzLibModuleAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzLibModuleAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzLibModuleAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzLibModuleAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzLibModuleAuthService({context:context}));
            }
            return IbzLibModuleAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}