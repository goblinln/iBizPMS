import { SysDepartmentUIServiceBase } from './sys-department-ui-service-base';

/**
 * 部门UI服务对象
 *
 * @export
 * @class SysDepartmentUIService
 */
export default class SysDepartmentUIService extends SysDepartmentUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof SysDepartmentUIService
     */
    private static basicUIServiceInstance: SysDepartmentUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof SysDepartmentUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysDepartmentUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysDepartmentUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysDepartmentUIService
     */
    public static getInstance(context: any): SysDepartmentUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysDepartmentUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysDepartmentUIService.UIServiceMap.get(context.srfdynainstid)) {
                SysDepartmentUIService.UIServiceMap.set(context.srfdynainstid, new SysDepartmentUIService({context:context}));
            }
            return SysDepartmentUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}