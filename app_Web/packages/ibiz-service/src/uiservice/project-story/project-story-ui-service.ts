import { ProjectStoryUIServiceBase } from './project-story-ui-service-base';

/**
 * 需求UI服务对象
 *
 * @export
 * @class ProjectStoryUIService
 */
export default class ProjectStoryUIService extends ProjectStoryUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProjectStoryUIService
     */
    private static basicUIServiceInstance: ProjectStoryUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectStoryUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectStoryUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectStoryUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectStoryUIService
     */
    public static getInstance(context: any): ProjectStoryUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectStoryUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectStoryUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProjectStoryUIService.UIServiceMap.set(context.srfdynainstid, new ProjectStoryUIService({context:context}));
            }
            return ProjectStoryUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}