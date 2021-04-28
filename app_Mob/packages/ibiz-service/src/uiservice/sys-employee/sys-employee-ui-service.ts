import { SysEmployeeUIServiceBase } from './sys-employee-ui-service-base';

/**
 * 人员UI服务对象
 *
 * @export
 * @class SysEmployeeUIService
 */
export default class SysEmployeeUIService extends SysEmployeeUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof SysEmployeeUIService
     */
    private static basicUIServiceInstance: SysEmployeeUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof SysEmployeeUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysEmployeeUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysEmployeeUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysEmployeeUIService
     */
    public static getInstance(context: any): SysEmployeeUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysEmployeeUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysEmployeeUIService.UIServiceMap.get(context.srfdynainstid)) {
                SysEmployeeUIService.UIServiceMap.set(context.srfdynainstid, new SysEmployeeUIService({context:context}));
            }
            return SysEmployeeUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}