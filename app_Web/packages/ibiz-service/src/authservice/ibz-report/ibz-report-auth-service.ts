import { IbzReportAuthServiceBase } from './ibz-report-auth-service-base';


/**
 * 汇报汇总权限服务对象
 *
 * @export
 * @class IbzReportAuthService
 * @extends {IbzReportAuthServiceBase}
 */
export default class IbzReportAuthService extends IbzReportAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzReportAuthService}
     * @memberof IbzReportAuthService
     */
    private static basicUIServiceInstance: IbzReportAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzReportAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzReportAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzReportAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzReportAuthService
     */
     public static getInstance(context: any): IbzReportAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzReportAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzReportAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzReportAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzReportAuthService({context:context}));
            }
            return IbzReportAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}