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
                CounterServiceRegister.allCounterMap.set('PSSYSAPPS/Mob/PSAPPCOUNTERS/MobMenuCounter.json', () => import('../counter/mob-menu-counter/mob-menu-counter-counter'));
        CounterServiceRegister.allCounterMap.set('PSSYSAPPS/Mob/PSAPPCOUNTERS/MobProductPlanCounter.json', () => import('../counter/mob-product-plan-counter/mob-product-plan-counter-counter'));
        CounterServiceRegister.allCounterMap.set('PSSYSAPPS/Mob/PSAPPCOUNTERS/MobProductReleaseCounter.json', () => import('../counter/mob-product-release-counter/mob-product-release-counter-counter'));
        CounterServiceRegister.allCounterMap.set('PSSYSAPPS/Mob/PSAPPCOUNTERS/MobTestSuiteCounter.json', () => import('../counter/mob-test-suite-counter/mob-test-suite-counter-counter'));
        CounterServiceRegister.allCounterMap.set('PSSYSAPPS/Mob/PSAPPCOUNTERS/MobTestTaskCounter.json', () => import('../counter/mob-test-task-counter/mob-test-task-counter-counter'));
        CounterServiceRegister.allCounterMap.set('PSSYSAPPS/Mob/PSAPPCOUNTERS/MyFavoriteMobCounter.json', () => import('../counter/my-favorite-mob-counter/my-favorite-mob-counter-counter'));
        CounterServiceRegister.allCounterMap.set('PSSYSAPPS/Mob/PSAPPCOUNTERS/MyMobCounter.json', () => import('../counter/my-mob-counter/my-mob-counter-counter'));
        CounterServiceRegister.allCounterMap.set('PSSYSAPPS/Mob/PSAPPCOUNTERS/MySubmitReporttCounter.json', () => import('../counter/my-submit-reportt-counter/my-submit-reportt-counter-counter'));
        CounterServiceRegister.allCounterMap.set('PSSYSAPPS/Mob/PSAPPCOUNTERS/ProductMobCounter.json', () => import('../counter/product-mob-counter/product-mob-counter-counter'));
        CounterServiceRegister.allCounterMap.set('PSSYSAPPS/Mob/PSAPPCOUNTERS/ProductTestMobCounter.json', () => import('../counter/product-test-mob-counter/product-test-mob-counter-counter'));
        CounterServiceRegister.allCounterMap.set('PSSYSAPPS/Mob/PSAPPCOUNTERS/ProjectCounter.json', () => import('../counter/project-counter/project-counter-counter'));
    }

    /**
     * 获取指定CounterService
     *
     * @public
     * @memberof CounterServiceRegister
     */
    public async getService(data: any, counterKey: string | undefined) {
        if(!counterKey){
            return;
        }
        const importService = CounterServiceRegister.allCounterMap.get(counterKey);
        const importModule = await importService();
        return importModule.default.getInstance(data);
    }

}
