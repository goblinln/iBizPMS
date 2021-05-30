import { SysTeamMemberUIServiceBase } from './sys-team-member-ui-service-base';

/**
 * 组成员UI服务对象
 *
 * @export
 * @class SysTeamMemberUIService
 */
export default class SysTeamMemberUIService extends SysTeamMemberUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof SysTeamMemberUIService
     */
    private static basicUIServiceInstance: SysTeamMemberUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof SysTeamMemberUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysTeamMemberUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysTeamMemberUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysTeamMemberUIService
     */
    public static getInstance(context: any): SysTeamMemberUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysTeamMemberUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysTeamMemberUIService.UIServiceMap.get(context.srfdynainstid)) {
                SysTeamMemberUIService.UIServiceMap.set(context.srfdynainstid, new SysTeamMemberUIService({context:context}));
            }
            return SysTeamMemberUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}