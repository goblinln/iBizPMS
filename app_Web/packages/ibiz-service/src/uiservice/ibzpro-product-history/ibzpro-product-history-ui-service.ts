import { IBZProProductHistoryUIServiceBase } from './ibzpro-product-history-ui-service-base';

/**
 * 产品操作历史UI服务对象
 *
 * @export
 * @class IBZProProductHistoryUIService
 */
export default class IBZProProductHistoryUIService extends IBZProProductHistoryUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZProProductHistoryUIService
     */
    private static basicUIServiceInstance: IBZProProductHistoryUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZProProductHistoryUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZProProductHistoryUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProProductHistoryUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZProProductHistoryUIService
     */
    public static getInstance(context: any): IBZProProductHistoryUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZProProductHistoryUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZProProductHistoryUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZProProductHistoryUIService.UIServiceMap.set(context.srfdynainstid, new IBZProProductHistoryUIService({context:context}));
            }
            return IBZProProductHistoryUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}