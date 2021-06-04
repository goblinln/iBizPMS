import { ProjectWeeklyUIServiceBase } from './project-weekly-ui-service-base';

/**
 * 项目周报UI服务对象
 *
 * @export
 * @class ProjectWeeklyUIService
 */
export default class ProjectWeeklyUIService extends ProjectWeeklyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProjectWeeklyUIService
     */
    private static basicUIServiceInstance: ProjectWeeklyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectWeeklyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectWeeklyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectWeeklyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectWeeklyUIService
     */
    public static getInstance(context: any): ProjectWeeklyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectWeeklyUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectWeeklyUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProjectWeeklyUIService.UIServiceMap.set(context.srfdynainstid, new ProjectWeeklyUIService({context:context}));
            }
            return ProjectWeeklyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}