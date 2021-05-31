import { IbzLibCaseStepTmpAuthServiceBase } from './ibz-lib-case-step-tmp-auth-service-base';


/**
 * 用例库用例步骤权限服务对象
 *
 * @export
 * @class IbzLibCaseStepTmpAuthService
 * @extends {IbzLibCaseStepTmpAuthServiceBase}
 */
export default class IbzLibCaseStepTmpAuthService extends IbzLibCaseStepTmpAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzLibCaseStepTmpAuthService}
     * @memberof IbzLibCaseStepTmpAuthService
     */
    private static basicUIServiceInstance: IbzLibCaseStepTmpAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzLibCaseStepTmpAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzLibCaseStepTmpAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzLibCaseStepTmpAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzLibCaseStepTmpAuthService
     */
     public static getInstance(context: any): IbzLibCaseStepTmpAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzLibCaseStepTmpAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzLibCaseStepTmpAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzLibCaseStepTmpAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzLibCaseStepTmpAuthService({context:context}));
            }
            return IbzLibCaseStepTmpAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}