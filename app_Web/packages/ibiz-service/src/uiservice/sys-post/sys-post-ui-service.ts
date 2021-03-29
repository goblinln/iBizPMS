import { SysPostUIServiceBase } from './sys-post-ui-service-base';

/**
 * 岗位UI服务对象
 *
 * @export
 * @class SysPostUIService
 */
export default class SysPostUIService extends SysPostUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof SysPostUIService
     */
    private static basicUIServiceInstance: SysPostUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof SysPostUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysPostUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysPostUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysPostUIService
     */
    public static getInstance(context: any): SysPostUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysPostUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysPostUIService.UIServiceMap.get(context.srfdynainstid)) {
                SysPostUIService.UIServiceMap.set(context.srfdynainstid, new SysPostUIService({context:context}));
            }
            return SysPostUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}