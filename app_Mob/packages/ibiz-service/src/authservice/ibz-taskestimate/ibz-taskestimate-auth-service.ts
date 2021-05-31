import { IbzTaskestimateAuthServiceBase } from './ibz-taskestimate-auth-service-base';


/**
 * 任务预计权限服务对象
 *
 * @export
 * @class IbzTaskestimateAuthService
 * @extends {IbzTaskestimateAuthServiceBase}
 */
export default class IbzTaskestimateAuthService extends IbzTaskestimateAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzTaskestimateAuthService}
     * @memberof IbzTaskestimateAuthService
     */
    private static basicUIServiceInstance: IbzTaskestimateAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzTaskestimateAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzTaskestimateAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzTaskestimateAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzTaskestimateAuthService
     */
     public static getInstance(context: any): IbzTaskestimateAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzTaskestimateAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzTaskestimateAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzTaskestimateAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzTaskestimateAuthService({context:context}));
            }
            return IbzTaskestimateAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}