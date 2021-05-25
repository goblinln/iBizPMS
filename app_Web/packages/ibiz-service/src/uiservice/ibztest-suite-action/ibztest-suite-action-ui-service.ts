import { IBZTestSuiteActionUIServiceBase } from './ibztest-suite-action-ui-service-base';

/**
 * 套件日志UI服务对象
 *
 * @export
 * @class IBZTestSuiteActionUIService
 */
export default class IBZTestSuiteActionUIService extends IBZTestSuiteActionUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZTestSuiteActionUIService
     */
    private static basicUIServiceInstance: IBZTestSuiteActionUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZTestSuiteActionUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZTestSuiteActionUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZTestSuiteActionUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZTestSuiteActionUIService
     */
    public static getInstance(context: any): IBZTestSuiteActionUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZTestSuiteActionUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZTestSuiteActionUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZTestSuiteActionUIService.UIServiceMap.set(context.srfdynainstid, new IBZTestSuiteActionUIService({context:context}));
            }
            return IBZTestSuiteActionUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}