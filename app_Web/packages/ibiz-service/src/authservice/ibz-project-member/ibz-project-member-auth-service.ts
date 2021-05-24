import { IbzProjectMemberAuthServiceBase } from './ibz-project-member-auth-service-base';


/**
 * 项目相关成员权限服务对象
 *
 * @export
 * @class IbzProjectMemberAuthService
 * @extends {IbzProjectMemberAuthServiceBase}
 */
export default class IbzProjectMemberAuthService extends IbzProjectMemberAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzProjectMemberAuthService}
     * @memberof IbzProjectMemberAuthService
     */
    private static basicUIServiceInstance: IbzProjectMemberAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzProjectMemberAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzProjectMemberAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzProjectMemberAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzProjectMemberAuthService
     */
     public static getInstance(context: any): IbzProjectMemberAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzProjectMemberAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzProjectMemberAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzProjectMemberAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzProjectMemberAuthService({context:context}));
            }
            return IbzProjectMemberAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}