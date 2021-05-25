import { IbzProReportlyActionUIServiceBase } from './ibz-pro-reportly-action-ui-service-base';

/**
 * 汇报日志UI服务对象
 *
 * @export
 * @class IbzProReportlyActionUIService
 */
export default class IbzProReportlyActionUIService extends IbzProReportlyActionUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzProReportlyActionUIService
     */
    private static basicUIServiceInstance: IbzProReportlyActionUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzProReportlyActionUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzProReportlyActionUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzProReportlyActionUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzProReportlyActionUIService
     */
    public static getInstance(context: any): IbzProReportlyActionUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzProReportlyActionUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzProReportlyActionUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzProReportlyActionUIService.UIServiceMap.set(context.srfdynainstid, new IbzProReportlyActionUIService({context:context}));
            }
            return IbzProReportlyActionUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}