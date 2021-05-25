import { IBZProToDoActionUIServiceBase } from './ibzpro-to-do-action-ui-service-base';

/**
 * ToDo日志UI服务对象
 *
 * @export
 * @class IBZProToDoActionUIService
 */
export default class IBZProToDoActionUIService extends IBZProToDoActionUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZProToDoActionUIService
     */
    private static basicUIServiceInstance: IBZProToDoActionUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZProToDoActionUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZProToDoActionUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProToDoActionUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZProToDoActionUIService
     */
    public static getInstance(context: any): IBZProToDoActionUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZProToDoActionUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZProToDoActionUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZProToDoActionUIService.UIServiceMap.set(context.srfdynainstid, new IBZProToDoActionUIService({context:context}));
            }
            return IBZProToDoActionUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}