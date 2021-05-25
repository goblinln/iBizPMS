import { IbzProTestTaskActionUIServiceBase } from './ibz-pro-test-task-action-ui-service-base';

/**
 * 测试单日志UI服务对象
 *
 * @export
 * @class IbzProTestTaskActionUIService
 */
export default class IbzProTestTaskActionUIService extends IbzProTestTaskActionUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzProTestTaskActionUIService
     */
    private static basicUIServiceInstance: IbzProTestTaskActionUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzProTestTaskActionUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzProTestTaskActionUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzProTestTaskActionUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzProTestTaskActionUIService
     */
    public static getInstance(context: any): IbzProTestTaskActionUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzProTestTaskActionUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzProTestTaskActionUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzProTestTaskActionUIService.UIServiceMap.set(context.srfdynainstid, new IbzProTestTaskActionUIService({context:context}));
            }
            return IbzProTestTaskActionUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}