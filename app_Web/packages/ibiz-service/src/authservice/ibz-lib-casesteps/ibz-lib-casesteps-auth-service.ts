import { IbzLibCasestepsAuthServiceBase } from './ibz-lib-casesteps-auth-service-base';


/**
 * 用例库用例步骤权限服务对象
 *
 * @export
 * @class IbzLibCasestepsAuthService
 * @extends {IbzLibCasestepsAuthServiceBase}
 */
export default class IbzLibCasestepsAuthService extends IbzLibCasestepsAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzLibCasestepsAuthService}
     * @memberof IbzLibCasestepsAuthService
     */
    private static basicUIServiceInstance: IbzLibCasestepsAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzLibCasestepsAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzLibCasestepsAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzLibCasestepsAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzLibCasestepsAuthService
     */
     public static getInstance(context: any): IbzLibCasestepsAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzLibCasestepsAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzLibCasestepsAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzLibCasestepsAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzLibCasestepsAuthService({context:context}));
            }
            return IbzLibCasestepsAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}