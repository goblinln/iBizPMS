import { TestTaskUIServiceBase } from './test-task-ui-service-base';

/**
 * 测试版本UI服务对象
 *
 * @export
 * @class TestTaskUIService
 */
export default class TestTaskUIService extends TestTaskUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TestTaskUIService
     */
    private static basicUIServiceInstance: TestTaskUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TestTaskUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TestTaskUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestTaskUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TestTaskUIService
     */
    public static getInstance(context: any): TestTaskUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TestTaskUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TestTaskUIService.UIServiceMap.get(context.srfdynainstid)) {
                TestTaskUIService.UIServiceMap.set(context.srfdynainstid, new TestTaskUIService({context:context}));
            }
            return TestTaskUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}