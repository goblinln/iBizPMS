import { ProjectUIServiceBase } from './project-ui-service-base';

/**
 * 项目UI服务对象
 *
 * @export
 * @class ProjectUIService
 */
export default class ProjectUIService extends ProjectUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProjectUIService
     */
    private static basicUIServiceInstance: ProjectUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectUIService
     */
    public static getInstance(context: any): ProjectUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProjectUIService.UIServiceMap.set(context.srfdynainstid, new ProjectUIService({context:context}));
            }
            return ProjectUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}