import { DocLibModuleUIServiceBase } from './doc-lib-module-ui-service-base';

/**
 * 文档库分类UI服务对象
 *
 * @export
 * @class DocLibModuleUIService
 */
export default class DocLibModuleUIService extends DocLibModuleUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof DocLibModuleUIService
     */
    private static basicUIServiceInstance: DocLibModuleUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof DocLibModuleUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  DocLibModuleUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  DocLibModuleUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof DocLibModuleUIService
     */
    public static getInstance(context: any): DocLibModuleUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new DocLibModuleUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!DocLibModuleUIService.UIServiceMap.get(context.srfdynainstid)) {
                DocLibModuleUIService.UIServiceMap.set(context.srfdynainstid, new DocLibModuleUIService({context:context}));
            }
            return DocLibModuleUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}