import { SysOrganizationUIServiceBase } from './sys-organization-ui-service-base';

/**
 * 单位UI服务对象
 *
 * @export
 * @class SysOrganizationUIService
 */
export default class SysOrganizationUIService extends SysOrganizationUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof SysOrganizationUIService
     */
    private static basicUIServiceInstance: SysOrganizationUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof SysOrganizationUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysOrganizationUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysOrganizationUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysOrganizationUIService
     */
    public static getInstance(context: any): SysOrganizationUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysOrganizationUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysOrganizationUIService.UIServiceMap.get(context.srfdynainstid)) {
                SysOrganizationUIService.UIServiceMap.set(context.srfdynainstid, new SysOrganizationUIService({context:context}));
            }
            return SysOrganizationUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}