import { IbzFavoritesUIServiceBase } from './ibz-favorites-ui-service-base';

/**
 * 收藏UI服务对象
 *
 * @export
 * @class IbzFavoritesUIService
 */
export default class IbzFavoritesUIService extends IbzFavoritesUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzFavoritesUIService
     */
    private static basicUIServiceInstance: IbzFavoritesUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzFavoritesUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzFavoritesUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzFavoritesUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzFavoritesUIService
     */
    public static getInstance(context: any): IbzFavoritesUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzFavoritesUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzFavoritesUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzFavoritesUIService.UIServiceMap.set(context.srfdynainstid, new IbzFavoritesUIService({context:context}));
            }
            return IbzFavoritesUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}