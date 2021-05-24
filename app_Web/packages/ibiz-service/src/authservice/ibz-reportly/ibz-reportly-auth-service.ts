import { IbzReportlyAuthServiceBase } from './ibz-reportly-auth-service-base';


/**
 * 汇报权限服务对象
 *
 * @export
 * @class IbzReportlyAuthService
 * @extends {IbzReportlyAuthServiceBase}
 */
export default class IbzReportlyAuthService extends IbzReportlyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzReportlyAuthService}
     * @memberof IbzReportlyAuthService
     */
    private static basicUIServiceInstance: IbzReportlyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzReportlyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzReportlyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzReportlyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzReportlyAuthService
     */
     public static getInstance(context: any): IbzReportlyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzReportlyAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzReportlyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzReportlyAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzReportlyAuthService({context:context}));
            }
            return IbzReportlyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}