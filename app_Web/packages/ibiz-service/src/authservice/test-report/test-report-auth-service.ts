import { TestReportAuthServiceBase } from './test-report-auth-service-base';


/**
 * 测试报告权限服务对象
 *
 * @export
 * @class TestReportAuthService
 * @extends {TestReportAuthServiceBase}
 */
export default class TestReportAuthService extends TestReportAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TestReportAuthService}
     * @memberof TestReportAuthService
     */
    private static basicUIServiceInstance: TestReportAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TestReportAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestReportAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestReportAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestReportAuthService
     */
     public static getInstance(context: any): TestReportAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestReportAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestReportAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TestReportAuthService.AuthServiceMap.set(context.srfdynainstid, new TestReportAuthService({context:context}));
            }
            return TestReportAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}