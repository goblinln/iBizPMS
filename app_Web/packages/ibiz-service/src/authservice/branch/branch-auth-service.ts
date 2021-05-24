import { BranchAuthServiceBase } from './branch-auth-service-base';


/**
 * 产品的分支和平台信息权限服务对象
 *
 * @export
 * @class BranchAuthService
 * @extends {BranchAuthServiceBase}
 */
export default class BranchAuthService extends BranchAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {BranchAuthService}
     * @memberof BranchAuthService
     */
    private static basicUIServiceInstance: BranchAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof BranchAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  BranchAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  BranchAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof BranchAuthService
     */
     public static getInstance(context: any): BranchAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new BranchAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!BranchAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                BranchAuthService.AuthServiceMap.set(context.srfdynainstid, new BranchAuthService({context:context}));
            }
            return BranchAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}