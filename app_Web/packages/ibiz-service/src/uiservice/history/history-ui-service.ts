import { HistoryUIServiceBase } from './history-ui-service-base';

/**
 * 操作历史UI服务对象
 *
 * @export
 * @class HistoryUIService
 */
export default class HistoryUIService extends HistoryUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof HistoryUIService
     */
    private static basicUIServiceInstance: HistoryUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof HistoryUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  HistoryUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  HistoryUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof HistoryUIService
     */
    public static getInstance(context: any): HistoryUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new HistoryUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!HistoryUIService.UIServiceMap.get(context.srfdynainstid)) {
                HistoryUIService.UIServiceMap.set(context.srfdynainstid, new HistoryUIService({context:context}));
            }
            return HistoryUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}