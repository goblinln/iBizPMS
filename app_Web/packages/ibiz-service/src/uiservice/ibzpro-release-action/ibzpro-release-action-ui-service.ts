import { IBZProReleaseActionUIServiceBase } from './ibzpro-release-action-ui-service-base';

/**
 * 发布日志UI服务对象
 *
 * @export
 * @class IBZProReleaseActionUIService
 */
export default class IBZProReleaseActionUIService extends IBZProReleaseActionUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZProReleaseActionUIService
     */
    private static basicUIServiceInstance: IBZProReleaseActionUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZProReleaseActionUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZProReleaseActionUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProReleaseActionUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZProReleaseActionUIService
     */
    public static getInstance(context: any): IBZProReleaseActionUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZProReleaseActionUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZProReleaseActionUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZProReleaseActionUIService.UIServiceMap.set(context.srfdynainstid, new IBZProReleaseActionUIService({context:context}));
            }
            return IBZProReleaseActionUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}