import { IbzCaseUIServiceBase } from './ibz-case-ui-service-base';

/**
 * 测试用例UI服务对象
 *
 * @export
 * @class IbzCaseUIService
 */
export default class IbzCaseUIService extends IbzCaseUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzCaseUIService
     */
    private static basicUIServiceInstance: IbzCaseUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzCaseUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzCaseUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzCaseUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzCaseUIService
     */
    public static getInstance(context: any): IbzCaseUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzCaseUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzCaseUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzCaseUIService.UIServiceMap.set(context.srfdynainstid, new IbzCaseUIService({context:context}));
            }
            return IbzCaseUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}