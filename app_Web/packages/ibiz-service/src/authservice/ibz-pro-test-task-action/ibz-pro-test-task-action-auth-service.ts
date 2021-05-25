import { IbzProTestTaskActionAuthServiceBase } from './ibz-pro-test-task-action-auth-service-base';


/**
 * 测试单日志权限服务对象
 *
 * @export
 * @class IbzProTestTaskActionAuthService
 * @extends {IbzProTestTaskActionAuthServiceBase}
 */
export default class IbzProTestTaskActionAuthService extends IbzProTestTaskActionAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzProTestTaskActionAuthService}
     * @memberof IbzProTestTaskActionAuthService
     */
    private static basicUIServiceInstance: IbzProTestTaskActionAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzProTestTaskActionAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzProTestTaskActionAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzProTestTaskActionAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzProTestTaskActionAuthService
     */
     public static getInstance(context: any): IbzProTestTaskActionAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzProTestTaskActionAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzProTestTaskActionAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzProTestTaskActionAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzProTestTaskActionAuthService({context:context}));
            }
            return IbzProTestTaskActionAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}