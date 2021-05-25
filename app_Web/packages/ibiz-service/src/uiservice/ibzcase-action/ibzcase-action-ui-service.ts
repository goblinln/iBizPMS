import { IBZCaseActionUIServiceBase } from './ibzcase-action-ui-service-base';

/**
 * 测试用例日志UI服务对象
 *
 * @export
 * @class IBZCaseActionUIService
 */
export default class IBZCaseActionUIService extends IBZCaseActionUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZCaseActionUIService
     */
    private static basicUIServiceInstance: IBZCaseActionUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZCaseActionUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZCaseActionUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZCaseActionUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZCaseActionUIService
     */
    public static getInstance(context: any): IBZCaseActionUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZCaseActionUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZCaseActionUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZCaseActionUIService.UIServiceMap.set(context.srfdynainstid, new IBZCaseActionUIService({context:context}));
            }
            return IBZCaseActionUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}