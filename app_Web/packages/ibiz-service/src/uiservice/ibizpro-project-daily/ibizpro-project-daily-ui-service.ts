import { IbizproProjectDailyUIServiceBase } from './ibizpro-project-daily-ui-service-base';

/**
 * 项目日报UI服务对象
 *
 * @export
 * @class IbizproProjectDailyUIService
 */
export default class IbizproProjectDailyUIService extends IbizproProjectDailyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbizproProjectDailyUIService
     */
    private static basicUIServiceInstance: IbizproProjectDailyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbizproProjectDailyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbizproProjectDailyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproProjectDailyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbizproProjectDailyUIService
     */
    public static getInstance(context: any): IbizproProjectDailyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbizproProjectDailyUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbizproProjectDailyUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbizproProjectDailyUIService.UIServiceMap.set(context.srfdynainstid, new IbizproProjectDailyUIService({context:context}));
            }
            return IbizproProjectDailyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}