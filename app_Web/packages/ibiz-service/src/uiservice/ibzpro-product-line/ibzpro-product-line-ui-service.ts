import { IBZProProductLineUIServiceBase } from './ibzpro-product-line-ui-service-base';

/**
 * 产品线UI服务对象
 *
 * @export
 * @class IBZProProductLineUIService
 */
export default class IBZProProductLineUIService extends IBZProProductLineUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZProProductLineUIService
     */
    private static basicUIServiceInstance: IBZProProductLineUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZProProductLineUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZProProductLineUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProProductLineUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZProProductLineUIService
     */
    public static getInstance(context: any): IBZProProductLineUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZProProductLineUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZProProductLineUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZProProductLineUIService.UIServiceMap.set(context.srfdynainstid, new IBZProProductLineUIService({context:context}));
            }
            return IBZProProductLineUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}