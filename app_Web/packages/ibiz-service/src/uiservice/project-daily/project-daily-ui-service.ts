import { ProjectDailyUIServiceBase } from './project-daily-ui-service-base';

/**
 * 项目日报UI服务对象
 *
 * @export
 * @class ProjectDailyUIService
 */
export default class ProjectDailyUIService extends ProjectDailyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProjectDailyUIService
     */
    private static basicUIServiceInstance: ProjectDailyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectDailyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectDailyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectDailyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectDailyUIService
     */
    public static getInstance(context: any): ProjectDailyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectDailyUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectDailyUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProjectDailyUIService.UIServiceMap.set(context.srfdynainstid, new ProjectDailyUIService({context:context}));
            }
            return ProjectDailyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}