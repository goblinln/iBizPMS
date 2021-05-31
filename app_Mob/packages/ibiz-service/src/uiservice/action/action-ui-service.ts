import { ActionUIServiceBase } from './action-ui-service-base';

/**
 * 系统日志UI服务对象
 *
 * @export
 * @class ActionUIService
 */
export default class ActionUIService extends ActionUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ActionUIService
     */
    private static basicUIServiceInstance: ActionUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ActionUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ActionUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ActionUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ActionUIService
     */
    public static getInstance(context: any): ActionUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ActionUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ActionUIService.UIServiceMap.get(context.srfdynainstid)) {
                ActionUIService.UIServiceMap.set(context.srfdynainstid, new ActionUIService({context:context}));
            }
            return ActionUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}