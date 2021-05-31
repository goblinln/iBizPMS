import { IbzproProductUserTaskUIServiceBase } from './ibzpro-product-user-task-ui-service-base';

/**
 * 产品汇报用户任务UI服务对象
 *
 * @export
 * @class IbzproProductUserTaskUIService
 */
export default class IbzproProductUserTaskUIService extends IbzproProductUserTaskUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzproProductUserTaskUIService
     */
    private static basicUIServiceInstance: IbzproProductUserTaskUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzproProductUserTaskUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzproProductUserTaskUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzproProductUserTaskUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzproProductUserTaskUIService
     */
    public static getInstance(context: any): IbzproProductUserTaskUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzproProductUserTaskUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzproProductUserTaskUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzproProductUserTaskUIService.UIServiceMap.set(context.srfdynainstid, new IbzproProductUserTaskUIService({context:context}));
            }
            return IbzproProductUserTaskUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}