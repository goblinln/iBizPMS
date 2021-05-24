import { DocAuthServiceBase } from './doc-auth-service-base';


/**
 * 文档权限服务对象
 *
 * @export
 * @class DocAuthService
 * @extends {DocAuthServiceBase}
 */
export default class DocAuthService extends DocAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {DocAuthService}
     * @memberof DocAuthService
     */
    private static basicUIServiceInstance: DocAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof DocAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  DocAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  DocAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof DocAuthService
     */
     public static getInstance(context: any): DocAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new DocAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!DocAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                DocAuthService.AuthServiceMap.set(context.srfdynainstid, new DocAuthService({context:context}));
            }
            return DocAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}