import { PSSysAppUIServiceBase } from './pssys-app-ui-service-base';

/**
 * 系统应用UI服务对象
 *
 * @export
 * @class PSSysAppUIService
 */
export default class PSSysAppUIService extends PSSysAppUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof PSSysAppUIService
     */
    private static basicUIServiceInstance: PSSysAppUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof PSSysAppUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  PSSysAppUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  PSSysAppUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof PSSysAppUIService
     */
    public static getInstance(context: any): PSSysAppUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new PSSysAppUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!PSSysAppUIService.UIServiceMap.get(context.srfdynainstid)) {
                PSSysAppUIService.UIServiceMap.set(context.srfdynainstid, new PSSysAppUIService({context:context}));
            }
            return PSSysAppUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}