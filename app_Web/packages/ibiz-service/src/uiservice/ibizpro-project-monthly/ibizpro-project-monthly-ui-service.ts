import { IbizproProjectMonthlyUIServiceBase } from './ibizpro-project-monthly-ui-service-base';

/**
 * 项目月报UI服务对象
 *
 * @export
 * @class IbizproProjectMonthlyUIService
 */
export default class IbizproProjectMonthlyUIService extends IbizproProjectMonthlyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbizproProjectMonthlyUIService
     */
    private static basicUIServiceInstance: IbizproProjectMonthlyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbizproProjectMonthlyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbizproProjectMonthlyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproProjectMonthlyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbizproProjectMonthlyUIService
     */
    public static getInstance(context: any): IbizproProjectMonthlyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbizproProjectMonthlyUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbizproProjectMonthlyUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbizproProjectMonthlyUIService.UIServiceMap.set(context.srfdynainstid, new IbizproProjectMonthlyUIService({context:context}));
            }
            return IbizproProjectMonthlyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}