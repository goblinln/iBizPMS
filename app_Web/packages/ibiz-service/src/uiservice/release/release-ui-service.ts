import { ReleaseUIServiceBase } from './release-ui-service-base';

/**
 * 发布UI服务对象
 *
 * @export
 * @class ReleaseUIService
 */
export default class ReleaseUIService extends ReleaseUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ReleaseUIService
     */
    private static basicUIServiceInstance: ReleaseUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ReleaseUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ReleaseUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ReleaseUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ReleaseUIService
     */
    public static getInstance(context: any): ReleaseUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ReleaseUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ReleaseUIService.UIServiceMap.get(context.srfdynainstid)) {
                ReleaseUIService.UIServiceMap.set(context.srfdynainstid, new ReleaseUIService({context:context}));
            }
            return ReleaseUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}