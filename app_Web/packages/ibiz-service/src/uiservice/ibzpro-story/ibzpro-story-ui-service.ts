import { IBZProStoryUIServiceBase } from './ibzpro-story-ui-service-base';

/**
 * 需求UI服务对象
 *
 * @export
 * @class IBZProStoryUIService
 */
export default class IBZProStoryUIService extends IBZProStoryUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZProStoryUIService
     */
    private static basicUIServiceInstance: IBZProStoryUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZProStoryUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZProStoryUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProStoryUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZProStoryUIService
     */
    public static getInstance(context: any): IBZProStoryUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZProStoryUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZProStoryUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZProStoryUIService.UIServiceMap.set(context.srfdynainstid, new IBZProStoryUIService({context:context}));
            }
            return IBZProStoryUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}