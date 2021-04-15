import { IbzReportlyUIServiceBase } from './ibz-reportly-ui-service-base';

/**
 * 汇报UI服务对象
 *
 * @export
 * @class IbzReportlyUIService
 */
export default class IbzReportlyUIService extends IbzReportlyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzReportlyUIService
     */
    private static basicUIServiceInstance: IbzReportlyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzReportlyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzReportlyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzReportlyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzReportlyUIService
     */
    public static getInstance(context: any): IbzReportlyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzReportlyUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzReportlyUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzReportlyUIService.UIServiceMap.set(context.srfdynainstid, new IbzReportlyUIService({context:context}));
            }
            return IbzReportlyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}