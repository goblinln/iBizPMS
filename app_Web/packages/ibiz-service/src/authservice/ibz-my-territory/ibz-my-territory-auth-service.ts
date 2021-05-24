import { IbzMyTerritoryAuthServiceBase } from './ibz-my-territory-auth-service-base';


/**
 * 我的地盘权限服务对象
 *
 * @export
 * @class IbzMyTerritoryAuthService
 * @extends {IbzMyTerritoryAuthServiceBase}
 */
export default class IbzMyTerritoryAuthService extends IbzMyTerritoryAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzMyTerritoryAuthService}
     * @memberof IbzMyTerritoryAuthService
     */
    private static basicUIServiceInstance: IbzMyTerritoryAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzMyTerritoryAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzMyTerritoryAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzMyTerritoryAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzMyTerritoryAuthService
     */
     public static getInstance(context: any): IbzMyTerritoryAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzMyTerritoryAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzMyTerritoryAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzMyTerritoryAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzMyTerritoryAuthService({context:context}));
            }
            return IbzMyTerritoryAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}