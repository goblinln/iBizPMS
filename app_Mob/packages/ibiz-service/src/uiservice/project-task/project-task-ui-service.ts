import { ProjectTaskUIServiceBase } from './project-task-ui-service-base';

/**
 * 任务UI服务对象
 *
 * @export
 * @class ProjectTaskUIService
 */
export default class ProjectTaskUIService extends ProjectTaskUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProjectTaskUIService
     */
    private static basicUIServiceInstance: ProjectTaskUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectTaskUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectTaskUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTaskUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectTaskUIService
     */
    public static getInstance(context: any): ProjectTaskUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectTaskUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectTaskUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProjectTaskUIService.UIServiceMap.set(context.srfdynainstid, new ProjectTaskUIService({context:context}));
            }
            return ProjectTaskUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}