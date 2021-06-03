import { MyTaskUIServiceBase } from './my-task-ui-service-base';

/**
 * 任务UI服务对象
 *
 * @export
 * @class MyTaskUIService
 */
export default class MyTaskUIService extends MyTaskUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof MyTaskUIService
     */
    private static basicUIServiceInstance: MyTaskUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof MyTaskUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  MyTaskUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  MyTaskUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof MyTaskUIService
     */
    public static getInstance(context: any): MyTaskUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new MyTaskUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!MyTaskUIService.UIServiceMap.get(context.srfdynainstid)) {
                MyTaskUIService.UIServiceMap.set(context.srfdynainstid, new MyTaskUIService({context:context}));
            }
            return MyTaskUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}