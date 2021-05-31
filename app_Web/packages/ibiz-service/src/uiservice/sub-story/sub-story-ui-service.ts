import { SubStoryUIServiceBase } from './sub-story-ui-service-base';

/**
 * 需求UI服务对象
 *
 * @export
 * @class SubStoryUIService
 */
export default class SubStoryUIService extends SubStoryUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof SubStoryUIService
     */
    private static basicUIServiceInstance: SubStoryUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof SubStoryUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SubStoryUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  SubStoryUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SubStoryUIService
     */
    public static getInstance(context: any): SubStoryUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SubStoryUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SubStoryUIService.UIServiceMap.get(context.srfdynainstid)) {
                SubStoryUIService.UIServiceMap.set(context.srfdynainstid, new SubStoryUIService({context:context}));
            }
            return SubStoryUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}