import { SysUpdateLogUIServiceBase } from './sys-update-log-ui-service-base';

/**
 * 更新日志UI服务对象
 *
 * @export
 * @class SysUpdateLogUIService
 */
export default class SysUpdateLogUIService extends SysUpdateLogUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof SysUpdateLogUIService
     */
    private static basicUIServiceInstance: SysUpdateLogUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof SysUpdateLogUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysUpdateLogUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysUpdateLogUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysUpdateLogUIService
     */
    public static getInstance(context: any): SysUpdateLogUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysUpdateLogUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysUpdateLogUIService.UIServiceMap.get(context.srfdynainstid)) {
                SysUpdateLogUIService.UIServiceMap.set(context.srfdynainstid, new SysUpdateLogUIService({context:context}));
            }
            return SysUpdateLogUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}