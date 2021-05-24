import { IBZProProductActionUIServiceBase } from './ibzpro-product-action-ui-service-base';

/**
 * 产品日志UI服务对象
 *
 * @export
 * @class IBZProProductActionUIService
 */
export default class IBZProProductActionUIService extends IBZProProductActionUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZProProductActionUIService
     */
    private static basicUIServiceInstance: IBZProProductActionUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZProProductActionUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZProProductActionUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProProductActionUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZProProductActionUIService
     */
    public static getInstance(context: any): IBZProProductActionUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZProProductActionUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZProProductActionUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZProProductActionUIService.UIServiceMap.set(context.srfdynainstid, new IBZProProductActionUIService({context:context}));
            }
            return IBZProProductActionUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}