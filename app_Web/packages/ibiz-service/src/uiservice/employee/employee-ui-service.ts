import { EmployeeUIServiceBase } from './employee-ui-service-base';

/**
 * 人员UI服务对象
 *
 * @export
 * @class EmployeeUIService
 */
export default class EmployeeUIService extends EmployeeUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof EmployeeUIService
     */
    private static basicUIServiceInstance: EmployeeUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof EmployeeUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  EmployeeUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  EmployeeUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof EmployeeUIService
     */
    public static getInstance(context: any): EmployeeUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new EmployeeUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!EmployeeUIService.UIServiceMap.get(context.srfdynainstid)) {
                EmployeeUIService.UIServiceMap.set(context.srfdynainstid, new EmployeeUIService({context:context}));
            }
            return EmployeeUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}