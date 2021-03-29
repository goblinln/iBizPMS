import { ProjectProductUIServiceBase } from './project-product-ui-service-base';

/**
 * 项目产品UI服务对象
 *
 * @export
 * @class ProjectProductUIService
 */
export default class ProjectProductUIService extends ProjectProductUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProjectProductUIService
     */
    private static basicUIServiceInstance: ProjectProductUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectProductUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectProductUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectProductUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectProductUIService
     */
    public static getInstance(context: any): ProjectProductUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectProductUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectProductUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProjectProductUIService.UIServiceMap.set(context.srfdynainstid, new ProjectProductUIService({context:context}));
            }
            return ProjectProductUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}