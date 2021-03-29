import { SubTaskUIServiceBase } from './sub-task-ui-service-base';

/**
 * 任务UI服务对象
 *
 * @export
 * @class SubTaskUIService
 */
export default class SubTaskUIService extends SubTaskUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof SubTaskUIService
     */
    private static basicUIServiceInstance: SubTaskUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof SubTaskUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SubTaskUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  SubTaskUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SubTaskUIService
     */
    public static getInstance(context: any): SubTaskUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SubTaskUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SubTaskUIService.UIServiceMap.get(context.srfdynainstid)) {
                SubTaskUIService.UIServiceMap.set(context.srfdynainstid, new SubTaskUIService({context:context}));
            }
            return SubTaskUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}