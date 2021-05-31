import { DocLibUIServiceBase } from './doc-lib-ui-service-base';

/**
 * 文档库UI服务对象
 *
 * @export
 * @class DocLibUIService
 */
export default class DocLibUIService extends DocLibUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof DocLibUIService
     */
    private static basicUIServiceInstance: DocLibUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof DocLibUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  DocLibUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  DocLibUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof DocLibUIService
     */
    public static getInstance(context: any): DocLibUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new DocLibUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!DocLibUIService.UIServiceMap.get(context.srfdynainstid)) {
                DocLibUIService.UIServiceMap.set(context.srfdynainstid, new DocLibUIService({context:context}));
            }
            return DocLibUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}