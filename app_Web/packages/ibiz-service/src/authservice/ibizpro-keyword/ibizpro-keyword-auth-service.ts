import { IBIZProKeywordAuthServiceBase } from './ibizpro-keyword-auth-service-base';


/**
 * 关键字权限服务对象
 *
 * @export
 * @class IBIZProKeywordAuthService
 * @extends {IBIZProKeywordAuthServiceBase}
 */
export default class IBIZProKeywordAuthService extends IBIZProKeywordAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBIZProKeywordAuthService}
     * @memberof IBIZProKeywordAuthService
     */
    private static basicUIServiceInstance: IBIZProKeywordAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBIZProKeywordAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBIZProKeywordAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBIZProKeywordAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBIZProKeywordAuthService
     */
     public static getInstance(context: any): IBIZProKeywordAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBIZProKeywordAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBIZProKeywordAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBIZProKeywordAuthService.AuthServiceMap.set(context.srfdynainstid, new IBIZProKeywordAuthService({context:context}));
            }
            return IBIZProKeywordAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}