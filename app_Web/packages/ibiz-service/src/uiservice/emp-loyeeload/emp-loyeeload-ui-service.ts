import { EmpLoyeeloadUIServiceBase } from './emp-loyeeload-ui-service-base';

/**
 * 员工负载表UI服务对象
 *
 * @export
 * @class EmpLoyeeloadUIService
 */
export default class EmpLoyeeloadUIService extends EmpLoyeeloadUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof EmpLoyeeloadUIService
     */
    private static basicUIServiceInstance: EmpLoyeeloadUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof EmpLoyeeloadUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  EmpLoyeeloadUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  EmpLoyeeloadUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof EmpLoyeeloadUIService
     */
    public static getInstance(context: any): EmpLoyeeloadUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new EmpLoyeeloadUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!EmpLoyeeloadUIService.UIServiceMap.get(context.srfdynainstid)) {
                EmpLoyeeloadUIService.UIServiceMap.set(context.srfdynainstid, new EmpLoyeeloadUIService({context:context}));
            }
            return EmpLoyeeloadUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}