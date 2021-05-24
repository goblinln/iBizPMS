import { IBZTaskEstimateUIServiceBase } from './ibztask-estimate-ui-service-base';

/**
 * 任务预计UI服务对象
 *
 * @export
 * @class IBZTaskEstimateUIService
 */
export default class IBZTaskEstimateUIService extends IBZTaskEstimateUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZTaskEstimateUIService
     */
    private static basicUIServiceInstance: IBZTaskEstimateUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZTaskEstimateUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZTaskEstimateUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZTaskEstimateUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZTaskEstimateUIService
     */
    public static getInstance(context: any): IBZTaskEstimateUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZTaskEstimateUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZTaskEstimateUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZTaskEstimateUIService.UIServiceMap.set(context.srfdynainstid, new IBZTaskEstimateUIService({context:context}));
            }
            return IBZTaskEstimateUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}