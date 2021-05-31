import { IbztaskteamUIServiceBase } from './ibztaskteam-ui-service-base';

/**
 * 任务团队UI服务对象
 *
 * @export
 * @class IbztaskteamUIService
 */
export default class IbztaskteamUIService extends IbztaskteamUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbztaskteamUIService
     */
    private static basicUIServiceInstance: IbztaskteamUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbztaskteamUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbztaskteamUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbztaskteamUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbztaskteamUIService
     */
    public static getInstance(context: any): IbztaskteamUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbztaskteamUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbztaskteamUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbztaskteamUIService.UIServiceMap.set(context.srfdynainstid, new IbztaskteamUIService({context:context}));
            }
            return IbztaskteamUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}