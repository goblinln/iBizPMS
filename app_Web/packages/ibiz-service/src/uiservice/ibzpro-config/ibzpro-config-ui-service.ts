import { IbzproConfigUIServiceBase } from './ibzpro-config-ui-service-base';

/**
 * 系统配置表UI服务对象
 *
 * @export
 * @class IbzproConfigUIService
 */
export default class IbzproConfigUIService extends IbzproConfigUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzproConfigUIService
     */
    private static basicUIServiceInstance: IbzproConfigUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzproConfigUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzproConfigUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzproConfigUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzproConfigUIService
     */
    public static getInstance(context: any): IbzproConfigUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzproConfigUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzproConfigUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzproConfigUIService.UIServiceMap.set(context.srfdynainstid, new IbzproConfigUIService({context:context}));
            }
            return IbzproConfigUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}