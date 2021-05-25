import { IBZTaskActionUIServiceBase } from './ibztask-action-ui-service-base';

/**
 * 任务日志UI服务对象
 *
 * @export
 * @class IBZTaskActionUIService
 */
export default class IBZTaskActionUIService extends IBZTaskActionUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZTaskActionUIService
     */
    private static basicUIServiceInstance: IBZTaskActionUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZTaskActionUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZTaskActionUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZTaskActionUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZTaskActionUIService
     */
    public static getInstance(context: any): IBZTaskActionUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZTaskActionUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZTaskActionUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZTaskActionUIService.UIServiceMap.set(context.srfdynainstid, new IBZTaskActionUIService({context:context}));
            }
            return IBZTaskActionUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}