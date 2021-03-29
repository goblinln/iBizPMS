import { IBZProProductUIServiceBase } from './ibzpro-product-ui-service-base';

/**
 * 平台产品UI服务对象
 *
 * @export
 * @class IBZProProductUIService
 */
export default class IBZProProductUIService extends IBZProProductUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZProProductUIService
     */
    private static basicUIServiceInstance: IBZProProductUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZProProductUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZProProductUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProProductUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZProProductUIService
     */
    public static getInstance(context: any): IBZProProductUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZProProductUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZProProductUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZProProductUIService.UIServiceMap.set(context.srfdynainstid, new IBZProProductUIService({context:context}));
            }
            return IBZProProductUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}