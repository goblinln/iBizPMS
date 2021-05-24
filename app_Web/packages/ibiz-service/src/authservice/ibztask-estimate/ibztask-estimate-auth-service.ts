import { IBZTaskEstimateAuthServiceBase } from './ibztask-estimate-auth-service-base';


/**
 * 任务预计权限服务对象
 *
 * @export
 * @class IBZTaskEstimateAuthService
 * @extends {IBZTaskEstimateAuthServiceBase}
 */
export default class IBZTaskEstimateAuthService extends IBZTaskEstimateAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZTaskEstimateAuthService}
     * @memberof IBZTaskEstimateAuthService
     */
    private static basicUIServiceInstance: IBZTaskEstimateAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZTaskEstimateAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZTaskEstimateAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZTaskEstimateAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZTaskEstimateAuthService
     */
     public static getInstance(context: any): IBZTaskEstimateAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZTaskEstimateAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZTaskEstimateAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZTaskEstimateAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZTaskEstimateAuthService({context:context}));
            }
            return IBZTaskEstimateAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}