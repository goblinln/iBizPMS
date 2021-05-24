import { FileAuthServiceBase } from './file-auth-service-base';


/**
 * 附件权限服务对象
 *
 * @export
 * @class FileAuthService
 * @extends {FileAuthServiceBase}
 */
export default class FileAuthService extends FileAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {FileAuthService}
     * @memberof FileAuthService
     */
    private static basicUIServiceInstance: FileAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof FileAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  FileAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  FileAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof FileAuthService
     */
     public static getInstance(context: any): FileAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new FileAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!FileAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                FileAuthService.AuthServiceMap.set(context.srfdynainstid, new FileAuthService({context:context}));
            }
            return FileAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}