import { DocUIServiceBase } from './doc-ui-service-base';

/**
 * 文档UI服务对象
 *
 * @export
 * @class DocUIService
 */
export default class DocUIService extends DocUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof DocUIService
     */
    private static basicUIServiceInstance: DocUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof DocUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  DocUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  DocUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof DocUIService
     */
    public static getInstance(context: any): DocUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new DocUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!DocUIService.UIServiceMap.get(context.srfdynainstid)) {
                DocUIService.UIServiceMap.set(context.srfdynainstid, new DocUIService({context:context}));
            }
            return DocUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}