import { CaseUIServiceBase } from './case-ui-service-base';

/**
 * 测试用例UI服务对象
 *
 * @export
 * @class CaseUIService
 */
export default class CaseUIService extends CaseUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof CaseUIService
     */
    private static basicUIServiceInstance: CaseUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof CaseUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  CaseUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  CaseUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof CaseUIService
     */
    public static getInstance(context: any): CaseUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new CaseUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!CaseUIService.UIServiceMap.get(context.srfdynainstid)) {
                CaseUIService.UIServiceMap.set(context.srfdynainstid, new CaseUIService({context:context}));
            }
            return CaseUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}