import { IbizproProjectWeeklyUIServiceBase } from './ibizpro-project-weekly-ui-service-base';

/**
 * 项目周报UI服务对象
 *
 * @export
 * @class IbizproProjectWeeklyUIService
 */
export default class IbizproProjectWeeklyUIService extends IbizproProjectWeeklyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbizproProjectWeeklyUIService
     */
    private static basicUIServiceInstance: IbizproProjectWeeklyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbizproProjectWeeklyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbizproProjectWeeklyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproProjectWeeklyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbizproProjectWeeklyUIService
     */
    public static getInstance(context: any): IbizproProjectWeeklyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbizproProjectWeeklyUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbizproProjectWeeklyUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbizproProjectWeeklyUIService.UIServiceMap.set(context.srfdynainstid, new IbizproProjectWeeklyUIService({context:context}));
            }
            return IbizproProjectWeeklyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}