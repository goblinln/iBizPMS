import { BurnUIServiceBase } from './burn-ui-service-base';

/**
 * burnUI服务对象
 *
 * @export
 * @class BurnUIService
 */
export default class BurnUIService extends BurnUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof BurnUIService
     */
    private static basicUIServiceInstance: BurnUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof BurnUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  BurnUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  BurnUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof BurnUIService
     */
    public static getInstance(context: any): BurnUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new BurnUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!BurnUIService.UIServiceMap.get(context.srfdynainstid)) {
                BurnUIService.UIServiceMap.set(context.srfdynainstid, new BurnUIService({context:context}));
            }
            return BurnUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}