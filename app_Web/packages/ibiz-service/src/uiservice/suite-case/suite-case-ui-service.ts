import { SuiteCaseUIServiceBase } from './suite-case-ui-service-base';

/**
 * 套件用例UI服务对象
 *
 * @export
 * @class SuiteCaseUIService
 */
export default class SuiteCaseUIService extends SuiteCaseUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof SuiteCaseUIService
     */
    private static basicUIServiceInstance: SuiteCaseUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof SuiteCaseUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SuiteCaseUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  SuiteCaseUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SuiteCaseUIService
     */
    public static getInstance(context: any): SuiteCaseUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SuiteCaseUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SuiteCaseUIService.UIServiceMap.get(context.srfdynainstid)) {
                SuiteCaseUIService.UIServiceMap.set(context.srfdynainstid, new SuiteCaseUIService({context:context}));
            }
            return SuiteCaseUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}