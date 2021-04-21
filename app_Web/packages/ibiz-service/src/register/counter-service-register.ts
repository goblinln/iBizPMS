/**
 * 计数器服务注册中心
 *
 * @export
 * @class CounterServiceRegister
 */
export class CounterServiceRegister {

    /**
     * CounterServiceRegister 单例对象
     *
     * @private
     * @static
     * @memberof CounterServiceRegister
     */
    private static CounterServiceRegister: CounterServiceRegister;

    /**
     * 所有CounterService Map对象
     *
     * @private
     * @static
     * @memberof CounterServiceRegister
     */
    private static allCounterMap: Map<string, any> = new Map();

    /**
     * 获取 CounterServiceRegister 单例对象
     *
     * @public
     * @static
     * @memberof CounterServiceRegister
     */
    public static getInstance() {
        if (!this.CounterServiceRegister) {
            this.CounterServiceRegister = new CounterServiceRegister();
        }
        return this.CounterServiceRegister;
    }

    /**
     * Creates an instance of CounterServiceRegister.
     * @memberof CounterServiceRegister
     */
    constructor() {
        this.init();
    }

    /**
     * 初始化
     *
     * @protected
     * @memberof CounterServiceRegister
     */
    protected init(): void {
                CounterServiceRegister.allCounterMap.set('PSSYSAPPS/Web/PSAPPCOUNTERS/ProjectTaskQCounter.json', () => import('../counter/project-task-qcounter/project-task-qcounter-counter'));
        CounterServiceRegister.allCounterMap.set('PSSYSAPPS/Web/PSAPPCOUNTERS/MyReportCounter.json', () => import('../counter/my-report-counter/my-report-counter-counter'));
    }

    /**
     * 获取指定CounterService
     *
     * @public
     * @memberof CounterServiceRegister
     */
    public async getService(data: any, counterKey: string) {
        const importService = CounterServiceRegister.allCounterMap.get(counterKey);
        const importModule = await importService();
        return importModule.default.getInstance(data);
    }

}
