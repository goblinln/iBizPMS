import { IBZStoryActionUIServiceBase } from './ibzstory-action-ui-service-base';

/**
 * 需求日志UI服务对象
 *
 * @export
 * @class IBZStoryActionUIService
 */
export default class IBZStoryActionUIService extends IBZStoryActionUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZStoryActionUIService
     */
    private static basicUIServiceInstance: IBZStoryActionUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZStoryActionUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZStoryActionUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZStoryActionUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZStoryActionUIService
     */
    public static getInstance(context: any): IBZStoryActionUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZStoryActionUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZStoryActionUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZStoryActionUIService.UIServiceMap.set(context.srfdynainstid, new IBZStoryActionUIService({context:context}));
            }
            return IBZStoryActionUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}