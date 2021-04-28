/**
 * 实体服务注册中心
 *
 * @export
 * @class ServiceRegisterBase
 */
export class ServiceRegisterBase {

    /**
     * 所有服务Map
     *
     * @protected
     * @type {*}
     * @memberof ServiceRegisterBase
     */
    protected allService: Map<string, () => Promise<any>> = new Map();

    /**
     * 已加载服务Map缓存
     *
     * @protected
     * @type {Map<string, any>}
     * @memberof ServiceRegisterBase
     */
    protected serviceCache: Map<string, any> = new Map();

    /**
     * Creates an instance of ServiceRegisterBase.
     * @memberof ServiceRegisterBase
     */
    constructor() {
        this.init();
    }

    /**
     * 初始化
     *
     * @protected
     * @memberof ServiceRegisterBase
     */
    protected init(): void {}

    /**
     * 加载服务
     *
     * @protected
     * @param {string} serviceName
     * @returns {Promise<any>}
     * @memberof ServiceRegisterBase
     */
    protected async loadService(serviceName: string): Promise<any> {
        const service = this.allService.get(serviceName);
        if (service) {
            return service();
        }
    }

    /**
     * 获取服务
     *
     * @param {string} name
     * @returns {Promise<any>}
     * @memberof ServiceRegisterBase
     */
    public async getService(name: string): Promise<any> {
        if (this.serviceCache.has(name)) {
            return this.serviceCache.get(name);
        }
        const currentService: any = await this.loadService(name);
        if (currentService && currentService.default) {
            const instance: any = new currentService.default();
            this.serviceCache.set(name, instance);
            return instance;
        }
    }

}