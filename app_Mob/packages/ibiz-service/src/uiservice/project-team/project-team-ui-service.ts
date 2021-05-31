import { ProjectTeamUIServiceBase } from './project-team-ui-service-base';

/**
 * 项目团队UI服务对象
 *
 * @export
 * @class ProjectTeamUIService
 */
export default class ProjectTeamUIService extends ProjectTeamUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProjectTeamUIService
     */
    private static basicUIServiceInstance: ProjectTeamUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectTeamUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectTeamUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTeamUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectTeamUIService
     */
    public static getInstance(context: any): ProjectTeamUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectTeamUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectTeamUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProjectTeamUIService.UIServiceMap.set(context.srfdynainstid, new ProjectTeamUIService({context:context}));
            }
            return ProjectTeamUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}