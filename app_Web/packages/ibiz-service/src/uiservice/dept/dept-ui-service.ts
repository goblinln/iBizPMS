import { DeptUIServiceBase } from './dept-ui-service-base';

/**
 * 部门UI服务对象
 *
 * @export
 * @class DeptUIService
 */
export default class DeptUIService extends DeptUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof DeptUIService
     */
    private static basicUIServiceInstance: DeptUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof DeptUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  DeptUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  DeptUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof DeptUIService
     */
    public static getInstance(context: any): DeptUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new DeptUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!DeptUIService.UIServiceMap.get(context.srfdynainstid)) {
                DeptUIService.UIServiceMap.set(context.srfdynainstid, new DeptUIService({context:context}));
            }
            return DeptUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}