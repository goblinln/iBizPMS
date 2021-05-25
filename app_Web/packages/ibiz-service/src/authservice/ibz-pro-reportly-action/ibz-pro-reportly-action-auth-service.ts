import { IbzProReportlyActionAuthServiceBase } from './ibz-pro-reportly-action-auth-service-base';


/**
 * 汇报日志权限服务对象
 *
 * @export
 * @class IbzProReportlyActionAuthService
 * @extends {IbzProReportlyActionAuthServiceBase}
 */
export default class IbzProReportlyActionAuthService extends IbzProReportlyActionAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzProReportlyActionAuthService}
     * @memberof IbzProReportlyActionAuthService
     */
    private static basicUIServiceInstance: IbzProReportlyActionAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzProReportlyActionAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzProReportlyActionAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzProReportlyActionAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzProReportlyActionAuthService
     */
     public static getInstance(context: any): IbzProReportlyActionAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzProReportlyActionAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzProReportlyActionAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzProReportlyActionAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzProReportlyActionAuthService({context:context}));
            }
            return IbzProReportlyActionAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}