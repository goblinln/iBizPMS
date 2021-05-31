import { ProjectStatsUIServiceBase } from './project-stats-ui-service-base';

/**
 * 项目统计UI服务对象
 *
 * @export
 * @class ProjectStatsUIService
 */
export default class ProjectStatsUIService extends ProjectStatsUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProjectStatsUIService
     */
    private static basicUIServiceInstance: ProjectStatsUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectStatsUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectStatsUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectStatsUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectStatsUIService
     */
    public static getInstance(context: any): ProjectStatsUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectStatsUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectStatsUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProjectStatsUIService.UIServiceMap.set(context.srfdynainstid, new ProjectStatsUIService({context:context}));
            }
            return ProjectStatsUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}