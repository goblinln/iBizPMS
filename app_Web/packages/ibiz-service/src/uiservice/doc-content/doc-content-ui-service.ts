import { DocContentUIServiceBase } from './doc-content-ui-service-base';

/**
 * 文档内容UI服务对象
 *
 * @export
 * @class DocContentUIService
 */
export default class DocContentUIService extends DocContentUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof DocContentUIService
     */
    private static basicUIServiceInstance: DocContentUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof DocContentUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  DocContentUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  DocContentUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof DocContentUIService
     */
    public static getInstance(context: any): DocContentUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new DocContentUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!DocContentUIService.UIServiceMap.get(context.srfdynainstid)) {
                DocContentUIService.UIServiceMap.set(context.srfdynainstid, new DocContentUIService({context:context}));
            }
            return DocContentUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}