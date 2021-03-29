import { FileUIServiceBase } from './file-ui-service-base';

/**
 * 附件UI服务对象
 *
 * @export
 * @class FileUIService
 */
export default class FileUIService extends FileUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof FileUIService
     */
    private static basicUIServiceInstance: FileUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof FileUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  FileUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  FileUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof FileUIService
     */
    public static getInstance(context: any): FileUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new FileUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!FileUIService.UIServiceMap.get(context.srfdynainstid)) {
                FileUIService.UIServiceMap.set(context.srfdynainstid, new FileUIService({context:context}));
            }
            return FileUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}