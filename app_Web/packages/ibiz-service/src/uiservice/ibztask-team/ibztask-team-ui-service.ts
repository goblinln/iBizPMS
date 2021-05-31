import { IBZTaskTeamUIServiceBase } from './ibztask-team-ui-service-base';

/**
 * 任务团队UI服务对象
 *
 * @export
 * @class IBZTaskTeamUIService
 */
export default class IBZTaskTeamUIService extends IBZTaskTeamUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZTaskTeamUIService
     */
    private static basicUIServiceInstance: IBZTaskTeamUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZTaskTeamUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZTaskTeamUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZTaskTeamUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZTaskTeamUIService
     */
    public static getInstance(context: any): IBZTaskTeamUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZTaskTeamUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZTaskTeamUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZTaskTeamUIService.UIServiceMap.set(context.srfdynainstid, new IBZTaskTeamUIService({context:context}));
            }
            return IBZTaskTeamUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}