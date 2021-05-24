import { IBIZProTagUIServiceBase } from './ibizpro-tag-ui-service-base';

/**
 * 标签UI服务对象
 *
 * @export
 * @class IBIZProTagUIService
 */
export default class IBIZProTagUIService extends IBIZProTagUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBIZProTagUIService
     */
    private static basicUIServiceInstance: IBIZProTagUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBIZProTagUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBIZProTagUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBIZProTagUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBIZProTagUIService
     */
    public static getInstance(context: any): IBIZProTagUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBIZProTagUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBIZProTagUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBIZProTagUIService.UIServiceMap.set(context.srfdynainstid, new IBIZProTagUIService({context:context}));
            }
            return IBIZProTagUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}