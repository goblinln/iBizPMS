import { ProjectBugUIServiceBase } from './project-bug-ui-service-base';

/**
 * BugUI服务对象
 *
 * @export
 * @class ProjectBugUIService
 */
export default class ProjectBugUIService extends ProjectBugUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProjectBugUIService
     */
    private static basicUIServiceInstance: ProjectBugUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectBugUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectBugUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectBugUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectBugUIService
     */
    public static getInstance(context: any): ProjectBugUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectBugUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectBugUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProjectBugUIService.UIServiceMap.set(context.srfdynainstid, new ProjectBugUIService({context:context}));
            }
            return ProjectBugUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}