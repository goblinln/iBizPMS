import { ProjectBuildUIServiceBase } from './project-build-ui-service-base';

/**
 * 版本UI服务对象
 *
 * @export
 * @class ProjectBuildUIService
 */
export default class ProjectBuildUIService extends ProjectBuildUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProjectBuildUIService
     */
    private static basicUIServiceInstance: ProjectBuildUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectBuildUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectBuildUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectBuildUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectBuildUIService
     */
    public static getInstance(context: any): ProjectBuildUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectBuildUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectBuildUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProjectBuildUIService.UIServiceMap.set(context.srfdynainstid, new ProjectBuildUIService({context:context}));
            }
            return ProjectBuildUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}