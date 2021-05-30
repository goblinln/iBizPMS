import { ProjectTaskEstimateUIServiceBase } from './project-task-estimate-ui-service-base';

/**
 * 项目工时统计UI服务对象
 *
 * @export
 * @class ProjectTaskEstimateUIService
 */
export default class ProjectTaskEstimateUIService extends ProjectTaskEstimateUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProjectTaskEstimateUIService
     */
    private static basicUIServiceInstance: ProjectTaskEstimateUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectTaskEstimateUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectTaskEstimateUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTaskEstimateUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectTaskEstimateUIService
     */
    public static getInstance(context: any): ProjectTaskEstimateUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectTaskEstimateUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectTaskEstimateUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProjectTaskEstimateUIService.UIServiceMap.set(context.srfdynainstid, new ProjectTaskEstimateUIService({context:context}));
            }
            return ProjectTaskEstimateUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}