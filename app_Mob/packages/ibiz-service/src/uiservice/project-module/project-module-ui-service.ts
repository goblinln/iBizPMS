import { ProjectModuleUIServiceBase } from './project-module-ui-service-base';

/**
 * 任务模块UI服务对象
 *
 * @export
 * @class ProjectModuleUIService
 */
export default class ProjectModuleUIService extends ProjectModuleUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProjectModuleUIService
     */
    private static basicUIServiceInstance: ProjectModuleUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectModuleUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectModuleUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectModuleUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectModuleUIService
     */
    public static getInstance(context: any): ProjectModuleUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectModuleUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectModuleUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProjectModuleUIService.UIServiceMap.set(context.srfdynainstid, new ProjectModuleUIService({context:context}));
            }
            return ProjectModuleUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}