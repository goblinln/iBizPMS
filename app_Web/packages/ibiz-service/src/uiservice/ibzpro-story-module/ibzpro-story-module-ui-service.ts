import { IBZProStoryModuleUIServiceBase } from './ibzpro-story-module-ui-service-base';

/**
 * 需求模块UI服务对象
 *
 * @export
 * @class IBZProStoryModuleUIService
 */
export default class IBZProStoryModuleUIService extends IBZProStoryModuleUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZProStoryModuleUIService
     */
    private static basicUIServiceInstance: IBZProStoryModuleUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZProStoryModuleUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZProStoryModuleUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProStoryModuleUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZProStoryModuleUIService
     */
    public static getInstance(context: any): IBZProStoryModuleUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZProStoryModuleUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZProStoryModuleUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZProStoryModuleUIService.UIServiceMap.set(context.srfdynainstid, new IBZProStoryModuleUIService({context:context}));
            }
            return IBZProStoryModuleUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}