import { SysTeamUIServiceBase } from './sys-team-ui-service-base';

/**
 * 组UI服务对象
 *
 * @export
 * @class SysTeamUIService
 */
export default class SysTeamUIService extends SysTeamUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof SysTeamUIService
     */
    private static basicUIServiceInstance: SysTeamUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof SysTeamUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysTeamUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysTeamUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysTeamUIService
     */
    public static getInstance(context: any): SysTeamUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysTeamUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysTeamUIService.UIServiceMap.get(context.srfdynainstid)) {
                SysTeamUIService.UIServiceMap.set(context.srfdynainstid, new SysTeamUIService({context:context}));
            }
            return SysTeamUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}