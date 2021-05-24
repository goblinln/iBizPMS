import { IbzCaseAuthServiceBase } from './ibz-case-auth-service-base';


/**
 * 测试用例权限服务对象
 *
 * @export
 * @class IbzCaseAuthService
 * @extends {IbzCaseAuthServiceBase}
 */
export default class IbzCaseAuthService extends IbzCaseAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzCaseAuthService}
     * @memberof IbzCaseAuthService
     */
    private static basicUIServiceInstance: IbzCaseAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzCaseAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzCaseAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzCaseAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzCaseAuthService
     */
     public static getInstance(context: any): IbzCaseAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzCaseAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzCaseAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzCaseAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzCaseAuthService({context:context}));
            }
            return IbzCaseAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}