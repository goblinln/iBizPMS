import { StoryStageUIServiceBase } from './story-stage-ui-service-base';

/**
 * 需求阶段UI服务对象
 *
 * @export
 * @class StoryStageUIService
 */
export default class StoryStageUIService extends StoryStageUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof StoryStageUIService
     */
    private static basicUIServiceInstance: StoryStageUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof StoryStageUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  StoryStageUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  StoryStageUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof StoryStageUIService
     */
    public static getInstance(context: any): StoryStageUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new StoryStageUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!StoryStageUIService.UIServiceMap.get(context.srfdynainstid)) {
                StoryStageUIService.UIServiceMap.set(context.srfdynainstid, new StoryStageUIService({context:context}));
            }
            return StoryStageUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}