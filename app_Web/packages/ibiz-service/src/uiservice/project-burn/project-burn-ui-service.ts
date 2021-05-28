import { ProjectBurnUIServiceBase } from './project-burn-ui-service-base';

/**
 * burnUI服务对象
 *
 * @export
 * @class ProjectBurnUIService
 */
export default class ProjectBurnUIService extends ProjectBurnUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProjectBurnUIService
     */
    private static basicUIServiceInstance: ProjectBurnUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectBurnUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectBurnUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectBurnUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectBurnUIService
     */
    public static getInstance(context: any): ProjectBurnUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectBurnUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectBurnUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProjectBurnUIService.UIServiceMap.set(context.srfdynainstid, new ProjectBurnUIService({context:context}));
            }
            return ProjectBurnUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}