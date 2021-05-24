import { DocLibAuthServiceBase } from './doc-lib-auth-service-base';


/**
 * 文档库权限服务对象
 *
 * @export
 * @class DocLibAuthService
 * @extends {DocLibAuthServiceBase}
 */
export default class DocLibAuthService extends DocLibAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {DocLibAuthService}
     * @memberof DocLibAuthService
     */
    private static basicUIServiceInstance: DocLibAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof DocLibAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  DocLibAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  DocLibAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof DocLibAuthService
     */
     public static getInstance(context: any): DocLibAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new DocLibAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!DocLibAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                DocLibAuthService.AuthServiceMap.set(context.srfdynainstid, new DocLibAuthService({context:context}));
            }
            return DocLibAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}