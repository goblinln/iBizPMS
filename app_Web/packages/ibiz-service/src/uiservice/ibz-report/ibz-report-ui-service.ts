import { IbzReportUIServiceBase } from './ibz-report-ui-service-base';

/**
 * 汇报汇总UI服务对象
 *
 * @export
 * @class IbzReportUIService
 */
export default class IbzReportUIService extends IbzReportUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzReportUIService
     */
    private static basicUIServiceInstance: IbzReportUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzReportUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzReportUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzReportUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzReportUIService
     */
    public static getInstance(context: any): IbzReportUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzReportUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzReportUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzReportUIService.UIServiceMap.set(context.srfdynainstid, new IbzReportUIService({context:context}));
            }
            return IbzReportUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}