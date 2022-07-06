import { StorySpecUIServiceBase } from './story-spec-ui-service-base';

/**
 * 需求描述UI服务对象
 *
 * @export
 * @class StorySpecUIService
 */
export default class StorySpecUIService extends StorySpecUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof StorySpecUIService
     */
    private static basicUIServiceInstance: StorySpecUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof StorySpecUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  StorySpecUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  StorySpecUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof StorySpecUIService
     */
    public static getInstance(context: any): StorySpecUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new StorySpecUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!StorySpecUIService.UIServiceMap.get(context.srfdynainstid)) {
                StorySpecUIService.UIServiceMap.set(context.srfdynainstid, new StorySpecUIService({context:context}));
            }
            return StorySpecUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}