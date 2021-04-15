import { IBZWEEKLYUIServiceBase } from './ibzweekly-ui-service-base';

/**
 * 周报UI服务对象
 *
 * @export
 * @class IBZWEEKLYUIService
 */
export default class IBZWEEKLYUIService extends IBZWEEKLYUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZWEEKLYUIService
     */
    private static basicUIServiceInstance: IBZWEEKLYUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZWEEKLYUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZWEEKLYUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZWEEKLYUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZWEEKLYUIService
     */
    public static getInstance(context: any): IBZWEEKLYUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZWEEKLYUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZWEEKLYUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZWEEKLYUIService.UIServiceMap.set(context.srfdynainstid, new IBZWEEKLYUIService({context:context}));
            }
            return IBZWEEKLYUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}