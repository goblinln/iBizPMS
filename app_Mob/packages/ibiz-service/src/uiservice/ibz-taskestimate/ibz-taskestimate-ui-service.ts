import { IbzTaskestimateUIServiceBase } from './ibz-taskestimate-ui-service-base';

/**
 * 任务预计UI服务对象
 *
 * @export
 * @class IbzTaskestimateUIService
 */
export default class IbzTaskestimateUIService extends IbzTaskestimateUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzTaskestimateUIService
     */
    private static basicUIServiceInstance: IbzTaskestimateUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzTaskestimateUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzTaskestimateUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzTaskestimateUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzTaskestimateUIService
     */
    public static getInstance(context: any): IbzTaskestimateUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzTaskestimateUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzTaskestimateUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzTaskestimateUIService.UIServiceMap.set(context.srfdynainstid, new IbzTaskestimateUIService({context:context}));
            }
            return IbzTaskestimateUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}