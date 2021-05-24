import { DocContentAuthServiceBase } from './doc-content-auth-service-base';


/**
 * 文档内容权限服务对象
 *
 * @export
 * @class DocContentAuthService
 * @extends {DocContentAuthServiceBase}
 */
export default class DocContentAuthService extends DocContentAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {DocContentAuthService}
     * @memberof DocContentAuthService
     */
    private static basicUIServiceInstance: DocContentAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof DocContentAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  DocContentAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  DocContentAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof DocContentAuthService
     */
     public static getInstance(context: any): DocContentAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new DocContentAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!DocContentAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                DocContentAuthService.AuthServiceMap.set(context.srfdynainstid, new DocContentAuthService({context:context}));
            }
            return DocContentAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}