import { IbzLibAuthServiceBase } from './ibz-lib-auth-service-base';


/**
 * 用例库权限服务对象
 *
 * @export
 * @class IbzLibAuthService
 * @extends {IbzLibAuthServiceBase}
 */
export default class IbzLibAuthService extends IbzLibAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzLibAuthService}
     * @memberof IbzLibAuthService
     */
    private static basicUIServiceInstance: IbzLibAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzLibAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzLibAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzLibAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzLibAuthService
     */
     public static getInstance(context: any): IbzLibAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzLibAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzLibAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzLibAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzLibAuthService({context:context}));
            }
            return IbzLibAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}