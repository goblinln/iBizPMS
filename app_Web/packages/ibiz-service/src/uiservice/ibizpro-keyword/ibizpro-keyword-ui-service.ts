import { IBIZProKeywordUIServiceBase } from './ibizpro-keyword-ui-service-base';

/**
 * 关键字UI服务对象
 *
 * @export
 * @class IBIZProKeywordUIService
 */
export default class IBIZProKeywordUIService extends IBIZProKeywordUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBIZProKeywordUIService
     */
    private static basicUIServiceInstance: IBIZProKeywordUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBIZProKeywordUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBIZProKeywordUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBIZProKeywordUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBIZProKeywordUIService
     */
    public static getInstance(context: any): IBIZProKeywordUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBIZProKeywordUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBIZProKeywordUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBIZProKeywordUIService.UIServiceMap.set(context.srfdynainstid, new IBIZProKeywordUIService({context:context}));
            }
            return IBIZProKeywordUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}