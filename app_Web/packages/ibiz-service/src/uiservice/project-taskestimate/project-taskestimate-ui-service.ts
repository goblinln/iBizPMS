import { ProjectTaskestimateUIServiceBase } from './project-taskestimate-ui-service-base';

/**
 * 项目工时统计UI服务对象
 *
 * @export
 * @class ProjectTaskestimateUIService
 */
export default class ProjectTaskestimateUIService extends ProjectTaskestimateUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProjectTaskestimateUIService
     */
    private static basicUIServiceInstance: ProjectTaskestimateUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectTaskestimateUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectTaskestimateUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTaskestimateUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectTaskestimateUIService
     */
    public static getInstance(context: any): ProjectTaskestimateUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectTaskestimateUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectTaskestimateUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProjectTaskestimateUIService.UIServiceMap.set(context.srfdynainstid, new ProjectTaskestimateUIService({context:context}));
            }
            return ProjectTaskestimateUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}