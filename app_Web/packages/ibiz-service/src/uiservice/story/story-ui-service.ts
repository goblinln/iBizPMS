import { StoryUIServiceBase } from './story-ui-service-base';

/**
 * 需求UI服务对象
 *
 * @export
 * @class StoryUIService
 */
export default class StoryUIService extends StoryUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof StoryUIService
     */
    private static basicUIServiceInstance: StoryUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof StoryUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  StoryUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  StoryUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof StoryUIService
     */
    public static getInstance(context: any): StoryUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new StoryUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!StoryUIService.UIServiceMap.get(context.srfdynainstid)) {
                StoryUIService.UIServiceMap.set(context.srfdynainstid, new StoryUIService({context:context}));
            }
            return StoryUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}