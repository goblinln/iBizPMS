import { SysAccountUIServiceBase } from './sys-account-ui-service-base';

/**
 * 系统用户UI服务对象
 *
 * @export
 * @class SysAccountUIService
 */
export default class SysAccountUIService extends SysAccountUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof SysAccountUIService
     */
    private static basicUIServiceInstance: SysAccountUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof SysAccountUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysAccountUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysAccountUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysAccountUIService
     */
    public static getInstance(context: any): SysAccountUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysAccountUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysAccountUIService.UIServiceMap.get(context.srfdynainstid)) {
                SysAccountUIService.UIServiceMap.set(context.srfdynainstid, new SysAccountUIService({context:context}));
            }
            return SysAccountUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}