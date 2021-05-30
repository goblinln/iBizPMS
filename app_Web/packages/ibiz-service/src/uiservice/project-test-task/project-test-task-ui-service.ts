import { ProjectTestTaskUIServiceBase } from './project-test-task-ui-service-base';

/**
 * 测试版本UI服务对象
 *
 * @export
 * @class ProjectTestTaskUIService
 */
export default class ProjectTestTaskUIService extends ProjectTestTaskUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProjectTestTaskUIService
     */
    private static basicUIServiceInstance: ProjectTestTaskUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectTestTaskUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectTestTaskUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTestTaskUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectTestTaskUIService
     */
    public static getInstance(context: any): ProjectTestTaskUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectTestTaskUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectTestTaskUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProjectTestTaskUIService.UIServiceMap.set(context.srfdynainstid, new ProjectTestTaskUIService({context:context}));
            }
            return ProjectTestTaskUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}