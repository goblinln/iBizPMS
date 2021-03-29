import { IBzDocUIServiceBase } from './ibz-doc-ui-service-base';

/**
 * 文档UI服务对象
 *
 * @export
 * @class IBzDocUIService
 */
export default class IBzDocUIService extends IBzDocUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBzDocUIService
     */
    private static basicUIServiceInstance: IBzDocUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBzDocUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBzDocUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBzDocUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBzDocUIService
     */
    public static getInstance(context: any): IBzDocUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBzDocUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBzDocUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBzDocUIService.UIServiceMap.set(context.srfdynainstid, new IBzDocUIService({context:context}));
            }
            return IBzDocUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}