import { SysUserUIServiceBase } from './sys-user-ui-service-base';

/**
 * 系统用户UI服务对象
 *
 * @export
 * @class SysUserUIService
 */
export default class SysUserUIService extends SysUserUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof SysUserUIService
     */
    private static basicUIServiceInstance: SysUserUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof SysUserUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysUserUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysUserUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysUserUIService
     */
    public static getInstance(context: any): SysUserUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysUserUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysUserUIService.UIServiceMap.get(context.srfdynainstid)) {
                SysUserUIService.UIServiceMap.set(context.srfdynainstid, new SysUserUIService({context:context}));
            }
            return SysUserUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}