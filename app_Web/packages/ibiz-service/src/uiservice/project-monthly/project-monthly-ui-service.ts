import { ProjectMonthlyUIServiceBase } from './project-monthly-ui-service-base';

/**
 * 项目月报UI服务对象
 *
 * @export
 * @class ProjectMonthlyUIService
 */
export default class ProjectMonthlyUIService extends ProjectMonthlyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProjectMonthlyUIService
     */
    private static basicUIServiceInstance: ProjectMonthlyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectMonthlyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectMonthlyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectMonthlyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectMonthlyUIService
     */
    public static getInstance(context: any): ProjectMonthlyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectMonthlyUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectMonthlyUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProjectMonthlyUIService.UIServiceMap.set(context.srfdynainstid, new ProjectMonthlyUIService({context:context}));
            }
            return ProjectMonthlyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}