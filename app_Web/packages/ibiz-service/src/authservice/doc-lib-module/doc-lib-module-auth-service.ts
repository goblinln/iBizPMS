import { DocLibModuleAuthServiceBase } from './doc-lib-module-auth-service-base';


/**
 * 文档库分类权限服务对象
 *
 * @export
 * @class DocLibModuleAuthService
 * @extends {DocLibModuleAuthServiceBase}
 */
export default class DocLibModuleAuthService extends DocLibModuleAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {DocLibModuleAuthService}
     * @memberof DocLibModuleAuthService
     */
    private static basicUIServiceInstance: DocLibModuleAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof DocLibModuleAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  DocLibModuleAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  DocLibModuleAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof DocLibModuleAuthService
     */
     public static getInstance(context: any): DocLibModuleAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new DocLibModuleAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!DocLibModuleAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                DocLibModuleAuthService.AuthServiceMap.set(context.srfdynainstid, new DocLibModuleAuthService({context:context}));
            }
            return DocLibModuleAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}