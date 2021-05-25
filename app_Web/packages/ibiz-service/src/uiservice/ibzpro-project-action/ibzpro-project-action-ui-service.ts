import { IBZProProjectActionUIServiceBase } from './ibzpro-project-action-ui-service-base';

/**
 * 项目日志UI服务对象
 *
 * @export
 * @class IBZProProjectActionUIService
 */
export default class IBZProProjectActionUIService extends IBZProProjectActionUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZProProjectActionUIService
     */
    private static basicUIServiceInstance: IBZProProjectActionUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZProProjectActionUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZProProjectActionUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProProjectActionUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZProProjectActionUIService
     */
    public static getInstance(context: any): IBZProProjectActionUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZProProjectActionUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZProProjectActionUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZProProjectActionUIService.UIServiceMap.set(context.srfdynainstid, new IBZProProjectActionUIService({context:context}));
            }
            return IBZProProjectActionUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}