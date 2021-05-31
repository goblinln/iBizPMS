import { IBZTaskTeamAuthServiceBase } from './ibztask-team-auth-service-base';


/**
 * 任务团队权限服务对象
 *
 * @export
 * @class IBZTaskTeamAuthService
 * @extends {IBZTaskTeamAuthServiceBase}
 */
export default class IBZTaskTeamAuthService extends IBZTaskTeamAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZTaskTeamAuthService}
     * @memberof IBZTaskTeamAuthService
     */
    private static basicUIServiceInstance: IBZTaskTeamAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZTaskTeamAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZTaskTeamAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZTaskTeamAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZTaskTeamAuthService
     */
     public static getInstance(context: any): IBZTaskTeamAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZTaskTeamAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZTaskTeamAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZTaskTeamAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZTaskTeamAuthService({context:context}));
            }
            return IBZTaskTeamAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}