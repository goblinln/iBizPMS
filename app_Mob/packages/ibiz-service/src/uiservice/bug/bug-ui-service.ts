import { BugUIServiceBase } from './bug-ui-service-base';

/**
 * BugUI服务对象
 *
 * @export
 * @class BugUIService
 */
export default class BugUIService extends BugUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof BugUIService
     */
    private static basicUIServiceInstance: BugUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof BugUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  BugUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  BugUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof BugUIService
     */
    public static getInstance(context: any): BugUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new BugUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!BugUIService.UIServiceMap.get(context.srfdynainstid)) {
                BugUIService.UIServiceMap.set(context.srfdynainstid, new BugUIService({context:context}));
            }
            return BugUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}