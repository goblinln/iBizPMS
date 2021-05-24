import { IbzFavoritesAuthServiceBase } from './ibz-favorites-auth-service-base';


/**
 * 收藏权限服务对象
 *
 * @export
 * @class IbzFavoritesAuthService
 * @extends {IbzFavoritesAuthServiceBase}
 */
export default class IbzFavoritesAuthService extends IbzFavoritesAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzFavoritesAuthService}
     * @memberof IbzFavoritesAuthService
     */
    private static basicUIServiceInstance: IbzFavoritesAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzFavoritesAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzFavoritesAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzFavoritesAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzFavoritesAuthService
     */
     public static getInstance(context: any): IbzFavoritesAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzFavoritesAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzFavoritesAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzFavoritesAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzFavoritesAuthService({context:context}));
            }
            return IbzFavoritesAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}