import { TestUIServiceBase } from './test-ui-service-base';

/**
 * 产品UI服务对象
 *
 * @export
 * @class TestUIService
 */
export default class TestUIService extends TestUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TestUIService
     */
    private static basicUIServiceInstance: TestUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TestUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestUIService
     */
    public static getInstance(context: any): TestUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestUIService.UIServiceMap.get(context.srfdynainstid)) {
                TestUIService.UIServiceMap.set(context.srfdynainstid, new TestUIService({context:context}));
            }
            return TestUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}