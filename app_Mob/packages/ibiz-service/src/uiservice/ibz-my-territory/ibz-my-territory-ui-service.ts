import { IbzMyTerritoryUIServiceBase } from './ibz-my-territory-ui-service-base';

/**
 * 我的地盘UI服务对象
 *
 * @export
 * @class IbzMyTerritoryUIService
 */
export default class IbzMyTerritoryUIService extends IbzMyTerritoryUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzMyTerritoryUIService
     */
    private static basicUIServiceInstance: IbzMyTerritoryUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzMyTerritoryUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzMyTerritoryUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzMyTerritoryUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzMyTerritoryUIService
     */
    public static getInstance(context: any): IbzMyTerritoryUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzMyTerritoryUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzMyTerritoryUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzMyTerritoryUIService.UIServiceMap.set(context.srfdynainstid, new IbzMyTerritoryUIService({context:context}));
            }
            return IbzMyTerritoryUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}