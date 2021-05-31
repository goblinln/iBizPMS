import { IbzReportRoleConfigUIServiceBase } from './ibz-report-role-config-ui-service-base';

/**
 * 汇报角色配置UI服务对象
 *
 * @export
 * @class IbzReportRoleConfigUIService
 */
export default class IbzReportRoleConfigUIService extends IbzReportRoleConfigUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzReportRoleConfigUIService
     */
    private static basicUIServiceInstance: IbzReportRoleConfigUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzReportRoleConfigUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzReportRoleConfigUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzReportRoleConfigUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzReportRoleConfigUIService
     */
    public static getInstance(context: any): IbzReportRoleConfigUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzReportRoleConfigUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzReportRoleConfigUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzReportRoleConfigUIService.UIServiceMap.set(context.srfdynainstid, new IbzReportRoleConfigUIService({context:context}));
            }
            return IbzReportRoleConfigUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}