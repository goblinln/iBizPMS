import { TestReportUIServiceBase } from './test-report-ui-service-base';

/**
 * 测试报告UI服务对象
 *
 * @export
 * @class TestReportUIService
 */
export default class TestReportUIService extends TestReportUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TestReportUIService
     */
    private static basicUIServiceInstance: TestReportUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TestReportUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestReportUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestReportUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestReportUIService
     */
    public static getInstance(context: any): TestReportUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestReportUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestReportUIService.UIServiceMap.get(context.srfdynainstid)) {
                TestReportUIService.UIServiceMap.set(context.srfdynainstid, new TestReportUIService({context:context}));
            }
            return TestReportUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}