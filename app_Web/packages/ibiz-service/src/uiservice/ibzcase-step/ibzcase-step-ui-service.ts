import { IBZCaseStepUIServiceBase } from './ibzcase-step-ui-service-base';

/**
 * 用例步骤UI服务对象
 *
 * @export
 * @class IBZCaseStepUIService
 */
export default class IBZCaseStepUIService extends IBZCaseStepUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZCaseStepUIService
     */
    private static basicUIServiceInstance: IBZCaseStepUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZCaseStepUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZCaseStepUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZCaseStepUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZCaseStepUIService
     */
    public static getInstance(context: any): IBZCaseStepUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZCaseStepUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZCaseStepUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZCaseStepUIService.UIServiceMap.set(context.srfdynainstid, new IBZCaseStepUIService({context:context}));
            }
            return IBZCaseStepUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}