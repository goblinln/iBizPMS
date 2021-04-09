import { IbzproProjectUserTaskUIServiceBase } from './ibzpro-project-user-task-ui-service-base';

/**
 * 项目汇报用户任务UI服务对象
 *
 * @export
 * @class IbzproProjectUserTaskUIService
 */
export default class IbzproProjectUserTaskUIService extends IbzproProjectUserTaskUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzproProjectUserTaskUIService
     */
    private static basicUIServiceInstance: IbzproProjectUserTaskUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzproProjectUserTaskUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzproProjectUserTaskUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzproProjectUserTaskUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzproProjectUserTaskUIService
     */
    public static getInstance(context: any): IbzproProjectUserTaskUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzproProjectUserTaskUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzproProjectUserTaskUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzproProjectUserTaskUIService.UIServiceMap.set(context.srfdynainstid, new IbzproProjectUserTaskUIService({context:context}));
            }
            return IbzproProjectUserTaskUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}