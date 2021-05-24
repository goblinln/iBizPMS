import { HistoryAuthServiceBase } from './history-auth-service-base';


/**
 * 操作历史权限服务对象
 *
 * @export
 * @class HistoryAuthService
 * @extends {HistoryAuthServiceBase}
 */
export default class HistoryAuthService extends HistoryAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {HistoryAuthService}
     * @memberof HistoryAuthService
     */
    private static basicUIServiceInstance: HistoryAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof HistoryAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  HistoryAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  HistoryAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof HistoryAuthService
     */
     public static getInstance(context: any): HistoryAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new HistoryAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!HistoryAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                HistoryAuthService.AuthServiceMap.set(context.srfdynainstid, new HistoryAuthService({context:context}));
            }
            return HistoryAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}