import { ReportlyUIServiceBase } from './reportly-ui-service-base';

/**
 * 汇报UI服务对象
 *
 * @export
 * @class ReportlyUIService
 */
export default class ReportlyUIService extends ReportlyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ReportlyUIService
     */
    private static basicUIServiceInstance: ReportlyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ReportlyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ReportlyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ReportlyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ReportlyUIService
     */
    public static getInstance(context: any): ReportlyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ReportlyUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ReportlyUIService.UIServiceMap.get(context.srfdynainstid)) {
                ReportlyUIService.UIServiceMap.set(context.srfdynainstid, new ReportlyUIService({context:context}));
            }
            return ReportlyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}