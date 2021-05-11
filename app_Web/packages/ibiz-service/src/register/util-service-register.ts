
/**
 * 功能服务注册中心
 *
 * @export
 * @class UtilServiceRegister
 */
export class UtilServiceRegister {

    /**
     * UtilServiceRegister 单例对象
     *
     * @private
     * @static
     * @memberof UtilServiceRegister
     */
    private static UtilServiceRegister: UtilServiceRegister;

    /**
     * 所有UIService Map对象
     *
     * @private
     * @static
     * @memberof UtilServiceRegister
     */
    private static allUtilServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of UtilServiceRegister.
     * @memberof UtilServiceRegister
     */
    constructor() {
        this.init();
    }

    /**
     * 获取UIServiceRegister 单例对象
     *
     * @public
     * @static
     * @memberof UIServiceRegister
     */
    public static getInstance() {
        if (!this.UtilServiceRegister) {
            this.UtilServiceRegister = new UtilServiceRegister();
        }
        return this.UtilServiceRegister;
    }

    /**
     * 初始化
     *
     * @protected
     * @memberof UtilServiceRegister
     */
    protected init(): void {
                UtilServiceRegister.allUtilServiceMap.set('dynafilter', () => import('../utilservice/dynafilter/dynafilter-util-service'));
        UtilServiceRegister.allUtilServiceMap.set('dynadashboard', () => import('../utilservice/dynadashboard/dynadashboard-util-service'));
    }

    /**
     * 获取指定UtilService
     *
     * @public
     * @memberof UtilServiceRegister
     */
    public async getService(context: any, entityKey: string) {
        const importService = UtilServiceRegister.allUtilServiceMap.get(entityKey);
        if (importService) {
            const importModule = await importService();
            return importModule.default.getInstance(context);
        }
    }

}