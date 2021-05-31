import { IbizproIndexUIServiceBase } from './ibizpro-index-ui-service-base';

/**
 * 索引检索UI服务对象
 *
 * @export
 * @class IbizproIndexUIService
 */
export default class IbizproIndexUIService extends IbizproIndexUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbizproIndexUIService
     */
    private static basicUIServiceInstance: IbizproIndexUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbizproIndexUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbizproIndexUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproIndexUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbizproIndexUIService
     */
    public static getInstance(context: any): IbizproIndexUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbizproIndexUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbizproIndexUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbizproIndexUIService.UIServiceMap.set(context.srfdynainstid, new IbizproIndexUIService({context:context}));
            }
            return IbizproIndexUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}